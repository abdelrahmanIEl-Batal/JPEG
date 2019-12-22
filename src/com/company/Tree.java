package com.company;

import javafx.util.Pair;

import java.util.*;

public class Tree {
    final Pair<Integer,Integer> p = new Pair<>(-10,-10);     // -10 -10 is a dummy descriptor
    Map< Pair<Integer,Integer> ,Node> hash = new HashMap<>();
    PriorityQueue<Node> nodes = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node node, Node t1) {
            return t1.getFreq() - node.getFreq();       // descending
        }
    });
    Tree(PriorityQueue<Node> l){
        nodes.addAll(l);
        while (nodes.size()>1){
            Node temp = nodes.poll();
            Node temp2 = nodes.poll();
            Node newNode = new Node(new Pair<>(-10,-10),temp.getFreq() + temp2.getFreq()); // dummy
            temp.setParent(newNode);
            temp2.setParent(newNode);
            newNode.setLeft(temp);
            newNode.setRight(temp2);
            hash.put(temp.getValue(),temp);
            hash.put(temp2.getValue(),temp2);
            nodes.add(newNode);
        }
    }

    String encode(Pair<Integer,Integer> c) {
        Node t = hash.get(c);
        //System.out.println(t.getFreq() + "       " + t.getChar());
        String res = "";
        while (t.getParent() != null) {
            if (t.getParent().getRight() == t) {
                res += "0";
            } else res += "1";
            t = t.getParent();
        }
        String r = "";
        for (int i = res.length() - 1; i >= 0; --i) r += res.charAt(i);
        return r;
    }


    String decode(String s){
        Node root = nodes.peek();
        String ans = "";
        for(int i=0;i<s.length();++i)
        {
            if(root.getRight()==null || root.getLeft()==null) root = nodes.peek();
            if(s.charAt(i)=='1') root = root.getRight();
            else root = root.getLeft();
            if(root.getValue()!=p) ans+=root.getValue();
        }
        return ans;
    }
}
