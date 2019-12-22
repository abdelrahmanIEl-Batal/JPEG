package com.company;

import javafx.util.Pair;

public class Node {
    private Pair<Integer,Integer> value;
    private int freq;
    private Node parent,left,right;

    public Node(Pair<Integer,Integer> c,int freq){
        this.value = c;
        this.freq = freq;
        parent = left = right = null;
    }

    Node getRight(){
        return this.right;
    }
    Node getLeft(){
        return this.left;
    }
    Node getParent(){
        return this.parent;
    }
    void setParent(Node n){
        this.parent = n;
    }
    void setLeft(Node n){
        this.left = n;
    }
    void setRight(Node n){
        this.right = n;
    }
    Pair<Integer, Integer> getValue(){
        return this.value;
    }
    int getFreq(){
        return this.freq;
    }
}
