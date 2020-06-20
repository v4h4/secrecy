package Security;

import javax.swing.JFileChooser;
import Tools.FileManagement;

public class CryptoFileManagement {

	private FileManagement fm = null;
	private CryptoAes aes = null;
	
	public CryptoFileManagement(CryptoAes aes) {
			this.aes = aes;
			this.fm = new FileManagement();
	} 
	
	
	public boolean decryptFileOnHDViaFileChooser(){
		try{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Select file to decrypt");
			//fileChooser.getSelectedFile()
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			    String filepath = fileChooser.getSelectedFile().getAbsolutePath();
			    return decryptFileOnHD(filepath);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	
	public boolean encryptFileOnHDViaFileChooser(){
		try{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Select file to encrypt");
			//fileChooser.getSelectedFile()
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			    String filepath = fileChooser.getSelectedFile().getAbsolutePath();
			    return encryptFileOnHD(filepath);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean decryptFileOnHD (String fullPath) {
    	byte[] encryptedByteArr = fm.getFilePathToByteArr(fullPath);
    	byte[] decryptedByteArr = aes.decrypt(encryptedByteArr); 
    	return fm.byteArrToFile(fullPath, decryptedByteArr);
    }
    
    public boolean encryptFileOnHD (String fullPath) {
    	byte[] plainByteArr = fm.getFilePathToByteArr(fullPath);
    	byte[] encryptedByteArr = aes.encrypt(plainByteArr); 
    	return fm.byteArrToFile(fullPath, encryptedByteArr);
    }
    
    public boolean decryptByteArrTFile(String fullPath,byte[] encryptedFileByteArr) {
    	byte[] decryptedByteArr = aes.decrypt(encryptedFileByteArr);
    	return fm.byteArrToFile(fullPath, decryptedByteArr);
    }
    
    public boolean encryptByteArrToFile(String fullPath,byte[] plainFileByteArr) {
    	byte[] encryptedByteArr = aes.encrypt(plainFileByteArr);
    	return fm.byteArrToFile(fullPath, encryptedByteArr);
    }
    
    public byte[] decryptedFileToEncryptedByteArr (String fullPath) {
    	byte[] plainByteArr = fm.getFilePathToByteArr(fullPath);
    	return aes.encrypt(plainByteArr);
    }
    
    public byte[] encryptedFileToDecryptedByteArr (String fullPath) {
    	byte[] encryptedByteArr = fm.getFilePathToByteArr(fullPath);
    	return aes.decrypt(encryptedByteArr); 
    }
     	
    public boolean writeEncryptedTextToFile (String fullPath,String plainText) {
    	String ecryptedText =aes.encryptString(plainText);
    	return fm.writeTextToTextFile(fullPath, ecryptedText);
    }
    
    public String readEncryptedTextFromFile (String fullPath) {
        String encryptedFileText = fm.readTextFromFile(fullPath);
    	return aes.decryptString(encryptedFileText);
    }
    
    public Object decryptedObjectFromFile(String fullpath) {
    	byte[] arr = fm.getFilePathToByteArr(fullpath);
    	arr = aes.decrypt(arr);
    	return fm.convertByteArrToObject(arr);
    }
    
    public void encryptObjectToFile(Object obj, String fullPath) {
    	byte[] arr = fm.convertObjectToByteArr(obj);
    	arr = aes.encrypt(arr);
    	fm.byteArrToFile(fullPath, arr);
    	
    }
    
}
