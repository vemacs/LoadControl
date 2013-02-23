package com.nullblock.vemacs.loadcontrol;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWorldInit(WorldInitEvent event) {
    	World world = event.getWorld();
    	String worldname = world.getName();
    	if (!this.parsedworlds.contains(worldname)) {
    	List<String> worlds = this.getConfig().getStringList("worlds");
    			if ( worldname.contains("myst") || worldname.contains("age") ) {
    				world.setKeepSpawnInMemory(false);
    				Bukkit.unloadWorld(worldname, false);
    				this.parsedworlds.add(worldname);
    				new WorldUnloadEvent(world);
    				getLogger().info("Prevented " + worldname + " from keeping spawn loaded");
    			}
    	}
    }
}