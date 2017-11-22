package site.drunkripper.drunkshops.commands;

import java.math.BigDecimal;
import java.util.Optional;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.economy.account.UniqueAccount;
import org.spongepowered.api.service.economy.transaction.ResultType;
import org.spongepowered.api.service.economy.transaction.TransactionResult;
import org.spongepowered.api.world.Location;

import site.drunkripper.drunkshops.DrunkShops;
import site.drunkripper.drunkshops.handlers.LanguageHandler;
import site.drunkripper.drunkshops.object.Point3d;
import site.drunkripper.drunkshops.object.ShopObject;

public class DrunkShopsCommandRent implements CommandExecutor {
private DrunkShops plugin;
	
	public DrunkShopsCommandRent(DrunkShops plugin) {
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
		if(!shop.isAvailable()) {
			p.sendMessage(LanguageHandler.SHOP_NOT_FOR_SALE);
		}
		
/*		if(!(shop.daysLeft == 0) && ConfigHandler.getInstance().getConfig().getNode("settings", "prerent").getBoolean()) {
			//Pre-Rent If Enabled
		}*/
		
		BigDecimal price = BigDecimal.valueOf(shop.getPrice());
		Optional<EconomyService> serviceOpt = Sponge.getServiceManager().provide(EconomyService.class);
		if (!serviceOpt.isPresent()) {
		    p.sendMessage(LanguageHandler.NO_ECONOMY);
		    return CommandResult.success();
		}
		EconomyService service = serviceOpt.get();
		Optional<UniqueAccount> uOpt = service.getOrCreateAccount(p.getUniqueId());
		if(uOpt.isPresent()) {
			UniqueAccount acc = uOpt.get();
			TransactionResult result = acc.withdraw(service.getDefaultCurrency(), price, null);			
			if(result.getResult() == ResultType.SUCCESS) {
				shop.setOwner(p.getUniqueId());
				shop.available = false;
				p.sendMessage(LanguageHandler.CLAIM_SUCCESS);
			} else {
				p.sendMessage(LanguageHandler.NOT_ENOUGH_MONEY);
			}
		}
		return CommandResult.success();
	}
}
