package programGui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ProgramUi {


public static void main(String[] args) { 
    
    JFrame frame = new JFrame("PIXEList");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400,600);
    
    JMenuBar menubar = new JMenuBar();
    JMenu m1 = new JMenu("Menu");
    JMenu m2 = new JMenu("Help");
    menubar.add(m1);
    menubar.add(m2);
    JMenuItem m10 = new JMenuItem("New");
    JMenuItem m11 = new JMenuItem("Open File");
    JMenuItem m12 = new JMenuItem("Save as");
    m1.add(m10);
    m1.add(m11);
    m1.add(m12);
    
    JMenuItem m20 = new JMenuItem("Welcome");
    m2.add(m20);
    
    JPanel panel = new JPanel(); 
    JLabel label = new JLabel("Enter Text:");
    JTextField textfield = new JTextField(10);
    JButton send = new JButton("Send");
    JButton clear = new JButton("Clear");
    panel.add(label);
    panel.add(textfield);
    panel.add(send);
    panel.add(clear);

    JTextArea ta = new JTextArea();
    JSeparator js = new JSeparator();
    
    frame.getContentPane().add(BorderLayout.SOUTH,panel);
    frame.getContentPane().add(BorderLayout.NORTH,menubar);
    frame.getContentPane().add(BorderLayout.CENTER,js);
    frame.getContentPane().add(BorderLayout.CENTER,ta);
    frame.setVisible(true);
    }
    
}