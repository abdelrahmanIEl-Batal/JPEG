package com.company;

import javafx.util.Pair;

import javax.swing.*;
import javax.swing.table.TableStringConverter;
import java.util.*;

public class Encoder {
    ArrayList<Pair<Integer,Integer> > descriptors;
    Map< Pair<Integer,Integer> , Integer > freq;
    ArrayList<String> additionalBits;
    String input ;
    PriorityQueue<Node> nodes;
    Tree huffman;
    Encoder(String s){
        descriptors = new ArrayList<>();
        freq = new HashMap<>();
        additionalBits = new ArrayList<>();
        input = s;
        nodes = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                return t1.getFreq() - node.getFreq();       // descending
            }
        });
    }


    // divide input stream into a groups and get descriptor for each group and calculates each descriptor probability
    void getDescribotrs(){
        int zero = 0;
        String [] split = input.split(" ");
        for(int i=0;i<split.length;++i){
            int curr = Integer.parseInt(split[i]);
            if(curr==0){
                zero++;
            }
            else{
                descriptors.add(new Pair<>(zero,getCategory(Math.abs(curr))));
                additionalBits.add(getBinary(curr));
                zero = 0;
            }
        }
        /*
        for(int i=0;i<descriptors.size();++i){
            System.out.println(descriptors.get(i).getKey()+"/"+descriptors.get(i).getValue());
        }
        */
        for(int i=0;i<descriptors.size();++i){
            if(!freq.containsKey(descriptors.get(i))){
                freq.put(descriptors.get(i),1);
            }
            else{
                int count = freq.get(descriptors.get(i));
                freq.put(descriptors.get(i),count+1);
            }
        }
        for(Map.Entry<Pair<Integer, Integer>, Integer> e: freq.entrySet()){
            System.out.println(e.getKey().getKey() + "/" + e.getKey().getValue()+ " Prob: " + e.getValue()+"/"+(descriptors.size()+1));
            Pair<Integer,Integer> p = e.getKey();
            nodes.add(new Node(p,e.getValue()));
        }
        nodes.add(new Node(new Pair<Integer, Integer>(-1,-1),1));     // -1 -1 for EOB
        System.out.print(nodes.size());
    }


    // get categroy of number
    int getCategory(int n) {
        return (int) (Math.log(n) / Math.log(2)) + 1;
    }


    // Get binary representation for a number
    String getBinary(int n){
        int temp = Math.abs(n);
        String ret ="";

        while(temp > 0){
            if(temp%2==1) ret = "1" + ret;
            else ret = "0" + ret;
            temp/=2;
        }

        StringBuilder res = new StringBuilder(ret);
        if(n<0){
            for(int i=0;i<res.length();++i){
                if(res.charAt(i)=='0')  res.setCharAt(i,'1');
                else res.setCharAt(i,'0');
            }
        }
        //System.out.println(res);
        return res.toString();
    }

    void writeToFile(){  // just displays output here no file yet
        huffman = new Tree(nodes);
        String ans = "";
        /*
        for(int i=0;i<descriptors.size();++i){
            ans+= huffman.encode(descriptors.get(i)); ans+=",";
            ans+=additionalBits.get(i); ans+=" ";
        }
        System.out.println(ans);
         */

        for(Node p : nodes){
            System.out.println(p.getValue().getKey()+"/" + p.getValue().getValue()+" " + huffman.encode(p.getValue()));
        }

    }
}
