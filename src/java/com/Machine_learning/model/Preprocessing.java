package com.Machine_learning.model;
import java.io.File;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.OptionHandler;
import weka.core.PropertyPath;
import weka.core.Stopwords;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.stopwords.StopwordsHandler;
import weka.core.tokenizers.CharacterDelimitedTokenizer;
import weka.core.tokenizers.NGramTokenizer;
import weka.core.tokenizers.Tokenizer;
import weka.filters.Filter;
import weka.filters.UnsupervisedFilter;
import weka.filters.unsupervised.attribute.StringToWordVector;



/**
 *
 * @author Jonas Tornfors Filip Rydberg
 */
public class Preprocessing extends CharacterDelimitedTokenizer implements UnsupervisedFilter, OptionHandler{
    String datasetPath;
    
    public Preprocessing(String path) throws Exception{
      this.datasetPath = path;          
    }
    

    public Instances getDataSet(){
        try
        {
            DataSource source = new DataSource(this.datasetPath);    
            Instances data = source.getDataSet();
            
            NGramTokenizer tokenizer = new NGramTokenizer();
            tokenizer.setNGramMinSize(2);
            tokenizer.setNGramMaxSize(2); 
            tokenizer.setDelimiters("[\\w+\\d+]");
            
            
            StringToWordVector filter = new StringToWordVector();
            filter.setStopwordsHandler(new MyStopWords());
            filter.setTokenizer(tokenizer);
            filter.setInputFormat(data);
            Instances filteredData = Filter.useFilter(data, filter);
            
            return filteredData;
        }catch(Exception e){
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String globalInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasMoreElements() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String nextElement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tokenize(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRevision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
