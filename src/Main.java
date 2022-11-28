import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import data.models.Job;
import data.models.User;
import gui.util.DateLabelFormatter;
import services.DbService;

public class Main {

    private static DbService db = new DbService();

    private static void UpdateTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        List<User> users = db.GetUsers();
        for (User user : users) {
            model.insertRow(0, new Object[] {
                    user.id, user.name, user.job, user.BirthDate.toString()
            });
        }
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);

        Container content = frame.getContentPane();
        content.setLayout(null);

        JLabel nameLabel = new JLabel("ФИО");
        nameLabel.setSize(100, 20);
        nameLabel.setLocation(20, 20);
        content.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setSize(150, 20);
        nameField.setLocation(50, 20);
        content.add(nameField);

        JLabel dateLabel = new JLabel("Дата рождения");
        dateLabel.setSize(100, 20);
        dateLabel.setLocation(20, 50);
        content.add(dateLabel);

        UtilDateModel model = new UtilDateModel();
        model.setDate(2022, 11, 28);
        model.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setLocation(130, 50);
        datePicker.setSize(200, 40);
        content.add(datePicker);

        JLabel jobLabel = new JLabel("Должность");
        jobLabel.setSize(100, 20);
        jobLabel.setLocation(60, 90);
        content.add(jobLabel);

        List<Job> jobs = db.GetJobs();
        List<String> jobSelectStrings = new ArrayList<>();
        for (Job job : jobs) {
            jobSelectStrings.add(job.name);
        }

        JComboBox jobSelectBox = new JComboBox(jobSelectStrings.toArray());
        jobSelectBox.setLocation(130, 80);
        jobSelectBox.setSize(200, 30);
        content.add(jobSelectBox);

        JButton authBtn = new JButton("Логин/регистрация");
        var authSize = authBtn.getPreferredSize();
        authBtn.setSize(authSize.width, authSize.height);
        authBtn.setLocation(120, 120);
        content.add(authBtn);

        JLabel messageLabel = new JLabel("...");
        messageLabel.setSize(100, 20);
        messageLabel.setLocation(10, 150);
        content.add(messageLabel);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("ФИО");
        tableModel.addColumn("Должность");
        tableModel.addColumn("Дата рождения");
        JTable table = new JTable(tableModel);
        table.setSize(450, 500);
        table.setLocation(400, 10);
        content.add(table);

        UpdateTable(table);

        frame.setVisible(true);
    }
}