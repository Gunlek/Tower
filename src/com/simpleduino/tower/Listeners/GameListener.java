package com.simpleduino.tower.Listeners;

import com.simpleduino.guild.GuildAPI.GuildSQL;
import com.simpleduino.tower.CoutDown.LobbyReturn;
import com.simpleduino.tower.CoutDown.StartingCooldown;
import com.simpleduino.tower.Events.GameStartEvent;
import com.simpleduino.tower.Events.TeamAddPointEvent;
import com.simpleduino.tower.Events.TeamWinEvent;
import com.simpleduino.tower.Teams.TowerTeam;
import com.simpleduino.tower.TowerPlugin;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import java.util.ArrayList;

/**
 * Created by Simple-Duino on 20/06/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class GameListener implements Listener{

    public static TowerTeam teamBlue, teamRed;
    private int counter = 0;

    @EventHandler
    public void onGameStart(GameStartEvent e)
    {
        PlayerListener.gameStarted = true;
        teamBlue = new TowerTeam("blue", ChatColor.BLUE, "Bleus");
        teamRed = new TowerTeam("red", ChatColor.RED, "Rouges");
        ArrayList<Player> onlinePlayers = new ArrayList<>();
        for(Player p : Bukkit.getOnlinePlayers())
        {
            p.getInventory().clear();
            p.getInventory().setHelmet(null);
            p.getInventory().setChestplate(null);
            p.getInventory().setLeggings(null);
            p.getInventory().setBoots(null);
        }
        for(Player p : PlayerListener.playerLink.keySet())
        {
            onlinePlayers.add(p);
        }
        randomizeTeams(onlinePlayers);
        Potion potion = new Potion(PotionType.INVISIBILITY, 1);
        Item item1 = Bukkit.getWorld("tower").dropItem(new Location(Bukkit.getWorld("tower"), 0.5, 204.5, 1152.5), potion.toItemStack(1));
        item1.setVelocity(new Vector(0, 0, 0));
        new StartingCooldown().runTaskTimer(TowerPlugin.getPlugin(TowerPlugin.class), 0, 20L);
    }

    @EventHandler
    public void onTeamAddPoint(TeamAddPointEvent e)
    {
        TowerTeam tt = e.getTeam();
        Player p = e.getPlayer();

        Scoreboard sb = p.getScoreboard();
        if(tt.getName().equalsIgnoreCase("red"))
        {
            int newScore = sb.getObjective(DisplaySlot.SIDEBAR).getScore(tt.getColor() + tt.getDisplayName()).getScore()+1;
            if(newScore < 10) {
                sb.getObjective(DisplaySlot.SIDEBAR).getScore(tt.getColor() + tt.getDisplayName()).setScore(newScore);
            }
            else
            {
                Bukkit.getServer().getPluginManager().callEvent(new TeamWinEvent(tt));
            }
        }
        else if(tt.getName().equalsIgnoreCase("blue"))
        {
            int newScore = sb.getObjective(DisplaySlot.SIDEBAR).getScore(tt.getColor() + tt.getDisplayName()).getScore()+1;
            if(newScore < 10) {
                sb.getObjective(DisplaySlot.SIDEBAR).getScore(tt.getColor() + tt.getDisplayName()).setScore(newScore);
            }
            else
            {
                Bukkit.getServer().getPluginManager().callEvent(new TeamWinEvent(tt));
            }
        }

        for(Player p1 : Bukkit.getOnlinePlayers())
        {
            p1.setScoreboard(sb);
            p1.sendMessage(tt.getColor() + p.getName() + ChatColor.RESET + " a marqué un point");
        }
    }

    @EventHandler
    public void onTeamWin(TeamWinEvent e)
    {
        TowerTeam tt = e.getTeam();
        for(Player p : Bukkit.getOnlinePlayers())
        {
            if(tt.getMembers().contains(p))
            {
                GuildSQL guildSQL = new GuildSQL();
                if(guildSQL.hasGuild(p))
                {
                    if(guildSQL.getDailyCoins(guildSQL.getGuild(p))<Integer.parseInt(guildSQL.getGuildCoinsPerDay(guildSQL.getGuild(p)))) {
                        guildSQL.setGuildCoins(guildSQL.getGuild(p), Integer.parseInt(guildSQL.getGuildCoins(guildSQL.getGuild(p))) + 100);
                        guildSQL.setDailyCoins(guildSQL.getGuild(p), guildSQL.getDailyCoins(guildSQL.getGuild(p)) + 100);
                        p.sendMessage(ChatColor.GREEN + "Vous rapportez 100 coins à votre guilde");
                    }
                    else
                    {
                        p.sendMessage(ChatColor.GREEN + "Vous avez déjà atteint le plafond de gains quotidiens pour votre guilde");
                    }
                }
                p.sendMessage(ChatColor.YELLOW.toString() + ChatColor.MAGIC + "||| "+ChatColor.RESET.toString()+ChatColor.YELLOW+"Votre équipe remporte la partie "+ChatColor.MAGIC + "|||");
            }
            else {
                GuildSQL guildSQL = new GuildSQL();
                if(guildSQL.hasGuild(p))
                {
                    if(guildSQL.getDailyCoins(guildSQL.getGuild(p))<Integer.parseInt(guildSQL.getGuildCoinsPerDay(guildSQL.getGuild(p)))) {
                        guildSQL.setGuildCoins(guildSQL.getGuild(p), Integer.parseInt(guildSQL.getGuildCoins(guildSQL.getGuild(p))) + 10);
                        guildSQL.setDailyCoins(guildSQL.getGuild(p), guildSQL.getDailyCoins(guildSQL.getGuild(p)) + 10);
                        p.sendMessage(ChatColor.GREEN + "Vous rapportez 10 coins à votre guilde");
                    }
                    else
                    {
                        p.sendMessage(ChatColor.GREEN + "Vous avez déjà atteint le plafond de gains quotidiens pour votre guilde");
                    }
                }
                p.sendMessage(ChatColor.YELLOW.toString() + ChatColor.MAGIC + "||| " + ChatColor.RESET.toString() + ChatColor.YELLOW + "Les "+tt.getDisplayName()+" remportent la partie " + ChatColor.MAGIC + "|||");
            }

            p.setGameMode(GameMode.CREATIVE);
            ItemStack tnt = new ItemStack(Material.TNT, 512);
            ItemStack flint_and_steel = new ItemStack(Material.FLINT_AND_STEEL, 1);
            p.getInventory().clear();
            p.getInventory().addItem(flint_and_steel);
            p.getInventory().addItem(tnt);
            new LobbyReturn().runTaskLaterAsynchronously(TowerPlugin.getPlugin(TowerPlugin.class), 20L * 15);
        }
    }

    private void randomizeTeams(ArrayList<Player> pList)
    {
        for(Player p : pList)
        {
            Integer i = Integer.valueOf(Long.toString(Math.round(Math.random())));
            if(i==0)
            {
                if(teamBlue.getTeamSize() >= pList.size()/2)
                {
                    teamRed.addMember(p);
                    PlayerListener.playerLink.get(p).setTeam(teamRed);
                }
                else {
                    teamBlue.addMember(p);
                    PlayerListener.playerLink.get(p).setTeam(teamBlue);
                }
            }
            else
            {
                if (teamRed.getTeamSize() >= pList.size() / 2) {
                    teamBlue.addMember(p);
                    PlayerListener.playerLink.get(p).setTeam(teamBlue);
                }
                else {
                    teamRed.addMember(p);
                    PlayerListener.playerLink.get(p).setTeam(teamRed);
                }
            }
        }
    }

}
