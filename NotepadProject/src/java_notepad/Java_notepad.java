package java_notepad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.StringTokenizer;
import javax.swing.*;

public class Java_notepad extends JFrame {

    JTextArea mainarea;
    JMenuBar mbar;
    JMenu mnuFile, mnuEdit, mnuFormat, mnuHelp;
    JMenuItem itemNew, itemOpen, itemSave, itmSaveas, itmExit;
    String fileName;
    JFileChooser jc;

    public Java_notepad() {
        initComponent();
        itemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        itmSaveas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAs();
            }
        });
        itemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open();
            }
        });
    }

    private void initComponent() {
        jc = new JFileChooser(".");
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
        ImageIcon saveAsIcon = new ImageIcon(getClass().getResource("/img/Saveas.png"));

        //menuitem
        itemNew = new JMenuItem("New", newIcon);
        itemOpen = new JMenuItem("Open", openIcon);
        itemSave = new JMenuItem("Save", saveIcon);
        itmSaveas = new JMenuItem("Save As", saveAsIcon);
        //adding shortcut
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        //add menu item
        mnuFile.add(itemNew);
        mnuFile.add(itemOpen);
        mnuFile.add(itemSave);
        mnuFile.add(itmSaveas);
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

    private void save() {
        PrintWriter fout = null;
        try {
            if (fileName == null) {
                saveAs();
            } else {
                fout = new PrintWriter(new FileWriter(fileName));
                String s = mainarea.getText();
                StringTokenizer st = new StringTokenizer(s, System.getProperty("line.separator"));
                while (st.hasMoreElements()) {
                    fout.println(st.nextToken());
                }
                 fout.close();
                JOptionPane.showMessageDialog(rootPane, "File saved");
            }

        } catch (IOException e) {
        } finally {
           
        }
    }

    private void saveAs() {
        PrintWriter fout = null;
        int retval = -1;
        try {
            retval = jc.showSaveDialog(this);
            if (retval == JFileChooser.APPROVE_OPTION) {
                fout = new PrintWriter(new FileWriter(jc.getSelectedFile()));
            }

            String s = mainarea.getText();
            StringTokenizer st = new StringTokenizer(s, System.getProperty("line.separator"));
            while (st.hasMoreElements()) {
                fout.println(st.nextToken());
            }
            JOptionPane.showMessageDialog(rootPane, "File saved");
            fileName = jc.getSelectedFile().getName();
            setTitle(fileName = jc.getSelectedFile().getName());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fout.close();
        }

    }

    private void open() {
        try {
            int retval = jc.showOpenDialog(this);
            if (retval == JFileChooser.APPROVE_OPTION) {
                mainarea.setText(null);
                Reader in = new FileReader(jc.getSelectedFile());
                char[] buff = new char[1000000000];
                int nch;
                while ((nch = in.read(buff, 0, buff.length)) != -1) {
                    mainarea.append(new String(buff, 0, nch));
                }
            }
            fileName = jc.getSelectedFile().getName();
            setTitle(fileName = jc.getSelectedFile().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Java_notepad jn = new Java_notepad();
    }
}
