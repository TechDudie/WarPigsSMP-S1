package com.technodot.warpigssmp;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WarPigsMod extends JavaPlugin {
	
	public static final World world = Bukkit.getWorld("world");
	
	private static PluginManager pluginManager = null;
	
	private static final Listener EntityListener = new ProtectThePiggy();
	
	private void init() {
		pluginManager = getServer().getPluginManager();
	}
	
	@Override
	public void onEnable() {
		init();
		
		pluginManager.registerEvents(EntityListener, this);

		TechnoLogger.info("================================================================================");
		TechnoLogger.info("");
		TechnoLogger.info("WarPigsMod");
		TechnoLogger.info("");
		TechnoLogger.info("================================================================================");
		TechnoLogger.info("");
		TechnoLogger.info("Copyright (c) TechnoDot 2024. All rights reserved.");
		TechnoLogger.info("For help, contact \"technodot\" on Discord, or @TechDudie on GitHub.");
		TechnoLogger.info("WarPigsMod v1.0 loaded!");
	}
	
	@Override
	public void onDisable() {
		TechnoLogger.info("WarPigsMod v1.0 unloaded!");
	}
}
