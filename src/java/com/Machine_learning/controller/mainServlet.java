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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class mainServlet extends HttpServlet {
    //Statiskt object

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
        String testString = "Herro word";
        request.setAttribute("testString", testString);
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        
        
        
        
        String classifier = request.getParameter("classifier");
        System.out.println("---------------------"+classifier+"-----------------");
        String setting = request.getParameter("setting");
        String result;
        if(classifier.equals("nb")){
            NaiveBayes nb = new NaiveBayes();
            result = nb.getResult();
        }else if(classifier.equals("svm")){
            SupportVectorMachine svm = new SupportVectorMachine();
            result = svm.getResult();
        }else{
            result = "Something went wrong!";
        }
        
  
        
        request.setAttribute("result",result);
     
        
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
    
}

