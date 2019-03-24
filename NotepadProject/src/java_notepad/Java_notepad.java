package java_notepad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Java_notepad extends JFrame {

    JTextArea mainarea;
    JMenuBar mbar;
    JMenu mnuFile, mnuEdit, mnuFormat, mnuHelp;
    JMenuItem itemNew, itemOpen, itemSave;

    public Java_notepad() {
        initComponent();
    }

    private void initComponent() {
        mainarea = new JTextArea();
        getContentPane().add(mainarea);
        getContentPane().add(new JScrollPane(mainarea), BorderLayout.CENTER);
        setTitle("Untitled NotePad");
        setSize(800, 600);
        //menubar
        mbar = new JMenuBar();
        //menu
        mnuFile = new JMenu("File");
        mnuEdit = new JMenu("Edit");
        mnuFormat = new JMenu("Format");
        mnuHelp = new JMenu("Help");
        //ad icon  to menu item
        ImageIcon newIcon = new ImageIcon(getClass().getResource("/img/new.png"));
        ImageIcon openIcon = new ImageIcon(getClass().getResource("/img/open.png"));
        ImageIcon saveIcon = new ImageIcon(getClass().getResource("/img/Save.png"));

        //menuitem
        itemNew = new JMenuItem("New", newIcon);
        itemOpen = new JMenuItem("Open", openIcon);
        itemSave = new JMenuItem("Save", saveIcon);
        //adding shortcut
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        
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
        Java_notepad jn = new Java_notepad();
    }
}
