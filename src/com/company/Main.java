package com.company;


import java.io.*;
import java.util.*;

public class Main {
    static TreeNode root = null;
    static boolean ifContain = false;
    public static void main(String[] args) {
        // write your code here
        String city = "NewYork"; //put your city here to have a check
        javaRecursive(city);
    }
    public static void javaRecursive(String city) {
        //read txt file into list
        List<String> fileContent = TXTReader.readFile();
        //build bst based on dictionary order
        for (String s : fileContent) {
            insert(s);
        }

        System.out.println("\n preorder : \n");
        preOrderTraversal(root, city);
        System.out.println("\n inorder : \n");
        inOrderTraversal(root);
        System.out.println("\n postorder : \n");
        postOrderTraversal(root);

        if (ifContain) {
            System.out.println("\n Yes, BST contains your input");
        } else {
            System.out.println("\n No, BST doesn't contain your input");
        }
    }

    private static void preOrderTraversal(TreeNode root, String city) {
        if (root == null) {
            return;
        }
        if (isContain(city, root.data)) {
                ifContain = true;
        }
        System.out.println(root.data);
        if (root.left != null) {
            preOrderTraversal(root.left, city);
        }
        if (root.right != null) {
            preOrderTraversal(root.right, city);
        }
    }

    private static void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inOrderTraversal(root.left);
        }
        System.out.println(root.data);
        if (root.right != null) {
            inOrderTraversal(root.right);
        }
    }

    private static void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            postOrderTraversal(root.left);
        }
        if (root.right != null) {
            postOrderTraversal(root.right);
        }
        System.out.println(root.data);
    }

    private static boolean isContain(String city, String data) {
        return city.equals(data);
    }

    private static void insert(String data) {
        TreeNode node = new TreeNode(data);

        if (root == null) {
            root = node;
        } else {
            TreeNode index = root;
            TreeNode current;

            while (true) {
                current = index;
                if (data.compareTo(index.data) < 0) {
                    index = index.left;
                    if (index == null) {
                        current.left = node;
                        break;
                    }
                } else {
                    index = index.right;
                    if (index == null) {
                        current.right = node;
                        break;
                    }
                }
            }
        }
    }
}

class TreeNode{
    public String data;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class TXTReader {
    public static List<String> readFile() {
        String fileName = "data.txt";
        List<String> content = new ArrayList<>();

        try (FileReader reader = new FileReader(fileName);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                content.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}

