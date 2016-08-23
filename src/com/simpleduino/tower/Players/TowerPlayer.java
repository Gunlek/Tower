package com.simpleduino.tower.Players;

import com.simpleduino.tower.Teams.TowerTeam;
import org.bukkit.entity.Player;

/**
 * Created by Simple-Duino on 31/05/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class TowerPlayer {

    private String name;
    private String uuid;
    private Player playerObject;
    private TowerTeam team = null;
    private String className = null;

    public TowerPlayer(Player p)
    {
        this.name = p.getName();
        this.uuid = p.getUniqueId().toString();
        this.playerObject = p;
    }

    public Player getPlayerObject()
    {
        return this.playerObject;
    }

    public String getName()
    {
        return this.name;
    }

    public String getUuid()
    {
        return this.uuid;
    }

    public boolean hasTeam()
    {
        if(this.team==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean hasClass()
    {
        if(this.className == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public TowerTeam getTeam()
    {
        return this.team;
    }

    public void setTeam(TowerTeam t)
    {
        this.team = t;
    }

}
