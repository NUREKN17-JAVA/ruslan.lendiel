package com.annalohvinenko.usermanagement.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.annalohvinenko.usermanagement.db.DaoFactory;
import com.annalohvinenko.usermanagement.db.UserDao;
import com.annalohvinenko.usermanagement.gui.BrowsePanel;
import  com.annalohvinenko.usermanagement.gui.AddPanel;
import  com.annalohvinenko.usermanagement.gui.MainFrame;
import com.annalohvinenko.usermanagement.util.Messages;


public class MainFrame extends JFrame {
    
	private static final long serialVersionUID = 2668816163406000368L;
	
	private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_WIDTH = 800;
    private JPanel contentPanel;
    private JPanel browsePanel;
    private AddPanel addPanel;

    private UserDao dao;


    public MainFrame() {
        super();
        dao = DaoFactory.getInstance().getUserDao();
        initialize();
    }
    
    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(Messages.getString("MainFrame.user_management")); //$NON-NLS-1$
        this.setContentPane(getContentPanel());
    }
    
    private JPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);
        }
        return contentPanel;
    }

    private JPanel getBrowsePanel() {
        if (browsePanel == null) {
            browsePanel = new BrowsePanel(this);
        }
       
        return browsePanel;
    }
    
    public UserDao getDao() {
        return dao;
    }  

    /**
     * @param args
     */
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);

    }

    public void showAddPanel() {
        showPanel(getAddPanel());
        
    }

    private JPanel getAddPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	private void showPanel(JPanel panel) {
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
        panel.repaint();
        
    }


}
