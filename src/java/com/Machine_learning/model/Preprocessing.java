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
import weka.core.tokenizers.WordTokenizer;
import weka.filters.Filter;
import weka.filters.UnsupervisedFilter;
import weka.filters.unsupervised.attribute.StringToWordVector;



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
                //WordTokenizer tokenizer = new WordTokenizer();  
                StringToWordVector filter = new StringToWordVector();
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
}
