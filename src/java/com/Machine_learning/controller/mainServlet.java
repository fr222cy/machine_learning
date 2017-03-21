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
import java.util.List;
import com.Machine_learning.model.NaiveBayes;
import com.Machine_learning.model.SupportVectorMachine;
import com.Machine_learning.model.Preprocessing;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class mainServlet extends HttpServlet {
    
    String datasetPath = "/com/Machine_learning/data/categories.arff";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
        String testString = "Herro word";
        request.setAttribute("testString", testString);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
            
        
        
        Preprocessing preprocess = new Preprocessing(datasetPath);
        String classifier = request.getParameter("classifier");
        String result;
        if(classifier.equals("nb")){
            try {
                NaiveBayes nb = new NaiveBayes(preprocess.getDataSet());
                result = nb.getResult();

            } catch (Exception ex) {
                Logger.getLogger(mainServlet.class.getName()).log(Level.SEVERE, null, ex);
                result = "Something went wrong!";
            }
        }else if(classifier.equals("svm")){
            SupportVectorMachine svm = new SupportVectorMachine();
            result = svm.getResult();
        }else{
           result = "Did not chose a classifier"; 
        }
             
  
        
        request.setAttribute("result",result);
     
        
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
    
}

