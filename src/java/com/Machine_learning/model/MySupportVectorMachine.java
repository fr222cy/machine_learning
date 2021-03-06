/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Machine_learning.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.classifiers.functions.LibSVM;
import static weka.core.Utils.splitOptions;

/**
 *
 * @author filip
 */
public class MySupportVectorMachine extends ClassifierResults{
    LibSVM classifier;
    Instances dataInstances;
    String options = "-C 7 -K 2";  //Default gamma( -G ) = 1/number of attributes
  
    public MySupportVectorMachine(Instances data){
        dataInstances = data;
        try {
            classifier = new LibSVM();
            classifier.setOptions(splitOptions(options));
            classifier.buildClassifier(data);
            eval = new Evaluation(dataInstances);
        } catch (Exception ex) {
            System.out.println("THROWN " + ex.getMessage());
            Logger.getLogger(MySupportVectorMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void applyMethod(String method){ 
        try {
            
            List<Instances> datasets = new ArrayList<>();
               if(method.equals("cross-validation")){
                  eval.crossValidateModel(classifier, dataInstances, 4, new Random(1));
                  return;
               }else if(method.equals("test-set")){
                   Preprocessing preprocessTestSet = new Preprocessing(null);
                   datasets = preprocessTestSet.getDataSets(MyNaiveBayes.class.getResource("/data/categories-per-train.arff").getPath(), MyNaiveBayes.class.getResource("/data/2017-articles-correct.arff").getPath());
                  
               }else if(method.equals("percentage")){
                    Preprocessing preprocessTestSet = new Preprocessing(null);
                    datasets = preprocessTestSet.getDataSets(MyNaiveBayes.class.getResource("/data/categories-per-train.arff").getPath(), MyNaiveBayes.class.getResource("/data/categories-per-test.arff").getPath());
           
               }else{
                   return;
               }
              
                classifier = new LibSVM();
                classifier.setOptions(splitOptions(options));
                classifier.buildClassifier(datasets.get(0));
                eval = new Evaluation(datasets.get(0));
                eval.evaluateModel(classifier, datasets.get(1));     
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
