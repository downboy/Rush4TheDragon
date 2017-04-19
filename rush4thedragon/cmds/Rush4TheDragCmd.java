package rush4thedragon.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rush4thedragon.Rush4TheDragon;
import rush4thedragon.utils.Utils;

public class Rush4TheDragCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("unpause") && p.hasPermission("r4td.status")){
					if(Rush4TheDragon.status == false){
						p.sendMessage("§cFirst, enabled Rush4TheDragon !");
					}else if(Rush4TheDragon.pause){
						Rush4TheDragon.pause = false;
						p.sendMessage(Rush4TheDragon.g().getConfig().getString("messages.pause.unpause").replace('&', '§').replace("%prefix%", Utils.getPrefix()));
						Bukkit.broadcastMessage(Rush4TheDragon.g().getConfig().getString("messages.broadcast.unpause").replace('&', '§').replace("%prefix%", Utils.getPrefix()));
					}else{
						p.sendMessage(Rush4TheDragon.g().getConfig().getString("messages.unpause.unpause").replace('&', '§').replace("%prefix%", Utils.getPrefix()));
					}
				}else if(args[0].equalsIgnoreCase("pause") && p.hasPermission("r4td.status")){
					if(Rush4TheDragon.status == false){
						p.sendMessage("§cFirst, enabled Rush4TheDragon !");
					}else if(Rush4TheDragon.pause){
						p.sendMessage(Rush4TheDragon.g().getConfig().getString("messages.unpause.pause").replace('&', '§').replace("%prefix%", Utils.getPrefix()));
					}else{
						Rush4TheDragon.pause = true;
						p.sendMessage(Rush4TheDragon.g().getConfig().getString("messages.pause.pause").replace('&', '§').replace("%prefix%", Utils.getPrefix()));
						Bukkit.broadcastMessage(Rush4TheDragon.g().getConfig().getString("messages.broadcast.pause").replace('&', '§').replace("%prefix%", Utils.getPrefix()));
					}
				}else if(args[0].equalsIgnoreCase("enable") && p.hasPermission("r4td.admin")){
					if(Rush4TheDragon.status){
						p.sendMessage("§cRush4TheDragon is already enabled !");
					}else{
						Rush4TheDragon.status = true;
						p.sendMessage("§aRush4TheDragon is now enabled !");
					}
				}else if(args[0].equalsIgnoreCase("disable") && p.hasPermission("r4td.admin")){
					if(Rush4TheDragon.status){
						Rush4TheDragon.status = false;
						if(!Rush4TheDragon.pause) Rush4TheDragon.pause = true;
						p.sendMessage("§cRush4TheDragon is now disabled !");
						for(Player all : Bukkit.getServer().getOnlinePlayers()){
							all.setAllowFlight(false);
							all.setFlying(false);
						}
					}else{
						p.sendMessage("§cRush4TheDragon is already disabled !");
					}
				}else{
					displayHelp(p);
				}
			}else{
				displayHelp(p);
			}
		}
		return true;
	}
	
	public void displayHelp(Player p){
		p.sendMessage("§eRush4TheDragon | v"+Rush4TheDragon.version);
		String plStatus = "";
		if(!Rush4TheDragon.status) plStatus = "§cDISABLE";
		else plStatus = "§aENABLE";
		String status = "";
		if(Rush4TheDragon.pause) status = "§cPAUSE";
		else status = "§aGAME";
		p.sendMessage("§7Plugin Status: "+plStatus);
		if(Rush4TheDragon.status) p.sendMessage("§7Game Status: "+status);
		p.sendMessage("");
		if(Rush4TheDragon.status) p.sendMessage("§7- §f/r4td pause|unpause§8: §7Pause or unpause the game.");
		p.sendMessage("§7- §f/r4td enable|disable§8: §7Enabled or disabled Rush4TheDragon.");
	}

}
