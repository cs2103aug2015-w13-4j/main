package utilities;

import java.util.ArrayList;

public class CommandElements {
	private Command_Type command;
	private ArrayList<String> content;
	public CommandElements(Command_Type cmd, ArrayList<String> content){
		this.command= cmd;
		this.content = content;
	}
	public Command_Type getCommand(){
		return command;
	}
	public ArrayList<String> getContent(){
		return content;
	}
}
