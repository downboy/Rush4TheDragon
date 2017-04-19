package rush4thedragon.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import rush4thedragon.Rush4TheDragon;

public class Timer {
	
	public static String timerNeatDisplay(){
	    TimeZone tz = TimeZone.getTimeZone("UTC");
	    SimpleDateFormat sdf;
	    if (Rush4TheDragon.timer >= 86400L) {
	      sdf = new SimpleDateFormat("d'j' HH:mm:ss");
	    } else {
	      sdf = new SimpleDateFormat("HH:mm:ss");
	    }
	    sdf.setTimeZone(tz);
	    return sdf.format(new Date(Rush4TheDragon.timer * 1000L));
	 }  
	
	public static void updateBoard(){
		ScoreboardManager manager = Bukkit.getScoreboardManager();
	    Scoreboard board = manager.getMainScoreboard();
	    Objective o = board.getObjective("r4td");
	    if((Rush4TheDragon.status == false)){
	    	if(o != null) o.unregister();
	    	return;
	    }else if(o == null){
	      o = board.registerNewObjective("r4td", "dummy");
	      String n = "_";
	      n = "§aRush4TheDragon v2";
	      if(Rush4TheDragon.g().getConfig().getString("game.scoreboardName") != null){
	    	  n = Rush4TheDragon.g().getConfig().getString("game.scoreboardName").replace('&', '§');
	      }
	      o.setDisplayName(n);
	      if(!Rush4TheDragon.g().getConfig().getBoolean("dragon")) {
	        o.getScore("§cDragon").setScore(-4);
	      }else{
	        o.getScore("§aDragon").setScore(-4);
	      }
	      o.setDisplaySlot(DisplaySlot.SIDEBAR);
	    }
	    try{
	      board.resetScores(Rush4TheDragon.oldText);
	      board.resetScores("§cPAUSE");
	    }catch (Exception e) {}
	    Rush4TheDragon.oldText = timerNeatDisplay();
	    o.getScore(Rush4TheDragon.oldText).setScore(-1);
	    if(!Rush4TheDragon.g().getConfig().getBoolean("dragon")){
	      o.getScore("§cDragon").setScore(-4);
	    }else{
	      board.resetScores("§cDragon");
	      o.getScore("§aDragon").setScore(-4);
	    }
	    if(Rush4TheDragon.pause) {
	      o.getScore("§cPAUSE").setScore(-6);
	    }
	}
	 
}
