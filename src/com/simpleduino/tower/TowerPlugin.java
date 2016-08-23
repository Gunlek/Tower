package com.simpleduino.tower;

import com.simpleduino.guild.GuildAPI.GuildAPI;
import com.simpleduino.tower.Commands.TowerCommands;
import com.simpleduino.tower.Commands.teamChatCommand;
import com.simpleduino.tower.Listeners.GameListener;
import com.simpleduino.tower.Listeners.PlayerListener;
import com.simpleduino.tower.Messaging.PluginListener;
import com.simpleduino.tower.Players.TowerPlayer;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by Simple-Duino on 20/06/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class TowerPlugin extends JavaPlugin {

    public static Permission permission = null;
    public static Chat chat = null;

    public void onEnable()
    {
        this.getServer().getPluginManager().registerEvents(new GameListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PluginListener());

        this.getServer().getPluginCommand("tower").setExecutor(new TowerCommands());
        this.getServer().getPluginCommand("teamchat").setExecutor(new teamChatCommand());

        File f1 = new File("plugins/Tower/config.yml");
        if(!f1.exists())
        {
            f1.getParentFile().mkdirs();
            try {
                f1.createNewFile();
                YamlConfiguration cf = YamlConfiguration.loadConfiguration(f1);
                cf.set("main.main-worldName", "world");
                cf.set("main.save-worldName", "worldSave");
                cf.set("returnLobby", "lb_1");
                cf.save(f1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File f = new File("plugins/Tower/spawnpoints.yml");
        if(!f.exists())
        {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for(Entity e : Bukkit.getWorld("tower").getEntities())
        {
            if(e.getType().equals(EntityType.DROPPED_ITEM))
            {
                e.remove();
            }
        }

        for(Player p : Bukkit.getOnlinePlayers())
        {
            PlayerListener.playerLink.put(p, new TowerPlayer(p));
        }

        new GuildAPI();

        setupPermissions();
        setupChat();
    }

    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

    private boolean setupChat()
    {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return (chat != null);
    }
}
