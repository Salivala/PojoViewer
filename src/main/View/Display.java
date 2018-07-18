package main.View;

import main.Model;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.lang.reflect.Field;
import java.time.temporal.ChronoUnit;
import java.util.Enumeration;
import java.util.Scanner;

/**
 * Display for the main application
 */
public class Display {
    private JFrame frame;
    private JLabel inputLabel;
    private JTextField inputField;
    private JTree classTree;
    private JPanel centerPanel;
    private JPanel topPanel;

    public Display() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        initFrame();
        initTopPanel();
        initCenterPanel();
        frame.setVisible(true);
    }

    private void initFrame() {
        frame = new JFrame("Class Viewer");
        frame.setContentPane(new JPanel(new BorderLayout()));
        frame.setSize(300,300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initTopPanel() {
        inputLabel = new JLabel("Enter class name");
        inputField = new JTextField();
        inputField.setColumns(6);
        topPanel = new JPanel(new FlowLayout());
        topPanel.add(inputLabel);
        topPanel.add(inputField);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
    }

    private void initCenterPanel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        Model model = new Model();
        centerPanel = new JPanel();
        classTree = new JTree(node(TestPojo.class));
        centerPanel.add(classTree);
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    DefaultMutableTreeNode initTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(inputField.getText());
        createNodes(root);
        return root;
    }

    void createNodes(DefaultMutableTreeNode root) {
        DefaultMutableTreeNode cl = null;
        DefaultMutableTreeNode primative = null;
        root.add(new DefaultMutableTreeNode("ok"));
        cl = new DefaultMutableTreeNode("UserObject");
        cl.add(new DefaultMutableTreeNode("int"));
        root.add(cl);
    }

    DefaultMutableTreeNode node(Class cl) {
        DefaultMutableTreeNode node = null;
        node = new DefaultMutableTreeNode(cl.getName());
        for (Field field : cl.getDeclaredFields()) {
            //node.add(node(field.getType()));
            if (field.getType().isPrimitive()) {
                node.add(new DefaultMutableTreeNode(field.getType()));
            }
            else {
                node.add(node(field.getType()));
            }
        }
        return node;
    }
}
