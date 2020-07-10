package cz.apigames.zetanpcs;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.md_5.bungee.api.ChatColor;

public class FileHandler {

	private static Main main;
	
    static File dataFile;
    static FileConfiguration NPCdata;
 
    public static void createDataFile(UUID uuid, String name, String id) {
    	dataFile = new File("plugins" + File.separator + "ZetaNPCs" + File.separator + "npcdata" + File.separator, uuid + ".yml");
        if (!dataFile.exists()) {
            try {
            	dataFile.createNewFile();
            } catch(Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error creating " + dataFile.getName() + "!");
            }
        }
        NPCdata = YamlConfiguration.loadConfiguration(dataFile);
        NPCdata.createSection("Data");
        NPCdata.set("Data.Name", name);
        NPCdata.set("Data.UUID", uuid);
        NPCdata.set("Data.ID", id);
		
        NPCdata.createSection("Interact");
        NPCdata.set("Interact.Command", false);
        NPCdata.set("Interact.SendServer", false);
		
        NPCdata.createSection("Command");
        NPCdata.set("Command.execute", "null");
		
        NPCdata.createSection("SendServer");
        NPCdata.set("SendServer.serverName", "null");
        NPCdata.set("SendServer.JoinMessage", "&8» &7Pøipojuji k serveru &6null&7..");   
		
        try {
        	NPCdata.save(dataFile);
        } catch(Exception e)  {
        	Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error saving " + dataFile.getName() + "!");
        }
    }
 
    public static File getDataFile(UUID uuid) {
    	dataFile = new File(main.getDataFolder(), "player data" + File.separator + uuid + ".yml");
        return dataFile;
    }
 
    public static void loadDataFile(UUID uuid) {
    	dataFile = new File(main.getDataFolder(), "player data" + File.separator + uuid + ".yml");
    	NPCdata = YamlConfiguration.loadConfiguration(dataFile);
    }
 
    public static FileConfiguration getNpcData(UUID uuid) {
    	dataFile = new File(main.getDataFolder(), "player data" + File.separator + uuid + ".yml");
    	NPCdata = YamlConfiguration.loadConfiguration(dataFile);
        return NPCdata;
    }
}
