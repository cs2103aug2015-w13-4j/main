package logic;

import java.util.logging.Level;
import java.util.logging.Logger;

import database.Storage;
import utilities.CommandElements;
import utilities.Command_Priority;
import utilities.Command_Type;
import utilities.TaskDate;
import parser.CommandParser;
import logic.Launch;

public class Operation {

	private static Logger logger;

	public Operation() {
		logger = Logger.getLogger("Operation");
	}

	/*
	 * Main method to process the user input
	 */
	public String processOperation(String input) {
		logger.log(Level.INFO, "input recieved");
		// get the commandElements from parser
		Display message = Launch.getDisplay();
		CommandElements processed = CommandParser.ProcessInput(input);
		logger.log(Level.INFO, "input processed" + processed.getType());
		if (performCommand(processed.getType(), processed)) {
			logger.log(Level.INFO, "performing command" + processed.getType());
			return message.operation(processed.getType(), processed.getName());
		} else {
			logger.log(Level.INFO, "invalid input");
			return message.error(input);
		}
	}

	/**
	 * perform the command
	 * 
	 * @param command
	 * @param content
	 * @return
	 */
	private boolean performCommand(Command_Type command, CommandElements content) {
		Storage action = Launch.getStorage();
		Search search = Launch.getSearch();
		boolean isSuccessful;
		switch (command) {
		case ADD_TASK:
			logger.log(Level.INFO, "command is add");
			isSuccessful = action.addTask(content.getName(), content.getStartDate(),content.getEndDate(), getPriority(content.getPriority()));
			return isSuccessful;
		case EDIT_TASK:
			logger.log(Level.INFO, "command is edit");
			System.out.println("here");
			isSuccessful = action.editTask(content.getID(), content.getField().toString(), getEditContent(content));
			logger.log(Level.INFO, "success is " + isSuccessful);
			return isSuccessful;
		case DELETE_TASK:
			logger.log(Level.INFO, "command is delete");

		case FINISH_TASK:
			logger.log(Level.INFO, "command is completed");

		case SEARCH_TASK:
			logger.log(Level.INFO, "command is search");

			isSuccessful = search.searchWord(action.load(), content.getName());
			return isSuccessful;
		case UNDO:
			logger.log(Level.INFO, "command is undo");

		case DIRECTORY:
			logger.log(Level.INFO, "command is change directory");
		default:
			// throw exception
		}
		return false;
	}

	/**
	 * Converting Command_Priority to String
	 * 
	 * @param priority
	 *            is the Command_Priority user input
	 * @return String equality of Command_Priority
	 */
	private String getPriority(Command_Priority priority) {
		switch (priority) {
		case HIGH:
			return "high";
		case MEDIUM:
			return "medium";
		case LOW:
			return "low";
		}
		return "";
	}

	/*
	 * For the edit command. Get the field which user want to edit
	 */
	private String getEditContent(CommandElements content) {
		logger.log(Level.INFO, "finding edit command");

		switch (content.getField()) {
		case NAME:
			logger.log(Level.INFO, "edit name");
			return content.getName();
		case START_DATE:
			logger.log(Level.INFO, "edit start date");

			TaskDate startDate = content.getStartDate();
			return startDate.toString();
		case END_DATE:
			logger.log(Level.INFO, "edit end date");
			TaskDate endDate = content.getEndDate();
			return endDate.toString();
		case PRIORITY:
			logger.log(Level.INFO, "edit priority");
			return content.getPriority().toString();
		}
		return "";
	}
}
