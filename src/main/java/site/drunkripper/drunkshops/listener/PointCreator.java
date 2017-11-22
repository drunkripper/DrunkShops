package site.drunkripper.drunkshops.listener;

import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import site.drunkripper.drunkshops.DrunkShops;
import site.drunkripper.drunkshops.handlers.ConfigHandler;
import site.drunkripper.drunkshops.handlers.LanguageHandler;
import site.drunkripper.drunkshops.handlers.PermissionHandler;
import site.drunkripper.drunkshops.object.PlayerDataStorage;
import site.drunkripper.drunkshops.object.Point3d;

public class PointCreator {

	private DrunkShops plugin;
	
	public PointCreator(DrunkShops p) {
		this.plugin = p;
	}
	
	@Listener public void onRightClick(InteractBlockEvent.Secondary.MainHand interact, @First Player player) {
		if(!player.getItemInHand(HandTypes.MAIN_HAND).isPresent()) return;
		if(!player.getItemInHand(HandTypes.MAIN_HAND).get().getItem().equals(ConfigHandler.WAND_ITEM))			return;
		if(!player.hasPermission(PermissionHandler.SELECT_ZONE)) {
			player.sendMessage(LanguageHandler.SELECT_ZONE_PERM_DENY);
			return;
		}			
		if(!plugin.enabledInWorld(player.getWorld())) {
			player.sendMessage(LanguageHandler.NOT_ENABLED_IN_WORLD);
			return;
		}
		if(!interact.getTargetBlock().getLocation().isPresent()) {
			return;
		}
		interact.setCancelled(true);
		for(PlayerDataStorage data1 : plugin.pdsl) {
			PlayerDataStorage data = data1;
			plugin.pdsl.remove(data);
			if(data.getPlayer().getName() == player.getName()) {
				data.setPointB(new Point3d(interact.getTargetBlock().getLocation().get()));
			}
			plugin.pdsl.add(data);
			String coords = "X: " + interact.getTargetBlock().getLocation().get().getX() + ", Y: " + interact.getTargetBlock().getLocation().get().getY() + ", Z: " + interact.getTargetBlock().getLocation().get().getZ();;
			player.sendMessage(Text.join(new Text[] {LanguageHandler.LOGO, Text.of(TextColors.AQUA, LanguageHandler.POINT_SECONDARY_SET_S.replaceAll("%COORDS%", coords))}));
			return;
		}
		PlayerDataStorage newData = new PlayerDataStorage(null, null, null);
		newData.setPlayer(player);
		newData.setPointB(new Point3d(interact.getTargetBlock().getLocation().get()));
		String coords = "X: " + interact.getTargetBlock().getLocation().get().getX() + ", Y: " + interact.getTargetBlock().getLocation().get().getY() + ", Z: " + interact.getTargetBlock().getLocation().get().getZ();;
		player.sendMessage(Text.join(new Text[] {LanguageHandler.LOGO, Text.of(TextColors.AQUA, LanguageHandler.POINT_SECONDARY_SET_S.replaceAll("%COORDS%", coords))}));
		plugin.pdsl.add(newData);
		return;
	}
	
	@Listener public void onLeftClick(InteractBlockEvent.Primary.MainHand interact, @First Player player) {
		if(!player.getItemInHand(HandTypes.MAIN_HAND).isPresent()) return;
		if(!player.getItemInHand(HandTypes.MAIN_HAND).get().getItem().equals(ConfigHandler.WAND_ITEM))			return;
		if(!player.hasPermission(PermissionHandler.SELECT_ZONE)) {
			player.sendMessage(LanguageHandler.SELECT_ZONE_PERM_DENY);
			return;
		}			
		if(!plugin.enabledInWorld(player.getWorld())) {
			player.sendMessage(LanguageHandler.NOT_ENABLED_IN_WORLD);
			return;
		}
		if(!interact.getTargetBlock().getLocation().isPresent()) {
			return;
		}
		interact.setCancelled(true);
		for(PlayerDataStorage data1 : plugin.pdsl) {
			PlayerDataStorage data = data1;
			plugin.pdsl.remove(data);
			if(data.getPlayer().getName() == player.getName()) {
				data.setPointA(new Point3d(interact.getTargetBlock().getLocation().get()));
			}
			plugin.pdsl.add(data);
			String coords = "X: " + interact.getTargetBlock().getLocation().get().getX() + ", Y: " + interact.getTargetBlock().getLocation().get().getY() + ", Z: " + interact.getTargetBlock().getLocation().get().getZ();;
			player.sendMessage(Text.join(new Text[] {LanguageHandler.LOGO, Text.of(TextColors.AQUA, LanguageHandler.POINT_PRIMARY_SET_S.replaceAll("%COORDS%", coords))}));
			return;
		}
		PlayerDataStorage newData = new PlayerDataStorage(null, null, null);
		newData.setPlayer(player);
		newData.setPointA(new Point3d(interact.getTargetBlock().getLocation().get()));
		String coords = "X: " + interact.getTargetBlock().getLocation().get().getX() + ", Y: " + interact.getTargetBlock().getLocation().get().getY() + ", Z: " + interact.getTargetBlock().getLocation().get().getZ();;
		player.sendMessage(Text.join(new Text[] {LanguageHandler.LOGO, Text.of(TextColors.AQUA, LanguageHandler.POINT_PRIMARY_SET_S.replaceAll("%COORDS%", coords))}));
		plugin.pdsl.add(newData);
		return;
	}
	
}
