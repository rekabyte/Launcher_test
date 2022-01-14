package ca.rekabit.launcher;

import javafx.application.Application;

import javax.swing.*;

public class Main{

    public static void main(String[] args) {

        try {
            Class.forName("javafx.application.Application");
            Application.launch(FxApplication.class, args);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Bro va installer javaFX \n" +
                    "et montre moi ce message: \n" + e.getMessage());
        }

    }
}
