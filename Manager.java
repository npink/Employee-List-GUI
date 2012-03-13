/*  
    Represents a manager, a type of employee, 
    additionally storing title and bonus
    
    Author: Nick Pink
*/

class Manager extends Employee {
    private String _title;
    private double _bonus;
    
    // Default constructor
    public Manager() {
        super("Mr. Manager", 777777777, 100000, MaritalStatus.DOMESTIC_PARTNER);
        initialize("Overlord", 87500);
    }
    
    // Parameterized constructor
    // title - manager's title
    // bonus - dollar amount of manager's bonus, must be zero or greater
    public Manager(
        String name, int ssn, double salary, MaritalStatus marital_status, 
        String title, double bonus
    ) {
        super(name, ssn, salary, marital_status);
        initialize(title, bonus);
    }
    
    // Logic shared by constructors
    private void initialize( 
        String title, double bonus
    ) {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("'title' must not be empty");
        }
        if (bonus < 0) {
            throw new IllegalArgumentException("'bonus' must not be negative");
        }
        
        _title = title;
        _bonus = bonus;
    }
    
    // Get title
    public String title() {
        return _title;
    }
    
    // Get bonus
    public double bonus() {
        return _bonus;
    }
    
    public String toString() {
        return (
            super.toString() + 
            "\nTitle:\t\t\t" + _title +
            "\nBonus:\t\t\t" + us_dollar_format.format(_bonus)
        );
    }
}