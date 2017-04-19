package rush4thedragon.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import rush4thedragon.Rush4TheDragon;

public class JoinManager implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(Rush4TheDragon.status){
			if(Rush4TheDragon.pause){
				p.sendMessage(Rush4TheDragon.g().getConfig().getString("messages.join.ready").replace('&', '§'));
			}else{
				p.sendMessage(Rush4TheDragon.g().getConfig().getString("messages.join.start").replace('&', '§').replace("%time%", Rush4TheDragon.timer+""));
			}
		}
	}

}
