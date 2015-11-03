package database;

import java.io.File;

public class FileUtils {
	public static boolean isFilenameValid(String filename) {
		File file = new File(filename);
		//try {
			System.out.println(file.getAbsolutePath());
		//} catch (IOException ioe) {
			//return false;
		//}
		return true;
	}

}
