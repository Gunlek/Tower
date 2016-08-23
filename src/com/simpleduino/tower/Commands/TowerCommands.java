package com.simpleduino.tower.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Simple-Duino on 30/05/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class TowerCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length >= 1)
        {
            switch(args[0])
            {
                case "admin":
                    new AdminCommand(sender, args);
                    break;

                case "start":
                    new StartCommand((Player)sender);
                    break;
            }
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Erreur: Pas assez d'arguments, syntaxe: /tw <admin | start>");
        }
        return false;
    }
}
