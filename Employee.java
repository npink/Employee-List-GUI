/*  
    Represents an employee, storing his/her name, SSN,
    salary, and marital status.
    
    Author: Nick Pink
*/

import java.text.DecimalFormat;

class Employee implements Cloneable, Comparable {
    // Total number of objects instantiated
    private static int _total = 0;
    
    private String _name;
    private int _ssn;
    private double _salary;
    private MaritalStatus _marital_status;
    // Can only be set to one of these values
    public enum MaritalStatus {
        SINGLE, MARRIED, DOMESTIC_PARTNER
    }
    
    // Used to format currency data
    protected static DecimalFormat us_dollar_format = new DecimalFormat("$###,###.##");
    
    // Default constructor
    public Employee() {
        initialize("John Doe", 123456789, 1000000.00, MaritalStatus.SINGLE);
    }
    
    // Parameterized constructor
    // name - full name, cannot be blank
    // ssn - 9-digit social security number
    // salary - must be greater than zero
    // marital_status - must be either single, married, or domestic partner
    public Employee(
        String name, 
        int ssn, 
        double salary, 
        MaritalStatus marital_status
    ) {
        initialize(name, ssn, salary, marital_status);
    }
    
    // Logic shared by constructors
    private void initialize(
        String name, 
        int ssn, 
        double salary, 
        MaritalStatus marital_status
    ) {
        name(name);
        salary(salary);
        ssn(ssn);
        _marital_status = marital_status;
        _total++;
    }
    
    // Required by Cloneable interface
    public Employee clone() throws CloneNotSupportedException {
        return (Employee)super.clone();
    }
    
    // Required by Comparable interface
    public int compareTo(Object other_emp) {
        String name = ((Employee) other_emp).name();
        return _name.compareToIgnoreCase(name);
    }
    
    // Check if this employee is the name person as another
    // Returns true if SSN is the same
    public boolean equals(Object obj) {
        int ssn = ((Employee) obj).ssn();
        return _ssn == ssn;
    }
    
    /*------------ Static methods --------------*/
    
    // Gets the total number of Employees instantiated
    // Returns int
    public static int total() {
        return _total;
    }
    
    /*------------ Instance methods ------------*/
    
    // Set employee name
    public void name(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name must be not be an empty.");
        }
        _name = name;
    }
    
    // Get name
    public String name() {
        return _name;
    }
    
    // Get SSN
    public int ssn() {
        return _ssn;
    }
    
    // Set social security number
    private void ssn(int ssn) {
        if (ssn < 0 || String.valueOf(ssn).length() != 9) {
            throw new IllegalArgumentException(
                "Social security number must be a 9 digit positive number."
            );
        }
        _ssn = ssn;
    }
    
    // Set salary
    private void salary(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Salary must be a decimal greater than zero."
            );
        }
        _salary = amount;
    }
    
    // Get salary
    public double salary() {
        return _salary;
    }
    
    // Get marital status
    public MaritalStatus marital_status() {
        return _marital_status;
    }
    
    // Adds specified amount to salary
    // amount - amount to add to salary, must be a double greater than 0
    // Return void
    public void raise(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount of raise must be greater than 0.");    
        }
        _salary += amount;
    }
    
    // Creates a String representation of the instance data
    // Returns String
    public String toString() {
        return (
            "Name:\t\t\t" + _name +
            "\nSSN:\t\t\t" + _ssn +
            "\nSalary:\t\t\t" + us_dollar_format.format(_salary) +
            "\nMarital status:\t\t" + _marital_status
        );
    }
}