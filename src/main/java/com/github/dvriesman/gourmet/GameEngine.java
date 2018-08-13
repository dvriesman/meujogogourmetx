package com.github.dvriesman.gourmet;

import java.util.*;

public class GameEngine {

    private static String BOLO = "Bolo de Chocolate";

    private AskListener askListener;

    public void addAskListner(AskListener askListener) {
        this.askListener = askListener;
    }

    private Map<String, List<String>> gameData = new LinkedHashMap<>();

    public void init() {
        List<String> massaList = new LinkedList<>();
        massaList.add("Lasanha");
        gameData.put("massa", massaList);
    }

    public void compute() {
        if (askListener == null) {
            throw new RuntimeException("Asklistener não definido!");
        }
        String latestOption = null;
        Set<String> categorias = new LinkedHashSet<>();
        categorias.addAll(gameData.keySet());
        categorias.add(BOLO);
        boolean ok = false;
        FIRSTLOOP:
        for (String categoria: categorias) {
            latestOption = categoria;
            ok = ask(categoria);
            if (ok) {
                List<String> options = gameData.get(categoria);
                if (options != null) {
                    for (String option : options) {
                        latestOption = option;
                        ok = ask(option);
                        if (ok) {
                            break FIRSTLOOP;
                        }
                    }
                }
                break;
            }
        }
        if (ok) {
            askListener.message("Acertei de novo!");
        } else {
            String prato = askListener.input("Qual prato você pensou?", "Desisto");
            String question = askListener.input(prato + " é ______ mas " + latestOption  + " não.", "Complete");
            List<String> list = null;
            if ((list = gameData.get(question)) == null) {
                list = new LinkedList<>();
                gameData.put(question, list);
            }
            list.add(prato);
        }
    }

    private boolean ask(String word) {
        return askListener.confirm("O prato que você pensou é " + word+ "?");
    }

}
