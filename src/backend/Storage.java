package backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
	protected static final String DEFAULT_SAVE_DIRECTORY;
	protected static final String ERROR_CREATE_SAVE_DIRECTORY;

	private String saveDir;

	public Storage(String saveDir) {
		this.saveDir = saveDir;
	}

	public Storage() {
		this.saveDir = DEFAULT_SAVE_DIRECTORY;
		createSaveDirectory(DEFAULT_SAVE_DIRECTORY);
	}

	private void createSaveDirectory(String directory) {
		try {
			File dir = new File(directory);
			if (!dir.exists()) {
				dir.mkdir();
			}
		} catch (IOException ioe) {
			System.out.println(ERROR_CREATE_SAVE_DIRECTORY);
		}
	}

	public ArrayList<TaskEvent> load(String dir) {
		return null;
	}

	public boolean save(ArrayList<String> data) {
		return true;
	}

	public boolean edit(long taskID, String editField, String editContent) {
		return true;
	}

	public boolean delete(long taskID) {
		return true;
	}

}

