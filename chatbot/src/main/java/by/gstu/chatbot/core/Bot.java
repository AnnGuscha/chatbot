package by.gstu.chatbot.core;

import by.gstu.chatbot.core.parser.DataParser;
import by.gstu.chatbot.core.parser.Keyword;
import by.gstu.chatbot.core.parser.Regex;
import by.gstu.chatbot.core.parser.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface Bot {
    String getName();
    String getType();
    String getAnswer(String question);
}
