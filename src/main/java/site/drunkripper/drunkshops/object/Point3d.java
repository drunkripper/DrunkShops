package site.drunkripper.drunkshops.object;

import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import com.flowpowered.math.vector.Vector3d;

public class Point3d {
	
	private double x;
	private double y;
	private double z;
	private World world;

	public Point3d(Vector3d vec) {
		this.x = vec.getX();
		this.y = vec.getY();
		this.z = vec.getZ();
	}
	
	public Point3d(double x, double y, double z, World world) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.world = world;
	}
	
	public Point3d(Location<World> location) {
		this.x = location.getX();
		this.y = location.getY();
		this.z = location.getZ();
		this.world = location.getExtent();
	}

	public void setX(int x) { this.x = x; return;}
	public double getX() {return this.x;}

	public void setY(int y) { this.y = y; return;}
	public double getY() {return this.y;}	
	
	public void setZ(int z) { this.z = z; return;}
	public double getZ() {return this.z;}

	public World getWorld() {
		return this.world;
	}

}
