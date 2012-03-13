import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;

class EmployeeListView extends JFrame implements ActionListener {
    
    private ArrayList<Employee> _model;
    private Employee compare_to;
    private Container _content;
    private JButton _add_button, _sort_button, _search_button;
    private JLabel _status;
    private JFrame _add_dialog;
    private JTable _table;
    private JScrollPane _scrollpane;
    private String[] columns = {
        "Name",
        "Title",
        "Salary",
        "Bonus",
        "SSN",
        "Marital Status",
        "Boss",
        "Department"
    };
    
    // Window constructor
    public EmployeeListView(ArrayList<Employee> model) {
        _model = model;
        compare_to = new Employee();
        initFrame();
        initButtons();
        initTable();
        validate(); // Causes component to redraw
    }
    
    // Initialize window
    private void initFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        _content = getContentPane();
        _content.setLayout(new BoxLayout(_content, BoxLayout.Y_AXIS));
        setSize(850, 400);
        setTitle("Employee List Manager");
        setVisible(true);
    }
    
    // Initialize buttons
    private void initButtons() {
        JPanel panel = new JPanel();
        _add_button = new JButton("Add Employee...");
        _add_button.addActionListener(this);
        panel.add(_add_button);
        
        _sort_button = new JButton("Sort list");
        _sort_button.addActionListener(this);
        panel.add(_sort_button);
        
        _search_button = new JButton("Find by name...");
        _search_button.addActionListener(this);
        _search_button.setEnabled(false);
        panel.add(_search_button);
        
        _content.add(panel);
        
        _status = new JLabel(" ");
        _content.add(_status);
    }
    
    // Initialize the table that displays the employee list
    private void initTable() {
        TableModel dataModel = new AbstractTableModel() {
            public String getColumnName(int col) {
                return columns[col];
            }
            public int getColumnCount() { return columns.length; }
            public int getRowCount() { return _model.size();}
            public Object getValueAt(int row, int col) {
                Employee employee = _model.get(row);
                Object value = "";
                switch (col) {
                    case 0:  value = employee.name();
                        break;
                    case 1: 
                        if (employee instanceof Manager) {
                            value = ((Manager)employee).title();
                        }
                        break;
                    case 2: value = employee.salary();
                        break;
                    case 3: 
                        if (employee instanceof Manager) {
                            value = ((Manager)employee).bonus();
                        }
                        break;
                    case 4: value = employee.ssn();
                        break;
                    case 5: value = employee.marital_status();
                        break;
                    case 6: 
                        if (employee instanceof Worker) {
                            value = ((Worker)employee).boss();
                        }
                        break;
                    case 7: 
                        if (employee instanceof Worker) {
                            value = ((Worker)employee).department();
                        }
                        break;
                }
                return value;
            }
        };
        
        _table = new JTable(dataModel);
        _scrollpane = new JScrollPane(_table);
        _content.add(_scrollpane);
    }
    
    // Handle button clicks within the window
    public void actionPerformed(ActionEvent e) {
        
        JButton pressed = (JButton)e.getSource();
        
        if (pressed.isEnabled()) {
            if (pressed == _add_button) {
                _add_dialog = new CreateEmployeeView(_model, this);
            }
            else if (pressed == _sort_button) {
                Collections.sort(_model);
                _search_button.setEnabled(true);
                Painter.repaint(this);
            }
            else if (pressed == _search_button) {
                String input = JOptionPane.showInputDialog("Enter name to search for:");
                if (input != null && input != "") {
                    compare_to.name(input);
                    int index = Collections.binarySearch((java.util.List)_model, compare_to);
                    if (index >= 0) {
                        ListSelectionModel selectionModel = _table.getSelectionModel();
                        selectionModel.setSelectionInterval(index, index);
                        _status.setText("Employee found.");
                        Painter.repaint(this);
                    } else {
                        _status.setText("Employee not found.");
                    }
                }
            }
        }
    }
    
    // Allows the window for creating employees to disable search in this window
    // Binary search can only be done on a sorted list and we cannot assume the list is
    // sorted after an employee has been added
    public void disableSearch() {
        _search_button.setEnabled(false);
    }

}