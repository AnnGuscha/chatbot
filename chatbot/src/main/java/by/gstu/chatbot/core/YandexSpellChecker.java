package by.gstu.chatbot.core;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class YandexSpellChecker {

    final static Logger LOG = Logger.getLogger(YandexSpellChecker.class);

    private Client client;

    public YandexSpellChecker() {
        client = Client.create();
    }

    //Hillo, превет, меня зовут sku maybe i can help yau
    public String check(String text) {
        try {
            ClientResponse response = sendRequest(text);
            JSONArray array = getJSON(response);
            if(array.isEmpty()){
                return text;
            }
            Map incorrectWords = getIncorrectWordsMap(array);
            String correctedText = replaceIncorrectWords(text, incorrectWords);
            return correctedText;
        } catch (SpellCheckerException e) {
            LOG.debug(e.getMessage());
            return text;
        }
    }

    private JSONArray getJSON(final ClientResponse response) throws SpellCheckerException {
        String output = response.getEntity(String.class);
        JSONParser parser = new JSONParser();
        Object obj;
        try {
            obj = parser.parse(output);
        } catch (ParseException e) {
            throw new SpellCheckerException(TypeError.PARSING_ERROR, "PARSING_ERROR");
        }

        JSONArray array = (JSONArray) obj;
        if (array == null) {
            throw new SpellCheckerException(TypeError.PARSING_ERROR, "PARSING_ERROR");
        }
        return array;
    }

    private String replaceIncorrectWords(final String text, final Map<String, List<String>> replacementWords) throws SpellCheckerException {
        String correctText = text;
        for (Map.Entry<String, List<String>> entry : replacementWords.entrySet()) {
            if(!entry.getValue().isEmpty()) {
                correctText = correctText.replace(entry.getKey(), entry.getValue().get(0));
            }
        }
        return correctText;
    }

    private Map getIncorrectWordsMap(final JSONArray array) throws SpellCheckerException {
        Map<String, List<String>> incorrectWords = new LinkedHashMap();
        Iterator<Object> it = array.iterator();
        while (it.hasNext()) {
            JSONObject jo = (JSONObject) it.next();
            String incorrectWord = (String) jo.get("word");
            List<String> correctWords = getCorrectWords((JSONArray) jo.get("s"));
            incorrectWords.put(incorrectWord, correctWords);
        }
        return incorrectWords;
    }

    private List<String> getCorrectWords(final JSONArray array) throws SpellCheckerException {
        List<String> correctWords = new ArrayList<>();
        //@SuppressWarnings("unchecked")
        Iterator<Object> sit = array.iterator();
        while (sit.hasNext()) {
            String v = (String) sit.next();
            correctWords.add(v);
        }
        return correctWords;
    }

    private ClientResponse sendRequest(final String text) throws SpellCheckerException {
        String encodedText;
        try {
            encodedText = URLEncoder.encode(text, "UTF8");
        } catch (UnsupportedEncodingException e) {
            throw new SpellCheckerException(TypeError.ENCODING_ERROR, "ENCODING_ERROR");
        }

        WebResource webResource = client
                .resource("http://speller.yandex.net/services/spellservice.json/checkText"
                        + "?text=" + encodedText);

        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new SpellCheckerException(TypeError.HTTP_ERROR, "HTTP_ERROR");
        }
        return response;
    }
}

class SpellCheckerException extends Exception {
    TypeError result;
    public SpellCheckerException(final TypeError result, final String message) {
        super(result.name());
    }
}

enum TypeError {
    ENCODING_ERROR,
    PARSING_ERROR,
    HTTP_ERROR;
}