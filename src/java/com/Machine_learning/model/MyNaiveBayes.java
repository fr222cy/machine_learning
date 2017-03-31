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
import weka.classifiers.functions.LibSVM;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author filip
 */
public class MyNaiveBayes {
    Classifier nb;
    Instances dataInstances;
    Evaluation eval;
    public MyNaiveBayes(Instances data){
        dataInstances = data;
        try {
         nb = new NaiveBayes();
         nb.buildClassifier(dataInstances);        
         eval = new Evaluation(dataInstances);
        } catch (Exception ex) {
            Logger.getLogger(MyNaiveBayes.class.getName()).log(Level.SEVERE, null, ex);
        }      
        
    }
    
    public void applyMethod(String method){
        try {
               if(method.equals("cross-validation")){
                   System.out.println("CHOOSE cross-valid");
                  eval.crossValidateModel(nb, dataInstances, 4, new Random(1));
               }else if(method.equals("test-set")){
                   //TODO
               }else if(method.equals("percentage")){
                   int trainSize = (int) Math.round(dataInstances.numInstances() * 0.8);
                   int testSize = dataInstances.numInstances() - trainSize;
                   Instances train = new Instances(dataInstances, 0, trainSize);
                   Instances test = new Instances(dataInstances, trainSize, testSize);
                   train.randomize(new java.util.Random(0));
                   nb.buildClassifier(train);  
                   eval.evaluateModel(nb, test);
               }       
        }catch (Exception ex) {
        Logger.getLogger(MyNaiveBayes.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public String getResult(){
        try {        
            return  eval.toSummaryString();
        } catch (Exception ex) {
            Logger.getLogger(MyNaiveBayes.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "Something went wrong (NB)";
    }
    
}
