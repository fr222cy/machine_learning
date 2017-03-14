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
        //inititerar object
        
        String classifier = request.getParameter("classifier");
        String setting = request.getParameter("setting");
        
        request.setAttribute("classifier","You seleceted "+ classifier);
        request.setAttribute("setting", " with "+  setting);
        
        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
    
}

