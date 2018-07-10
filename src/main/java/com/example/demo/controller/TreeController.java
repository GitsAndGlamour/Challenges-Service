package com.example.demo.controller;

import com.example.demo.to.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeController {

    public Map<String, Object> getResults(final int levels) {
        Map<String, Object> treeMap = new HashMap<>();
        Node<String> tree = getTree(levels);
        treeMap.put("tree", tree);
        treeMap.put("hierarchicalOrder", getResultsByHierarchy(tree));
        treeMap.put("generationalOrder", getResultsByGeneration(tree));
        return treeMap;
    }

    private Node<String> getTree(final int levels) {
        Node tree = createTree(getAlphabetLetter(), new ArrayList<>());
        generateRootedTree(levels,tree);
        return tree;
    }


    private String getResultsByHierarchy(Node<String> tree) {
        StringBuilder result = new StringBuilder();
        getHierarchicalOrder(result, tree);
        return result.toString();
    }

    private String getResultsByGeneration(Node<String> tree) {
        Map<Integer, List<Node>> rowMap = new HashMap<>();
        mapTreeLevel(tree, true, rowMap, null);
        return getGenerationalOrder(rowMap);
    }

    private String getAlphabetLetter() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int randomIndex = (int) Math.floor(Math.random() * 26);
        return String.valueOf(alphabet.charAt(randomIndex));
    }

    private void generateRootedTree(int levels, Node root) {
        while (0 < levels--) {
            int childrenCount = 0;
            int numberOfChildren = (int) Math.floor(Math.random() * 5);
            do {
                Node child = new Node(getAlphabetLetter(), root);
                root.getChildren().add(child);
                childrenCount++;
            } while (childrenCount < numberOfChildren);
            for (int i = 0; i < childrenCount; i++) {
                Node child = (Node) root.getChildren().get(i);
                generateRootedTree(levels, child);
            }
        }
    }

    private Node createTree(String root, List<String> children) {
        Node rootNode = new Node(root, null);
        for (String child : children) {
            Node childNode = new Node(child, rootNode);
            rootNode.getChildren().add(childNode);
        }
        return rootNode;
    }

    private void getHierarchicalOrder(StringBuilder result, Node tree) {
        String parent = tree.getName();
        result.append(parent.toUpperCase());
        List<Node> children = tree.getChildren();
        for (Node child : children) {
            result.append(" ");
            if (child.getChildren().size() > 0) {
                getHierarchicalOrder(result, child);
            } else {
                result.append(child.getName().toUpperCase());
            }
        }
    }

    private String getGenerationalOrder(Map<Integer, List<Node>> rowMap) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, List<Node>> row : rowMap.entrySet()) {
            for (Node node : row.getValue()) {
                result.append(node.getName().toUpperCase() + " ");
            }
        }
        return result.toString().substring(0, result.lastIndexOf(" "));
    }

    private void mapTreeLevel(Node root, boolean isRoot, Map<Integer, List<Node>> rowMap, List<Node> trees) {
        Integer row = rowMap.size();
        List<Node> nodes = new ArrayList<>();
        if (isRoot) {
            nodes.add(root);
            rowMap.put(row, nodes);
            if (root.getChildren().size() > 0) {
                List<Node> children = root.getChildren();
                rowMap.put(++row, children);
                mapTreeLevel(root, false, rowMap, children);
            }
        } else {
            for (Node tree : trees) {
                if (tree.getChildren().size() > 0) {
                    List<Node> children = tree.getChildren();
                    for (Node child : children) {
                        nodes.add(child);
                    }
                }
                rowMap.put(row, nodes);
                mapTreeLevel(root, false, rowMap, nodes);
            }
        }
    }
}
