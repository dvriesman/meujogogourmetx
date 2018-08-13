package com.github.dvriesman.gourmet;

import javax.swing.*;
import java.awt.*;

public class MainApp extends JFrame implements AskListener {

    private static String TITLE = "Jogo Gourmet";
    private static String INITIAL_QUESTION = "Pense em um prato que gosta";

    private GameEngine gameEngine = new GameEngine();

    public static void main(String args[]) {
        new MainApp();
    }

    public MainApp() {
        gameEngine.addAskListner(this);
        gameEngine.init();
        setLocationRelativeTo(null);
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(initialPanel());
        pack();
        setVisible(true);
    }

    private JPanel initialPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));
        JLabel label = new JLabel(INITIAL_QUESTION);
        label.setAlignmentX(0.5F);
        panel.add(label);
        JButton button = new JButton("OK");
        button.setAlignmentX(0.5F);
        button.addActionListener(e ->  gameEngine.compute());
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        return panel;
    }

    @Override
    public boolean confirm(String question) {
        return JOptionPane.showConfirmDialog(getParent(),
                question,"Confirm",0) == 0;
    }

    @Override
    public String input(String question, String dialogTitle) {
        return JOptionPane.showInputDialog(getParent(), question, dialogTitle,3);
    }

    @Override
    public void message(String message) {
        JOptionPane.showMessageDialog(getParent(), message, "Jogo Gourmet", 1);
    }

}
