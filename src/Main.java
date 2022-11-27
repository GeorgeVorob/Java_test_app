import java.awt.Container;
import java.util.Properties;

import javax.swing.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import gui.util.DateLabelFormatter;

public class Main {
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

        String[] jobStrings = { "1", "2", "3", "4", "5" };
        JComboBox jobSelectBox = new JComboBox(jobStrings);
        jobSelectBox.setSelectedIndex(4);
        jobSelectBox.setLocation(130, 80);
        jobSelectBox.setSize(200, 30);
        content.add(jobSelectBox);

        JButton authBtn = new JButton("Логин/регистрация");
        var authSize = authBtn.getPreferredSize();
        authBtn.setSize(authSize.width, authSize.height);
        authBtn.setLocation(120, 120);
        content.add(authBtn);

        frame.setVisible(true);
    }
}