package site.drunkripper.drunkshops;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.World;

import com.google.inject.Inject;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import site.drunkripper.drunkshops.commands.DrunkShopsCommand;
import site.drunkripper.drunkshops.commands.DrunkShopsCommandCreate;
import site.drunkripper.drunkshops.commands.DrunkShopsCommandInfo;
import site.drunkripper.drunkshops.commands.DrunkShopsCommandList;
import site.drunkripper.drunkshops.commands.DrunkShopsCommandRent;
import site.drunkripper.drunkshops.handlers.ConfigHandler;
import site.drunkripper.drunkshops.handlers.LanguageHandler;
import site.drunkripper.drunkshops.handlers.PermissionHandler;
import site.drunkripper.drunkshops.handlers.ShopHandler;
import site.drunkripper.drunkshops.listener.PlayerConnection;
import site.drunkripper.drunkshops.listener.PlayerMovement;
import site.drunkripper.drunkshops.listener.PointCreator;
import site.drunkripper.drunkshops.object.PlayerDataStorage;
import site.drunkripper.drunkshops.object.Point3d;
import site.drunkripper.drunkshops.object.ShopObject;

@Plugin(id="drunkshops", name="Drunk Shops", version="0.1-ALPHA")
public class DrunkShops {
	
	private static DrunkShops instance;
	
	ShopHandler shopHandler = ShopHandler.getInstance();

	@Inject private Logger logger;
	
	@Inject @DefaultConfig(sharedRoot = false)
	private File configFile;
	
	@Inject @DefaultConfig(sharedRoot = false)
	ConfigurationLoader<CommentedConfigurationNode> configManager;
	
	@Inject @ConfigDir(sharedRoot = false)
	private File defaultConfigDir;
	
	@Inject @ConfigDir(sharedRoot = false)
	public File pluginDir;
	
	public List<PlayerDataStorage> pdsl = new ArrayList<PlayerDataStorage>();
	public List<ShopObject> shops = new ArrayList<ShopObject>();
	public HashMap<UUID, ShopObject> lastShopWalkedIn = new HashMap<UUID, ShopObject>();
	
	//Create Listeners
	CommandSpec createShop = CommandSpec.builder()
	    .description(LanguageHandler.COMMAND_DESC_CREATE)
	    .permission(PermissionHandler.COMMAND_PERM_CREATE)
	    .arguments(GenericArguments.string(Text.of("Name")),
	    		GenericArguments.doubleNum(Text.of("Price")))
	    .executor(new DrunkShopsCommandCreate(this))
	    .build();
	CommandSpec listShops = CommandSpec.builder()
		    .description(LanguageHandler.COMMAND_DESC_LIST)
		    .permission(PermissionHandler.COMMAND_PERM_LIST)
		    .executor(new DrunkShopsCommandList(this))
		    .build();
	CommandSpec infoShop = CommandSpec.builder()
		    .description(LanguageHandler.COMMAND_DESC_LIST)
		    .permission(PermissionHandler.COMMAND_PERM_LIST)
		    .executor(new DrunkShopsCommandInfo(this))
		    .build();
	CommandSpec rentShop = CommandSpec.builder()
		    .description(LanguageHandler.COMMAND_DESC_LIST)
		    .permission(PermissionHandler.COMMAND_PERM_LIST)
		    .executor(new DrunkShopsCommandRent(this))
		    .build();
	CommandSpec drunkShops = CommandSpec.builder()
	    .description(LanguageHandler.COMMAND_DESC_MAIN)
	    .permission(PermissionHandler.COMMAND_PERM_MAIN)
	    .executor(new DrunkShopsCommand(this))
	    .child(createShop, new String[] { "create" })
	    .child(listShops, new String[] { "list" })
	    .child(rentShop, new String[] { "rent" })
	    .child(infoShop, new String[] { "info" })
	    .build();
	
	public Logger getLogger() {
		return this.logger;
	}
	  
	@Listener public void onGameInitilzation(GameInitializationEvent e) {
		Sponge.getCommandManager().register(this, drunkShops, "drunkshops", "ds");
		ConfigHandler.getInstance().setup(configFile, configManager);
		
		CommentedConfigurationNode node = ConfigHandler.getInstance().getConfig().getNode("json", "shops");
		importShops(node);
	}
	
	public void importShops(CommentedConfigurationNode node) {

	}
	
	@Listener public void onStart(GameStartedServerEvent event) {
		Sponge.getEventManager().registerListeners(this, new PointCreator(this));
		Sponge.getEventManager().registerListeners(this, new PlayerConnection(this));
		Sponge.getEventManager().registerListeners(this, new PlayerMovement(this));

	}
	
	public String pointInAShop(Point3d point) {
		for(ShopObject shop : this.shops) {
			if(shop.isPointInside(point)) {
				return shop.getName();
			}
		}
		return "null";
	}
	
	public ShopObject getShop(Point3d point) {
		for(ShopObject shop : this.shops) {
			if(shop.isPointInside(point)) {
				return shop;
			}
		}
		return null;
	}
	
	public boolean enabledInWorld(World world) {
		return true;
	}
	
	public void exportShops() {
		File shopDir = new File(defaultConfigDir, "shops");
		if(!shopDir.exists()) {
			shopDir.mkdirs();
		}
		for(ShopObject shop : shops) {
			shopHandler.serialize(shopDir, shop);
		}
	}
	
	public static DrunkShops getInstance() {
		return instance;
	}
	
}
