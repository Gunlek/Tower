package com.simpleduino.tower.Commands;

import com.simpleduino.tower.Events.GameStartEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Simple-Duino on 20/06/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class StartCommand {

    public StartCommand(Player sender)
    {
        Bukkit.getServer().getPluginManager().callEvent(new GameStartEvent(sender));
    }
}
