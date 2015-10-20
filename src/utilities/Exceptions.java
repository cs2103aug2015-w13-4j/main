package utilities;

public class Exceptions {
	public static class TaskIDNotExistException extends Exception {
		private static final long serialVersionUID = 3864573618902880956L;
		public TaskIDNotExistException(String message) {
			super(message);
		}
	}
}
