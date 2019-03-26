package java_notepad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class Java_notepad extends JFrame {

    JTextArea mainarea;
    JMenuBar mbar;
    JMenu mnuFile, mnuEdit, mnuFormat, mnuHelp;
    JMenuItem itemNew, itemOpen, itemSave, itmSaveas,
            itmExit, itmCopy, itmCut, itmPaste,itmFontColor;
    JCheckBoxMenuItem wordWrap;
    String fileName;
    JFileChooser jc;
    String fileContent;
    UndoManager undo;
    UndoAction undoAction;
    RedoAction redoAction;

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
        itemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open_new();
            }
        });
        itmExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        itmCut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainarea.cut();
            }
        });
        itmCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainarea.copy();
            }
        });
        itmPaste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainarea.paste();
            }
        });
        mainarea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undo.addEdit(e.getEdit());
                undoAction.update();
                redoAction.update();
            }
        });

      //  mainarea.setWrapStyleWord(true);
        wordWrap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(wordWrap.isSelected()){
                            mainarea.setLineWrap(true);
                   mainarea.setWrapStyleWord(true);
                }else{
                            mainarea.setLineWrap(false);
                    mainarea.setWrapStyleWord(false);
                }
            }
        });
        itmFontColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c=JColorChooser.showDialog(rootPane, "Choose Font Color", Color.yellow);
                mainarea.setForeground(c);
                
            }
        });
    }

    private void initComponent() {
        jc = new JFileChooser(".");
        mainarea = new JTextArea();
        undo = new UndoManager();
        ImageIcon undoIcon = new ImageIcon(getClass().getResource("/img/Undo.png"));
        ImageIcon redoIcon = new ImageIcon(getClass().getResource("/img/Redo.png"));
        undoAction = new UndoAction(undoIcon);
        redoAction = new RedoAction(redoIcon);
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
        ImageIcon exitIcon = new ImageIcon(getClass().getResource("/img/exit.png"));
        ImageIcon copyIcon = new ImageIcon(getClass().getResource("/img/copy.png"));
        ImageIcon cutIcon = new ImageIcon(getClass().getResource("/img/cut.png"));
        ImageIcon pasteIcon = new ImageIcon(getClass().getResource("/img/past.png"));

        //menuitem
        itemNew = new JMenuItem("New", newIcon);
        itemOpen = new JMenuItem("Open", openIcon);
        itemSave = new JMenuItem("Save", saveIcon);
        itmSaveas = new JMenuItem("Save As", saveAsIcon);
        itmExit = new JMenuItem("Exit", exitIcon);
        itmCopy = new JMenuItem("Copy", copyIcon);
        itmCut = new JMenuItem("Cut", cutIcon);
        itmPaste = new JMenuItem("Paste", pasteIcon);
        itmFontColor=new JMenuItem("Font Color");
        wordWrap=new JCheckBoxMenuItem("Word Wrap");
        //adding shortcut
        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        itmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        itmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        itmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        //add menu item
        mnuFile.add(itemNew);
        mnuFile.add(itemOpen);
        mnuFile.add(itemSave);
        mnuFile.add(itmSaveas);
        mnuFile.addSeparator();
        mnuFile.add(itmExit);
        mnuEdit.add(undoAction);
        mnuEdit.add(redoAction);
        mnuEdit.add(itmCopy);
        mnuEdit.add(itmCut);
        mnuEdit.add(itmPaste);
        mnuFormat.add(wordWrap);
        mnuFormat.add(itmFontColor);
        
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

                JOptionPane.showMessageDialog(rootPane, "File saved");
                fileContent = mainarea.getText();
            }

        } catch (IOException e) {
        } finally {
            if (fout != null) {
                fout.close();
            }

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
            fileContent = mainarea.getText();
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

    private void open_new() {
        if (!mainarea.getText().equals("") && !mainarea.getText().equals(fileContent)) {
            if (fileName == null) {
                int option = JOptionPane.showConfirmDialog(rootPane, "Do you Want o save the changes?");
                if (option == 0) {
                    saveAs();
                    clear();
                } else if (option == 2) {
                } else {
                    clear();
                }
            } else {
                int option = JOptionPane.showConfirmDialog(rootPane, "Do you Want o save the changes?");
                if (option == 0) {
                    save();
                    clear();
                } else if (option == 2) {
                } else {
                    clear();
                }
            }
        } else {
            clear();
        }

    }

    private void clear() {
        mainarea.setText(null);
        setTitle("Untitled Notepad");
        fileName = null;
        fileContent = null;
    }

    class UndoAction extends AbstractAction {

        public UndoAction(ImageIcon undoIcon) {
            super("Undo", undoIcon);
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                undo.undo();
            } catch (CannotUndoException ex) {
                ex.printStackTrace();
            }
            update();
            redoAction.update();
        }

        protected void update() {
            if (undo.canUndo()) {
                setEnabled(true);
                putValue(Action.NAME, "Undo");
            }else{
                setEnabled(false);
                putValue(Action.NAME, "Undo");
            }
        }

    }

    class RedoAction extends AbstractAction {

        public RedoAction(ImageIcon redoIcon) {
            super("Redo", redoIcon);
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                undo.redo();
            } catch (CannotRedoException ex) {
                ex.printStackTrace();
            }
            update();
             undoAction.update();
        }

        protected void update() {
            if (undo.canRedo()) {
                setEnabled(true);
                putValue(Action.NAME, "Redo");
            }else{
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }

    }

    public static void main(String[] args) {
        Java_notepad jn = new Java_notepad();
    }
}
