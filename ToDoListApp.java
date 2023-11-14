import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoListApp {

    private JFrame frame;
    private JPanel panel;
    private JTextField textField;
    private JButton addButton;
    private JButton markCompletedButton;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private JTextField deleteTextField;
    private int counter = 1; // Counter for numbering tasks

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    ToDoListApp window = new ToDoListApp();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ToDoListApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("ToDo List App");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(44, 62, 80)); // Dark background

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int shadowGap = 5;
                int shadowOffset = 4;
                Color shadowColor = new Color(0, 0, 0, 150);
                g2d.setColor(shadowColor);
                g2d.fillRect(shadowOffset, shadowOffset, getWidth() - shadowGap - shadowOffset,
                        getHeight() - shadowGap - shadowOffset);
            }
        };
        panel.setLayout(null);
        panel.setBounds(10, 10, 564, 342);
        panel.setBackground(new Color(52, 73, 94)); // Panel background
        frame.getContentPane().add(panel);
        

        textField = new JTextField();
        textField.setBackground(new Color(52, 73, 94));
        textField.setBounds(60, 30, 300, 30);
        textField.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(textField);

        addButton = new JButton("Add");
        addButton.setBounds(400, 30, 100, 30);
        addButton.setBackground(new Color(34, 167, 240)); // Sky blue button color
        addButton.setForeground(Color.BLUE); // White text color
        addButton.setFocusPainted(false);
        addButton.setFont(new Font("Arial", Font.BOLD, 14)); // Bold and modern font
        panel.add(addButton);

        markCompletedButton = new JButton("Mark as Completed");
        markCompletedButton.setBounds(60, 320, 150, 30);
        markCompletedButton.setBackground(new Color(102, 204, 0)); // Lime green button color
        markCompletedButton.setForeground(Color.MAGENTA); // White text color
        markCompletedButton.setFocusPainted(false);
        markCompletedButton.setFont(new Font("Arial", Font.BOLD, 14)); // Bold and modern font
        panel.add(markCompletedButton);

        listModel = new DefaultListModel<>();
        
        list = new JList<>(listModel);
        list.setBounds(60, 80, 400, 230);
        list.setFont(new Font("Arial", Font.BOLD, 14));
        list.setOpaque(false); // Set list to be transparent
        list.setSelectionBackground(Color.CYAN); // Yellow selection background
        JScrollPane scrollPane = new JScrollPane(list) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(52, 73, 94); // Dark blue
                Color color2 = new Color(44, 62, 80); // Dark gray
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
                super.paintComponent(g);
            }
        };
        scrollPane.setBounds(60, 80, 400, 230);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listModel.addElement(counter + ". " + textField.getText());
                textField.setText("");
                counter++;
            }
        });

        markCompletedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Add custom HTML for displaying completed items with green color
                    listModel.set(selectedIndex, "<html><font color='green'>" + listModel.getElementAt(selectedIndex) + "</font></html>");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a task to mark as completed");
                }
            }
        });
    }
}
