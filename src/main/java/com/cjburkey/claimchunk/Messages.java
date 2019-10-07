package com.cjburkey.claimchunk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

public class Messages {

    private transient static Gson gson;

    public String errEnterValidNum = "&aPlease enter a valid number";
    public String noPluginPerm = "&cYou do not have permission to use ClaimChunk.";
    public String claimNoPerm = "&cYou do not have permission to claim chunks.";
    public String claimLocationBlock = "&cYou cannot claim chunks here.";
    public String claimAlreadyOwned = "&cThis chunk is already claimed.";
    public String claimTooMany = "&cYou own the maximum number of chunks.";
    public String claimSuccess = "&aChunk claimed for %%PRICE%%!";
    public String claimNoCost = "&aFree";
    public String claimFree1 = "&aFirst chunk is free!";
    public String claimFrees = "&aFirst %%COUNT%% chunks are free!";
    public String claimNotEnoughMoney = "&cYou do not have enough money.";
    public String unclaimNoPerm = "&cYou do not have permission to unclaim chunks.";
    public String unclaimNotOwned = "&cThis chunk is not owned.";
    public String unclaimNotOwner = "&cYou do not own this chunk.";
    public String unclaimSuccess = "&aChunk unclaimed!";
    public String unclaimRefund = "&aYou have been refunded %%AMT%%.";
    public String unclaimAll = "&aUnclaimed %%CHUNKS%% chunks.";
    public String accessNoPerm = "&cYou do not have permission to give access to chunks.";
    public String accessHas = "&a%%PLAYER%% now has access to your chunks.";
    public String accessNoLongerHas = "&a%%PLAYER%% no longer has access to your chunks.";
    public String accessToggleMultiple = "&aThe provided players'' access to your chunks has been toggled.";
    public String accessNoPlayer = "&cThat player has not joined the server before.";
    public String accessOneself = "&cYou already have access to your own chunks.";
    public String tntNoPerm = "&cYou do not have permission to toggle TNT in this chunk.";
    public String tntAlreadyEnabled = "&cTNT is already enabled in the config.";
    public String tntEnabled = "&aTNT has been enabled in this chunk.";
    public String tntDisabled = "&aTNT has been disabled in this chunk.";
    public String nameClear = "&aYour name has been cleared.";
    public String nameNotSet = "&cYou do not have a custom name set.";
    public String nameSet = "&aYour name has been set: %%NAME%%.";
    public String chunkOwner = "&6Entering the territory of %%PLAYER%%.";
    public String unknownChunkOwner = "&6Entering claimed territory.";
    public String chunkSelf = "&6Entering your own territory.";
    public String chunkLeave = "&6Exiting the territory of %%PLAYER%%.";
    public String chunkLeaveUnknown = "&6Entering unclaimed territory.";
    public String chunkLeaveSelf = "&6Exiting your territory.";
    public String chunkNoEdit = "&cYou cannot edit chunks owned by %%PLAYER%%.";
    public String autoNoPerm = "&cYou may not auto-claim chunks.";
    public String autoEnabled = "&aAutomatic claiming enabled.";
    public String autoDisabled = "&aAutomatic claiming disabled.";
    public String reloadNoPerm = "&cYou do not have permission to use that sub command";
    public String playerEnterChunk = "&6%%PLAYER%% has entered your claimed chunk";
    public String enabledAlerts = "&aEnabled alerts";
    public String disabledAlerts = "&aDisabled alerts";
    public String helpTitle = "ClaimChunk Help";
    public String helpCommandTitle = "%%CMD%% Help";
    public String infoTitle = "Chunk Information";
    public String infoPosition = "Chunk position: &l%%X%%, %%Z%% in %%WORLD%%";
    public String infoOwnerUnknown = "&7Unknown";
    public String infoOwner = "Chunk owner: &l%%PLAYER%%";
    public String infoNameNone = "&7None";
    public String infoName = "Chunk name: &l%%NAME%%";
    public String claimsTitle = "Claims for %%NAME%% in %%WORLD%%";
    public String claimsChunk = "%%X%%, %%Z%%";
    public String claimsPagination = "Page %%PAGE%% of %%MAXPAGE%%";

    static Messages load(File file) throws IOException {
        // Load or create new
        Messages messages = (file.exists())
                ? getGson().fromJson(String.join("", Files.readAllLines(file.toPath(), StandardCharsets.UTF_8)), Messages.class)
                : new Messages();

        // Write it so new messages are written
        Files.write(file.toPath(),
                Collections.singletonList(getGson().toJson(messages)),
                StandardCharsets.UTF_8, StandardOpenOption.CREATE);

        return messages;
    }

    private static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .disableHtmlEscaping()
                    .create();
        }
        return gson;
    }

}