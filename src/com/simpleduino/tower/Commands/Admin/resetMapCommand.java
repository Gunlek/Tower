package com.simpleduino.tower.Commands.Admin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.simpleduino.tower.TowerPlugin;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Created by Simple-Duino on 20/06/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class resetMapCommand {

    File f = new File("plugins/Tower/config.yml");
    YamlConfiguration cf = YamlConfiguration.loadConfiguration(f);

    public resetMapCommand(CommandSender sender)
    {
        String[] lobby = cf.get("returnLobby").toString().replace(" ", "").split(",");
        sender.sendMessage(ChatColor.GREEN + "Reset de la map en cours...");
        for(Player p : Bukkit.getOnlinePlayers())
        {
            p.getInventory().clear();
            p.teleport(p.getLocation().getWorld().getSpawnLocation());
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(lobby[0]);

            p.sendPluginMessage(TowerPlugin.getPlugin(TowerPlugin.class), "BungeeCord", out.toByteArray());
        }

        Bukkit.unloadWorld(cf.get("main.main-worldName").toString(), false);
        File saveMap = new File(cf.get("main.save-worldName").toString());
        File restoreMap = new File(cf.get("main.main-worldName").toString());
        try {
            FileUtils.deleteDirectory(restoreMap);
            restoreMap.mkdir();
            FileUtils.copyDirectory(saveMap, restoreMap);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "restart");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
