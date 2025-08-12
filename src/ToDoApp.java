import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ToDoApp {


    private ArrayList<String> tasks = new ArrayList<>();
    private DefaultListModel<String> taskListModel = new DefaultListModel<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoApp().createAndShowGUI());
    }

    private void createAndShowGUI() {

        JFrame frame = new JFrame("To-Do List App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JTextField taskField = new JTextField(20);
        JButton addButton = new JButton("Add");
        topPanel.add(taskField);
        topPanel.add(addButton);

        JList<String> taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        JPanel bottomPanel = new JPanel();
        JButton deleteButton = new JButton("Delete");
        bottomPanel.add(deleteButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                tasks.add(task);
                updateTaskList();
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter a task.");
            }
        });

        taskField.addActionListener(e -> addButton.doClick());

        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                tasks.remove(selectedIndex);
                updateTaskList();
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a task to delete.");
            }
        });
        frame.setVisible(true);
    }

    private void updateTaskList() {
        taskListModel.clear();
        for (int i = 0; i < tasks.size(); i++) {
            taskListModel.addElement((i + 1) + ". " + tasks.get(i));
        }
    }
}
