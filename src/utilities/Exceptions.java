package utilities;

//@@author A0130909H Shirlene
/**
 * Handles all exception
 * @author Shirlene
 *
 */
public class Exceptions {
	public static class TaskIDNotExistException extends Exception {
		private static final long serialVersionUID = 1;

		public TaskIDNotExistException(String message) {
			super(message);
		}
	}

	public static class OperationNotPerformed extends Exception {
		private static final long serialVersionUID = 2;

		public OperationNotPerformed(String message) {
			super(message);
		}
	}

	public static class EditFieldNotFound extends Exception {
		private static final long serialVersionUID = 3;

		public EditFieldNotFound(String message) {
			super(message);
		}
	}

	public static class CommandNotFound extends Exception {
		private static final long serialVersionUID = 4;

		public CommandNotFound(String message) {
			super(message);
		}
	}
}
