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
      System.out.println("DATASET PATH"+this.datasetPath);
        String[] options = new String[1];
        options[0] = "asdf";
        applyFilter(options);
    }
    
    public void applyFilter(String[] options) throws Exception{
     
     Stopwords sw = new Stopwords();
     sw.read(new File("/com/Machine_learning/data/stop-word-list.txt"));
   
    }
    
    public Instances getDataSet() throws Exception{
        try
        {
            DataSource source = new DataSource(this.datasetPath);
            System.out.println("DATASET SOURCE"+source);
            Instances data = source.getDataSet();
            if (data.classIndex() == -1)
            {
              data.setClassIndex(data.numAttributes() - 1);
            }
            return data;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
