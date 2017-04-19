package rush4thedragon.utils;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class Utils {
	
	public static String prefix = "§a[Rush4TheDragon]";
	
	public static String getPrefix(){
		return prefix;
	}
	
	public static void firework(Player p){
		Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
        Random r = new Random();   
        int rt = r.nextInt(4) + 1;
        Type type = Type.BALL;       
        if (rt == 1) type = Type.BALL;
        if (rt == 2) type = Type.BALL_LARGE;
        if (rt == 3) type = Type.BURST;
        if (rt == 4) type = Type.CREEPER;
        if (rt == 5) type = Type.STAR;   
        Random r2 = new Random();
        int rt2 = r2.nextInt(3) +1;
        FireworkEffect effect = null;
        if(rt2 == 1) effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(Color.RED).withFade(Color.BLUE).with(type).trail(r.nextBoolean()).build();
        if(rt2 == 2) effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(Color.ORANGE).withFade(Color.AQUA).with(type).trail(r.nextBoolean()).build();
        if(rt2 == 3) effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(Color.YELLOW).withFade(Color.GREEN).with(type).trail(r.nextBoolean()).build();
        if(rt2 == 4) effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(Color.GREEN).withFade(Color.LIME).with(type).trail(r.nextBoolean()).build();
        fwm.addEffect(effect);
        int rp = r.nextInt(2) + 1;
        fwm.setPower(rp);
        fw.setFireworkMeta(fwm);  
	}

}
