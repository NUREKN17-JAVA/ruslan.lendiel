package com.annalohvinenko.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.ParseException;


import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPanel extends JPanel implements ActionListener {
 
	private static final long serialVersionUID = 2371425014873030612L;
	
	protected MainFrame parent;
	protected JPanel buttonPanel;
	protected JPanel fieldPanel;
	protected JButton cancelButton;
    protected JButton okButton;
    protected JTextField firstNameField;
    protected JTextField dateOfBirthField;
    protected JTextField lastNameField;
    protected Color bgColor;

    public AddPanel(MainFrame parent) {
        this.parent = parent;
        initialize();
    }

    protected void initialize() {
        this.setName("addPanel"); 
        this.setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.NORTH);
        this.add(getButtonPanel(), BorderLayout.SOUTH); 
    }
	
	 protected JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(3, 2));
            addLabeledField(fieldPanel, "Имя", getFirstNameField()); 
            addLabeledField(fieldPanel, "Фамилия", getLastNameField()); 
            addLabeledField(fieldPanel, "Дата рождения", getDateOfBirthField()); 
        }
        return fieldPanel;
    }
	
    protected JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getOkButton(), null);
            buttonPanel.add(getCancelButton(), null);
        }
        return buttonPanel;
    }

    protected JButton getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new JButton();
            cancelButton.setText("Отмена"); 
            cancelButton.setName("cancelButton"); 
            cancelButton.setActionCommand("cancel");
            cancelButton.addActionListener(this);
        }
        return cancelButton;
    }

    protected JButton getOkButton() {
        if (okButton == null) {
            okButton = new JButton();
            okButton.setText("ОК"); 
            okButton.setName("okButton"); 
            okButton.setActionCommand("ok");
            okButton.addActionListener(this);
        }
        return okButton;
    }

   

    protected JTextField getDateOfBirthField() {
        if (dateOfBirthField == null) {
            dateOfBirthField = new JTextField();
            dateOfBirthField.setName("dateOfBirthField"); 
        }
        return dateOfBirthField;
    }

    protected JTextField getLastNameField() {
        if (lastNameField == null) {
            lastNameField = new JTextField();
            lastNameField.setName("lastNameField"); 
        }
        return lastNameField;
    }

    protected void addLabeledField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textField);
        panel.add(label);
        panel.add(textField);
    }

    protected JTextField getFirstNameField() {
        if (firstNameField == null) {
            firstNameField = new JTextField();
            firstNameField.setName("firstNameField"); 
        }
        return firstNameField;
    }

    public void actionPerformed(ActionEvent e) {
        
    }
    
    protected void doAction(ActionEvent e) throws ParseException {
   
    }
    
  
    
}