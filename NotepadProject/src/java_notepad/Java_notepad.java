package java_notepad;

import java.awt.*;
import javax.swing.*;

public class Java_notepad extends JFrame {

    JTextArea mainarea;
    JMenuBar mbar;
    JMenu mnuFile,mnuEdit,mnuFormat,mnuHelp;
    JMenuItem itemNew,itemOpen,itemSave;

    public Java_notepad() {
        initComponent();
    }

    private void initComponent() {
        mainarea = new JTextArea();
        getContentPane().add(mainarea);
        getContentPane().add(new JScrollPane(mainarea),BorderLayout.CENTER);
        setTitle("Untitled NotePad");
        setSize(800,600);
        //menubar
        mbar=new JMenuBar();
        //menu
        mnuFile=new JMenu("File");
        mnuEdit=new JMenu("Edit");
        mnuFormat=new JMenu("Format");
        mnuHelp=new JMenu("Help");
        //menuitem
        itemNew=new JMenuItem("New");
        itemOpen=new JMenuItem("Open");
        itemSave=new JMenuItem("Save");
        //add menu item
        mnuFile.add(itemNew);
        mnuFile.add(itemOpen);
        mnuFile.add(itemSave);
        //add menu item to menu bar
        mbar.add(mnuFile);
        mbar.add(mnuEdit);
        mbar.add(mnuFormat);
        mbar.add(mnuHelp);
        //add menubar to frame
        setJMenuBar(mbar);
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
       Java_notepad jn=new Java_notepad();
    }
}
