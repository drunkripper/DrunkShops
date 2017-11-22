package site.drunkripper.drunkshops.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import site.drunkripper.drunkshops.DrunkShops;
import site.drunkripper.drunkshops.handlers.LanguageHandler;
import site.drunkripper.drunkshops.object.Cube;
import site.drunkripper.drunkshops.object.PlayerDataStorage;
import site.drunkripper.drunkshops.object.Point3d;
import site.drunkripper.drunkshops.object.ShopObject;

public class DrunkShopsCommandCreate implements CommandExecutor {

	private DrunkShops plugin;
	
	public DrunkShopsCommandCreate(DrunkShops plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if(!(src instanceof Player)) { src.sendMessage(Text.of(LanguageHandler.MUST_BE_PLAYER)); return CommandResult.success(); }
		for(PlayerDataStorage data : plugin.pdsl) {
			if(data.getPlayer().getUniqueId() == ((Player) src).getUniqueId()) {
				Player p = (Player) src;
				if(data.getPointA() == null) {
					src.sendMessage(LanguageHandler.POINT_A_MISSING);
					return CommandResult.success();
				}
				if(data.getPointB() == null) {
					src.sendMessage(LanguageHandler.POINT_B_MISSING);
					return CommandResult.success();
				}
				if(data.getPointA().getWorld() != data.getPointB().getWorld()) {
					src.sendMessage(LanguageHandler.POINTS_NOT_IN_SAME_DIM);
					return CommandResult.success();
				}
				Cube newCube = new Cube(data.getPointA(), data.getPointB());
				//String name, UUID owner, Cube cube, double price, int daysLeft
				for(ShopObject shop : plugin.shops) {
					if(newCube.intersects(shop.getCube())) {
						src.sendMessage(LanguageHandler.POINTS_INTERSECT);
						return CommandResult.success();
					}
				}
				ShopObject shop = new ShopObject(args.getOne("Name").get().toString(), null, newCube, (double) args.getOne("Price").get(), 0);
				plugin.shops.add(shop);
			}
		}
		
		return CommandResult.success();
	}

	private boolean checkIntersection(Point3d pointA, Point3d pointB) {
		
		return false;
	}

}
