package laylia_core.main;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.variables.Variables;
import laylia_core.fishing_totalizer.Totalizer;

public class Laylia_API extends JavaPlugin
{
	public static Laylia_API mother;

	Totalizer totalizer = new Totalizer();
	
	public BossbarManager bossbar_manager;
	public FireworkManager firework_manager;
	public GlobalScheduler global_scheduler;
	
	@Override
	public void onEnable()
	{
		Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Laylia Core API 활성화");
		mother = this;
		new Lang();
		
		Bukkit.getServicesManager().register(Laylia_API.class, this, this, ServicePriority.Highest);
		Bukkit.getPluginManager().registerEvents(totalizer, this);
		
		bossbar_manager = new BossbarManager();
		firework_manager = new FireworkManager();
		global_scheduler = new GlobalScheduler();
	}
	
	@Override
	public void onDisable()
	{
		Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "Laylia Core API 비활성화");
		totalizer.Write_Total();
		bossbar_manager.removeAll();
	}
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
    	if(cmd.getName().equalsIgnoreCase("la-iteminfo"))
    	{
    		if(sender instanceof Player && sender.hasPermission("*"))
    		{
    			Player player = (Player)sender;
    			String msg = player.getInventory().getItemInMainHand().toString();
    			msg = msg.replace("§", "ⓒ");
    			player.sendMessage(msg);
    		}
    		return true;
    	}
    	if(cmd.getName().equalsIgnoreCase("la-skript_variable"))
    	{
    		if(sender instanceof Player && sender.hasPermission("*") && args.length == 1)
    		{
    			Player player = (Player)sender;
    			String o = Variables.getVariable(args[0], null, false).toString();
    			// Variables.setVariable(name, value, e, local);
    			player.sendMessage(args[0] + "변수는 " + o + " 라는 값임");
    		}
    		return true;
    	}
    	return false;
    }
}
