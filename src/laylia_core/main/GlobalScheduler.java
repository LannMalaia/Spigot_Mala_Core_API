package laylia_core.main;


import org.bukkit.Bukkit;

import laylia_core.element.Time_Event;

public class GlobalScheduler
{
	public GlobalScheduler()
	{
		Bukkit.getScheduler().runTaskTimer(Laylia_API.mother, new Timer(), 20, 20);
	}
}

class Timer implements Runnable
{

	@Override
	public void run()
	{
		Bukkit.getPluginManager().callEvent(new Time_Event());
	}
}