package com.simpleduino.tower.Commands;

import com.simpleduino.tower.Commands.Admin.SetSpawnCommand;
import com.simpleduino.tower.Commands.Admin.joinTeamCommand;
import com.simpleduino.tower.Commands.Admin.resetMapCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Simple-Duino on 30/05/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class AdminCommand {

    public AdminCommand(CommandSender sender, String[] args) {
        if(sender instanceof Player)
        {
            if(args.length >= 2)
            {
                if(args[0].equalsIgnoreCase("admin")) {
                    switch (args[1].toLowerCase()) {
                        case "setspawn":
                            new SetSpawnCommand(sender, args);
                            break;

                        case "jointeam":
                            new joinTeamCommand(sender, args);
                            break;

                        case "reset":
                            new resetMapCommand(sender);
                            break;
                    }
                }
            }
            else
            {
                sender.sendMessage(ChatColor.RED + "Erreur: Pas assez d'arguments, syntaxe: /tw admin <jointeam | setspawn | reset>");
            }
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Erreur: Cette commande doit être executée par un joueur");
        }
    }
}
