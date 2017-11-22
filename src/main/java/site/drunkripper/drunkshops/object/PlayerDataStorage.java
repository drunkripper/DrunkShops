package site.drunkripper.drunkshops.object;

import org.spongepowered.api.entity.living.player.Player;

public class PlayerDataStorage {
	
	private Player player;
	private Point3d a;
	private Point3d b;
	
	public PlayerDataStorage(Player player, Point3d a, Point3d b) {
		this.player = player;
		this.a = a;
		this.b = b;
	}
	
	public void setPlayer(Player player) {this.player = player; return;}
	public Player getPlayer() {return this.player;}
	
	public void setPointA(Point3d point) {this.a = point; return;}
	public Point3d getPointA() {return this.a;}
	
	public void setPointB(Point3d point) {this.b = point; return;}
	public Point3d getPointB() {return this.b;}
}
