package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import Tools.FileManagement;


public class MainFrame {
	private JFrame mainFrame = null;
	private PanelForSecurityCredentials securityCredentailsPanel=null;
	private PanelForCyrptoControl cryptoPanel=null;
	private ValidationDialogGUI val=null;
	private FileManagement fm = null;
	
	public MainFrame(){
		this.fm = new FileManagement();
		startClientWindowGUI("Secrecy");
		this.val= new ValidationDialogGUI(mainFrame);
		mainFrameCloseListener();
		addPanelForSecurityCredentialsToFrame();
		addPanelForCryptoControlToFrame();
		
	}
	
	public void startClientWindowGUI(String title){
		try{
			this.mainFrame = new JFrame();
			mainFrame.setTitle(title);
			mainFrame.setVisible(false);
			mainFrame.setSize(985, 370);
			mainFrame.setLayout(null);
			if(fm.runningFromJar()) {
				mainFrame.setIconImage(fm.getImageFromWithinJar("/resources/secrecy.png"));				
			}else{//fm.runingFromIde()
				mainFrame.setIconImage(fm.getImageFromFile("./src/resources/secrecy.png"));
			}
			mainFrame.setLocationRelativeTo(null);
			mainFrame.setResizable(false);
			mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			mainFrame.setLocation(800, 10);
			mainFrameCloseListener();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	private void addPanelForSecurityCredentialsToFrame(){
		securityCredentailsPanel = new PanelForSecurityCredentials();
		mainFrame.add(securityCredentailsPanel.getJPanel()).setBounds(10, 10, 480, 325);
	}
	
	private void addPanelForCryptoControlToFrame(){
		this.cryptoPanel = new PanelForCyrptoControl(this);
		mainFrame.add(cryptoPanel.getJPanel()).setBounds(495, 10, 480, 325);
	}
	
	private void mainFrameCloseListener(){
		mainFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				boolean close=true;//val.dynamicConfirmationDialog("Closing down Admin Bank Chatt", "Are you sure that you want to close this Admin Bank Chatt?");
				if(close == true){
					mainFrame.dispose();
					System.exit(0);
					//mainFrameServerSelection.showMainFrame();
				}	
			}
		});
	}
	
	public String getSelectedAlgorithmMode(){
		return this.securityCredentailsPanel.getSelectedAlgorithmMode();		
	}
	
	public boolean isSecCredentialsInactivated(){
		return this.securityCredentailsPanel.isSecCredentialsInactivated();
	}
	
	public String getKey(){
		return this.securityCredentailsPanel.getKey();
	}
	
	public String getIvKey(){
		return this.securityCredentailsPanel.getIvKey();
	}
	
	public String getSalt() {
		return this.securityCredentailsPanel.getSalt();
	}
	
	public int getKeyIterations() {
		return this.securityCredentailsPanel.getKeyIterations();
	}
	
	public void showGUI(){
		this.mainFrame.setVisible(true);
	}
	
	public ValidationDialogGUI getValidationDialog() {
		return this.val;
	}
}
