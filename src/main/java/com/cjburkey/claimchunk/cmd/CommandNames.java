package com.cjburkey.claimchunk.cmd;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class CommandNames {
    private FileConfiguration config;
    private final String path;
    private File file;

    @Command
    private final String cmdAccess = "access";
    @Command
    private final String cmdAdminOverride = "adminoverride";
    @Command
    private final String cmdAdminUnclaim = "adminunclaim";
    @Command
    private final String getCmdAdminUnclaimAll = "adminunclaimall";
    @Command
    private final String cmdAlert = "alert";
    @Command
    private final String cmdAuto = "auto";
    @Command
    private final String cmdClaim = "claim";
    @Command
    private final String cmdGive = "give";
    @Command
    private final String cmdHelp = "help";
    @Command
    private final String cmdInfo = "info";
    @Command
    private final String cmdList = "list";
    @Command
    private final String cmdName = "name";
    @Command
    private final String cmdReload = "reload";
    @Command
    private final String cmdShow = "show";
    @Command
    private final String cmdTnt = "tnt";
    @Command
    private final String cmdUnclaim = "unclaim";
    @Command
    private final String getCmdUnclaimAll = "unclaimall";

    @CmdArgument
    private final String argPlayer = "player";
    @CmdArgument
    private final String argAcrossAllWorlds = "acrossAllWorlds";
    @CmdArgument
    private final String argNewName= "newName";
    @CmdArgument
    private final String argCommand = "command";
    @CmdArgument
    private final String argPage = "page";
    @CmdArgument
    private final String argTrue = "true";
    @CmdArgument
    private final String argFalse = "false";
    @CmdArgument
    private final String argSeconds = "seconds";


    public CommandNames(String path) {
        this.path = path;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void init(){
        file = new File(path + File.separator + "commands.yml");
        try {
            file.createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        config = YamlConfiguration.loadConfiguration(file);
        addSingleOption("main-command.main", "chunk");

        addSingleOption("main-command.aliases", Arrays.asList("claimchunk"));
        buildConfig(Command.class, Path.COMMAND);
        buildConfig(CmdArgument.class, Path.ARGUMENT);
        save();

    }

    private void addSingleOption(String path, Object defaultValue) {
        if(!config.contains(path)) {
            config.set(path, defaultValue);
        }
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void buildConfig(Class<? extends Annotation> annotationClass, Path path) {
        try {
            List<String> elements = new ArrayList<>();
            for(Field c : getClass().getDeclaredFields()) {
                if(c.isAnnotationPresent(annotationClass)) {
                    String cmd = (String) c.get(this);
                    elements.add(cmd);
                    if(!config.getKeys(true).contains(path.getValue()+cmd)) {
                        config.set(path.getValue()+cmd, cmd);
                    }
                }
            }

            for(String c : Objects.requireNonNull(config.getConfigurationSection(path.getValue())).getKeys(true)) {
                if(!elements.contains(c)){
                    config.set(path.getValue()+c, null);
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public String get(String key, Path path) {
        return config.getString(path.getValue() + key);
    }

    public String getMainCommand() {
        return config.getString("main-command.main");
    }

    public List<String> getAliases() {
        return config.getStringList("main-command.aliases");
    }

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Command {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface CmdArgument {
}


