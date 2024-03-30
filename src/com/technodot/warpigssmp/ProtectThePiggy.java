package com.technodot.warpigssmp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ProtectThePiggy implements Listener {
	
	public static final Random RNG = new Random();
	public static final String[] deathMessages = {" was inflicted with the wrath of TechnoBlade", " learned that TechnoBlade NEVER DIES"};
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		LivingEntity entity = event.getEntity();
		
		if (entity instanceof Pig) {
		
			event.getDrops().clear();
			
			if (!(entity.getKiller() instanceof Player)) { return; }
			
			Player player = (Player) entity.getKiller();
			player.getPlayer().getWorld().strikeLightningEffect(player.getLocation());
			player.getPlayer().damage(69420.0);
			
			WarPigsMod.world.spawnEntity(player.getLocation(), EntityType.PIG);
			
		} else if (entity instanceof Hoglin) {
			event.getDrops().remove(new ItemStack(Material.PORKCHOP));
			event.getDrops().remove(new ItemStack(Material.COOKED_PORKCHOP));
			event.getDrops().add(new ItemStack(Material.GOLDEN_CARROT, 1 + RNG.nextInt(2)));
		}
		
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (event.getDeathMessage().contains("died")) {
			event.setDeathMessage(event.getEntity().getDisplayName() + deathMessages[RNG.nextInt(2)]);
			List<ItemStack> itemsToRemove = new ArrayList<ItemStack>();
			for (ItemStack item : event.getDrops()) {
				if (RNG.nextInt(2) == 0) {
					itemsToRemove.add(item);
				}
			}
			event.getDrops().removeAll(itemsToRemove);
		}
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		if (event.getRightClicked() instanceof Pig) {
			if (player.getInventory().getItemInMainHand().getType().equals(Material.LEAD) || player.getInventory().getItemInOffHand().getType().equals(Material.LEAD)) {
				PlayerInventory inventory = player.getInventory();
				inventory.remove(new ItemStack(Material.LEAD));
				player.getPlayer().getWorld().strikeLightningEffect(player.getLocation());
				player.getPlayer().damage(69420.0);
			}
		}
	}
}
