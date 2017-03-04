package by.gstu.chatbot.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static Bot bot;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<String> messageHistory = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        DataParser dp = new DataParser();

        bot = new Bot("0", dp);

        String userMessage, botMessage;

        while (true) {
            botMessage = "Bot: " + bot.getMessage();
            messageHistory.add(botMessage);
            System.out.println(botMessage);

            System.out.print("You: ");
            userMessage = br.readLine();

            String response = bot.send(userMessage);

            if (response.length() != 0) {
                messageHistory.add(response);
            }
        }
    }
}
