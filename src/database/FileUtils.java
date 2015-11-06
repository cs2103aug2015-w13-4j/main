package database;

import java.io.*;

// @@author A0130503B
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

	public static String getFirstLine(String filename)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		String string = br.readLine();
		br.close();
		return string;
	}

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
}
