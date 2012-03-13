/*  
    Represents a worker, a type of employee, 
    adding boss and department attributes
    
    Author: Nick Pink
*/

class Worker extends Employee {
    private String _boss;
    private String _department;
    
    // Default constructor
    public Worker() {
        super("Bill the Worker", 987125555, 50, MaritalStatus.DOMESTIC_PARTNER);
        initialize("Mr. Manager", "Retail");
    }
    
    // Parameterized constructor
    // boss - the worker's boss
    // department - the worker's department
    public Worker(
        String name, int ssn, double salary, MaritalStatus marital_status, 
        String boss, String department
    ) {
        super(name, ssn, salary, marital_status);
        initialize(boss, department);
    }
    
    // Initialization logic shared by constructors
    private void initialize(
        String boss, String department
    ) {
        _boss = boss;
        _department = department;
    }
    
    // Get boss
    public String boss() {
        return _boss;
    }
    
    // Get department
    public String department() {
        return _department;
    }
    
    public String toString() {
        return (
            super.toString() + 
            "\nBoss:\t\t\t" + _boss +
            "\nDepartment:\t\t" + _department
        );
    }
}