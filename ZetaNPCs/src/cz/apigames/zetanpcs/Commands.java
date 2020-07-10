package cz.apigames.zetanpcs;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor {
	
	Main main = Main.getPlugin(Main.class);
	
	NPCLib library = main.getNPCLib();
	
	static String removeNPC = "false";
	static String changeSkin = "false";
	static String skinValue = "null";
	static String skinSignature = "null";
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("npc")) {
			if(args.length == 0) {
				String message = "&8� &cMus� zadat n�jak� argument!";
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
			} else {
				if(args[0].equalsIgnoreCase("create")) {
					if(player.hasPermission("zetanpc.create")) {
						if(!args[1].isEmpty()) {
							NPC npc = library.createNPC();
							npc.setLocation(player.getLocation());
							npc.create();
							npc.show(player);
							UUID npcUUID = npc.getUniqueId();
							String id = npc.getId();
							
							FileHandler.createDataFile(npcUUID, args[1], id);
						
							String message = "&8� &7NPC s ID &e#" + id + "&7 bylo vytvo�eno!";
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
						} else {
							String message = "&8� &cMus� zadat n�zev NPC!";
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
						}
					} else {
						String message = "&8� &cNem� dostate�n� opr�vn�n�!";
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
					}
					
				}
				
				if(args[0].equalsIgnoreCase("remove")) {
					if(player.hasPermission("zetanpc.remove")) {
						if(removeNPC == "false") {
							
							removeNPC = "true";
							String message = "&8� &aVstoupil jsi do re�imu maz�n�!";
							String message2 = "&8� &7Klikni na NPC, kter� chce� smazat.";
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message2));
							
						} else {
							
							removeNPC = "false";
							String message = "&8� &cOpustil jsi re�im maz�n�!";
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
						}
					} else {
						String message = "&8� &cNem� dostate�n� opr�vn�n�!";
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
					}
				}
				
				if(args[0].equalsIgnoreCase("skin")) {
					if(player.hasPermission("zetanpc.skin")) {
						if(changeSkin == "false") {
							if(!args[2].isEmpty()) 
								
								skinValue = args[1];
								skinSignature = args[2];
								
								changeSkin = "true";
								String message = "&8� &aVstoupil jsi do re�imu zm�ny skinu!";
								String message2 = "&8� &7Vyber NPC kliknut�m na n�j.";
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message2));
							} else {
								String message = "&8� &cMus� zadat jm�no hr��e!";
								sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
							}
							
						} else {
							
							changeSkin = "false";
							String message = "&8� &cOpustil jsi re�im zm�ny skinu!";
							sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
						}
					} else {
						String message = "&8� &cNem� dostate�n� opr�vn�n�!";
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
					}
				}
			} 			
			
		return false;
	}
	
}
