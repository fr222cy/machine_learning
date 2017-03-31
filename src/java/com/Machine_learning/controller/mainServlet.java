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
import com.Machine_learning.model.MyNaiveBayes;
import com.Machine_learning.model.SupportVectorMachine;
import com.Machine_learning.model.Preprocessing;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class mainServlet extends HttpServlet {
    
    String datasetPath = mainServlet.class.getResource("/data/categories.arff").getPath();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
        String testString = "Herro word";
        request.setAttribute("testString", testString);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String preprocessVal = request.getParameter("shouldPreprocess"); 
            boolean shouldPreprocess = false;
            if(preprocessVal.equals("1")){
                shouldPreprocess = true;
            }
            
            String method = request.getParameter("testMethod"); 
            System.out.println("METHOD: " + method);
            
          
            Preprocessing preprocess = new Preprocessing(datasetPath);  
           
            
            String classifier = request.getParameter("classifier");
            String result;
            if(classifier.equals("nb")){
                MyNaiveBayes nb = new MyNaiveBayes(preprocess.getDataSet(shouldPreprocess));
                nb.applyMethod(method);
                result = nb.getResult();
            }else if(classifier.equals("svm")){
                SupportVectorMachine svm = new SupportVectorMachine();
                result = svm.getResult();
            }else{
                result = "Did not chose a classifier";
            }
            request.setAttribute("result",result);
            request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(mainServlet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Herro");
        }
    }
    
}

