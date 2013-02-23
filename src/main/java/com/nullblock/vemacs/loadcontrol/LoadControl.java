package com.nullblock.vemacs.loadcontrol;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LoadControl extends JavaPlugin implements Listener {
	
	private List<String> parsedworlds;
	
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
    	String mode = this.getConfig().getString("mode"); 
    		if (mode.equals("blacklist")) {
    			if (worlds.contains(worldname)) {
    				world.setKeepSpawnInMemory(false);
    				Bukkit.unloadWorld(worldname, false);
    				this.parsedworlds.add(worldname);
    				getLogger().info("Prevented " + worldname + "from keeping spawn loaded");
    			}
    		}
    		if (mode.equals("whitelist")) {
    			if (!worlds.contains(worldname)) {
    				world.setKeepSpawnInMemory(false);
    				Bukkit.unloadWorld(worldname, false);
    				this.parsedworlds.add(worldname);
    				getLogger().info("Prevented " + worldname + "from keeping spawn loaded");
    			}
    	}
    }
}