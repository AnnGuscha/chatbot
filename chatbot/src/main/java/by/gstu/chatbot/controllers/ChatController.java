package by.gstu.chatbot.controllers;

import by.gstu.chatbot.core.Bot;
import by.gstu.chatbot.core.ChatRepository;
import by.gstu.chatbot.core.MyBot;
import by.gstu.chatbot.core.YandexSpellChecker;
import by.gstu.chatbot.core.datalayer.DataBaseService;
import by.gstu.chatbot.core.parser.DataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.beans.Encoder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatRepository chatRepository;
    private static MyBot bot;
    private final Map<DeferredResult<List<String>>, Integer> chatRequests =
            new ConcurrentHashMap<DeferredResult<List<String>>, Integer>();
    YandexSpellChecker spellChecker = new YandexSpellChecker();

    @Autowired
    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
        DataParser dp = new DataParser();
        bot = new MyBot("0", dp);
        this.chatRepository.addMessage("[bot]" + bot.getMessage());
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public DeferredResult<List<String>> getMessages(@RequestParam int messageIndex) {

        final DeferredResult<List<String>> deferredResult = new DeferredResult<>(null, Collections.emptyList());
        this.chatRequests.put(deferredResult, messageIndex);

        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                chatRequests.remove(deferredResult);
            }
        });

        List<String> messages = this.chatRepository.getMessages(messageIndex);
        if (!messages.isEmpty()) {
            deferredResult.setResult(messages);
        }

        return deferredResult;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void postMessage(@RequestParam String message, HttpServletResponse rsp) {
        this.chatRepository.addMessage(message);
        String userMessage = spellChecker.check(message.substring(message.indexOf(']') + 1));
        String response = DataBaseService.fillResponse(bot.send(userMessage));
        if (response.length() == 0) {
            response = DataBaseService.fillResponse(bot.getMessage());
        }
        this.chatRepository.addMessage("[bot]" + response);

        for (Entry<DeferredResult<List<String>>, Integer> entry : this.chatRequests.entrySet()) {
            List<String> messages = this.chatRepository.getMessages(entry.getValue());
            entry.getKey().setResult(messages);
        }

        try {
            PrintWriter out = rsp.getWriter();
            out.println(URLEncoder.encode(response, "UTF-8"));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @RequestMapping(value = "/post", method = RequestMethod.POST)
//    public void postMessageGET(@RequestParam String message, HttpServletRequest request, HttpServletResponse response) {
//        try {
//            PrintWriter out = response.getWriter();
//            out.println("Hello, world!" + message);
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //return "some string";
////        this.chatRepository.addMessage(message);
////        String userMessage = message.substring(message.indexOf(']') + 1);
////        String response = bot.send(userMessage);
////        if (response.length() != 0) {
////            this.chatRepository.addMessage("[bot]" + response);
////        } else {
////            this.chatRepository.addMessage("[bot]" + bot.getMessage());
////        }
////
////        for (Entry<DeferredResult<List<String>>, Integer> entry : this.chatRequests.entrySet()) {
////            List<String> messages = this.chatRepository.getMessages(entry.getValue());
////            entry.getKey().setResult(messages);
////        }
////        return response;
//    }

    @RequestMapping("/reload")
    public void reload() {
        ((MyBot)bot).setParser(new DataParser());
    }
}
