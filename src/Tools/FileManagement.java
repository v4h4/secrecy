package Tools;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class FileManagement {
	
	public Boolean fileIsDirectory(String path) {
		return Files.isDirectory(Paths.get(path));
	}
	
	public boolean fileExists(String path){
		return Files.exists(Paths.get(path));
	}
	
	public byte[] getFilePathToByteArr(String fullPath) {
		try {
			Path path = Paths.get(fullPath);
			byte[] byteArr = Files.readAllBytes(path);
			return byteArr;
		} catch (Exception ex) {
			System.out.println("Error when reading and getting byte[] from file: " + ex);
			ex.printStackTrace();
		}
		return null;
	}


	public boolean byteArrToFile(String fullPath, byte[] byteArr) {
		try {
			Files.write(Paths.get(fullPath), byteArr);
			return true;
		} catch (Exception ex) {
			System.out.println("Error when saving file: " + ex.getMessage());
		}
		return false;
	}

	public String readTextFromFile(String fullPath) {
		try {
			ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(Paths.get(fullPath));
			return String.join("\n", lines);
		} catch (Exception ex) {
			System.out.println("could not read text from file: "+ex.getMessage());
		}
		return null;
	}

	public boolean writeTextToTextFile(String fullPath, String text) {
		Path path = Paths.get(fullPath);
		try {
			Files.write(path, text.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public Image getImageFromWithinJar(String path) {

		InputStream in = ApplicationStart.Main.class.getResourceAsStream(path);
		try {
			BufferedImage bufferedImage = ImageIO.read(in);
			in.close();
			return bufferedImage;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	

	public Image getImageFromFile(String path) {
		try {
			return ImageIO.read(new ByteArrayInputStream(getFilePathToByteArr(path)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Icon getIconFromFile(String path) {
		return new ImageIcon(getFilePathToByteArr(path));
	}
	
	public boolean runningFromIde() {
		String protocol = this.getClass().getResource("").getProtocol();
		if (Objects.equals(protocol, "file")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean runningFromJar() {
		String protocol = this.getClass().getResource("").getProtocol();
		if (Objects.equals(protocol, "jar")) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getFilePathFromTreeNode(DefaultMutableTreeNode node) {
		TreeNode[] nodes = node.getPath();
		String path = "";
		for(int i=0;i<nodes.length;i++) {
			path += nodes[i].toString()+"/";
		}
		return path;		
	}
	
	public boolean writeObjectToFile(String path,Object object){
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(object);
		    oos.close();
		    fos.close();
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public Object readObjectFromFile(String path) {
		try {
			FileInputStream fis = new FileInputStream(new File(path));
		    ObjectInputStream ois = new ObjectInputStream(fis);
		    Object obj = ois.readObject();
		    ois.close();
			fis.close();
			return obj;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	    return null;
	}
	
	
	public byte[] convertObjectToByteArr(Object object) {
		try {
	    	 ByteArrayOutputStream bos = new ByteArrayOutputStream();
	         ObjectOutput out = new ObjectOutputStream(bos);
	         out.writeObject(object);
	         return bos.toByteArray();
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    }
	    return null;
	}

	public Object convertByteArrToObject(byte[] bytes){
	    try{
	    	ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
	        ObjectInput in = new ObjectInputStream(bis);
	        return in.readObject();
	    }catch(Exception ex) {
	    	ex.printStackTrace();
	    } 
	    return null;
	}
	
	public void saveFileDialog(byte[] byteFile,String filename,String fileExtension){
		try{
			System.out.println("\n\n\n\nfileName == "+filename);
			System.out.println("fileExtension == "+fileExtension+"\n\n\n\n");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Save the encrypted file");
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			
				File file = fileChooser.getSelectedFile();
				String file_Name=fileChooser.getCurrentDirectory()+"\\"+fileChooser.getName(file)+fileExtension;
				FileOutputStream fos = new FileOutputStream(file_Name);
			    fos.write(byteFile);
			    fos.close();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void saveFileViaFileChooser(String projPath) {
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		  String path = fileChooser.getSelectedFile().getAbsolutePath();
		  byteArrToFile(path,getFilePathToByteArr(projPath));
		}
	}
	
	public String fullPathToFilename(String fullpath) {
		return Paths.get(fullpath).getFileName().toString();
	}
}
