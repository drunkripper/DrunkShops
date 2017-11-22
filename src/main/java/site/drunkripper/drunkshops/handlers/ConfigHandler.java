package site.drunkripper.drunkshops.handlers;

import java.io.File;
import java.io.IOException;

import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class ConfigHandler {
	
	public static final ItemType WAND_ITEM = ItemTypes.GOLDEN_HOE;
	
	private static ConfigHandler instance = new ConfigHandler();
    
    public static ConfigHandler getInstance() {
            return instance;
    }
   
    private ConfigurationLoader<CommentedConfigurationNode> configLoader;
    private CommentedConfigurationNode config;
   
    public void setup(File configFile, ConfigurationLoader<CommentedConfigurationNode> configLoader) {
            this.configLoader = configLoader;
           
            if (!configFile.exists()) {
                    try {
                            configFile.createNewFile();
                            loadConfig();
                            config.getNode("shops").setComment("This is where the plugin stores its data. Don't mess with it unless you know what you're doing.").setValue(null);
                            saveConfig();
                    }
                   
                    catch (Exception e) {
                            e.printStackTrace();
                    }
            }
           
            else {
                    loadConfig();
            }
    }
   
    public CommentedConfigurationNode getConfig() {
            return config;
    }
   
    public void saveConfig() {
            try {
                    configLoader.save(config);
            }
           
            catch (IOException e) {
                    e.printStackTrace();
            }
    }
   
    public void loadConfig() {
            try {
                    config = configLoader.load();
            }
           
            catch (IOException e) {
                    e.printStackTrace();
            }
    }
}
