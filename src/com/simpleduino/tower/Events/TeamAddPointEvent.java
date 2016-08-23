package com.simpleduino.tower.Events;

import com.simpleduino.tower.Teams.TowerTeam;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Simple-Duino on 27/05/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class TeamAddPointEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private TowerTeam towerTeam;

    public TeamAddPointEvent(TowerTeam tt, Player p)
    {
        this.towerTeam = tt;
        this.player = p;
    }

    public TowerTeam getTeam()
    {
        return this.towerTeam;
    }

    public Player getPlayer()
    {
        return this.player;
    }

    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}
