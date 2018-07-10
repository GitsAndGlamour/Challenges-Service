package com.example.demo.to;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private String name;
    private List<Node<T>> children;

    public Node(String rootName, Node<T> parent) {
        this.name = rootName;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Node<T>> children) {
        this.children = children;
    }
}
