package cz.reindl.game.utils;

import cz.reindl.game.GameHub;
import cz.reindl.game.entity.Enemies;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ProgressBarCheck {

    //JProgressBar functionality and use test
    GameHub hub;
    JFrame window = new JFrame();
    public JProgressBar bar = new JProgressBar(0, 5); //Minimum and maximum value of bar
    public JButton button;

    public ProgressBarCheck(GameHub gameHub) {
        this.hub = gameHub;
    }

    public void progressBarCheck() {
        bar.setValue(5); //Percentage of bar
        bar.setString(String.valueOf(Enemies.RAT.getEntityHp())); //String of enemy HP
        bar.setBounds(0, 0, 300, 50);
        bar.setStringPainted(true); //Paints the percentage of bar
        bar.setFont(new Font("MV Boli", Font.BOLD, 25));
        bar.setForeground(Color.RED);
        bar.setBackground(Color.BLACK);
        window.add(bar);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(420, 420);
        window.setLayout(null);
        window.setVisible(true);

        fill();
    }

    public void fill() {
        button = new JButton();
        button.addActionListener(hub.actionHandler);
        button.setActionCommand("fightEnemy");
        button.setBounds(20, 20, 20, 20);
        window.add(button);
    }

}
