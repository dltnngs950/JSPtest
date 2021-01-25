package kr.or.ddit.util;

public class FileUtil {
	
	//contentDisposition ==> form-data; name="file"; filename="brown.png"
	public static String getFileName(String contentDisposition) {
		String[] names = contentDisposition.split("; ");
		
		for(String name : names) {
			
			if(name.startsWith("filename=")) {
				
				// filename="brown.png"
				name = name.replace("filename=", "");
				
				//"brown.png"
				return name.substring(1, name.length()-1);
			}
		}
		
		return "";
	}
	
	public static String getFileExtension(String filename) {
		int a = filename.lastIndexOf(".");
		
		if (a != -1) {
			return "."+filename.substring(a+1, filename.length());
		}
		
		return "";
	}

}
