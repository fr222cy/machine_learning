/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Machine_learning.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.stopwords.StopwordsHandler;

/**
 *
 * @author filip
 */
public class MyStopWords implements StopwordsHandler  {
    private HashSet<String> myStopwords = new HashSet<String>();
    String stopwordsPath = MyStopWords.class.getResource("/data/stopwords.txt").getFile();

    public MyStopWords() {
        //Load in your own stopwords, etc.
        Scanner sc;
        try {
            sc = new Scanner(new File(stopwordsPath));
            while(sc.hasNext()){
                String word = sc.nextLine(); 
                myStopwords.add(word);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(MyStopWords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean isStopword(String word) {
        return myStopwords.contains(word); 
    }
}
