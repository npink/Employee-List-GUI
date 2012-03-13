import java.awt.*;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

// Window for creating employees and adding them to the list
class CreateEmployeeView extends JFrame implements ActionListener {
    
    private ArrayList<Employee> _model;
    private EmployeeListView _parent_view;
    private Container _content;
    private JPanel _form;
    private JLabel _heading;
    private ButtonGroup _employee_type;
    private JPanel _title_panel, _bonus_panel,
        _manager_name_panel, _department_panel;
    private JRadioButton _worker;
    private JRadioButton _manager;
    private JTextField _name, _title, _ssn, 
        _salary, _bonus, _manager_name, _department;
    private ButtonGroup _marital_status;
    private JRadioButton _single, _married, _domestic;
    private JButton _create;
    
    // Initialize the window
    public CreateEmployeeView(ArrayList<Employee> model, EmployeeListView parent_view) {
        _model = model;
        _parent_view = parent_view;
        initFrame();
        initErrorLabel();
        initEmployeeTypeSelector();
        initName();
        initTitle();
        initSSN();
        initSalary();
        initBonus();
        initMaritalStatus();
        initManagerName();
        initDepartment();
        toggleWorkerType();
        initCreateButton();
    }
    
    /* Initalize the various components within the window */
    ////////////////////////////////////////////////////////
    private void initFrame() {
        _content = getContentPane();
        _content.setLayout(new FlowLayout());
        setTitle("Add Employee");
        setSize(600, 400);
        setVisible(true);
        setSize(601, 401);
        _form = new JPanel();
        _form.setLayout(new BoxLayout(_form, BoxLayout.Y_AXIS));
        _content.add(_form);
    }
    
    private void initErrorLabel() {
        _heading = new JLabel(" ", JLabel.LEFT);
        _form.add(_heading);
    }
    
