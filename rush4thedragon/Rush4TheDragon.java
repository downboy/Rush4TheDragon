package rush4thedragon;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import rush4thedragon.cmds.Rush4TheDragCmd;
import rush4thedragon.listeners.DragonDeath;
import rush4thedragon.listeners.JoinManager;
import rush4thedragon.listeners.NoDamage;
import rush4thedragon.timer.Timer;

public class Rush4TheDragon extends JavaPlugin{
	
	public static String version = "2.0.0";	
	public static long timer;
	public static boolean pause;
	public static String oldText;
	public static boolean status;
	public static boolean isGameFinish;
	
	public static Rush4TheDragon instance;
	
	public static Rush4TheDragon g(){
		return instance;
	}
	
	public void l(String str){
		getLogger().log(Level.INFO, str);
	}
	
	@Override
	public void onEnable() {
		instance = this;
		
		getConfig().options().copyHeader(true);
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		status = getConfig().getBoolean("pluginEnabled");
		timer = getConfig().getLong("game.timer");
		pause = getConfig().getBoolean("game.pause");
		isGameFinish = getConfig().getBoolean("dragon");
		
		getCommand("rush4thedragon").setExecutor(new Rush4TheDragCmd());
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new DragonDeath(), this);
		pm.registerEvents(new JoinManager(), this);
		pm.registerEvents(new NoDamage(), this);
		
	    Bukkit.getScheduler().runTaskTimer(this, new Runnable(){
	      public void run(){
	    	  if(!pause){
	    		  timer += 1L;
	    	  }
	    	  Timer.updateBoard();
	      }
	    }, 20L, 20L);
	    
		l("---------------------------------------");
		l("=> Rush4TheDragon:");
		l("Plugin version: v"+version+".");
		l("Plugin enabled: "+status+".");
		l("Thank for using Rush4TheDragon !");
		l("---------------------------------------");
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {   
		getConfig().set("pluginEnabled", Boolean.valueOf(status));
		getConfig().set("game.pause", Boolean.valueOf(pause));
		getConfig().set("dragon", Boolean.valueOf(isGameFinish));
		getConfig().set("game.timer", Long.valueOf(Rush4TheDragon.timer));
		saveConfig();
		super.onDisable();
	}

}
