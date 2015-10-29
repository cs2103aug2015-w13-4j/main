package utilities;

public class Exceptions {
	public static class TaskIDNotExistException extends Exception {
		private static final long serialVersionUID = 3864573618902880956L;
		public TaskIDNotExistException(String message) { super(message); }
	}

	public static class FileCorruptedException extends Exception {
		public FileCorruptedException(String message) { super(message); }
	}
	public static class OperationNotPerformed extends Exception{
		public OperationNotPerformed(String message){ super(message);}	
	}
	public static class EditFieldNotFound extends Exception{
		public EditFieldNotFound(String message) { super(message);}
	}
	public static class CommandNotFound extends Exception{
		public CommandNotFound(String message) { super(message);}
	}
}
