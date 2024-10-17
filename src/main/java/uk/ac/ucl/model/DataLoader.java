package uk.ac.ucl.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataLoader {
    private DataFrame dataframe;

    public DataLoader() {
        this.dataframe = new DataFrame(new ArrayList<>());
    }

    public void loadData(String filePath) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String row;
        boolean isFirstRow = true;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",",-1);
            if (isFirstRow) {
                for (String columnName : data) {
                    dataframe.addColumn(columnName);
                }
                isFirstRow = false;
            } else {
                for (int i = 0; i < data.length; i++) {
                    String columnName = dataframe.getColumnNames().get(i);
                    dataframe.addValue(columnName, data[i]);
                }
            }
        }
        csvReader.close();
    }

    public DataFrame getDataFrame() {
        return this.dataframe;
    }

}
