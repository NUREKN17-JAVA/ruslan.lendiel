package com.annalohvinenko.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BrowsePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5955495123527812587L;
	private MainFrame parent;
    private JPanel buttonPanel;
    private JButton addButton;
    private JButton detailsButton;
    private JButton deleteButton;
    private JButton editButton;
    private JScrollPane tablePanel;
    private JTable userTable;

    public BrowsePanel(MainFrame frame) {
        parent = frame;
        initialize();
    }

    private void initialize() {
        this.setName("browsePanel"); //$NON-NLS-1$
        this.setLayout(new BorderLayout());
        this.add(getTablePanel(), BorderLayout.CENTER);
        this.add(getButtonsPanel(), BorderLayout.SOUTH);
    }

    private JPanel getButtonsPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getAddButton(), null);
            buttonPanel.add(getEditButton(), null);
            buttonPanel.add(getDeleteButton(), null);
            buttonPanel.add(getDetailsButton(), null);
        }
        return buttonPanel;
    }

    private JButton getDetailsButton() {
        if (detailsButton == null) {
            detailsButton = new JButton();
            detailsButton.setText("Детали"); //$NON-NLS-1$
            detailsButton.setName("detailsButton"); //$NON-NLS-1$
            detailsButton.setActionCommand("details"); //$NON-NLS-1$
            detailsButton.addActionListener(this);
        }
        return detailsButton;
    }

    private JButton getDeleteButton() {
        if (deleteButton == null) {
            deleteButton = new JButton();
            deleteButton.setText("Удалить"); //$NON-NLS-1$
            deleteButton.setName("deleteButton"); //$NON-NLS-1$
            deleteButton.setActionCommand("delete"); //$NON-NLS-1$
            deleteButton.addActionListener(this);
        }
        return deleteButton;
    }

    private JButton getEditButton() {
        if (editButton == null) {
            editButton = new JButton();
            editButton.setText("Изменить"); //$NON-NLS-1$
            editButton.setName("editButton"); //$NON-NLS-1$
            editButton.setActionCommand("edit"); //$NON-NLS-1$
            editButton.addActionListener(this);
        }
        return editButton;
    }

    private JButton getAddButton() {
        if (addButton == null) {
            addButton = new JButton();
            addButton.setText("Добавить"); //$NON-NLS-1$
            addButton.setName("addButton"); //$NON-NLS-1$
            addButton.setActionCommand("add"); //$NON-NLS-1$
            addButton.addActionListener(this);
        }
        return addButton;
    }

    private JScrollPane getTablePanel() {
        if (tablePanel == null) {
            tablePanel = new JScrollPane(getUserTable());
        }
        return tablePanel;
    }

    private JTable getUserTable() {
        if (userTable == null) {
            userTable = new JTable();
            userTable.setName("userTable"); //$NON-NLS-1$
        }
        return userTable;
    }

	@Override
	   public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("add".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
            this.setVisible(false);
           // parent.showAddPanel();
        } else if ("edit".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a user, please",
                        "Edit user", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        //    User user = ((UserTableModel) userTable.getModel())
       //             .getUser(selectedRow);
            this.setVisible(false);
       //     parent.showEditPanel(user);
        } else if ("delete".equalsIgnoreCase(actionCommand)) { //$NON-NLS-1$
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a user, please",
                        "Edit user", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        //    try {
        //        parent.getDao().delete(
        //                ((UserTableModel) userTable.getModel())
        //                       .getUser(selectedRow));
        //    } catch (DatabaseException e1) {
        //        JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",
        //                JOptionPane.ERROR_MESSAGE);
       //     }
        //    initTable();
            return;
        } else if("details".equalsIgnoreCase(actionCommand)){
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a user, please",
                        "Details user", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
          //  User user = ((UserTableModel) userTable.getModel())
          //          .getUser(selectedRow);
            this.setVisible(false);
          //  parent.showDetailsPanel(user);
        }

    }
}