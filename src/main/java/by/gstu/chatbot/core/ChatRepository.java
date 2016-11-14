package by.gstu.chatbot.core;

import java.util.List;

public interface ChatRepository {

    List<String> getMessages(int messageIndex);

    void addMessage(String message);

}
