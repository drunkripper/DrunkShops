package site.drunkripper.drunkshops.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import site.drunkripper.drunkshops.DrunkShops;
import site.drunkripper.drunkshops.handlers.LanguageHandler;
import site.drunkripper.drunkshops.object.Cube;
import site.drunkripper.drunkshops.object.ShopObject;

public class DrunkShopsCommandList implements CommandExecutor {

	private DrunkShops plugin;
	
	public DrunkShopsCommandList(DrunkShops plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		src.sendMessage(Text.of("There are: " + plugin.shops.size() + ", shops"));
		for(ShopObject shop : plugin.shops) {
			Cube cube = shop.getCube();
			String str = "minX: %MINX%, minY: %MINY%, minZ: %MINZ%, maxX: %MAXX%, maxY: %MAXY%, maxZ: %MAXZ%";
			str = str.replace("%MINX%", String.valueOf(cube.minX));
			str = str.replace("%MINY%", String.valueOf(cube.minY));
			str = str.replace("%MINZ%", String.valueOf(cube.minZ));
			str = str.replace("%MAXX%", String.valueOf(cube.maxX));
			str = str.replace("%MAXY%", String.valueOf(cube.maxY));
			str = str.replace("%MAXZ%", String.valueOf(cube.maxZ));
			src.sendMessage(Text.join(new Text[] {LanguageHandler.LOGO, Text.of(TextColors.AQUA, str)}));
		}
		return null;
	}

}
