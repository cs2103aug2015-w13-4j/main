package utilities;

//@@author A0130909H Shirlene
/**
 * Handles all exception
 * 
 * @author Shirlene
 *
 */
public class Exceptions {
	/**
	 * Exception for when taskid does not exist in file
	 *
	 */
	public static class TaskIDNotExistException extends Exception {
		private static final long serialVersionUID = 404514596737087454L;

		public TaskIDNotExistException(String message) {
			super(message);
		}
	}

	/**
	 * Exception for when operation is not performed
	 *
	 */
	public static class OperationNotPerformed extends Exception {
		private static final long serialVersionUID = -5066490802491867540L;

		public OperationNotPerformed(String message) {
			super(message);
		}
	}

	/**
	 * Exception when edit field cannot be found
	 *
	 */
	public static class EditFieldNotFound extends Exception {
		private static final long serialVersionUID = -452260938938082504L;

		public EditFieldNotFound(String message) {
			super(message);
		}
	}

	/**
	 * Exception when command user input does not exist
	 *
	 */
	public static class CommandNotFound extends Exception {
		private static final long serialVersionUID = -2444651689762170226L;

		public CommandNotFound(String message) {
			super(message);
		}
	}
}
