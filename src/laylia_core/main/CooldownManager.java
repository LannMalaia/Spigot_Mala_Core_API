package laylia_core.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class CooldownManager implements Runnable
{
	Player player;
	String cooldown_name;
	int cooltime;
	
	public CooldownManager(Player _p, String str, int time)
	{
		player = _p;
		cooldown_name = str;
		cooltime = time;
		player.setMetadata("cooldown.m." + str, new FixedMetadataValue(Laylia_API.mother, cooltime));
		Bukkit.getScheduler().runTaskLater(Laylia_API.mother, this, 20);
	}
	
	public static boolean Has_Cooldown(Player p, String name)
	{
		if(p.hasMetadata("cooldown.m." + name))
		{
			if(p.getMetadata("cooldown.m." + name).get(0).asInt() > 0)
			{
				return true;
			}
		}
		return false;
	}
	
	public static int Get_Cooldown(Player p, String name)
	{
		if(p.hasMetadata("cooldown.m." + name))
		{
			if(p.getMetadata("cooldown.m." + name).get(0).asInt() > 0)
			{
				return p.getMetadata("cooldown.m." + name).get(0).asInt();
			}
		}
		return 0;
	}
	
	public void run()
	{
		cooltime--;
		if(cooltime < 1)
		{
			player.removeMetadata("cooldown.m." + cooldown_name, Laylia_API.mother);
		}
		else
		{
			player.setMetadata("cooldown.m." + cooldown_name, new FixedMetadataValue(Laylia_API.mother, cooltime));
			Bukkit.getScheduler().runTaskLater(Laylia_API.mother, this, 20);
		}
	}
}