package site.drunkripper.drunkshops.listener;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import site.drunkripper.drunkshops.DrunkShops;
import site.drunkripper.drunkshops.handlers.LanguageHandler;
import site.drunkripper.drunkshops.object.Point3d;
import site.drunkripper.drunkshops.object.ShopObject;

public class PlayerMovement {
	
	private DrunkShops plugin;
	
	public PlayerMovement(DrunkShops plugin) {
		this.plugin = plugin;
	}
	
	@Listener public void onPlayerMove(MoveEntityEvent event, @First Player player) {
		if (event.getFromTransform().getLocation().getBlockX() == event.getToTransform().getLocation().getBlockX() && 
				event.getFromTransform().getLocation().getBlockZ() == event.getToTransform().getLocation().getBlockZ())
		{
			return;
		}
		if(!plugin.enabledInWorld(event.getToTransform().getExtent())) {
			return;
		}
		
		Point3d loc = new Point3d(event.getToTransform().getLocation().getX(), event.getToTransform().getLocation().getY(), event.getToTransform().getLocation().getZ(), event.getToTransform().getLocation().getExtent());
		if(plugin.lastShopWalkedIn.size() > 0) {
			if(plugin.lastShopWalkedIn.get(player.getUniqueId()) != null) {
				if(!plugin.lastShopWalkedIn.get(player.getUniqueId()).getCube().isPointInside(loc)) {
					player.sendMessage(Text.join(new Text[] {LanguageHandler.LOGO, Text.of(TextColors.RED, "Now leaving shop: " + plugin.lastShopWalkedIn.get(player.getUniqueId()).getName())}));
					plugin.lastShopWalkedIn.remove(player.getUniqueId());
				}
			}
		}
		for(ShopObject shop : plugin.shops) {
			if(plugin.lastShopWalkedIn.size() > 0) {
				if(plugin.lastShopWalkedIn.get(player.getUniqueId()).getName() == shop.getName()) {
					return;
				}
			}
			if(shop.isPointInside(loc)) {
				player.sendMessage(Text.join(LanguageHandler.LOGO, Text.of(TextColors.LIGHT_PURPLE, "Welcome to: " + shop.getName())));
				plugin.lastShopWalkedIn.put(player.getUniqueId(), shop);
				return;
			}
		}
	}
}
