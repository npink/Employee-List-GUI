/*
    This application allows a user to manage a list of employees.  The user
    can add employees, search by name, and sort the list.  Different data is collected
    depending on whether the employee is a worker or manager.
    
    To compile and run at the command line:
        javac -d ~/tmp/bin EmployeeListGUI.java EmployeeListView.java CreateEmployeeView.java Employee.java Manager.java Worker.java && java -cp ~/tmp/bin EmployeeListGUI
    
    Author: Nick Pink
*/

import java.util.ArrayList;

class EmployeeListGUI {
    
    // Constructor for the application
    public EmployeeListGUI(ArrayList<Employee> list) {
        EmployeeListView view = new EmployeeListView(list);
    }
    
}