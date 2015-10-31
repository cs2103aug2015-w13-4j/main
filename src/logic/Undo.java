package logic;

import java.util.logging.Level;
import java.util.logging.Logger;

import database.StorageImp;
import utilities.CommandElements;
import utilities.Command_Type;

public class Undo {
	private static Logger logger;
	StorageImp storage;

	public Undo(){
		logger = Logger.getLogger("Undo");
		storage = Launch.getStorage();
	}
	public boolean UndoTask(CommandElements content){
		
		logger.log(Level.INFO, "starting to undo");
		Command_Type type = content.getType();
		switch(type){
		case ADD_TASK:
			return UndoAdd(content);
		case DELETE_TASK:
			return UndoDelete(content);
		case EDIT_TASK:
			return UndoEdit(content);
		case FINISH_TASK:
			return UndoComplete(content);
		case DIRECTORY:
			return UndoDirectory(content);
		case FLAG_TASK:
			return UndoFlag(content);
		case UNFLAG_TASK:
			return UndoUnflag(content);
		default :
			return false;
		}
	}
	private boolean UndoAdd(CommandElements content){
		logger.log(Level.INFO, "undo add");
		return storage.deleteTaskByID(content.getID());
	}
	private boolean UndoDelete(CommandElements content){
		logger.log(Level.INFO, "undo delete");
		return storage.undoDeleteTaskByID(content.getID());
	}
	private boolean UndoEdit(CommandElements content){
		logger.log(Level.INFO, "undo edit");
		return storage.editTask(content.getID(), content.getField(), content.getName());
	}
	private boolean UndoComplete(CommandElements content){
		logger.log(Level.INFO, "undo Complete");
		return storage.markTaskAsUndone(content.getID());
	}
	private boolean UndoDirectory(CommandElements content){
		//not implemented
		return false;
	}
	private boolean UndoFlag(CommandElements content){
		logger.log(Level.INFO, "undo flag");
		return storage.unflagTask(content.getID());
	}
	private boolean UndoUnflag(CommandElements content){
		logger.log(Level.INFO,"undo unflag");
		return storage.flagTask(content.getID());
	}
	
}