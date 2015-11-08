package database;

import java.io.*;

// @@author A0130503B

/**
 * A helper class to do File I/O
 */
public class FileUtils {
	/**
	 * Checks if a file name is valid by creating a File and using {@link File#getCanonicalPath()}
	 * @param filename the file name to be checked
	 * @return boolean if there is no IOException
	 */
	public static boolean isFilenameValid(String filename) {
		File file = new File(filename);
		try {
			file.getCanonicalPath();
		} catch (IOException ioe) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if the file exists and is a directory
	 * @param file the file to be checked
	 * @return true if file exists and is a directory
	 */
	public static boolean isValidDirectory(File file) {
		return file.exists() && file.isDirectory();
	}

	/**
	 * Get the first line of a file
	 * @param filename name of the file
	 * @return the first line as a String
	 * @throws IOException
	 */
	public static String getFirstLine(String filename)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		String string = br.readLine();
		br.close();
		return string;
	}

	/**
	 * Move the a file from src location to dst location, and delete the src file after moving
	 * @param src source path
	 * @param dst destination path
	 * @throws IOException
	 */
	public static void moveFile(String src, String dst)
			throws IOException {
		File srcFile = new File(src);
		BufferedReader br = new BufferedReader(new FileReader(srcFile));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dst)));
		while (br.ready()) {
			String line = br.readLine();
			bw.write(line);
			bw.newLine();
		}
		bw.flush();
		srcFile.delete();
		br.close();
		bw.close();
	}

	/**
	 * Count the lines in a file
	 * @param dir the file to be counted
	 * @return the line count number
	 * @throws FileNotFoundException if file is not found
	 * @throws IOException
	 */
	public static int getLinesCount(String dir)
			throws FileNotFoundException, IOException {
		int counter = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(new File(dir)))) {
			while (br.ready()) {
				br.readLine();
				++counter;
			}
			return counter + 1;
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * Append a file name after dir if it is only a directory name.
	 * @param dir path
	 * @return a String of the updated path name
	 */
	public static String addDefFileNameToDir(String dir) {
		if (dir.endsWith(StorageImp.DIV)) {
			return dir + StorageImp.DEFAULT_SAVE_DIR;
		} else {
			return dir + StorageImp.DIV + StorageImp.DEFAULT_SAVE_DIR;
		}
	}
}
