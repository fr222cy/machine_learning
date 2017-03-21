/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Machine_learning.model;

import java.util.Iterator;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author filip
 */
public class NaiveBayes {
    
    public NaiveBayes(Instances data){ 
        Iterator iterator = data.listIterator();
        
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    
    public String getResult(){
        return "Naive Bayes was created";
    }
    
}
