import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FilePermission.*;
import java.util.Scanner;

/*<applet code="DivLic.class" height=100 width=700></applet>*/
public class DivLic extends Applet implements ActionListener {

    private Label nameLabel, ageLabel, genderLabel, policyTypeLabel, policyAmountLabel,headingLabel;
    private TextField nameField, ageField, policyAmountField;
    private Checkbox studentCheckbox,workingCheckbox,othersCheckbox;
    private Choice genderChoice, policyTypeChoice;
    private Button submitButton, clearButton, saveButton; //loadButton;
    private TextArea outputArea;
    private String[] policyTypes = { "Life Insurance", "Vehicle Insurance", "Home Insurance" };
    private String[] genders = { "Male", "Female", "Other" };
    private String filename = "insurance_data.csv";
    String name,gender,policyType;
    int age; double policyAmount;

    public void init() {
        
        nameLabel = new Label("Name:");
        ageLabel = new Label("Age:");
        genderLabel = new Label("Gender:");
        policyTypeLabel = new Label("Policy Type:");
        policyAmountLabel = new Label("Policy Amount:");
	    headingLabel=new Label("Details of the Customers:");
      
        CheckboxGroup cg=new CheckboxGroup();
        studentCheckbox = new Checkbox("Student",cg,false);
        workingCheckbox = new Checkbox("Working",cg,false);
	othersCheckbox = new Checkbox("Others",cg,false);

        
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
        
        //loadButton = new Button("Load");
        //loadButton.addActionListener(this);
        
        outputArea = new TextArea(20, 250);
        outputArea.setEditable(false);
        
        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(genderLabel);
        add(genderChoice);
        add(studentCheckbox);
        add(workingCheckbox);
        add(othersCheckbox);
        add(policyTypeLabel);
        add(policyTypeChoice);
        add(policyAmountLabel);
        add(policyAmountField);
        add(submitButton);
        add(clearButton);
        add(saveButton);
        //add(loadButton);
        add(outputArea);
        add(headingLabel);
        
    }
    
private File getFile() throws IOException {
        File file = new File(filename);
	    file.setWritable(true);
        if (!file.exists()) {
            FileWriter fw = new FileWriter(file);
            fw.write("Name,Age,Gender,Policy Type,Policy Amount\n");
            fw.close();
        }
        return file;
    }

public void paint(Graphics g)//Graphics object is a collection for drawing images,text,patterns etc.
{

setBackground(Color.CYAN);
g.setColor(Color.RED);
Font f=new Font("Courier New",3,45);
g.setFont(f);
g.drawString("Financial Insurance System", 600,500);
}


public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            name = nameField.getText();
            age = Integer.parseInt(ageField.getText());
            gender = genderChoice.getSelectedItem();
            policyType = policyTypeChoice.getSelectedItem();
            policyAmount = Double.parseDouble(policyAmountField.getText());

            // add code to process data and display output

            outputArea.append("Name: " + name + "\n");
            outputArea.append("Age: " + age + "\n");
            outputArea.append("Gender: " + gender + "\n");
            outputArea.append("Policy Type: " + policyType + "\n");
            outputArea.append("Policy Amount: " + policyAmount + "\n");
            outputArea.append("\n");
        } else if (e.getSource() == saveButton) {
            try {
                File file = getFile();
		file.setWritable(true);
                FileWriter fw = new FileWriter(file, true);
                fw.write(name + "," + age + "," + gender + "," + policyType + "," + policyAmount);
                fw.write("\n");
                fw.close();
            } catch (IOException ex) {
                //ex.printStackTrace();
            }
}
        
else if (e.getSource() == clearButton) {
            nameField.setText("");
            ageField.setText("");
            genderChoice.select(0);
            policyTypeChoice.select(0);
            policyAmountField.setText("");
            
        } 
        }}