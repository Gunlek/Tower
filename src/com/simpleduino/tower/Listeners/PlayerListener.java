package com.simpleduino.tower.Listeners;

import com.simpleduino.tower.Events.GameStartEvent;
import com.simpleduino.tower.Events.TeamAddPointEvent;
import com.simpleduino.tower.Players.TowerPlayer;
import com.simpleduino.tower.TowerPlugin;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.Dye;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

/**
 * Created by Simple-Duino on 20/06/2016.
 * Copyrights Simple-Duino, all rights reserved
 */

public class PlayerListener implements Listener{

    public static boolean gameStarted = false;
    public static HashMap<Player, Boolean> teamChat = new HashMap<>();
    public static HashMap<Player, TowerPlayer> playerLink = new HashMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        playerLink.put(e.getPlayer(), new TowerPlayer(e.getPlayer()));
        if(Bukkit.getServer().getMaxPlayers()==Bukkit.getServer().getOnlinePlayers().size())
        {
            Bukkit.getServer().getPluginManager().callEvent(new GameStartEvent(e.getPlayer()));
        }
        for(Player p : Bukkit.getOnlinePlayers())
        {
            p.sendMessage(ChatColor.RED + e.getPlayer().getName()+ChatColor.YELLOW+" a rejoint la partie");
            p.sendMessage(ChatColor.DARK_AQUA+"("+Integer.toString(Bukkit.getOnlinePlayers().size())+"/"+Integer.toString(Bukkit.getServer().getMaxPlayers())+") joueur(s) avant le démarrage de la partie");
        }
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e)
    {
        Player p = e.getPlayer();
        Location l1 = e.getBlock().getLocation();
        Location l = new Location(Bukkit.getWorld("tower"), (int)l1.getX(), (int)l1.getY(), (int)l1.getZ());
        if(e.getBlock().getType().equals(Material.CHEST))
        {
            p.sendMessage(ChatColor.DARK_GRAY + "Vous ne pouvez pas casser les coffres !");
            e.setCancelled(true);
        }
        /*if(PlayerListener.playerLink.get(p).getTeam().getName().equalsIgnoreCase("red"))
        {
            Location chest1 = new Location(Bukkit.getWorld("tower"), 63, 192, 1138);
            Location chest2 = new Location(Bukkit.getWorld("tower"), 65, 192, 1143);
            Location chest3 = new Location(Bukkit.getWorld("tower"), 65, 192, 1144);
            Location chest4 = new Location(Bukkit.getWorld("tower"), 65, 192, 1146);
            Location chest5 = new Location(Bukkit.getWorld("tower"), 65, 192, 1147);
            Location chest6 = new Location(Bukkit.getWorld("tower"), 61, 192, 1143);
            Location chest7 = new Location(Bukkit.getWorld("tower"), 61, 192, 1144);
            Location chest8 = new Location(Bukkit.getWorld("tower"), 61, 192, 1146);
            Location chest9 = new Location(Bukkit.getWorld("tower"), 61, 192, 1147);
            Location chest10 = new Location(Bukkit.getWorld("tower"), 63, 192, 1166);
            Location chest11 = new Location(Bukkit.getWorld("tower"), 49, 192, 1166);
            Location chest12 = new Location(Bukkit.getWorld("tower"), 35, 192, 1166);
            Location chest13 = new Location(Bukkit.getWorld("tower"), 35, 197, 1166);
            Location chest14 = new Location(Bukkit.getWorld("tower"), 35, 197, 1138);
            Location chest15 = new Location(Bukkit.getWorld("tower"), 49, 197, 1138);
            Location chest16 = new Location(Bukkit.getWorld("tower"), 63, 197, 1138);
            Location chest17 = new Location(Bukkit.getWorld("tower"), 35, 192, 1138);
            Location chest18 = new Location(Bukkit.getWorld("tower"), 49, 206, 1152);

            if(l.equals(chest1) || l.equals(chest2) || l.equals(chest3) || l.equals(chest4) || l.equals(chest5) || l.equals(chest6) || l.equals(chest7) || l.equals(chest8) || l.equals(chest9) || l.equals(chest10) || l.equals(chest11) || l.equals(chest12) || l.equals(chest13) || l.equals(chest14) || l.equals(chest15) || l.equals(chest16) || l.equals(chest17) || l.equals(chest18))
            {
                e.setCancelled(true);
                p.sendMessage(ChatColor.DARK_GRAY + "Vous ne pouvez pas casser les coffres de votre équipe !");
            }
        }
        else if(PlayerListener.playerLink.get(p).getTeam().getName().equalsIgnoreCase("blue"))
        {
            Location chest19 = new Location(Bukkit.getWorld("tower"), -63, 192, 1166);
            Location chest20 = new Location(Bukkit.getWorld("tower"), -65, 192, 1161);
            Location chest21 = new Location(Bukkit.getWorld("tower"), -65, 192, 1160);
            Location chest22 = new Location(Bukkit.getWorld("tower"), -65, 192, 1158);
            Location chest23 = new Location(Bukkit.getWorld("tower"), -65, 192, 1157);
            Location chest24 = new Location(Bukkit.getWorld("tower"), -61, 192, 1157);
            Location chest25 = new Location(Bukkit.getWorld("tower"), -61, 192, 1158);
            Location chest26 = new Location(Bukkit.getWorld("tower"), -61, 192, 1160);
            Location chest27 = new Location(Bukkit.getWorld("tower"), -61, 192, 1161);
            Location chest28 = new Location(Bukkit.getWorld("tower"), -63, 192, 1138);
            Location chest29 = new Location(Bukkit.getWorld("tower"), -49, 192, 1138);
            Location chest30 = new Location(Bukkit.getWorld("tower"), -35, 197, 1138);
            Location chest31 = new Location(Bukkit.getWorld("tower"), -35, 197, 1138);
            Location chest32 = new Location(Bukkit.getWorld("tower"), -35, 197, 1166);
            Location chest33 = new Location(Bukkit.getWorld("tower"), -49, 197, 1166);
            Location chest34 = new Location(Bukkit.getWorld("tower"), -63, 197, 1138);
            Location chest35 = new Location(Bukkit.getWorld("tower"), -35, 192, 1166);
            Location chest36 = new Location(Bukkit.getWorld("tower"), -49, 206, 1152);

            if(l.equals(chest19) || l.equals(chest20) || l.equals(chest21) || l.equals(chest22) || l.equals(chest23) || l.equals(chest24) || l.equals(chest25) || l.equals(chest26) || l.equals(chest27) || l.equals(chest28) || l.equals(chest29) || l.equals(chest30) || l.equals(chest31) || l.equals(chest32) || l.equals(chest33) || l.equals(chest34) || l.equals(chest35) || l.equals(chest36))
            {
                e.setCancelled(true);
                p.sendMessage(ChatColor.DARK_GRAY + "Vous ne pouvez pas casser les coffres de votre équipe !");
            }
        }*/
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e)
    {
        final Player p = e.getPlayer();
        Bukkit.getServer().getScheduler().runTaskLater(TowerPlugin.getPlugin(TowerPlugin.class), new Runnable() {
            @Override
            public void run() {
                if(PlayerListener.playerLink.get(p).hasTeam())
                {
                    p.teleport(PlayerListener.playerLink.get(p).getTeam().getSpawnPoint());
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 4, false, false));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 4, false, false));
                    p.setLevel(0);
                    p.setExp((float)0);
                }
            }
        }, 1L);

        if(PlayerListener.playerLink.get(e.getPlayer()).hasTeam()) {
            switch (PlayerListener.playerLink.get(e.getPlayer()).getTeam().getName().toLowerCase()) {
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

                    e.getPlayer().getInventory().setHelmet(helmetRed);
                    e.getPlayer().getInventory().setChestplate(chestplateRed);
                    e.getPlayer().getInventory().setLeggings(leggingsRed);
                    e.getPlayer().getInventory().setBoots(bootsRed);
                    e.getPlayer().getInventory().addItem(potato);
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

                    e.getPlayer().getInventory().setHelmet(helmetBlue);
                    e.getPlayer().getInventory().setChestplate(chestplateBlue);
                    e.getPlayer().getInventory().setLeggings(leggingsBlue);
                    e.getPlayer().getInventory().setBoots(bootsBlue);
                    e.getPlayer().getInventory().addItem(potatoBlue);
                    break;
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e)
    {
        Player p = e.getPlayer();
        Location l = p.getLocation();
        if(PlayerListener.gameStarted) {
            if (PlayerListener.playerLink.get(p).hasTeam()) {
                switch (PlayerListener.playerLink.get(p).getTeam().getName()) {
                    case "red":
                        if (-84 < (int) l.getX() && (int) l.getX() < -82 && 1151 < (int) l.getZ() && (int) l.getZ() < 1153 && 199 < (int)l.getY() && (int)l.getY() < 201) {
                            Bukkit.getServer().getPluginManager().callEvent(new TeamAddPointEvent(PlayerListener.playerLink.get(p).getTeam(), p));
                            p.teleport(PlayerListener.playerLink.get(p).getTeam().getSpawnPoint());
                        }
                        break;

                    case "blue":
                        if (83 < (int) l.getX() && (int) l.getX() < 85 && 1151 < (int) l.getZ() && (int) l.getZ() < 1153 && 199 < (int)l.getY() && (int)l.getY() < 201) {
                            Bukkit.getServer().getPluginManager().callEvent(new TeamAddPointEvent(PlayerListener.playerLink.get(p).getTeam(), p));
                            p.teleport(PlayerListener.playerLink.get(p).getTeam().getSpawnPoint());
                        }
                        break;
                }
            }
        }
    }

    @EventHandler
    public void onPlayerGetDamage(EntityDamageEvent e)
    {
        if(e.getEntity() instanceof  Player)
        {
            if(e.getCause().equals(EntityDamageEvent.DamageCause.VOID))
            {
                ((Player) e.getEntity()).setHealth(0);
            }
        }
    }

    @EventHandler
    public void onPlayerOpenEnchantTable(InventoryOpenEvent e)
    {
        if(e.getInventory().getType().equals(InventoryType.ENCHANTING))
        {
            EnchantingInventory ei = (EnchantingInventory)e.getInventory();
            Dye lapisDye = new Dye(DyeColor.BLUE);
            lapisDye.toItemStack();

            ItemStack lapis = lapisDye.toItemStack();
            lapis.setAmount(3);
            ei.setSecondary(lapis);
        }
    }

    @EventHandler
    public void onLapisDrop(PlayerDropItemEvent e)
    {
        if(e.getItemDrop().getType().equals(EntityType.DROPPED_ITEM))
        {
            Item i = e.getItemDrop();
            if(i.getItemStack().getType().equals(Material.INK_SACK)) {
                i.remove();
            }
        }
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent e)
    {
        if(e.getInventory().getType().equals(InventoryType.ENCHANTING))
        {
            if(e.getClickedInventory().getItem(e.getSlot()).getType().equals(Material.INK_SACK)) {
                e.setCancelled(true);
                e.getWhoClicked().getInventory().remove(Material.INK_SACK);
            }
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e)
    {
        if(PlayerListener.teamChat.containsKey(e.getPlayer()) && !e.getMessage().startsWith("!")) {
            e.setCancelled(true);
            if(playerLink.get(e.getPlayer()).hasTeam()) {
                for (Player p : PlayerListener.playerLink.get(e.getPlayer()).getTeam().getMembers()) {
                    p.sendMessage(ChatColor.BLUE.toString() + ChatColor.BOLD + "[TEAM] "+ChatColor.RESET + TowerPlugin.chat.getGroupPrefix(e.getPlayer().getWorld(), TowerPlugin.permission.getPrimaryGroup(e.getPlayer())).replace("&","§") + " " + e.getPlayer().getName() + ChatColor.RESET + ": " + e.getMessage());
                }
            }
        }
        else if(e.getMessage().startsWith("!"))
        {
            String msg = "";
            int index=0;
            for(String s : e.getMessage().split(""))
            {
                if(index>0)
                    msg+=s;
                index++;
            }
            e.setFormat(ChatColor.GOLD.toString() + ChatColor.BOLD + "[GLOBAL] "+ChatColor.RESET + TowerPlugin.chat.getGroupPrefix(e.getPlayer().getWorld(), TowerPlugin.permission.getPrimaryGroup(e.getPlayer())).replace("&","§") + " " + e.getPlayer().getName() + ChatColor.RESET + ": " + msg);
        }
        else
        {
            e.setFormat(ChatColor.GOLD.toString() + ChatColor.BOLD + "[GLOBAL] "+ChatColor.RESET + TowerPlugin.chat.getGroupPrefix(e.getPlayer().getWorld(), TowerPlugin.permission.getPrimaryGroup(e.getPlayer())).replace("&","§") + " " + e.getPlayer().getName() + ChatColor.RESET + ": " + e.getMessage());
        }
    }
}
