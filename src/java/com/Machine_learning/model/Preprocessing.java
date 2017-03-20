package com.Machine_learning.model;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author Jonas Tornfors Filip Rydberg
 */
public class Preprocessing {
    public Instances getDataSet() throws Exception{
        try
        {
            DataSource source = new DataSource("lel");
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
