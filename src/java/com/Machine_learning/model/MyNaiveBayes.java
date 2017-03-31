/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Machine_learning.model;

import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.classifiers.bayes.NaiveBayesMultinomialText;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author filip
 */
public class MyNaiveBayes {
    Classifier nb;
    Instances dataInstances;
    public MyNaiveBayes(Instances data){
        dataInstances = data;
        try {
         nb = new NaiveBayes();
         nb.buildClassifier(data);        
      
        } catch (Exception ex) {
            Logger.getLogger(MyNaiveBayes.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    public String getResult(){
        try {
            Evaluation eval = new Evaluation(dataInstances);
            //eval.evaluateModel(nb, dataInstances);
            eval.crossValidateModel(nb, dataInstances, 4, new Random(1));
           
            return  eval.toSummaryString();
        } catch (Exception ex) {
            Logger.getLogger(MyNaiveBayes.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "Something went wrong (NB)";
    }
    
}
