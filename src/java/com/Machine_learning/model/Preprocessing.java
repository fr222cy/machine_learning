package com.Machine_learning.model;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.OptionHandler;
import weka.core.PropertyPath;
import weka.core.Stopwords;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.stopwords.Rainbow;
import weka.core.stopwords.StopwordsHandler;
import weka.core.tokenizers.CharacterDelimitedTokenizer;
import weka.core.tokenizers.NGramTokenizer;
import weka.core.tokenizers.Tokenizer;
import weka.core.tokenizers.WordTokenizer;
import weka.filters.Filter;
import weka.filters.UnsupervisedFilter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.classifiers.functions.LibSVM;


/**
 *
 * @author Jonas Tornfors Filip Rydberg
 */
public class Preprocessing {
    String datasetPath;
    
    public Preprocessing(String path) throws Exception{
      this.datasetPath = path;          
    }
    

    public Instances getDataSet(boolean shouldPreprocess){
        try
        {
            DataSource source = new DataSource(this.datasetPath);    
            Instances data = source.getDataSet();
            if(shouldPreprocess){
                WordTokenizer tokenizer = new WordTokenizer();
                Rainbow rainbow =  new Rainbow();
                StringToWordVector filter = new StringToWordVector();
                filter.setStopwordsHandler(rainbow);
                filter.setTokenizer(tokenizer);
                filter.setInputFormat(data);
                data = Filter.useFilter(data, filter);
            }
            
            if(data.classIndex() == -1){
                data.setClassIndex(0);
            }
            return data;
        }catch(Exception e){
            System.out.println("Error while preprocessing:" + e.getMessage());
            throw new UnsupportedOperationException();
        }
    }
    
    public List<Instances> getDataSets(String trainPath, String testPath){
        DataSource train;   
        DataSource test; 
        List<Instances> instances = new ArrayList<>();
        try {
            train = new DataSource(trainPath);
            test = new DataSource(testPath);
        
            Instances trainSet = train.getDataSet();
            Instances testSet = test.getDataSet();
          WordTokenizer tokenizer = new WordTokenizer();
                Rainbow rainbow =  new Rainbow();
                StringToWordVector filter = new StringToWordVector();
                filter.setStopwordsHandler(rainbow);
                filter.setTokenizer(tokenizer);
                filter.setInputFormat(trainSet);
                instances.add(Filter.useFilter(trainSet, filter));
                instances.add(Filter.useFilter(testSet, filter));
                instances.get(0).setClassIndex(0);
                instances.get(1).setClassIndex(0);
                
                return instances;
                
        } catch (Exception ex) {
            Logger.getLogger(Preprocessing.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
