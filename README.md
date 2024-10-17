# Java Web Application for Patient Data Management

## Overview

This Java web application allows users to interact with patient data stored in CSV files. It includes functionality to view patient lists, search through patient data, visualize patient demographics, and export data as JSON. The project follows the Model-View-Controller (MVC) architecture.

### Features:

1. **Patient List View**: Displays a list of patient IDs with hyperlinks. Clicking on an ID shows detailed information about that patient.
2. **Search Function**: Allows users to search for keywords in the patient data, returning all matches with relevant data.
3. **Custom Patient Lists**: Provides lists filtered by criteria such as dead/alive, gender, and age.
4. **Data Visualization**: Includes age distribution, gender proportion, and marital status graphs.
5. **JSON Export**: Exports the patient data into a JSON file.

## How to Use

### 1. Change CSV File

To modify the CSV file path, update the following line in the `model.java` file:

```java
dataloader.loadData(filePath);  // Update the filePath string
```
