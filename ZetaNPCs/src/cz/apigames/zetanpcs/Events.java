package cz.apigames.zetanpcs;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.jitse.npclib.NPCLib;
import net.jitse.npclib.api.NPC;
import net.jitse.npclib.api.events.NPCInteractEvent;
import net.jitse.npclib.api.skin.Skin;
import net.md_5.bungee.api.ChatColor;

public class Events implements Listener {

	private Main main;
	
	NPCLib library = main.getNPCLib();
	
	public void onEnable() {
		main.getServer().getPluginManager().registerEvents(main, main);
	}
	
	@EventHandler
	public void onNPCInteract(NPCInteractEvent event) {
		
		Player player = event.getWhoClicked();
		NPC npc = event.getNPC();
		String npcID = npc.getId();
		UUID npcUUID = npc.getUniqueId();
		
		if(player.getInventory().getItemInMainHand().getType() == Material.STRING) {
			String message = "&8» &7ID tohoto NPC je &e#" + npcID;
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
			
		if(Commands.removeNPC != "false") {
			
			npc.destroy();
			String messageDestroy = "&8» &7Smazal jsi npc s ID &e#" + npcID;
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', messageDestroy));
			Commands.removeNPC = "false";
			
		}
		
		if(Commands.changeSkin != "false") {
			
			Skin skin = new Skin(Commands.skinValue, Commands.skinSignature);
		    npc.setSkin(skin);
			
			String messageDestroy = "&8» &7Zmìnil jsi skin pro NPC &e#" + npcID;
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', messageDestroy));
			Commands.changeSkin = "false";
			
		}
			
		} else {
			if(FileHandler.getNpcData(npcUUID).getBoolean("Interact.Command") != false) {
				
				if(FileHandler.getNpcData(npcUUID).getString("Command.execute") != "null") {
					
					player.performCommand(FileHandler.getNpcData(npcUUID).getString("Command.execute"));
					
				}
					
			} else if(FileHandler.getNpcData(npcUUID).getBoolean("Interact.SendServer") != false) {
				
				if(FileHandler.getNpcData(npcUUID).getString("SendServer.serverName") != "null") {
					
					Bungee.connectToBungeeServer(player, FileHandler.getNpcData(npcUUID).getString("SendServer.serverName"));
					String message = FileHandler.getNpcData(npcUUID).getString("SendServer.joinMessage");
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
					
				}
				
			}
				
		}
		
		
	}
	
}
