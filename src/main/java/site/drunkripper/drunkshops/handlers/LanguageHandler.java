package site.drunkripper.drunkshops.handlers;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class LanguageHandler {

	private static ConfigHandler instance = new ConfigHandler();
	
	public static final Text LOGO = Text.of(new Object[] { TextColors.BLUE, "[", TextColors.GOLD, "DrunkShops", TextColors.BLUE, "] " });
	public static final Text NATIONS_NOT_FOUND = Text.of(new Object[] { TextColors.RED, "Nations is not enabled/working on this server. Disabling." });
	public static final Text NATIONS_FOUND = Text.of("Nations found. Depending.");
	public static final Text OWN_SHOP = Text.join(new Text[] { LOGO, Text.of(new Object[] { TextColors.AQUA, "You own a shop, make sure to stock it!" }) });	  
	public static final Text COMMAND_DESC_LIST = Text.join(new Text[] { LOGO, Text.of(TextColors.RED,"The child command of drunk stops to list all shops") });
	public static final Text COMMAND_DESC_CREATE = Text.join(new Text[] { LOGO, Text.of(TextColors.RED,"The child command of drunk shops to create a shop") });
	public static final Text COMMAND_DESC_MAIN = Text.join(new Text[] { LOGO, Text.of(new Object[] { TextColors.AQUA, "The main parent command for drunk shops." }) });
	public static final Text SELECT_ZONE_PERM_DENY = Text.join(new Text[] {LOGO, Text.of(TextColors.RED,"You do not have permission to make a drunkshops zone.")});
	public static final Text NOT_ENABLED_IN_WORLD = Text.join(new Text[] {LOGO, Text.of(TextColors.RED, "DrunkShops are not enabled in this world.")});
	public static final String POINT_PRIMARY_SET_S = "Primary point selected. (%COORDS%)";
	public static final String POINT_SECONDARY_SET_S = "Secondary point selected. (%COORDS%)";
	public static final Text MUST_BE_PLAYER = Text.join(new Text[] {LOGO, Text.of(TextColors.RED, "You must be a player to run this command!")});
	public static final Text POINT_A_MISSING = Text.join(new Text[] {LOGO, Text.of(TextColors.RED, "You must set a point by left clicking with the DrunkShops wand")});
	public static final Text POINT_B_MISSING = Text.join(new Text[] {LOGO, Text.of(TextColors.RED, "You must set a point by right clicking with the DrunkShops wand")});
	public static final Text POINTS_NOT_IN_SAME_DIM = Text.join(new Text[] {LOGO, Text.of(TextColors.RED, "Your points are in seperate dimensions.")});
	public static final Text POINTS_INTERSECT = Text.join(new Text[] {LOGO, Text.of(TextColors.RED, "Your points come accross another shop.")});
	public static final Text NO_SHOP_HERE = Text.join(new Text[] {LOGO, Text.of(TextColors.AQUA, "This command must be ran inside a shop")});
	public static final Text SHOP_NOT_FOR_SALE = Text.of("Shop is not for sale.");
	public static final Text CLAIM_SUCCESS = Text.of("Claim Success");
	public static final Text NOT_ENOUGH_MONEY = Text.of("Not enough money.");
	public static final Text NO_ECONOMY = Text.of("No economy setup/detected to use sponge api. Contact an admin");
	
	public Text createBrandedStatement(String text, TextColors color) {
		return Text.join(new Text[] {LOGO, Text.join(new Text[] {Text.of(color), Text.of(text)})});
	}
	
	public static ConfigHandler getInstance() {
		return instance;
	}
}
