package java_notepad;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.*;

public class Java_notepad extends JFrame {

    JTextArea mainarea;

    public Java_notepad() {
        initComponent();
    }

    private void initComponent() {
        mainarea = new JTextArea();
        getContentPane().add(mainarea);
        getContentPane().add(new JScrollPane(mainarea),BorderLayout.CENTER);
        setTitle("Untitled NotePad");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
       Java_notepad jn=new Java_notepad();
    }
}
