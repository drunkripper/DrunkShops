package site.drunkripper.drunkshops.object;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.World;

public class Cube {

	private Point3d a;
	private Point3d b;
	public double minX;
	public double minY;
	public double minZ;
	public double maxX;
	public double maxY;
	public double maxZ;
	public World world;
	
	public Cube(Point3d a, Point3d b) {
		this.a = a;
		this.b = b;
		this.world = a.getWorld();
		if(a.getX() > b.getX()) {
			minX = b.getX();
			maxX = a.getX() + 1;
		} else {
			minX = a.getX();
			maxX = b.getX() + 1;
		}
		if(a.getY() > b.getY()) {
			minY = b.getY();
			maxY = a.getY() + 1;
		} else {
			minY = a.getY();
			maxY = b.getY() + 1;
		}
		if(a.getZ() > b.getZ()) {
			minZ = b.getZ();
			maxZ = a.getZ() + 1;
		} else {
			minZ = a.getZ();
			maxZ = b.getZ() + 1;
		}
	}
	
	
	
	public boolean intersects(Cube cube) {
		Sponge.getServer().getBroadcastChannel().send(Text.of(""));
		boolean x = this.minX <= cube.maxX && cube.minX <= this.maxX;
		boolean y = this.minY <= cube.maxY && cube.minY <= this.maxY;
		boolean z = this.minZ <= cube.maxZ && cube.minZ <= this.maxZ;
		
		Sponge.getServer().getBroadcastChannel().send(Text.of(this.minX + "<=" + cube.maxX + " : " + cube.minX + "<=" + this.maxX));
		Sponge.getServer().getBroadcastChannel().send(Text.of(this.minY + "<=" + cube.maxY + " : " + cube.minY + "<=" + this.maxY));
		Sponge.getServer().getBroadcastChannel().send(Text.of(this.minZ + "<=" + cube.maxZ + " : " + cube.minZ + "<=" + this.maxZ));
		Sponge.getServer().getBroadcastChannel().send(Text.of("X: " + x + ", Y: " + y + ", Z: " + z));
		return x && y && z;
		
	}

	public boolean isPointInside(Point3d a) {
		return isPointInside(a.getX(), a.getY(), a.getZ(), a.getWorld());
	}

	public boolean isPointInside(double x, double y, double z, World world) {
		return this.minX < x && this.maxX > x && this.minY < y && this.maxY > y && this.minZ < z && this.maxZ > z;
	}
}
