package logic;

import utilities.CommandElements;
import utilities.Command_Type;

import database.StorageImp;

import java.util.logging.Level;
import java.util.logging.Logger;

//@@author A0130909H Shirlene
/**
 * This class is to perform the undo command
 * 
 * @author Shirlene
 *
 */
public class Undo {
	private static Logger logger;
	StorageImp storage;

	// ================================================================
	// DEFAULT CONSTRUTOR
	// ================================================================
	public Undo() {
		logger = Logger.getLogger("Undo");
		storage = Launch.getStorage();
	}

	// ================================================================
	// MAIN UNDO METHOD
	// ================================================================
	public boolean undoTask(CommandElements content) {
		logger.log(Level.INFO, "starting to undo");
		Command_Type type = content.getType();
		switch (type) {
		case ADD_TASK:
			return undoAdd(content);
		case DELETE_TASK:
			return undoDelete(content);
		case EDIT_TASK:
			return undoEdit(content);
		case FINISH_TASK:
			return undoComplete(content);
		case UNFINISH_TASK:
			return undoUnfinish(content);
		case DIRECTORY:
			return undoDirectory(content);
		case FLAG_TASK:
			return undoFlag(content);
		case UNFLAG_TASK:
			return undoUnflag(content);
		default:
			return false;
		}
	}

	// ================================================================
	// HELPER METHOD FOR UNDO
	// ================================================================
	private boolean undoAdd(CommandElements content) {
		logger.log(Level.INFO, "undo add" + content.getID());
		return storage.deleteTaskByID(content.getID());
	}

	private boolean undoDelete(CommandElements content) {
		logger.log(Level.INFO, "undo delete");
		return storage.undoDeleteTaskByID(content.getID());
	}

	private boolean undoEdit(CommandElements content) {
		logger.log(Level.INFO, "undo edit");
		return storage.editTask(content.getID(), content.getField(), content.getName());
	}

	private boolean undoComplete(CommandElements content) {
		logger.log(Level.INFO, "undo Complete");
		return storage.markTaskAsUndone(content.getID());
	}

	private boolean undoUnfinish(CommandElements content) {
		logger.log(Level.INFO, "undo unfinish");
		return storage.markTaskAsDone(content.getID());
	}

	private boolean undoDirectory(CommandElements content) {
		logger.log(Level.INFO, "undo Change");
		return storage.changeDirectory(content.getName());
	}

	private boolean undoFlag(CommandElements content) {
		logger.log(Level.INFO, "undo flag");
		return storage.unflagTask(content.getID());
	}

	private boolean undoUnflag(CommandElements content) {
		logger.log(Level.INFO, "undo unflag");
		return storage.flagTask(content.getID());
	}
}