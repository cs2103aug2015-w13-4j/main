package parser;

import utilities.TaskTime;

//@@A0133965X
public class TimeParser {

	/**
	 * Translate time Strings in number format
	 * 
	 * @param the String containing time information
	 * @return TaskTime object
	 */
	public static TaskTime timeDecoder(String piece) throws NumberFormatException{
		if (piece.toLowerCase().contains("am") && !piece.toLowerCase().contains(":")) {
			if (piece.length() == 3) {
				int hour = Integer.parseInt(piece.charAt(0) + "");
				if (hour > 0) {
					return new TaskTime(hour, 0);
				}
			} else if (piece.length() == 4) {
				int hour = Integer.parseInt(piece.substring(0,2));
				if (hour < 12) {
					return new TaskTime(hour, 0);
				} else if (hour == 12) {
					return new TaskTime(0,0);
				}
			}
		} else if (piece.toLowerCase().contains("pm") && !piece.toLowerCase().contains(":")) {
			if (piece.length() == 3) {
				int hour = Integer.parseInt(piece.charAt(0) + "");
				if (hour > 0) {
					return new TaskTime(hour + 12, 0);
				}
			} else if (piece.length() == 4) {
				int hour = Integer.parseInt(piece.substring(0,2));
				if (hour < 12) {
					return new TaskTime(hour + 12, 0);
				} else if (hour == 12) {
					return new TaskTime(12, 0);
				}
			}
		} else if (piece.toLowerCase().contains(":")) {
			int pos = piece.indexOf(":");
			if (pos == 1) {
				int hour = Integer.parseInt(piece.charAt(0) + "");
				if (piece.length() == 4) {
					int minute = Integer.parseInt(piece.substring(2,4));
					return new TaskTime(hour, minute);
				} else if (piece.length() == 3) {
					int minute = Integer.parseInt(piece.substring(2,3));
					return new TaskTime(hour, minute);
				} else if (piece.length() == 6) {
					if (piece.toLowerCase().substring(4,6).equals("pm")) {
						if (hour != 12) {
							hour += 12;
						}
					}
					int minute = Integer.parseInt(piece.substring(2,4));
					return new TaskTime(hour, minute);
				} else if (piece.length() == 5) {
					if (piece.toLowerCase().substring(3,5).equals("pm")) {
						if (hour != 12) {
							hour += 12;
						}
					}
					int minute = Integer.parseInt(piece.substring(2,3));
					return new TaskTime(hour, minute);
				}
			} else if (pos == 2) {
				int hour = Integer.parseInt(piece.substring(0,2));
				if (piece.length() == 5) {
					int minute = Integer.parseInt(piece.substring(3,5));
					return new TaskTime(hour, minute);
				} else if (piece.length() == 4) {
					int minute = Integer.parseInt(piece.substring(3,4));
					return new TaskTime(hour, minute);
				} else if (piece.length() == 7) {
					if (piece.toLowerCase().substring(5,7).equals("pm")) {
						if (hour != 12) {
							hour += 12;
						}
					}
					int minute = Integer.parseInt(piece.substring(3,5));
					return new TaskTime(hour, minute);
				} else if (piece.length() == 6) {
					if (piece.toLowerCase().substring(4,6).equals("pm")) {
						if (hour != 12) {
							hour += 12;
						}
					}
					int minute = Integer.parseInt(piece.substring(3,4));
					return new TaskTime(hour, minute);
				} 
			}
		}
		return null;
	} 
}
