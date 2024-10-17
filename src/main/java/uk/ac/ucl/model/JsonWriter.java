package uk.ac.ucl.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class JsonWriter {

    public String outPutJSON(DataFrame dataframe) throws JsonProcessingException {
        ArrayList<String> columnNames = dataframe.getColumnNames();
        ArrayList<Hashtable<String,String>> records = new ArrayList<>();
        ObjectMapper objectmapper = new ObjectMapper();

        for (int index = 0; index < dataframe.getRowCount("ID"); index++){
            Hashtable<String,String> data = new Hashtable<>();
            for (String column : columnNames){

                data.put(column,dataframe.getValue(column,index));
            }
            records.add(data);
        }

        Hashtable<String,ArrayList<Hashtable<String,String>>> HashtableJson = new Hashtable<>();
        HashtableJson.put("patients:", records);
        return objectmapper.writeValueAsString(HashtableJson);
    }
    
}
