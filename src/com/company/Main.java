package com.company;


public class Main {

    public static void main(String[] args) {
        Encoder e = new Encoder("-2 0 0 2 0 0 3 2 0 1 0 0 -2 0 -1 0 0 1 0 0 -1");
        e.getDescribotrs();
        //System.out.print(e.getBinary(-2));
        System.out.println();
        e.writeToFile();
    }
}
