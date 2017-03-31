/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Machine_learning.model;

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
public class MySupportVectorMachine {
    LibSVM svm;
    Instances dataInstances;
    Evaluation eval;
    public MySupportVectorMachine(Instances data){
        dataInstances = data;
        try {
            eval = new Evaluation(dataInstances);
            String options = "-C 7 -K 2"; //Default gamma( -G ) = 1/number of attributes
            svm = new LibSVM();
            svm.setOptions(splitOptions(options));
            svm.buildClassifier(data);
        } catch (Exception ex) {
            Logger.getLogger(MySupportVectorMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void applyMethod(String method){
        try {
            if(method.equals("cross-validation"))
            {
                eval.crossValidateModel(svm, dataInstances, 4, new Random(1));
            }else if(method.equals("test-set")) {
                
            }else{
                
            }
        } catch (Exception ex) {
            Logger.getLogger(MySupportVectorMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getResult(){
        try {
            return  eval.toSummaryString();
        } catch (Exception ex) {
            Logger.getLogger(MyNaiveBayes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Something went wrong!(svm)";
    }
}
