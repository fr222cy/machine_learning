/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Machine_learning.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import com.Machine_learning.model.NaiveBayes;
import com.Machine_learning.model.SupportVectorMachine;
import com.Machine_learning.model.Preprocessing;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Iterator;



public class scraperServlet extends HttpServlet {
    String root = scraperServlet.class.getResource("/data/List_of_articles/pages").getPath();
    String[] categories = {
        root + "/100business.html",
        root + "/100entertainment.html",
        root + "/100politic.html",
        root + "/100technology.html",
        root + "/100sport.html"
    };
    
    
    String business = root + "/100business.html";
    String entertainment = root + "/100entertainment.html";
    String politics = root + "/100politic.html";
    String tech = root + "/100technology.html";
    String sports = root + "/100sport.html";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
        //for(String category : categories){
            StringBuilder contentBuilder = new StringBuilder();
            try {
                BufferedReader in = new BufferedReader(new FileReader(business));
                String str;
                while ((str = in.readLine()) != null) {
                    contentBuilder.append(str);
                }
                in.close();
            } catch (IOException e) {
            }
            String content = contentBuilder.toString();
            List<String> arr = getTagValues(content);
            System.out.println(arr.size());
            int count = 1;
            for(String url : arr){
                scrapeAndSave(url, business, count++);//byt business --> category
            }
        //}
    }
    private static final Pattern TAG_REGEX = Pattern.compile("<a class=\""+"l _HId" + "\" href=\"(.*?)\"");
    private static List<String> getTagValues(final String str) {
        final List<String> tagValues = new ArrayList<String>();
        final Matcher matcher = TAG_REGEX.matcher(str);
        while (matcher.find()) {
            tagValues.add(matcher.group(1));
        }
        return tagValues;
    }
    
    private void scrapeAndSave(String url, String category, int count){
        
        System.out.println(count+".txt" );
        System.out.println("URL: "+url);
    }
    
}

