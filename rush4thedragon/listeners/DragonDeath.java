package rush4thedragon.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import rush4thedragon.Rush4TheDragon;
import rush4thedragon.utils.Utils;

public class DragonDeath implements Listener {
	
	static int timer = 60;
	static World deathWorld;
	
	String s = Rush4TheDragon.g().getConfig().getString("world.name");
	World w = Bukkit.getServer().getWorld(s);
	World w1 = Bukkit.getServer().getWorld(s+"_the_end");
	
	@EventHandler
	public void onMobDeath(EntityDeathEvent ev){
		Entity en = ev.getEntity();
		if((en instanceof EnderDragon) && ((en.getWorld() == w) || (en.getWorld() == w1)) && (Rush4TheDragon.status)){
			if((!Rush4TheDragon.pause) && (!Rush4TheDragon.isGameFinish)){
				Rush4TheDragon.pause = true;
				Rush4TheDragon.isGameFinish = true;
				deathWorld = en.getWorld();
				Rush4TheDragon.g().getConfig().set("dragon", Boolean.valueOf(true));
				for(Player all : Bukkit.getServer().getOnlinePlayers()){
					all.setAllowFlight(true);
					all.setVelocity(new Vector(0.5, 5, 0.5));
					all.setFlying(true);
				}
				Bukkit.broadcastMessage(Rush4TheDragon.g().getConfig().getString("messages.finish").replace('&', '§').replace("%time%", Rush4TheDragon.timer+""));
				Bukkit.getScheduler().runTaskTimer(Rush4TheDragon.g(), new Runnable() {
					@Override
					public void run() {
						timer--;
						if(timer >= 0){
							for(Player all : Bukkit.getServer().getOnlinePlayers()){
								if(all.getWorld() == deathWorld){
									Utils.firework(all);
								}
							}
						}else{
							Bukkit.getScheduler().cancelTask(timer);
						}
					}
				}, 10L, 10L);
			}
		}
	}
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e){
		Player p = e.getPlayer();
		if(Rush4TheDragon.status == false){
			for(World w : Bukkit.getWorlds()){
				if(w.getName().contains("end")){
					p.teleport(new Location(Bukkit.getWorld(w.getName()), p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ()));
				}
			}
		}
	}

}
