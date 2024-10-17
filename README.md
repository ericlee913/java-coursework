Data Management
Overview
This Java web application allows users to interact with patient data stored in CSV files. It includes functionality to view patient lists, search through patient data, visualize patient demographics, and export data as JSON. The project follows the Model-View-Controller (MVC) architecture.

Features:
Patient List View: Displays a list of patient IDs with hyperlinks. Clicking on an ID shows detailed information about that patient.
Search Function: Allows users to search for keywords in the patient data, returning all matches with relevant data.
Custom Patient Lists: Provides lists filtered by criteria such as dead/alive, gender, and age.
Data Visualization: Includes age distribution, gender proportion, and marital status graphs.
JSON Export: Exports the patient data into a JSON file.
How to Use:
Change CSV File:

Modify the file path in model.java. Change the path in the Model() constructor, specifically at dataloader.loadData(filePath) on line 21.
Example: filePath = "data/patients100.csv"
Run the Application:

Build the project using Maven.
Open your browser and go to http://localhost:8080/.
Main Page Actions:

View Patient List: Displays hyperlinks of patient IDs and surnames.
Search: Enter a keyword to see all matching data across patients.
Custom Lists: View filtered lists (e.g., dead, alive, male, female).
Graphs: View age distribution, gender, and marital status in graphical form.
Export JSON: Download all patient data as a JSON file.
Design & Process:
The application uses the MVC design pattern, with separate model, view, and controller components for clear separation of concerns.
Pros:
Well-implemented search functionality using Hashtable<String, ArrayList<String>> for displaying detailed results.
Use of JSP and Chart.js for visualizing patient data in graphs.
Cons:
Lacks advanced design and abstract class usage.
Some classes like Model have low cohesion, performing multiple tasks that could be refactored.
