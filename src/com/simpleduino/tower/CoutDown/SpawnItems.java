package com.simpleduino.tower.CoutDown;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 * Created by Simple-Duino on 20/06/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class SpawnItems extends BukkitRunnable {

    private World world;

    public SpawnItems(World w)
    {
        this.world = w;
    }

    @Override
    public void run() {
        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        Item item1 = this.world.dropItem(new Location(this.world, 0.5, 204.5, 1166.5), iron);
        item1.setVelocity(new Vector(0, 0, 0));
        ExperienceOrb eo = ((ExperienceOrb)world.spawn(new Location(this.world, 0.5, 204.5, 1138.5), ExperienceOrb.class));
        eo.setExperience(4);
        eo.setVelocity(new Vector(0,0,0));
    }
}
