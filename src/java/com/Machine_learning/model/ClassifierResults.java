/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Machine_learning.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Evaluation;

/**
 *
 * @author filip
 */
public abstract class ClassifierResults {
      Evaluation eval;
    
      public String getResult(){
        try {
            return  eval.toSummaryString();
        } catch (Exception ex) {
            Logger.getLogger(MyNaiveBayes.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "Something went wrong ";
    }
    
    public void writeResultToFile(String filename){
        
        String root = ClassifierResults.class.getResource("/data/classificationResults/").getPath();
        
        File file = new File(root +filename+".txt");
        System.out.println("PATH ->" + file.getPath());
          try (FileWriter writer = new FileWriter(file.getPath())) {
                writer.write(eval.toSummaryString());
                writer.write(eval.toMatrixString());
                writer.write("F1-SCORE :" + eval.fMeasure(0));
                writer.flush();
                writer.close();
                
                System.out.println("...CREATED FILE");
            } catch (IOException ex) {
              Logger.getLogger(ClassifierResults.class.getName()).log(Level.SEVERE, null, ex);
          } catch (Exception ex) {
              Logger.getLogger(ClassifierResults.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
}
