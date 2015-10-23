package database;

/**
 * Created by zhongwei-z on 23/10/15.
 */
public class XMLio {
	private static String DEFAULT_SAVE_FILE = "tasks.json";
	private static String DEFAULT_PREF_FILE = ".pref";

	XMLio saveFile = new XMLio(DEFAULT_SAVE_FILE);
	XMLio prefFile = new XMLio(DEFAULT_PREF_FILE);

	public XMLio getSaveInstance() { return saveFile; }
	public XMLio getPrefInstance() { return prefFile; }

	private XMLio(String dir) {

	}
}
