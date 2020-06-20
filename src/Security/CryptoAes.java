package Security;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import GUI.MainFrame;

public class CryptoAes {

	private Base64 base64 = null;
	
	private MainFrame gui = null;	
	public CryptoAes(MainFrame gui){
		this.gui=gui;
		this.base64 = new Base64();
	}
	
    private byte[] encryption(byte[] src){   
        try{
        	Cipher cipher = Cipher.getInstance(gui.getSelectedAlgorithmMode());
            if(gui.getSelectedAlgorithmMode().equals("AES/GCM/PKCS5Padding")) {
            	cipher.init(Cipher.ENCRYPT_MODE, getSecreKeySpec(), getGCMParameterSpec());
            }else {
            	cipher.init(Cipher.ENCRYPT_MODE, getSecreKeySpec(), getSecretIvSpec());
            }
        	return cipher.doFinal(src);
        }catch(Exception ex){
        	ex.printStackTrace();
        }
    	return null;
    }
 
    
    private byte[] decryption(byte[] src){
    	try{
    		Cipher cipher = Cipher.getInstance(gui.getSelectedAlgorithmMode());
    		if(gui.getSelectedAlgorithmMode().equals("AES/GCM/PKCS5Padding")) {
    			cipher.init(Cipher.DECRYPT_MODE, getSecreKeySpec(), getGCMParameterSpec());
            }else {
            	cipher.init(Cipher.DECRYPT_MODE, getSecreKeySpec(), getSecretIvSpec());
            }
    		return cipher.doFinal(src);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return null;	
    }
    
    private IvParameterSpec getSecretIvSpec(){
		try{
			return new IvParameterSpec(gui.getIvKey().getBytes()); 
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
    
    private GCMParameterSpec getGCMParameterSpec(){
		try{
			return new GCMParameterSpec(16 * Byte.SIZE,gui.getIvKey().getBytes());     
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	} 
    
    private SecretKeySpec getSecreKeySpec(){
    	try{
    	   	SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    	   	PBEKeySpec spec = new PBEKeySpec(gui.getKey().toCharArray(),gui.getSalt().getBytes(), gui.getKeyIterations(), 128);
            SecretKey secretKey = factory.generateSecret(spec);
            return new SecretKeySpec(secretKey.getEncoded(), "AES");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return null;
    }
    
    public byte[] encrypt (byte[] plain) {
    	return encryption(plain);
    }
    
    public byte[] decrypt (byte[] encrypted) {
    	return decryption(encrypted);
    }
    
    public String encryptString (String plainText) {
    	plainText = base64.encode(plainText.getBytes());
    	byte[] encrypted = encrypt(plainText.getBytes());
    	return base64.encode(encrypted);
    }
    
    public String decryptString (String encryptedText) {
    	byte[] encryptedByteArr = base64.decode(encryptedText);
    	byte[] decrypted = decrypt(encryptedByteArr);
    	byte[] decryptedByteArr =  base64.decode(new String(decrypted));
    	return new String(decryptedByteArr);
    }
}
