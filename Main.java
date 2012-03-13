/*
    Test the EmployeeListGUI application by loading sample data

    To compile and run at the command line:
    javac -d ~/tmp/bin Main.java EmployeeListGUI.java EmployeeListView.java CreateEmployeeView.java Employee.java Manager.java Worker.java && java -cp ~/tmp/bin Main

Author: Nick Pink

*/

import java.util.ArrayList;

class Main {
    
    public static void main(String[] args) {
        new EmployeeListGUI(makeDummyList());
    }
    
    // Create test employee list to pass to the application
    private static ArrayList<Employee> makeDummyList() {
        ArrayList<Employee> team = new ArrayList<Employee>();
        team.add(
            new Employee()
        );
        team.add(
            new Employee("Nick", 987654321, 1000, Employee.MaritalStatus.MARRIED)
        );
        team.add(
            new Manager()
        );
        team.add(
            new Worker()
        );
        return team;
    }
}