package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import Security.CryptoAes;
import Security.CryptoFileManagement;

public class PanelForCyrptoControl {
	private JPanel cryptopPanel=null;
	private JTextArea textArea = null;
	private JButton encryptTextButton =null;
	private JButton decryptTextButton =null;
	private JButton encryptFileButton =null;
	private JButton decryptFileButton=null;
	private MainFrame gui=null;
	private CryptoAes aes = null;
	private CryptoFileManagement sec_fm = null;
	public PanelForCyrptoControl(MainFrame gui){
		this.gui=gui;
		this.aes = new CryptoAes(gui);
		this.sec_fm = new CryptoFileManagement(aes);
		createPanelForCyrptoControl();
		addEncryptTextButtonToPanel();
		addDecryptTextButtonToPanel();
		addTextAreaToPanel();
		addEncryptFileButtonToPanel();
		addDecryptFileButtonToPanel();
		
	}
	
	
	public void createPanelForCyrptoControl(){
		this.cryptopPanel = new JPanel();
		this.cryptopPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Crypto Control"));
		this.cryptopPanel.setVisible(true);
		this.cryptopPanel.setLayout(null);
	}
	
	public void addEncryptTextButtonToPanel(){
		this.encryptTextButton= new JButton();
		this.encryptTextButton= new JButton("Encrypt Text");
		this.encryptTextButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	try{
	        		if(gui.isSecCredentialsInactivated()==false){
	        			textArea.setText(aes.encryptString(textArea.getText()));
	        		}
	        	}catch(Exception ex){
	        		ex.printStackTrace();
	        		 gui.getValidationDialog().dynamicErrorDialogWindow("Input Error", "Your input is incorrect, please follow the instructions!");
	        	 }
	         }
	    });
		cryptopPanel.add(this.encryptTextButton).setBounds(10, 20, 225, 30);
	}
	
	
	public void addDecryptTextButtonToPanel(){
		this.decryptTextButton= new JButton();
		this.decryptTextButton= new JButton("Decrypt Text");
		this.decryptTextButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	try{
	        		if(gui.isSecCredentialsInactivated()==false){
	        			textArea.setText(aes.decryptString(textArea.getText()));
	        		
	        		}
	        	}catch(Exception ex){
	        		 gui.getValidationDialog().dynamicErrorDialogWindow("Input Error", "Your input is incorrect, please follow the instructions!");
	        	 }
	         }
	    });
		cryptopPanel.add(this.decryptTextButton).setBounds(245, 20, 225, 30);
	
	}
	
	public void addTextAreaToPanel(){
		this.textArea= new JTextArea();
		this.textArea.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		cryptopPanel.add(scrollPane).setBounds(10, 60, 460, 210);
	}
	
	public void addEncryptFileButtonToPanel(){
		this.encryptFileButton=new JButton();
		this.encryptFileButton= new JButton("Encrypt File");
		this.encryptFileButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	try{
	        		if(gui.isSecCredentialsInactivated()==false){
	        			sec_fm.encryptFileOnHDViaFileChooser();
	        		}
	        	}catch(Exception ex){
	        		 gui.getValidationDialog().dynamicErrorDialogWindow("Input Error", "Your input is incorrect, please follow the instructions!");
	        	 }
	         }
	    });
		cryptopPanel.add(this.encryptFileButton).setBounds(10, 280, 225, 30);
	
	}
	
	public void addDecryptFileButtonToPanel(){
		this.decryptFileButton=new JButton();
		this.decryptFileButton=new JButton("Decrypt File");
		this.decryptFileButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	try{
	        		if(gui.isSecCredentialsInactivated()==false){
	        			sec_fm.decryptFileOnHDViaFileChooser();
	        		}
	        	}catch(Exception ex){
	        		 gui.getValidationDialog().dynamicErrorDialogWindow("Input Error", "Your input is incorrect, please follow the instructions!");
	        	 }
	         }
	    });
		cryptopPanel.add(this.decryptFileButton).setBounds(245, 280, 225, 30);
	}

	public JPanel getJPanel(){
		return this.cryptopPanel;
	}

	
	
	
}
