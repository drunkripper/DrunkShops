package site.drunkripper.drunkshops.object;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.spongepowered.api.world.World;

public class ShopObject {

	private String name;
	private UUID owner;
	private Cube cube;
	private double price;
	public int daysLeft;
	private List<UUID> members = new ArrayList<UUID>();
	public boolean available = true;
	
	public ShopObject(String name, UUID owner, Cube cube, double price, int daysLeft, List<UUID> members) {
		this.owner = owner;
		this.cube = cube;
		this.price = price;
		this.daysLeft = daysLeft;
		this.members = members;
		this.name = name;
	}
	
	public ShopObject(String name, UUID owner, Cube cube, double price, int daysLeft) {
		if(owner == null) {
			this.available = true;
			this.owner = null;
		} else {
			this.available = false;
			this.owner = owner;
		}
		this.cube = cube;
		this.price = price;
		this.daysLeft = daysLeft;
		this.name = name;
	}
	
	public boolean isPointInside(double x, double y, double z, World world) {
		return this.cube.isPointInside(x, y, z, world);
	}
	
	public boolean isAvailable() {
		return this.available;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setOwner(UUID owner){
		this.owner = owner;
		if(this.available = true) {
			this.available = false;
		}
	}
	public UUID getOwner() {
		return this.owner;
	}
	
	public void setCube(Cube cube) {
		this.cube = cube;
	}
	public Cube getCube() {
		return this.cube;
	}
	
	public void setPrice(double price) {
		this.price = price;
		return;
	}
	public double getPrice() {
		return this.price;
	}
	
	public void addMember(UUID member) {
		this.members.add(member);
	}
	
	public void removeMember(UUID member) {
		this.members.remove(member);
	}
	
	public void resetMemmbers() {
		this.members = new ArrayList<UUID>();
	}

	public boolean isPointInside(Point3d loc) {
		return this.cube.isPointInside(loc);
		
	}

	public Object getPriceAvailable() {
		if(this.available == true) {
			return this.price;
		} else {
			return "Shop not for sale.";
		}
	}
	
	
	
}
