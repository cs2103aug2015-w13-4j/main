package cs2103t;

import java.util.ArrayList;

public class FileContent {
	
	// constructor 
	public FileContent() {
		// doing nothing
	}

	public static ArrayList<TaskEvent> importContent(ArrayList<String> rawContent) {
		if (rawContent.size()%5!=0) {
			System.out.println("import failed");
			return new ArrayList<TaskEvent>();
		}
		
		ArrayList<String[]> textGroup = new ArrayList<String[]>();
		String[] text = new String[5];
		ArrayList<TaskEvent> resultList = new ArrayList<TaskEvent>();
		
		for (int i = 0; i < rawContent.size()/5; i ++) {
			text = new String[5];
			for (int j = 0; j < 5; j ++) {
				text[j] = rawContent.get(i*5 + j);
			}
			textGroup.add(text);
		}
		for (int i = 0; i < textGroup.size(); i ++) {
			String name = textGroup.get(i)[0];
			TaskDate date = InputDecoder.dateDecoder(textGroup.get(i)[1]);
			int prio = Integer.parseInt(textGroup.get(i)[2]);
			boolean recur = Boolean.parseBoolean(textGroup.get(i)[3]);
			String des = textGroup.get(i)[4];
			resultList.add(new TaskEvent(name, date, prio, des, recur));
		}
		return resultList;
	}
	
	public static ArrayList<String> exportContent(ArrayList<TaskEvent> rawEvents) {
		ArrayList<String> resultList = new ArrayList<String>();
		
		for (int i = 0; i < rawEvents.size() - 1; i ++) {
			resultList.add(rawEvents.get(i).printToFile() + "\n");
		}
		resultList.add(rawEvents.get(rawEvents.size() - 1).printToFile());
		return resultList;
	}
}
