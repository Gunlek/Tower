package com.simpleduino.tower.Teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Simple-Duino on 30/05/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class TowerTeam {

    private String teamName;
    private ChatColor teamColor;
    private String displayName;
    private ArrayList<Player> members = new ArrayList<>();
    private int teamPoint = 0;
    File f = new File("plugins/Tower/spawnpoints.yml");
    YamlConfiguration cf = YamlConfiguration.loadConfiguration(f);

    public TowerTeam(String tn, ChatColor c, String dn)
    {
        this.teamName = tn;
        this.teamColor = c;
        this.displayName = dn;
    }

    public String getName()
    {
        return this.teamName;
    }

    public String getDisplayName()
    {
        return this.displayName;
    }

    public ChatColor getColor()
    {
        return this.teamColor;
    }

    public ArrayList<Player> getMembers()
    {
        return members;
    }

    public void addMember(Player p)
    {
        this.members.add(p);
    }

    public void delMember(Player p)
    {
        this.members.remove(p);
    }

    public int getTeamSize()
    {
        return this.members.size();
    }

    public boolean isMember(Player p) {
        if (members.contains(p)) {
            return true;
        } else {
            return false;
        }
    }

    public void setPoints(int p)
    {
        this.teamPoint = p;
    }

    public int getPoints()
    {
        return this.teamPoint;
    }

    public Location getSpawnPoint()
    {
        Location l = new Location(Bukkit.getWorld(cf.get(this.getName().toLowerCase()+".world").toString()), Double.parseDouble(cf.get(this.getName().toLowerCase()+".x").toString()), Double.parseDouble(cf.get(this.getName().toLowerCase()+".y").toString()), Double.parseDouble(cf.get(this.getName().toLowerCase()+".z").toString()), Float.parseFloat(cf.get(this.getName().toLowerCase()+".yaw").toString()), Float.parseFloat(cf.get(this.getName().toLowerCase()+".pitch").toString()));
        return l;
    }

}
