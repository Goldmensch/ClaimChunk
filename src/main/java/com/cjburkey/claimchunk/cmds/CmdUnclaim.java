package com.cjburkey.claimchunk.cmds;

import org.bukkit.entity.Player;
import com.cjburkey.claimchunk.cmd.ICommand;
import com.cjburkey.claimchunk.cmd.MainHandler;

public class CmdUnclaim implements ICommand {

	public String getCommand() {
		return "unclaim";
	}

	public String getDescription() {
		return "Unclaim the chunk you're standing in.";
	}

	public String[] getPermittedArguments() {
		return new String[] {  };
	}

	public int getRequiredArguments() {
		return 0;
	}

	public void onCall(Player executor, String[] args) {
		MainHandler.unclaimChunk(executor);
	}
	
}