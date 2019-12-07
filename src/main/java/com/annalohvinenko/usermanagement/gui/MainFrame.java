package com.annalohvinenko.usermanagement.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.annalohvinenko.usermanagement.db.DaoFactory;
import com.annalohvinenko.usermanagement.db.UserDao;
import com.annalohvinenko.usermanagement.gui.BrowsePanel;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 2668816163406000368L;
	
	private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_WIDTH = 800;
    private JPanel contentPanel;
    private JPanel browsePanel;
    private UserDao dao;

	
	 public MainFrame() {
	        super();
	        dao = DaoFactory.getInstance().getUserDao();
	        initialize();
	    }
	    
	    private void initialize() {
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	        this.setTitle("”правление пользовател€ми"); //$NON-NLS-1$
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
	    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
