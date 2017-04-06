/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Machine_learning.model;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.classifiers.functions.LibSVM;
import static weka.core.Utils.splitOptions;

/**
 *
 * @author filip
 */
public class MySupportVectorMachine{
    LibSVM classifier;
    Instances dataInstances;
    Evaluation eval;
    String options = "-C 7 -K 2";  //Default gamma( -G ) = 1/number of attributes
  
    public MySupportVectorMachine(Instances data){
        dataInstances = data;
        try {
            eval = new Evaluation(dataInstances);
            classifier = new LibSVM();
            classifier.setOptions(splitOptions(options));
            classifier.buildClassifier(data);
        } catch (Exception ex) {
            Logger.getLogger(MySupportVectorMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void applyMethod(String method){ 
        try {
               if(method.equals("cross-validation")){
                  eval.crossValidateModel(classifier, dataInstances, 4, new Random(1));
               }else if(method.equals("test-set")){
                   Preprocessing preprocessTestSet = new Preprocessing(null);
                   List<Instances> datasets = preprocessTestSet.getDataSets(MyNaiveBayes.class.getResource("/data/categories.arff").getPath(), MyNaiveBayes.class.getResource("/data/2017-articles.arff").getPath());
                   classifier = new LibSVM();
                   classifier.setOptions(splitOptions(options));
                   classifier.buildClassifier(datasets.get(0));
                   eval = new Evaluation(datasets.get(0));
                   eval.evaluateModel(classifier, datasets.get(1));
               }else if(method.equals("percentage")){
                   int trainSize = (int) Math.round(dataInstances.numInstances() * 0.66);
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
    
    public String getResult(){
        try {
            return  eval.toSummaryString();
        } catch (Exception ex) {
            Logger.getLogger(MyNaiveBayes.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "Something went wrong (NB)";
    }
}
