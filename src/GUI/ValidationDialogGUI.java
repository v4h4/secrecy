package GUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ValidationDialogGUI{
	private JFrame mainFrame=null;
	
	public ValidationDialogGUI(JFrame mainFrame){
		this.mainFrame=mainFrame;
	}
	
	
	@SuppressWarnings("static-access")
	public boolean dynamicWarningDialogWindow(String title,String message){
		this.mainFrame.setEnabled(false);
		JOptionPane errorDialog= new JOptionPane();
		errorDialog.showMessageDialog(null,message,title,JOptionPane.WARNING_MESSAGE);
		while(true){
			if(errorDialog.isShowing()==false){
				this.mainFrame.setEnabled(true);
				return true;
			}
		}
	}
	
	@SuppressWarnings("static-access")
	public boolean dynamicErrorDialogWindow(String title,String message){
		this.mainFrame.setEnabled(false);
		JOptionPane errorDialog= new JOptionPane();
		errorDialog.showMessageDialog(null,message,title,JOptionPane.ERROR_MESSAGE);
		while(true){
			if(errorDialog.isShowing()==false){
				this.mainFrame.setEnabled(true);
				return true;
			}
		}	
	}
	
	public boolean dynamicConfirmationDialog(String title,String message){
		int dialogResult = JOptionPane.showConfirmDialog(null, message, title,JOptionPane.YES_NO_OPTION);
		while(true){
			if (dialogResult == JOptionPane.YES_OPTION) {
				System.out.println("return true;");
				return true;
			}else{
				System.out.println("return false;");
				return false;
			}
		}
	}	
	
	public void saveEncryptedFileDialog(byte[] byteFile,String filename,String fileType){
		try{
			System.out.println("\n\n\n\nfileName == "+filename);
			System.out.println("fileType == "+fileType+"\n\n\n\n");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Save the encrypted file");
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			
				File file = fileChooser.getSelectedFile();
				String file_Name=fileChooser.getCurrentDirectory()+"\\"+fileChooser.getName(file)+fileType;
				FileOutputStream fos = new FileOutputStream(file_Name);
			    fos.write(byteFile);
			    fos.close();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void selectingFileDialog(){
		try{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Selecting file");
			//fileChooser.getSelectedFile()
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			    File file = fileChooser.getSelectedFile();
			    FileOutputStream fileOutputStream = new FileOutputStream("Blobs\\"+file.getName());
			    byte[] byteFile = convertFileToByteArray_IO(file);
			    fileOutputStream.write(byteFile);
			    fileOutputStream.close();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public boolean textComponetIsEmpty(String textComponent){
		char empty=' ';
		int counter=0;
		for(int i=0;i<textComponent.length();i++ ){
			if(textComponent.charAt(i)==empty){
				counter++;
			}
		}
		if(counter==textComponent.length()){
			return true;
		}
		return false;
	}
	
	public boolean textComponetIsIpNumber(String ip){
		if(toManyIpDots(ip)==true){
			return false;
		}
		String lastSub="";
		int beginIndex=0;
		for(int i=1;i<=ip.length();i++ ){
			String txt=ip.substring(beginIndex, i);
			if(textComponentIsIpNumeric(txt)==true){
				lastSub=txt;
			}
			if(textComponentIsIpNumeric(txt)==false && ip.charAt(i-1)=='.'){
				
				if(lastSub.length()>0 && lastSub.length()<=4){
					beginIndex=i;
				}
			}
			else if(textComponentIsIpNumeric(txt)==false){
				return false;
			}
		}
		return true;
	}
	
	private boolean textComponentIsIpNumeric(String textComponent){
		try{
			Integer.parseInt(textComponent);
			if(textComponent.length()>3){
				return false;
			}
		}catch(Exception ex){
			return false;
		}
		return true;
	}
	
	private boolean toManyIpDots(String ip){
		if(!ip.contains("..") && !ip.contains("...") && ip.length()<16 && ip.length()>6){
			int counter=0;
			for(int i=0;i<ip.length();i++){
				if(ip.charAt(i)=='.'){
					counter++;
				}
			}
			if(counter>0 && counter<4){
				return false;
			}
		}
		return true;
	}
	
	private byte[] convertFileToByteArray_IO(File file){
		FileInputStream fileInputStream=null;
        byte[] byteFile = new byte[(int) file.length()];
		try {
			fileInputStream = new FileInputStream(file);
		    fileInputStream.read(byteFile);
		    fileInputStream.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
		return byteFile;
	}
}
