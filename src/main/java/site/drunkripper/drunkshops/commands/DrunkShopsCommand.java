package site.drunkripper.drunkshops.commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import site.drunkripper.drunkshops.DrunkShops;
import site.drunkripper.drunkshops.handlers.ConfigHandler;

public class DrunkShopsCommand implements CommandExecutor {
	
	private DrunkShops plugin;
	
	public DrunkShopsCommand(DrunkShops plugin) {
		this.plugin = plugin;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		plugin.exportShops();
		return CommandResult.success();
	}

}
