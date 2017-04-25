package by.gstu.chatbot.core.datalayer;


import com.mysql.jdbc.StringUtils;

import java.util.List;

public class DataBaseService {

    private final static String startKey = "{";
    private final static String finishKey = "}";
    private static DataBase dataBase = new DataBase();

    public static String formatData(final String key) {
        final List<String> data = dataBase.sentQuery(key);
        final StringBuilder response = new StringBuilder();
        for (int i = 0; i < data.size() - 1; i++) {
            response.append(data.get(i)).append(", ");
        }
        response.append(data.get(data.size() - 1));
        return response.toString();
    }

    public static String fillResponse(String response) {
        if (!StringUtils.isEmptyOrWhitespaceOnly(response) && isNeedFillFromDB(response)) {
            response = replaceKey(response);
        }
        return response;
    }

    public static String replaceKey(String response) {
        final String key = getKey(response);
        return response.replace(startKey + key + finishKey, formatData(key));
    }

    private static boolean isNeedFillFromDB(final String response) {
//        final Pattern pattern = Pattern.compile(".*\\{.*\\}.*");
//        return pattern.matcher(response).matches();
        return response.contains(startKey) && response.contains(finishKey);
    }

    private static String getKey(final String response) {
        String keyword = response.substring(response.indexOf(startKey) + 1, response.indexOf(finishKey));
        return keyword;
    }
}
