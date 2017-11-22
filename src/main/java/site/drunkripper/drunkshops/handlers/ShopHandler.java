package site.drunkripper.drunkshops.handlers;

import java.io.File;
import java.io.IOException;

import site.drunkripper.drunkshops.DrunkShops;
import site.drunkripper.drunkshops.object.ShopObject;

public class ShopHandler {
	
	private DrunkShops plugin = DrunkShops.getInstance();
	private static ShopHandler instance = new ShopHandler();
	
	public static ShopHandler getInstance() {
		return instance;
	}
	
	public void serialize(File dir, ShopObject shop) {
		File file = new File(dir, shop.getName());
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	

}
