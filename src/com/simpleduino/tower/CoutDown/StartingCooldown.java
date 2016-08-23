package com.simpleduino.tower.CoutDown;

import com.simpleduino.tower.Listeners.PlayerListener;
import com.simpleduino.tower.TowerPlugin;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

/**
 * Created by Simple-Duino on 20/06/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class StartingCooldown extends BukkitRunnable {

    private static int counter = 10;

    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers())
        {
            p.setHealth(20);
            p.setFoodLevel(20);
            p.setGameMode(GameMode.SURVIVAL);
            p.setLevel(counter);
            p.setExp(0);
        }
        if(counter > 0)
        {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.CLICK, 10, 1);
            }
            counter--;
        }
        else
        {
            Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective obj = sb.registerNewObjective("Points", "dummy");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score bs = obj.getScore(ChatColor.BLUE + "Bleus");
            Score rs = obj.getScore(ChatColor.RED + "Rouges");
            bs.setScore(0);
            rs.setScore(0);
            Team blue = sb.registerNewTeam("Bleus");
            Team red = sb.registerNewTeam("Rouges");
            blue.setAllowFriendlyFire(false);
            blue.setPrefix(ChatColor.BLUE.toString());
            red.setAllowFriendlyFire(false);
            red.setPrefix(ChatColor.RED.toString());
            for(Player p : Bukkit.getOnlinePlayers())
            {
                PlayerListener.teamChat.put(p, true);
                p.setScoreboard(sb);
                if(PlayerListener.playerLink.get(p).hasTeam())
                {
                    if(PlayerListener.playerLink.get(p).getTeam().getName().equalsIgnoreCase("red"))
                    {
                        red.addEntry(p.getName());
                    }
                    else if(PlayerListener.playerLink.get(p).getTeam().getName().equalsIgnoreCase("blue"))
                    {
                        blue.addEntry(p.getName());
                    }
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 4, false, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 4, false, false));
                p.teleport(PlayerListener.playerLink.get(p).getTeam().getSpawnPoint());
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 1);
                if(PlayerListener.playerLink.get(p).hasTeam()) {
                    switch (PlayerListener.playerLink.get(p).getTeam().getName().toLowerCase()) {
                        case "red":
                            ItemStack helmetRed = new ItemStack(Material.LEATHER_HELMET);
                            ItemStack chestplateRed = new ItemStack(Material.LEATHER_CHESTPLATE);
                            ItemStack leggingsRed = new ItemStack(Material.LEATHER_LEGGINGS);
                            ItemStack bootsRed = new ItemStack(Material.LEATHER_BOOTS);

                            LeatherArmorMeta lmRed = (LeatherArmorMeta) helmetRed.getItemMeta();
                            lmRed.setColor(Color.fromRGB(255, 0, 0));
                            helmetRed.setItemMeta(lmRed);
                            chestplateRed.setItemMeta(lmRed);
                            leggingsRed.setItemMeta(lmRed);
                            bootsRed.setItemMeta(lmRed);
                            ItemStack potato = new ItemStack(Material.POTATO, 16);

                            p.getInventory().setHelmet(helmetRed);
                            p.getInventory().setChestplate(chestplateRed);
                            p.getInventory().setLeggings(leggingsRed);
                            p.getInventory().setBoots(bootsRed);
                            p.getInventory().addItem(potato);
                            break;

                        case "blue":
                            ItemStack helmetBlue = new ItemStack(Material.LEATHER_HELMET);
                            ItemStack chestplateBlue = new ItemStack(Material.LEATHER_CHESTPLATE);
                            ItemStack leggingsBlue = new ItemStack(Material.LEATHER_LEGGINGS);
                            ItemStack bootsBlue = new ItemStack(Material.LEATHER_BOOTS);

                            LeatherArmorMeta lmBlue = (LeatherArmorMeta) helmetBlue.getItemMeta();
                            lmBlue.setColor(Color.fromRGB(0, 0, 255));
                            helmetBlue.setItemMeta(lmBlue);
                            chestplateBlue.setItemMeta(lmBlue);
                            leggingsBlue.setItemMeta(lmBlue);
                            bootsBlue.setItemMeta(lmBlue);
                            ItemStack potatoBlue = new ItemStack(Material.POTATO, 16);

                            p.getInventory().setHelmet(helmetBlue);
                            p.getInventory().setChestplate(chestplateBlue);
                            p.getInventory().setLeggings(leggingsBlue);
                            p.getInventory().setBoots(bootsBlue);
                            p.getInventory().addItem(potatoBlue);
                            break;
                    }
                }
            }
            new SpawnItems(Bukkit.getWorld("tower")).runTaskTimer(TowerPlugin.getPlugin(TowerPlugin.class), 0, 20L*5);
            this.cancel();
        }
    }
}
