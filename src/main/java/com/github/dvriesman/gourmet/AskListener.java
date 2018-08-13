package com.github.dvriesman.gourmet;

public interface AskListener {
    boolean confirm(String question);
    String input(String question, String dialogTitle);
    void message(String message);
}
