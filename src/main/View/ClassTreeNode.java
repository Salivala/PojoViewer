package main.View;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;

public class ClassTreeNode implements TreeNode {
    public ArrayList<ClassTreeNode> children;
    String name;
    public ClassTreeNode(ArrayList<ClassTreeNode> children, String name) {
        this.children = children;
        this.name = name;
    }
    @Override
    public TreeNode getChildAt(int childIndex) {
        return null;
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public TreeNode getParent() {
        return null;
    }

    @Override
    public int getIndex(TreeNode node) {
        return children.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return new Enumeration<TreeNode>() {
            @Override
            public boolean hasMoreElements() {
                return false;
            }

            @Override
            public TreeNode nextElement() {
                return null;
            }
        };
    }
}
