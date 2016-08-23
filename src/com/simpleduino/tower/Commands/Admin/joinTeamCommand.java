package com.simpleduino.tower.Commands.Admin;

import com.simpleduino.tower.Listeners.GameListener;
import com.simpleduino.tower.Listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

/**
 * Created by Simple-Duino on 02/06/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class joinTeamCommand {

    public joinTeamCommand(CommandSender sender, String[] args)
    {
        if(sender instanceof Player)
        {
            Player p = (Player)sender;
            if(args.length >= 3)
            {
                if(args.length >= 4)
                {
                    Player pChange = Bukkit.getPlayer(args[3]);
                    String teamName = args[2];
                    Scoreboard sb = pChange.getScoreboard();
                    if(PlayerListener.playerLink.get(pChange).hasTeam()) {
                        PlayerListener.playerLink.get(pChange).getTeam().delMember(pChange);
                        sb.getTeam(PlayerListener.playerLink.get(pChange).getTeam().getName()).removeEntry(pChange.getName());
                    }
                    if(teamName.equalsIgnoreCase("blue"))
                    {
                        PlayerListener.playerLink.get(pChange).setTeam(GameListener.teamBlue);
                        GameListener.teamBlue.addMember(pChange);
                        p.sendMessage(ChatColor.GREEN + pChange.getName() + " a rejoint l'equipe des bleus");
                    }
                    else
                    {
                        PlayerListener.playerLink.get(pChange).setTeam(GameListener.teamRed);
                        GameListener.teamRed.addMember(pChange);
                        p.sendMessage(ChatColor.GREEN + pChange.getName() + " a rejoint l'equipe des rouges");
                    }
                    sb.getTeam(teamName).addEntry(pChange.getName());
                }
                else
                {
                    String teamName = args[2];
                    if(PlayerListener.playerLink.get(p).hasTeam()) {
                        PlayerListener.playerLink.get(p).getTeam().delMember(p);
                    }
                    if(teamName.equalsIgnoreCase("blue"))
                    {
                        PlayerListener.playerLink.get(p).setTeam(GameListener.teamBlue);
                        GameListener.teamBlue.addMember(p);
                        p.sendMessage(ChatColor.GREEN + "Vous avez rejoint l'equipe des bleus");
                    }
                    else
                    {
                        PlayerListener.playerLink.get(p).setTeam(GameListener.teamRed);
                        GameListener.teamRed.addMember(p);
                        p.sendMessage(ChatColor.GREEN + "Vous avez rejoint l'equipe des rouges");
                    }
                }

            }
            else
            {
                p.sendMessage(ChatColor.RED + "Erreur: Pas assez d'arguments, syntaxe: /tw admin jointeam <team_name> ou /hd admin jointeam <team_name> <player_name>");
            }
        }
    }

}
