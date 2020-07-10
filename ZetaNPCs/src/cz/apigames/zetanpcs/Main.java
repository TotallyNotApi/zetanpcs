package cz.apigames.zetanpcs;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.jitse.npclib.NPCLib;

public class Main extends JavaPlugin implements Listener {

	public NPCLib library;
	
	private static Main instance;
	
	public Commands commands;
	
    public void onEnable() {
        this.library = new NPCLib(this);
        instance = this;
        
        commands = new Commands();
		getCommand("npc").setExecutor(commands);
		
		getServer().getPluginManager().registerEvents(this, this);
    }
    
	public NPCLib getNPCLib() {;
		return library;
	}

	public static Main getInstance() {
		return instance;
	}
}
