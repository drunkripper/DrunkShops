package site.drunkripper.drunkshops.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;

import site.drunkripper.drunkshops.DrunkShops;
import site.drunkripper.drunkshops.handlers.LanguageHandler;
import site.drunkripper.drunkshops.object.Point3d;
import site.drunkripper.drunkshops.object.ShopObject;

public class DrunkShopsCommandInfo implements CommandExecutor {

	private DrunkShops plugin;
	
	public DrunkShopsCommandInfo(DrunkShops plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("rawtypes") @Override 
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if(!(src instanceof Player)) {
			src.sendMessage(LanguageHandler.MUST_BE_PLAYER);
			return CommandResult.success();
		}
		Player p = (Player) src;
		Location loc = p.getLocation();
		ShopObject shop = plugin.getShop(new Point3d(loc.getX(), loc.getY(), loc.getZ(), p.getWorld()));
		if(shop == null) {
			p.sendMessage(LanguageHandler.NO_SHOP_HERE);
			return CommandResult.success();
		}
		p.sendMessage(Text.of(TextColors.GOLD, "Shop Name: ", TextColors.AQUA, shop.getName()));
		p.sendMessage(Text.of(TextColors.GOLD, "Days Left Until Expire: ", TextColors.AQUA, shop.daysLeft));
		p.sendMessage(Text.of(TextColors.GOLD, "Owner UUID: ", TextColors.AQUA, shop.getOwner()));
		p.sendMessage(Text.of(TextColors.GOLD, "Cost: ", TextColors.AQUA, shop.getPriceAvailable()));
		return CommandResult.success();
	}
	
}
