package cz.apigames.zetanpcs;

import java.io.DataOutputStream;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.Messenger;

public class Bungee {
	
    static Logger log = Logger.getLogger("Minecraft");
    
    public static boolean connectToBungeeServer(Player player, String server) {
    	try {
    		Messenger messenger = Bukkit.getMessenger();
    		if (!messenger.isOutgoingChannelRegistered(Main.getInstance(), "BungeeCord")) {
    		messenger.registerOutgoingPluginChannel(Main.getInstance(), "BungeeCord");
    		}
    		
    		if (server.length() == 0) {
        	
    			player.sendMessage("&cDošlo k chybì pøi pøipojování! Kód chyby: ZetaNPCs-1");
    			return false;
          
    		}
    		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
    		@SuppressWarnings("resource")
			DataOutputStream out = new DataOutputStream(byteArray);
     
    		out.writeUTF("Connect");
    		out.writeUTF(server);
        
            player.sendPluginMessage(Main.getInstance(), "BungeeCord", byteArray.toByteArray());
    	}
        catch (Exception ex)
        {
        	ex.printStackTrace();
            log.warning("Spojeni hrace " + player.getName() + ": se serverem \"" + server + "\" se nepodarilo.");
            return false;
         }
         return true;
   	}
      
}
