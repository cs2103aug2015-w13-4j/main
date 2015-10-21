package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import utilities.Exceptions.TaskIDNotExistException;
import utilities.TaskEvent;


class FileManager {
	private static final String SAVE_DIRECTORY = "user_tasks.txt";
	private File file;
	private BufferedWriter bw;
	//private BufferedReader br;

	public FileManager(String directory) throws NullPointerException, IOException {
		file = new File(directory);
		boolean hasCreated = file.createNewFile();
		//br = new BufferedReader(new FileReader(file));
		bw = new BufferedWriter(new FileWriter(file, true));
		if (hasCreated) {
			if (directory.equals(Storage.CONFIG_PATH)) {
				writeFirstLine(SAVE_DIRECTORY);
			} else {
				writeFirstLine("0");
			}
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
				return br.readLine();
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
				System.out.println("firstline: "+line);
				while ((line = br.readLine()) != null) {
					System.out.println(line);
					array.add(line);
				}
			}
		} catch (IOException ioe) {
			throw ioe;
		}
		return array;
	}

	public void addTask(int taskID, String taskInfo) throws IOException {
		bw.write(String.valueOf(taskID) + Storage.TOKEN + taskInfo);
		bw.newLine();
		bw.flush();
	}
	/**
	 * This methods edits the task info that stores as text on the disk. Note that only
	 * int and Strings are passed, which means that you have to convert a TaskDate to
	 * String before calling this method.
	 * @param taskID
	 * @param field use TaskEvent.fieldToString() to convert
	 * @param newContent convert date before pass
	 * @throws Exception
	 */
	public void editTask(int taskID, String field, String newContent) throws Exception {
		ArrayList<String> temp = new ArrayList<String>();
		field = field.toLowerCase();
		if (field.equals("priority")) {
			newContent = TaskEvent.priorityToIntString(newContent);
			System.out.println(newContent);
		}
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			if (!br.ready()) {
				return;
			} else {
				String line = br.readLine();
				if (Integer.parseInt(line) < taskID) {
					throw new TaskIDNotExistException("You don't have that many tasks!");
				}
				temp.add(line);
				while ((line = br.readLine()) != null) {
					if (!startsWith(line, String.valueOf(taskID))) {
						temp.add(line);
					} else {
						int start = line.indexOf(field);
						int end = line.indexOf(Storage.TOKEN, start);
						String front = line.substring(0, start);
						String mid = field + ":" + newContent;

						if (field.equals("priority")) {
							temp.add(front + mid);
						} else {
							String tail = line.substring(end, line.length());
							temp.add(front + mid + tail);
						}
					}
				}
				try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
					raf.setLength(0);
				} catch (IOException ioe) {
					;
				}
				try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
					for (String str : temp) {
						out.write(str);
						out.newLine();
					}
				} catch (IOException ioe) {
					;
				}
			}
		} catch (IOException ioe) {
			;
		}
	}

	private boolean startsWith(String line, String start) {
		int till = line.indexOf(Storage.TOKEN);
		if (till != -1 && line.substring(0, till).equals(start)) {
			return true;
		}
		return false;
	}

	public void deleteTask(int taskID) throws Exception {
		ArrayList<String> temp = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			if (!br.ready()) {
				return;
			} else {
				String line = br.readLine();
				if (Integer.parseInt(line) < taskID) {
					throw new TaskIDNotExistException("You don't have that many tasks!");
				}
				temp.add(line);
				while ((line = br.readLine()) != null) {
					if (!line.startsWith(String.valueOf(taskID))) {
						temp.add(line);
					} else { }
				}
				try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
					raf.setLength(0);
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
					for (String str : temp) {
						out.write(str);
						out.newLine();
					}
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void clearFile() {
		try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
			raf.setLength(0);
		} catch (IOException ioe) {
			System.out.println("error");
		}
	}

	private void writeFirstLine(String str) throws IOException {
		bw.write(str);
		bw.newLine();
		bw.flush();
	}

	public void setSavingDirectory(String dir) {
		// TODO Auto-generated method stub
	}

	public void setTaskIDCounter(int i) throws IOException {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(String.valueOf(i));
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			if (!br.ready()) {
				return;
			} else {
				String line = br.readLine();
				while ((line = br.readLine()) != null) {
					temp.add(line);
				}
				try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
					raf.setLength(0);
				} catch (IOException ioe) {
					;
				}
				try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
					for (String str : temp) {
						out.write(str);
						out.newLine();
					}
				} catch (IOException ioe) {
					;
				}
			}
		} catch (IOException ioe) {
			;
		}
	}
}
