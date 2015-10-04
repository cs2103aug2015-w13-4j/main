package src.database;

import src.utilities.*;

import java.io.*;
import java.util.*;

class FileManager {
	private static final String LINE_BREAK = System.lineSepartor();
	private String directory;
	private File file;
	private int taskIDCounter;

	FileManager(String directory) throws IOException {
		this.directory = directory;
		file = new File(directory);
	}

	ArrayList<String> load() throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			if (!br.ready()) {
				return list;
			}
			taskIDCounter = Integer.parseInt(br.readLine());
			String taskInfo = "";
			while ((taskInfo = br.readLine()) != null) {
				list.add(taskInfo);
			}
		} catch (IOException ioe) {
			throw new LoadFromFileException(ioe);
		}
		return list;
	}

	void append(String string) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		out.write(string);
		out.write(LINE_BREAK);
	}

	int getTaskIDCounter() {
		return taskIDCounter;
	}
}

