/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Machine_learning.model;

import com.Machine_learning.controller.mainServlet;
import java.util.Iterator;
import java.util.List;
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
public class MyNaiveBayes extends ClassifierResults{
    Classifier classifier;
    Instances dataInstances;
    
    public MyNaiveBayes(Instances data){
        dataInstances = data;
        try {
         classifier = new NaiveBayes();
         classifier.buildClassifier(dataInstances);        
         eval = new Evaluation(dataInstances);
        } catch (Exception ex) {
            Logger.getLogger(MyNaiveBayes.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
    public void applyMethod(String method){ 
        try {
               if(method.equals("cross-validation")){
                  eval.crossValidateModel(classifier, dataInstances, 4, new Random(1));
               }else if(method.equals("test-set")){
                   Preprocessing preprocessTestSet = new Preprocessing(null);
                   List<Instances> datasets = preprocessTestSet.getDataSets(MyNaiveBayes.class.getResource("/data/categories.arff").getPath(), MyNaiveBayes.class.getResource("/data/2017-articles.arff").getPath());
                   classifier = new NaiveBayes();
                   classifier.buildClassifier(datasets.get(0));
                   eval = new Evaluation(datasets.get(0));
                   eval.evaluateModel(classifier, datasets.get(1));
               }else if(method.equals("percentage")){
                   int trainSize = (int) Math.round(dataInstances.numInstances() * 0.785);
                   int testSize = dataInstances.numInstances() - trainSize;
                   Instances train = new Instances(dataInstances, 0, trainSize);
                   Instances test = new Instances(dataInstances, trainSize, testSize);
                   train.randomize(new java.util.Random(0));
                   classifier.buildClassifier(train);  
                   eval.evaluateModel(classifier, test);
               }       
        }catch (Exception ex) {
        Logger.getLogger(MyNaiveBayes.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
            
  
    
}
