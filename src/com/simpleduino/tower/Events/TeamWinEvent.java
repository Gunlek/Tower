package com.simpleduino.tower.Events;

import com.simpleduino.tower.Teams.TowerTeam;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Simple-Duino on 27/05/2016.
 * Copyrights Simple-Duino, all rights reserved
 */
public class TeamWinEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private TowerTeam towerTeam;

    public TeamWinEvent(TowerTeam tt)
    {
        this.towerTeam = tt;
    }

    public TowerTeam getTeam()
    {
        return this.towerTeam;
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
