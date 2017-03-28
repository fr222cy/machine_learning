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
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.UserAgent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    String root = scraperServlet.class.getResource("/data/List_of_articles/").getPath();
    String[] categories = {
        root + "Business/100business.html",
        root + "Entertainment/100entertainment.html",
        root + "Politics/100politic.html",
        root + "Technology/100technology.html",
        root + "Sports/100sport.html"
    };
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
        for(String category : categories){
            StringBuilder contentBuilder = new StringBuilder();
            try {
                BufferedReader in = new BufferedReader(new FileReader(category));
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
                if(scrapeAndSave(url, category, count)){
                    count++;
                }
            }
        }
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
    
    private boolean scrapeAndSave(String url, String category, int count){
        
        try {
            if(url.contains("/live/"))
            {
                System.out.println("LIVE ARTICLE INVALID");
                return false;
            }
            UserAgent agent = new UserAgent();
            agent.visit(url);
            if(url.contains("/sport/")){
                return sportScrape(category, agent, count);
            }
            else{
                return normalScrape(category, agent, count);
            }
            
        } catch (ResponseException ex) {
            System.out.println("Article not found");
            return false;
        }
    }
    
       
    private boolean normalScrape(String category, UserAgent agent, int count){
            String content = "";
            Element title;
            try {
                title = agent.doc.findFirst("<h1 class=story-body__h1>");
                content += title.getText() + "\n";
                Element inner = agent.doc.findFirst("<div class=story-body__inner>");
                List<Element> text = inner.getChildElements();
                for(Element t : text)
                {
                    String str = t.getText();
                    if(t.getName().equals("h2") || t.getName().equals("h3")){
                        str += " ";
                    }
                    else if(t.getName().equals("script") || t.getName().equals("style")){
                        str = "";
                    }
                    else if(t.getName().equals("ul"))
                    {
                        for(Element e : t.getChildElements()){
                            str += e.getText() + " ";
                        }
                    }
                    content += str;
                }
            } catch (NotFound ex) {
                System.out.println("VIDEO ARTICLE: INVALID");
                return false;
            }
            
            //System.out.println(content);
            content = content.replaceAll("[,.!?;:]+\\b(?! .)", "$0 ").replaceAll("\\s+", " "); 
            File file = new File(category);
            String newFilePath = file.getParentFile().getPath()+"/"+count+".txt";
            System.out.println("CREATING FILE: "+count+".txt for " + file.getParentFile().getName() +" ...");
            writeToFile(newFilePath, content);
            return true;
    }
    
    private boolean sportScrape(String category, UserAgent agent, int count){
            String content = "";
            Element title;
            try {
                title = agent.doc.findFirst("<h1 class=\"story-headline gel-trafalgar-bold \">");
                content += title.getText() + "\n";
                Element inner = agent.doc.findFirst("<div id=story-body>");
                List<Element> text = inner.getChildElements();
                for(Element t : text)
                {
                    String str = t.getText();
                    if(t.getName().equals("h2") || t.getName().equals("h3")){
                        str += " ";
                    }
                    else if(t.getName().equals("script") || t.getName().equals("style")){
                        str = "";
                    }
                    else if(t.getName().equals("ul"))
                    {
                        for(Element e : t.getChildElements()){
                            str += e.getText() + " ";
                        }
                    }
                    content += str;
                }
            } catch (NotFound ex) {
                System.out.println("VIDEO ARTICLE: INVALID");
                return false;
            }
            
            //System.out.println(content);
            content = content.replaceAll("[,.!?;:]+\\b(?! .)", "$0 ").replaceAll("\\s+", " "); 
            File file = new File(category);
            String newFilePath = file.getParentFile().getPath()+"/"+count+".txt";
            System.out.println("CREATING FILE: "+count+".txt for " + file.getParentFile().getName() +" ...");
            writeToFile(newFilePath, content);
            return true;
    }
    
    
    private void writeToFile(String path, String content){
        try {
            try (FileWriter writer = new FileWriter(path)) {
                writer.write(content);
                writer.flush();
                writer.close();
                
                System.out.println("...CREATED FILE");
            }
        } catch (IOException ex) {
            Logger.getLogger(scraperServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}

