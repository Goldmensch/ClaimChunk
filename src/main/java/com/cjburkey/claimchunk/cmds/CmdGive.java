package com.cjburkey.claimchunk.cmds;

import com.cjburkey.claimchunk.ClaimChunk;
import com.cjburkey.claimchunk.Utils;
import com.cjburkey.claimchunk.cmd.Argument;
import com.cjburkey.claimchunk.cmd.ICommand;
import com.cjburkey.claimchunk.cmd.Path;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdGive implements ICommand {

    @Override
    public String getCommand(ClaimChunk claimChunk) {
        return claimChunk.getCommandNames().get("give", Path.COMMAND);
    }

    @Override
    public String getDescription(ClaimChunk claimChunk) {
        return claimChunk.getMessages().cmdGive;
    }

    @Override
    public boolean hasPermission(ClaimChunk claimChunk, CommandSender sender) {
        return Utils.hasPerm(sender, true, "claim");
    }

    @Override
    public String getPermissionMessage(ClaimChunk claimChunk) {
        return claimChunk.getMessages().giveNoPerm;
    }

    @Override
    public Argument[] getPermittedArguments(ClaimChunk claimChunk) {
        return new Argument[]{
                new Argument(claimChunk.getCommandNames().get("player", Path.ARGUMENT), Argument.TabCompletion.OFFLINE_PLAYER),
        };
    }

    @Override
    public int getRequiredArguments(ClaimChunk claimChunk) {
        return 1;
    }

    @Override
    public boolean onCall(ClaimChunk claimChunk, String cmdUsed, Player executor, String[] args) {
        claimChunk.getCommandHandler().mainHandler.giveChunk(executor, executor.getLocation().getChunk(), args[0]);
        return true;
    }

}
