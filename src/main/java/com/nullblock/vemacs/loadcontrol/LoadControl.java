package com.nullblock.vemacs.loadcontrol;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LoadControl extends JavaPlugin implements Listener {
	
	private List<String> parsedworlds = new ArrayList<>();
	
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    }
    
    public void onDisable() {
    }

    @EventHandler
    public void onWorldInit(WorldInitEvent event) {
    	World world = event.getWorld();
    	String worldname = world.getName();
    	List<String> worlds = this.getConfig().getStringList("worlds");
    			if (!worlds.contains(worldname)) {
    				world.setKeepSpawnInMemory(false);
    				getLogger().info("Prevented " + worldname + "from keeping spawn loaded");
    			}
    	}

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
    	World world = event.getWorld();
    	String worldname = world.getName();
    	List<String> worlds = this.getConfig().getStringList("worlds");
    	if (!this.parsedworlds.contains(worldname)) {
    			if (!worlds.contains(worldname)) {
    				Bukkit.unloadWorld(worldname, false);
    				this.parsedworlds.add(worldname);
    				getLogger().info("Prevented " + worldname + "from loading");
    			}
    	}
    }
}