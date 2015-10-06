package database;

import java.io.*;
import java.util.*;

class FileManager {
	private static final String SAVE_DIRECTORY = "database.txt";
	private File file;

	public FileManager(String directory) throws NullPointerException {
		file = new File(directory);
		if (directory.equals(Storage.CONFIG_PATH) && !file.exists()) {
			writeFirstLine(SAVE_DIRECTORY);
		} else {
			writeFirstLine("0");
		}
	}

	public String getSavingPath() throws IOException {
		return getFirstLine();
	}

	public int getTaskIDCounter() throws IOException, NumberFormatException {
		return Integer.parseInt(getFirstLine());
	}

	private String getFirstLine() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			if (!br.ready()) {
				return "";
			} else {
				String line = br.readLine();
				return line;
			}
		} catch (IOException ioe) {
			throw ioe;
		}
	}

	public ArrayList<String> loadTasks() throws IOException {
		ArrayList<String> array = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			if (!br.ready()) {
				return null;
			} else {
				String line = br.readLine(); // eat the first line
				while ((line = br.readLine()) != null) {
					array.add(line);
				}
			}
		} catch (IOException ioe) {
			throw ioe;
		}
		return array;
	}

	public void addTask(int taskID, String taskInfo) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write(String.valueOf(taskID) + Storage.TOKEN + taskInfo);
		} catch (IOException ioe) {
			throw ioe;
		}
	}

	public void editTask(int taskID, String field, String newContent) throws Exception {
		ArrayList<String> temp = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			if (!br.ready()) {
				return;
			} else {
				String line = br.readLine();
				while ((line = br.readLine()) != null) {
					if (!line.startsWith(String.valueOf(taskID))) {
						temp.add(line);
					} else {
						int start = line.indexOf(field);
						int end = line.indexOf(Storage.TOKEN, start);
						String front = line.substring(0, start);
						String mid = field + ":" + newContent;
						String tail = line.substring(end, line.length());
						temp.add(front+mid+tail);
					}
				}
				try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
					raf.setLength(0);
				} catch (IOException ioe) {
					;
				}
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
					for (String str: temp) {
						bw.write(str);
						bw.newLine();
					}
				} catch (IOException ioe) {
					;
				}
			}
		}
	}

	public void deleteTask(int taskID) throws Exception {

	}
	
	private void writeFirstLine(String str) {
		
	}

	public void setSavingDirectory(String dir) {
		// TODO Auto-generated method stub
		
	}
}
