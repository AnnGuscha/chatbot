package by.gstu.chatbot.core;

//import by.gstu.chatbot.alicebot.ChatterBean;
import by.gstu.chatbot.core.datalayer.DataBaseService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static Bot bot;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<String> messageHistory = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        //ChatterBean.main(new String[] {"gui"});
        //startMyBot();
        //startAlice();
    }

    private static void startAlice() throws IOException {
        String userMessage, botMessage;

        while (true) {
            System.out.print("You: ");
            userMessage = br.readLine();
            //alice.ChatterBean.getResponse(userMessage);

            botMessage = DataBaseService.fillResponse("Bot: " + bot.getAnswer(userMessage));
            messageHistory.add(botMessage);
            System.out.println(botMessage);
        }
    }

    private static void startMyBot() throws IOException {
        YandexSpellChecker spellChecker = new YandexSpellChecker();

        bot = new MyBot();

        String userMessage, botMessage;

        if(bot.getType().equals("first")){
            botMessage = "Bot: " + bot.getAnswer("");
            messageHistory.add(botMessage);
            System.out.println(botMessage);
        }

        while (true) {
            System.out.print("You: ");
            userMessage = br.readLine();
            userMessage = spellChecker.check(userMessage);

            botMessage = "Bot: " + bot.getAnswer(userMessage);//DataBaseService.fillResponse("Bot: " + bot.getMessage());
            messageHistory.add(botMessage);
            System.out.println(botMessage);
        }
    }
}
