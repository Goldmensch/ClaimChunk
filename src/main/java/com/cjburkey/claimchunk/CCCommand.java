package com.cjburkey.claimchunk;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CCCommand extends BukkitCommand {
    private final CommandExecutor commandExecutor;
    private final TabCompleter tabCompleter;

    protected CCCommand(@NotNull String name, CommandExecutor executor, TabCompleter tabCompleter) {
        super(name);
        this.commandExecutor = executor;
        this.tabCompleter = tabCompleter;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, String[] args) {
        return Objects.requireNonNull(tabCompleter.onTabComplete(sender, this, alias, args));
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        return commandExecutor.onCommand(sender, this, commandLabel, args);
    }

    @SuppressWarnings("unused")
    public void setProperties(Map<String, Object> properties) {
        for(Map.Entry<String, Object> c : properties.entrySet()) {
            setProperty(c.getKey(), c.getValue());
        }
    }

    public void setProperty(String name, Object value) {
        switch (name) {
            case "aliases":
                @SuppressWarnings("unchecked") List<String> aliases = (List<String>) value;
                this.setAliases(aliases);
                break;
            case "usage":
                this.setUsage((String) value);
                break;
            case "description":
                this.setDescription((String) value);
                break;
            case "permission":
                this.setPermission((String) value);
                break;
        }
    }

    public void register() {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(getName(), this);
        }catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