    private void initEmployeeTypeSelector() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new JLabel("Employee Type:", JLabel.LEFT));
        _employee_type = new ButtonGroup();
        _worker = new JRadioButton("Worker", true);
        _manager = new JRadioButton("Manager");
        _worker.addActionListener(this);
        _manager.addActionListener(this);
        _worker.setActionCommand("worker");
        _manager.setActionCommand("manager");
        _employee_type.add(_worker);
        _employee_type.add(_manager);
        panel.add(_worker);
        panel.add(_manager);
        _form.add(panel);
    }
    
    private void initName() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new JLabel("Name:", JLabel.LEFT));
        _name = new JTextField();
        _name.setColumns(15);
        panel.add(_name);
        _form.add(panel);
    }
    
    private void initTitle() {
        _title_panel = new JPanel();
        _title_panel.setLayout(new BoxLayout(
            _title_panel, BoxLayout.X_AXIS)
        );
        _title_panel.add(new JLabel("Title:", JLabel.LEFT));
        _title = new JTextField();
        _title.setColumns(15);
        _title_panel.add(_title);
        _form.add(_title_panel);
    }
    
    private void initSSN() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new JLabel("SSN:", JLabel.LEFT));
        _ssn = new JTextField();
        _ssn.setColumns(15);
        panel.add(_ssn);
        _form.add(panel);
    }
    
    private void initSalary() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new JLabel("Salary:", JLabel.LEFT));
        _salary = new JTextField();
        _salary.setColumns(15);
        panel.add(_salary);
        _form.add(panel); 
    }
    
    private void initBonus() {
        _bonus_panel = new JPanel();
        _bonus_panel.setLayout(new BoxLayout(_bonus_panel, BoxLayout.X_AXIS));
        _bonus_panel.add(new JLabel("Bonus:", JLabel.LEFT));
        _bonus = new JTextField();
        _bonus.setColumns(15);
        _bonus_panel.add(_bonus);
        _form.add(_bonus_panel);
    }
    
    private void initMaritalStatus() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new JLabel("Marital Status:", JLabel.LEFT));
        _marital_status = new ButtonGroup();
        _single = new JRadioButton("Single", true);
        _married = new JRadioButton("Married");
        _domestic = new JRadioButton("Domestic Partner");
        _single.setActionCommand("single");
        _married.setActionCommand("married");
        _domestic.setActionCommand("domestic");
        _marital_status.add(_single);
        _marital_status.add(_married);
        _marital_status.add(_domestic);
        panel.add(_single);
        panel.add(_married);
        panel.add(_domestic);
        _form.add(panel);
    }
    
    private void initManagerName() {
        _manager_name_panel = new JPanel();
        _manager_name_panel.setLayout(new BoxLayout(_manager_name_panel, BoxLayout.X_AXIS));
        _manager_name_panel.add(new JLabel("Manager Name:", JLabel.LEFT));
        _manager_name = new JTextField();
        _manager_name.setColumns(15);
        _manager_name_panel.add(_manager_name);
        _form.add(_manager_name_panel);
    }
    
    private void initDepartment() {
        _department_panel = new JPanel();
        _department_panel.setLayout(new BoxLayout(_department_panel, BoxLayout.X_AXIS));
        _department_panel.add(new JLabel("Department:", JLabel.LEFT));
        _department = new JTextField();
        _department.setColumns(15);
        _department_panel.add(_department);
        _form.add(_department_panel);
    }
    
    private void initCreateButton() {
        _create = new JButton("Create employee");
        _create.addActionListener(this);
        _form.add(_create);
    }
    /* End initialization code */
    
    // Handle any actions occuring within the window
    public void actionPerformed(ActionEvent e) {
        Object action = e.getSource();
        if (action == _manager) {
            toggleManagerType();
        } 
        else if (action == _worker) {
            toggleWorkerType();
        }
        else if (action == _create) {
    
        }
    }
    
    // Collect the data from the form, create an Employee object
    // If creation succeeds, add employee to the list
    // Otherwise, display an error message
    // Obviously this is not sophisticated error handling or input validation
    private void addEmployee() {
        try {
            Employee new_guy;
            String name = _name.getText();
            int ssn = Integer.parseInt(_ssn.getText() );
            double salary = Double.parseDouble(_salary.getText() );
        
            Employee.MaritalStatus status;
            String selected = _marital_status.getSelection().getActionCommand();
            if (selected == "single") {
                status = Employee.MaritalStatus.SINGLE;
            }
            else if (selected == "married") {
                status = Employee.MaritalStatus.MARRIED;
            }
            else { // 'domestic'
                status = Employee.MaritalStatus.DOMESTIC_PARTNER;
            }
        
            String selected_type = _employee_type.getSelection().getActionCommand();
            if (selected_type == "worker") {
                String boss = _manager_name.getText();
                String department = _department.getText();
                new_guy = new Worker(
                    name, ssn, salary, status, 
                    boss, department
                );
            }
            else {  // 'manager'
                double bonus = Double.parseDouble(_bonus.getText() );
                String title = _title.getText();
                new_guy = new Manager(
                    name, ssn, salary, status, 
                    title, bonus
                );
            }
            _model.add(new_guy);
            Painter.repaint(_parent_view);
            _heading.setText("Employee was added.");
            _parent_view.disableSearch();
        }
        catch (Exception ex) {
            _heading.setText("Employee was not added to list.  Invalid data was entered.");
        }
    }
    
    // Show fields that only pertain
    // to creating a manager and hide worker fields
    private void toggleManagerType() {
        _title_panel.setVisible(true);
        _bonus_panel.setVisible(true);
        _manager_name_panel.setVisible(false);
        _department_panel.setVisible(false);
    }
    
    // Show fields that only pertain
    // to creating a worker and hide manager fields
    private void toggleWorkerType() {
        _title_panel.setVisible(false);
        _bonus_panel.setVisible(false);
        _manager_name_panel.setVisible(true);
        _department_panel.setVisible(true);
    }
}