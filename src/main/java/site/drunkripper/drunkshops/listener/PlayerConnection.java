package site.drunkripper.drunkshops.listener;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;

import site.drunkripper.drunkshops.DrunkShops;

public class PlayerConnection {
	
	private DrunkShops plugin;
	
	public PlayerConnection(DrunkShops drunkShops) {
		this.plugin = drunkShops;
	}

	//Login Event
	@Listener public void Login(ClientConnectionEvent.Login login) {
		
	}
	
	//Auth Event
	@Listener public void Auth(ClientConnectionEvent.Auth auth) {
		
	}
	
	//Join Event
	@Listener public void Join(ClientConnectionEvent.Join join) {
		
	}
	
	//Disconnect Event
	@Listener public void Disconnect(ClientConnectionEvent.Disconnect disconnect) {
		
	}
	
}