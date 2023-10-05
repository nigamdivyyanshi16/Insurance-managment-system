import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class InsuranceSystem extends Applet implements ActionListener {

    private Label nameLabel, ageLabel, genderLabel, policyTypeLabel, policyAmountLabel;
    private TextField nameField, ageField, policyAmountField;
    private Choice genderChoice, policyTypeChoice;
    private Button submitButton, clearButton, saveButton, loadButton;
    private TextArea outputArea;
    private String[] policyTypes = { "Life Insurance", "Vehicle Insurance", "Home Insurance" };
    private String[] genders = { "Male", "Female", "Other" };
    private String filename = "insurance_data.txt";

    public void init() {
        
        nameLabel = new Label("Name:");
        ageLabel = new Label("Age:");
        genderLabel = new Label("Gender:");
        policyTypeLabel = new Label("Policy Type:");
        policyAmountLabel = new Label("Policy Amount:");
        
        nameField = new TextField(20);
        ageField = new TextField(3);
        policyAmountField = new TextField(10);
        
        genderChoice = new Choice();
        for (int i = 0; i < genders.length; i++) {
            genderChoice.add(genders[i]);
        }
        
        policyTypeChoice = new Choice();
        for (int i = 0; i < policyTypes.length; i++) {
            policyTypeChoice.add(policyTypes[i]);
        }
        
        submitButton = new Button("Submit");
        submitButton.addActionListener(this);
        
        clearButton = new Button("Clear");
        clearButton.addActionListener(this);
        
        saveButton = new Button("Save");
        saveButton.addActionListener(this);
        
        loadButton = new Button("Load");
        loadButton.addActionListener(this);
        
        outputArea = new TextArea(10, 50);
        outputArea.setEditable(false);
        
        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(genderLabel);
        add(genderChoice);
        add(policyTypeLabel);
        add(policyTypeChoice);
        add(policyAmountLabel);
        add(policyAmountField);
        add(submitButton);
        add(clearButton);
        add(saveButton);
        add(loadButton);
        add(outputArea);
        
    }

    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderChoice.getSelectedItem();
            String policyType = policyTypeChoice.getSelectedItem();
            double policyAmount = Double.parseDouble(policyAmountField.getText());
            
            // add code to process data and display output
            
            outputArea.append("Name: " + name + "\n");
            outputArea.append("Age: " + age + "\n");
            outputArea.append("Gender: " + gender + "\n");
            outputArea.append("Policy Type: " + policyType + "\n");
            outputArea.append("Policy Amount: " + policyAmount + "\n");
            outputArea.append("\n");
            
        } else if (e.getSource() == clearButton) {
            nameField.setText("");
            ageField.setText("");
            genderChoice.select(0);
            policyTypeChoice.select(0);
            policyAmountField.setText("");
            
        } else if (e.getSource() == saveButton) {
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(filename));
                writer.println(nameField.getText());
                writer.println(ageField.getText());
                writer.println(genderChoice.getSelectedItem());
                writer.println(policyTypeChoice.getSelectedItem());
                writer.println(policyAmountField.getText());
                writer.close();
            }
catch{}
        }
        }}