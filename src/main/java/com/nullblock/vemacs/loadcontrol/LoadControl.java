package com.nullblock.vemacs.loadcontrol;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LoadControl extends JavaPlugin implements Listener {
	
    private Set<String> parsedWorlds = new TreeSet<String>();
	
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
    	if (!this.parsedWorlds.contains(worldname)) {
    	List<String> blacklist = this.getConfig().getStringList("blacklist");
            for (String aBlacklist : blacklist) {
                if (worldname.contains(aBlacklist)) {
                    world.setKeepSpawnInMemory(false);
                    Bukkit.unloadWorld(worldname, false);
                    this.parsedWorlds.add(worldname);
                    new WorldUnloadEvent(world);
                    getLogger().info("Prevented " + worldname + " from keeping spawn loaded.");
                }
            }
    	}
    }
    
}
