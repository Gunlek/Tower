package com.simpleduino.tower.Commands;

import com.simpleduino.tower.Listeners.PlayerListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Simple-Duino on 20/06/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class teamChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(sender instanceof Player)
        {
            Player p = (Player)sender;
            if(PlayerListener.teamChat.containsKey(p))
            {
                PlayerListener.teamChat.remove(p);
                p.sendMessage(ChatColor.GREEN + "Vous avez désactivé le chat en équipe");
            }
            else
            {
                PlayerListener.teamChat.put(p, true);
                p.sendMessage(ChatColor.GREEN + "Vous avez activé le chat en équipe");
            }
        }
        return false;
    }
}
