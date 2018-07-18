package View;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.lang.reflect.Field;

/**
 * Display for the main application
 */
public class Display {
    private JFrame frame;
    private JLabel inputLabel;
    private JTextField inputField;
    private JButton goButton;
    private JTree classTree;
    private JPanel centerPanel;
    private JPanel topPanel;

    public Display() {
        initFrame();
        initTopPanel();
        frame.setVisible(true);

        goButton.addActionListener(e -> {
            try {
                initCenterPanel();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void initFrame() {
        frame = new JFrame("Class Viewer");
        frame.setContentPane(new JPanel(new BorderLayout()));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initTopPanel() {
        inputLabel = new JLabel("Enter class name");
        inputField = new JTextField();
        goButton = new JButton("Display POJO");
        inputField.setColumns(15);
        topPanel = new JPanel(new FlowLayout());
        topPanel.add(inputLabel);
        topPanel.add(inputField);
        topPanel.add(goButton);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.pack();
    }

    private void initCenterPanel() throws ClassNotFoundException {
        centerPanel = new JPanel();
        classTree = new JTree(node(Class.forName(inputField.getText())));
        centerPanel.add(classTree);
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.pack();
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
