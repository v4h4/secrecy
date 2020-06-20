package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class PanelForSecurityCredentials {
	private JPanel modeAndKeySelectionPanel=null;
	
	private JPanel algorithmPanel = null;
	private JComboBox<String> algorithmComboBox = null;
	
	private JPanel cryptoPanel=null;
	private JPasswordField cryptoKeyField =null;
	private JLabel keyStatusbLabel=null;
	
	private JPanel initalVectorpanel=null;
	private JPasswordField initialVectorField =null;
	private JLabel ivKeyStatusbLabel=null;
	
	private JPanel saltPanel=null;
	private JPasswordField saltField =null;
	private JLabel saltStatusLabel=null;
	
	private JPanel keyIterationsPanel = null;
	private JPasswordField keyIterationsField = null;
	private JLabel keyIterationsStatusLabel = null;
	
	
	private boolean inactivated=false;
	public PanelForSecurityCredentials(){
		createModeAndKeySelectionPanel();
		addComboBoxToPanel();
		
		addCryptopKeyFieldToPanel();
		addKeyStatusLabelToPanel();
		
		addInitialVectorFieldToPanel();
		addInitalVectorKeyStatusLabelToPanel();
		
		addSaltFieldToPanel();
		addSaltStatusLabelToPanel();
		
		addKeyIterationsFieldToPanel();
		addKeyIterationsStatusLabelToPanel();
	} 
	
	private void createModeAndKeySelectionPanel(){
		this.modeAndKeySelectionPanel = new JPanel();
		this.modeAndKeySelectionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Select Security Credentails"));
		this.modeAndKeySelectionPanel.setVisible(true);
		this.modeAndKeySelectionPanel.setLayout(null);
	}
	
	private void addComboBoxToPanel(){
		String algorithms[] = {
				"AES/CTR/PKCS5Padding",
				"AES/CBC/PKCS5Padding",
				"AES/GCM/PKCS5Padding"
		};
		algorithmPanel = new JPanel();
		algorithmPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Select Algorithm"));
		algorithmPanel.setVisible(true);
		algorithmPanel.setLayout(null);
		algorithmComboBox = new JComboBox<String>(algorithms);
		Font font = new Font("Consolas", Font.BOLD, 20);
		algorithmComboBox.setFont(font);
		algorithmPanel.add(algorithmComboBox).setBounds(10, 20, 440, 30);
		modeAndKeySelectionPanel.add(algorithmPanel).setBounds(10, 20, 460, 60);
		algorithmComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isIvKeyStatusInactivated();
				isKeyStatusInactivated();
				if(getSelectedAlgorithmMode().contains("DES")){
					cryptoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter key (8 characters)"));
					initalVectorpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter iv-key (8 characters)"));
					
				}else{
					cryptoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter key (16 characters)"));
					initalVectorpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter iv-key (16 characters)"));
				}
			}
		});
	}
	
	
	
	/********************************************************
	 **********************  K E Y  ************************* 
	 ********************************************************/
	
	private void addCryptopKeyFieldToPanel(){
		this.cryptoKeyField= new JPasswordField();
		this.cryptoPanel = new JPanel();
		cryptoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter key (16 characters)"));
		cryptoPanel.setVisible(true);
		cryptoPanel.setLayout(null);
		Font font = new Font("Arial", Font.BOLD, 18);
		cryptoKeyField.setFont(font);
		cryptoKeyField.setText("");
		cryptoPanel.add(cryptoKeyField).setBounds(10, 20, 205, 30);
		modeAndKeySelectionPanel.add(cryptoPanel).setBounds(10, 135, 225, 60);
		cryptoKeyField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				isKeyStatusInactivated();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				isKeyStatusInactivated();
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				isKeyStatusInactivated();	
			}
		});
	}
	
	private void addKeyStatusLabelToPanel(){
		this.keyStatusbLabel = new JLabel();
		Font font = new Font("Ariel", Font.BOLD, 21);
		keyStatusbLabel.setFont(font);
		keyStatusbLabel.setHorizontalAlignment(JLabel.CENTER);
		keyStatusbLabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Key Status"));
		modeAndKeySelectionPanel.add(keyStatusbLabel).setBounds(10, 85, 225, 50);
		isKeyStatusInactivated();
	}	
	
	private boolean isKeyStatusInactivated(){
		if( cryptoKeyField.getPassword().length==16){
			keyStatusbLabel.setForeground(new Color(0,150,0));
			keyStatusbLabel.setText("KEY CORRECT");
			inactivated= false;
		}else{
			keyStatusbLabel.setForeground(Color.RED);
			keyStatusbLabel.setText("KEY INCORRECT");
			inactivated= true;
		}
		return inactivated;
	}
	
	
	/********************************************************
	 *********************   I V   K E Y   ****************** 
	 ********************************************************/
	
	private void addInitialVectorFieldToPanel(){
		this.initialVectorField = new JPasswordField();
		this.initalVectorpanel = new JPanel();
		initalVectorpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter iv-key (16 characters)"));
		initalVectorpanel.setVisible(true);
		initalVectorpanel.setLayout(null);
		Font font = new Font("Arial", Font.BOLD, 18);
		initialVectorField.setFont(font);
		initialVectorField.setText("");
		initalVectorpanel.add(initialVectorField).setBounds(10, 20, 205, 30);
		modeAndKeySelectionPanel.add(initalVectorpanel).setBounds(245, 135, 225, 60);
		initialVectorField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				isIvKeyStatusInactivated();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				isIvKeyStatusInactivated();
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				isIvKeyStatusInactivated();	
			}
		});
	}
	
	private void addInitalVectorKeyStatusLabelToPanel(){
		this.ivKeyStatusbLabel = new JLabel();
		Font font = new Font("Ariel", Font.BOLD, 19);
		ivKeyStatusbLabel.setFont(font);
		ivKeyStatusbLabel.setHorizontalAlignment(JLabel.CENTER);
		ivKeyStatusbLabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"IV-Key Status"));
		modeAndKeySelectionPanel.add(ivKeyStatusbLabel).setBounds(245, 85, 225, 50);
		isIvKeyStatusInactivated();
	}
	
	private boolean isIvKeyStatusInactivated(){
		if(initialVectorField.getPassword().length==16){
			ivKeyStatusbLabel.setForeground(new Color(0,150,0));
			ivKeyStatusbLabel.setText("IV-KEY CORRECT");
			inactivated= false;
			initialVectorField.setEnabled(true);
		}else{
			ivKeyStatusbLabel.setForeground(Color.RED);
			ivKeyStatusbLabel.setText("IV-KEY INCORRECT");
			initialVectorField.setEnabled(true);
			inactivated= true;
		}
		return inactivated;
	}
	
	/********************************************************
	 **********************  S A L T  *********************** 
	 ********************************************************/
	
	
	private void addSaltFieldToPanel(){
		this.saltField= new JPasswordField();
		this.saltPanel = new JPanel();
		saltPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter key (16 characters)"));
		saltPanel.setVisible(true);
		saltPanel.setLayout(null);
		Font font = new Font("Arial", Font.BOLD, 18);
		saltField.setFont(font);
		saltField.setText("");
		saltPanel.add(saltField).setBounds(10, 20, 205, 30);
		modeAndKeySelectionPanel.add(saltPanel).setBounds(10, 255, 225, 60);
		saltField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				isSaltStatusInactivated();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				isSaltStatusInactivated();
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				isSaltStatusInactivated();	
			}
		});
	}
	
	private void addSaltStatusLabelToPanel(){
		this.saltStatusLabel = new JLabel();
		Font font = new Font("Ariel", Font.BOLD, 21);
		saltStatusLabel.setFont(font);
		saltStatusLabel.setHorizontalAlignment(JLabel.CENTER);
		saltStatusLabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Salt Status"));
		modeAndKeySelectionPanel.add(saltStatusLabel).setBounds(10, 205, 225, 50);
		isSaltStatusInactivated();
	}
	
	
	private boolean isSaltStatusInactivated(){
		if(saltField.getPassword().length==16){
			saltStatusLabel.setForeground(new Color(0,150,0));
			saltStatusLabel.setText("SALT CORRECT");
			inactivated= false;
		}else{
			saltStatusLabel.setForeground(Color.RED);
			saltStatusLabel.setText("SALT INCORRECT");
			inactivated= true;
		}
		return inactivated;
	}
	
	
	/********************************************************
	 ***********   K E Y   I T E R A T I O N S   ************ 
	 ********************************************************/
	
	private void addKeyIterationsFieldToPanel(){
		this.keyIterationsField= new JPasswordField();
		this.keyIterationsPanel = new JPanel();
		keyIterationsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Enter nbr ( >= 100 < 10M )"));
		
		keyIterationsPanel.setVisible(true);
		keyIterationsPanel.setLayout(null);
		Font font = new Font("Arial", Font.BOLD, 45);
		keyIterationsField.setFont(font);
		keyIterationsField.setText("");
		keyIterationsPanel.add(keyIterationsField).setBounds(10, 20, 205, 30);
		modeAndKeySelectionPanel.add(keyIterationsPanel).setBounds(245, 255, 225, 60);
		keyIterationsField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				isKeyIterationsStatusInactivated();
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				isKeyIterationsStatusInactivated();
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				isKeyIterationsStatusInactivated();	
			}
		});
	}
	
	private void addKeyIterationsStatusLabelToPanel(){
		this.keyIterationsStatusLabel = new JLabel();
		Font font = new Font("Ariel", Font.BOLD, 12);
		keyIterationsStatusLabel.setFont(font);
		keyIterationsStatusLabel.setHorizontalAlignment(JLabel.CENTER);
		keyIterationsStatusLabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"Key Iterations Status"));
		modeAndKeySelectionPanel.add(keyIterationsStatusLabel).setBounds(245, 205, 225, 50);
		isKeyIterationsStatusInactivated();
	}
	
	
	private boolean isKeyIterationsStatusInactivated(){
		keyIterationsField.setText(new String(keyIterationsField.getPassword()).replaceAll("\\D*", ""));
		if(keyIterationsField.getPassword().length >= 3 && keyIterationsField.getPassword().length <= 7){
			keyIterationsStatusLabel.setForeground(new Color(0,150,0));
			keyIterationsStatusLabel.setText("KEY ITERATIONS CORRECT");
			inactivated= false;
		}else{
			keyIterationsStatusLabel.setForeground(Color.RED);
			keyIterationsStatusLabel.setText("KEY ITERATIONS INCORRECT");
			inactivated= true;
		}
		return inactivated;
	}
	
	
	
	
	public boolean isSecCredentialsInactivated(){
		if(isIvKeyStatusInactivated() ){
			return true;
		}else if(isKeyStatusInactivated() ) {
			return true;
		}else if(isSaltStatusInactivated()) {
			return true;
		}else if(isKeyIterationsStatusInactivated()) {
			return true;
		}else{
			return false;
		}
	}
	
	public String getSelectedAlgorithmMode(){
		return this.algorithmComboBox.getSelectedItem().toString();
	}
	
	public JPanel getJPanel(){
		return this.modeAndKeySelectionPanel;
	}
	
	public String getKey(){
		return new String(this.cryptoKeyField.getPassword());
	}
	
	public String getIvKey(){
		return new String(this.initialVectorField.getPassword());
	}
	
	public String getSalt() {
		return new String(this.saltField.getPassword());
	}
	
	public int getKeyIterations() {
		return Integer.parseInt(new String(this.keyIterationsField.getPassword()));
	}
	
}
