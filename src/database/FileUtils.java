package database;

import java.io.*;

public class FileUtils {
	public static boolean isFilenameValid(String filename) {
		File file = new File(filename);
		try {
			file.getCanonicalPath();
		} catch (IOException ioe) {
			return false;
		}
		return true;
	}

	public static boolean isValidDirectory(File file) {
		return file.exists() && file.isDirectory();
	}

	public static String getFirstLine(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		String string = br.readLine();
		br.close();
		return string;
	}
}
