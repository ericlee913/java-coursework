package uk.ac.ucl.model;

import java.util.ArrayList;

public class DataFrame {
    private ArrayList<Column> data;

    public DataFrame (ArrayList<Column> data){
        this.data = data;
    }

    public void addColumn(String columnName) {
        for (Column existingColumn : data){
            if (existingColumn.getName().equals(columnName)) {
                System.out.println("Column '" + columnName + "' already exists.");
                return;}
        }
        Column newColumn = new Column(columnName, new ArrayList<>());
        data.add(newColumn);
    }

    public ArrayList<String> getColumnNames(){
        ArrayList<String> allnames = new ArrayList<>() ;
        for (Column existingColumn : data){
            allnames.add(existingColumn.getName());
        }
        return allnames;
    }

    public int getRowCount(String columnName) {
        for (Column existingColumn : data) {
            if (existingColumn.getName().equals(columnName)) {
                return existingColumn.getSize();
            }
        }
        throw new IllegalArgumentException("Column " + columnName + " not found.");
    }

    public String getValue(String columnName, int row){
        for (Column existingColumn : data) {
            if (existingColumn.getName().equals(columnName)) {
                if (row >= 0 && row < existingColumn.getSize()) {
                    return existingColumn.getRowValue(row);
                } else {
                    throw new IndexOutOfBoundsException("Invalid index");
                }
            }
        }
        throw new IllegalArgumentException("Column " + columnName + " not found.");
    }

    public String putValue(String columnName, int row, String value){
        for (Column existingColumn : data) {
            if (existingColumn.getName().equals(columnName)) {
                if (row >= 0 && row < existingColumn.getSize()) {
                    return existingColumn.setRowValue(row, value);
                }
                else {
                    throw new IndexOutOfBoundsException("Invalid index");
                }
            }
        }
        throw new IllegalArgumentException("Column '" + columnName + "' not found.");

    }

    public void addValue(String columnName, String value){
        for (Column existingColumn : data) {
            if (existingColumn.getName().equals(columnName)) {
                existingColumn.addRowValue(value);
                return;
            }
        }
        throw new IllegalArgumentException("Column '" + columnName + "' not found.");
    }

}
