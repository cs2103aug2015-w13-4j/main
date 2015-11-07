package logic;

import java.util.logging.Level;
import java.util.logging.Logger;

import database.StorageImp;
import utilities.CommandElements;
import utilities.Command_Type;
//@@Shirlene 
public class Redo {
	private static Logger logger;
	StorageImp storage;

	public Redo(){
		logger = Logger.getLogger("redo");
		storage = Launch.getStorage();
	}
	public boolean redoTask(CommandElements content){
		
		logger.log(Level.INFO, "starting to redo");
		Command_Type type = content.getType();
		switch(type){
		case ADD_TASK:
			return redoAdd(content);
		case DELETE_TASK:
			return redoDelete(content);
		case EDIT_TASK:
			return redoEdit(content);
		case FINISH_TASK:
			return redoComplete(content);
		case UNFINISH_TASK:
			return redoUnfinish(content);
		case DIRECTORY:
			return redoDirectory(content);
		case FLAG_TASK:
			return redoFlag(content);
		case UNFLAG_TASK:
			return redoUnflag(content);
		default :
			return false;
		}
	}
	private boolean redoAdd(CommandElements content){
		logger.log(Level.INFO, "redo add");
		return storage.undoDeleteTaskByID(content.getID());
	}
	private boolean redoDelete(CommandElements content){
		logger.log(Level.INFO, "redo delete");
		return storage.deleteTaskByID(content.getID());
	}
	private boolean redoEdit(CommandElements content){
		logger.log(Level.INFO, "redo edit");
		return storage.editTask(content.getID(), content.getField(), content.getName());
	}
	private boolean redoComplete(CommandElements content){
		logger.log(Level.INFO, "redo Complete");
		return storage.markTaskAsDone(content.getID());
	}
	private boolean redoUnfinish(CommandElements content){
		logger.log(Level.INFO, "redo unfinish");
		return storage.markTaskAsUndone(content.getID());
	}
	private boolean redoDirectory(CommandElements content){
		logger.log(Level.INFO, "redo change directory");
		return storage.changeDirectory(content.getName());
	}
	private boolean redoFlag(CommandElements content){
		logger.log(Level.INFO, "redo flag");
		return storage.flagTask(content.getID());
	}
	private boolean redoUnflag(CommandElements content){
		logger.log(Level.INFO,"undo unflag");
		return storage.unflagTask(content.getID());
	}
	
}