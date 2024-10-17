package uk.ac.ucl.model;

import java.text.DecimalFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Model
{
  private DataFrame dataframe;
  private DataLoader dataloader;


  public Model() throws IOException {
    this.dataloader = new DataLoader();
    dataloader.loadData("data/patients100.csv");
    dataframe = dataloader.getDataFrame();
  }

  public DataFrame getDataframe() {
    return dataframe;
  }

  public Hashtable<String, String> getPatientList() {
    Hashtable<String, String> patients = new Hashtable<String, String>();
    for (int i = 0; i < dataframe.getRowCount("ID"); i++) {
        String id = dataframe.getValue("ID", i);
        String lastName = dataframe.getValue("LAST", i);
        patients.put(id, lastName);
    }
    return patients;
}

  public ArrayList<String> getPatientData(String id){
    ArrayList<String> datatypes = dataframe.getColumnNames();
    ArrayList<String> patientdata = new ArrayList<String>();

    // Find the index of the patient with the given ID
    int index = -1;
    for (int i = 0; i < dataframe.getRowCount("ID"); i++) {
      if (dataframe.getValue("ID", i).equals(id)) {
        index = i;
        break;
      }
    }

    // If the patient was found, retrieve their data
    if (index != -1) {
      for (String data : datatypes){
        if (dataframe.getValue(data, index).isEmpty()){
          patientdata.add(data + ":  NIL");
        }
        else{
          patientdata.add(data + ":  " + dataframe.getValue(data, index));
        }
      }
    }

    return patientdata;
  }


  public Hashtable<String,ArrayList<String>> searchFor(String keyword)
  {
    Hashtable<String,ArrayList<String>> output = new Hashtable<>();
    ArrayList<String> datatypes = dataframe.getColumnNames();
    for (String datat : datatypes) {
      for (int i = 0; i < dataframe.getRowCount(datat); i++) {
          if (dataframe.getValue(datat, i).contains(keyword)) {
              String id = dataframe.getValue("ID", i);
              // If the ID is already in the output, add to its ArrayList
              if (output.containsKey(id)) {
                  output.get(id).add(dataframe.getValue(datat, i));
              }
              // Otherwise, create a new ArrayList for this ID
              else {
                  ArrayList<String> matches = new ArrayList<>();
                  matches.add(dataframe.getValue(datat, i));
                  output.put(id, matches);
              }
          }
      }
  }

  return output;
  }

  //This method outputs hashtable of dead patients
  public Hashtable<String,String> deceasedPatients(){
    Hashtable<String, String> deceasedpatients = new Hashtable<String, String>();
    for (int i = 0; i < dataframe.getRowCount("ID"); i++) {
      if (!dataframe.getValue("DEATHDATE", i).isEmpty()) {
        String id = dataframe.getValue("ID", i);
        String lastName = dataframe.getValue("LAST", i);
        deceasedpatients.put(id, lastName);
      }
    }
    return deceasedpatients;
  }

  //This method outputs hashtable of alivepatients
  public Hashtable<String,String> alivePatients(){
    Hashtable<String, String> Alivepatients = new Hashtable<String, String>();
    for (int i = 0; i < dataframe.getRowCount("ID"); i++) {
      if (dataframe.getValue("DEATHDATE", i).isEmpty()) {
        String id = dataframe.getValue("ID", i);
        String lastName = dataframe.getValue("LAST", i);
        Alivepatients.put(id, lastName);
      }
    }
    return Alivepatients;
  }

  //This method returns a List of patients in order of their age in float
  public List<List<String>> oldestToYoungest() {
    List<List<String>> idsAndDob = new ArrayList<>();

    for(int rowIndex = 0; rowIndex < dataframe.getRowCount("ID");rowIndex++)
    {
      if (dataframe.getValue("DEATHDATE", rowIndex).isEmpty()) {
        idsAndDob.add(List.of(dataframe.getValue("ID", rowIndex), String.valueOf((dataframe.getValue("BIRTHDATE", rowIndex)))));
      }
    }
    idsAndDob.sort((record1, record2) -> record1.get(1).compareTo(record2.get(1)));

    List<List<String>> idsAndAge = new ArrayList<>();

    for(int index = 0; index < idsAndDob.size(); index++)
    {
      idsAndAge.add(List.of(idsAndDob.get(index).getFirst(),String.valueOf(getAge(idsAndDob.get(index).get(1)))));
    }

    return idsAndAge;
  
    }

    //This method calculates the age of the patient using their birthdate
  private Float getAge(String birthdate) {
    LocalDate birthdateLocal = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    LocalDate now = LocalDate.now();

    Period diff = Period.between(birthdateLocal,now);

    float age = diff.getYears() + ((float)diff.getMonths()/12) + ((float)diff.getDays()/365);
    DecimalFormat df = new DecimalFormat("#.#");
    age = Float.parseFloat(df.format(age));
    return age;
  }

  //This method outputs hashtable of users of specific gender
  public Hashtable<String,String> patientListbyGender(String gender){
    Hashtable<String, String> patientListbyGender = new Hashtable<String, String>();
    if (gender.equals("Male")) {
      for (int i = 0; i < dataframe.getRowCount("ID"); i++) {
        if ((dataframe.getValue("GENDER", i)).equals("M")) {
          String id = dataframe.getValue("ID", i);
          String lastName = dataframe.getValue("LAST", i);
          patientListbyGender.put(id, lastName);
        }
      }
    }
    else if (gender.equals("Female")) {
      for (int i = 0; i < dataframe.getRowCount("ID"); i++) {
        if ((dataframe.getValue("GENDER", i)).equals("F")) {
          String id = dataframe.getValue("ID", i);
          String lastName = dataframe.getValue("LAST", i);
          patientListbyGender.put(id, lastName);
        }
      }
    }

    return patientListbyGender;
  }

  //This method outputs a list of age distribution in 5 age category, 0-20, 20-40, 40-60, 60-80, 80+
  public ArrayList<Integer> getAgeDistribution (){
    int zeroToTwenty = 0;
    int twentyToFourty = 0;
    int fourtyToSixty = 0;
    int sixtyToEighty = 0;
    int eightyplus = 0;
    ArrayList<Integer> counter = new ArrayList<>(Arrays.asList(zeroToTwenty,twentyToFourty,fourtyToSixty,sixtyToEighty,eightyplus));
    for(int Index = 0; Index < dataframe.getRowCount("ID"); Index++)
    {
      if (dataframe.getValue("DEATHDATE", Index).isEmpty()) {
        Float age = getAge(dataframe.getValue("BIRTHDATE",Index));
        if (age < 20) {
          counter.set(0, counter.get(0) + 1);
        } else if (age < 40) {
          counter.set(1, counter.get(1) + 1);
        } else if (age < 60) {
          counter.set(2, counter.get(2) + 1);
        } else if (age < 80) {
          counter.set(3, counter.get(3) + 1);
        } else {
          counter.set(4, counter.get(4) + 1);
        }
        
      }
    }
    return counter;

  }

  //This method outputs a list of number of male patient and female patient
  public ArrayList<Integer> getGender (){
    int male = 0;
    int female = 0;
    ArrayList<Integer> counter = new ArrayList<>(Arrays.asList(male,female));
    for(int Index = 0; Index < dataframe.getRowCount("ID"); Index++)
    {
      String gender = dataframe.getValue("GENDER",Index);
      if (gender.equals("M")) {
        counter.set(0, counter.get(0) + 1);
      } else {
        counter.set(1, counter.get(1) + 1);
      }

    }

    return counter;

  }


  //This method outputs a list of number of married,disclosed,single patients
  public ArrayList<Integer> getMarital (){
    int married = 0;
    int single = 0;
    int notDisclosed =0;
    ArrayList<Integer> counter = new ArrayList<>(Arrays.asList(married,single,notDisclosed));
    for(int Index = 0; Index < dataframe.getRowCount("ID"); Index++)
    {
      String status = dataframe.getValue("MARITAL",Index);
      if (status.equals("M")) {
        counter.set(0, counter.get(0) + 1);
      } else if(status.equals("S")){
        counter.set(1, counter.get(1) + 1);
      }
      else{
        counter.set(2, counter.get(2) + 1);
      }

    }

    return counter;

  }

}


