package com.Machine_learning.model;
import java.io.File;
import weka.core.Instances;
import weka.core.OptionHandler;
import weka.core.PropertyPath;
import weka.core.Stopwords;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.stopwords.StopwordsHandler;
import weka.filters.Filter;
import weka.filters.UnsupervisedFilter;
import weka.filters.unsupervised.attribute.StringToWordVector;



/**
 *
 * @author Jonas Tornfors Filip Rydberg
 */
public class Preprocessing extends Filter implements UnsupervisedFilter, OptionHandler{
    String datasetPath;
    
    public Preprocessing(String path) throws Exception{
      this.datasetPath = path;          
    }
    
    
    public Instances getDataSet(){
        try
        {
            DataSource source = new DataSource(this.datasetPath);
          
            Instances data = source.getDataSet();
            
            StringToWordVector stwv = new StringToWordVector();
            stwv.setStopwordsHandler(new MyStopWords());
            //data = Filter.useFilter(data, stwv);
            if (data.classIndex() == -1)
            {
              data.setClassIndex(data.numAttributes() - 1);
            }
            return data;
        }catch(Exception e){
            throw new UnsupportedOperationException();
        }
    }
}
