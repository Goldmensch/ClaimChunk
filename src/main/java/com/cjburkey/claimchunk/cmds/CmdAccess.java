package com.cjburkey.claimchunk.cmds;

import com.cjburkey.claimchunk.ClaimChunk;
import com.cjburkey.claimchunk.Utils;
import com.cjburkey.claimchunk.cmd.Argument;
import com.cjburkey.claimchunk.cmd.ICommand;
import com.cjburkey.claimchunk.cmd.Path;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdAccess implements ICommand {

    @Override
    public String getCommand(ClaimChunk claimChunk) {
        return claimChunk.getCommandNames().get("access", Path.COMMAND);
    }

    @Override
    public String getDescription(ClaimChunk claimChunk) {
        return claimChunk.getMessages().cmdAccess;
    }

    @Override
    public boolean hasPermission(ClaimChunk claimChunk, CommandSender sender) {
        return Utils.hasPerm(sender, true, "access");
    }

    @Override
    public String getPermissionMessage(ClaimChunk claimChunk) {
        return claimChunk.getMessages().accessNoPerm;
    }

    @Override
    public Argument[] getPermittedArguments(ClaimChunk claimChunk) {
        return new Argument[]{new Argument(claimChunk.getCommandNames().get("player", Path.ARGUMENT), Argument.TabCompletion.OFFLINE_PLAYER)};
    }

    @Override
    public int getRequiredArguments(ClaimChunk claimChunk) {
        return 0;
    }

    @Override
    public boolean onCall(ClaimChunk claimChunk, String cmdUsed, Player executor, String[] args) {
        if (args.length == 0) {
            claimChunk.getCommandHandler().mainHandler.listAccessors(executor);
        } else {
            claimChunk.getCommandHandler().mainHandler.accessChunk(executor, args[0].split(","));
        }
        return true;
    }

}
