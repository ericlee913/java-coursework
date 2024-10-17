package uk.ac.ucl.model;

import java.util.ArrayList;
public class Column {
    private String name;
    private ArrayList<String> rows;

    public Column(String name, ArrayList<String> rows){
        this.name = name;
        this.rows = rows;
    }

    public String getName(){ return name;}

    public int getSize() { return rows.size();}

    public String getRowValue(int i) {
        if (i >= 0 && i <rows.size()){
            return rows.get(i);
        }
        else {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    public String setRowValue(int i, String data){
        if (i >= 0 && i<rows.size()){
            return rows.set(i,data);
        }
        else {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    public void addRowValue(String data) {
        rows.add(data);
    }

}
