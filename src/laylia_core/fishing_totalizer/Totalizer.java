package laylia_core.fishing_totalizer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;

import laylia_core.main.Laylia_API;

public class Totalizer implements Listener
{
	// 총 낚시량
	public HashMap<String, Integer> total_fishing = new HashMap<String, Integer>();
	// 총 살해량
	public HashMap<String, Integer> total_killing = new HashMap<String, Integer>();
	
	SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
	String start_time = "";
	
	public Totalizer()
	{
		total_fishing = new HashMap<String, Integer>();
		start_time = date_format.format(Calendar.getInstance().getTime());
	}
	
	public void Write_Total()
	{
		String end_time = date_format.format(Calendar.getInstance().getTime());

		try
		{
			// 폴더 설정
			File directory = Laylia_API.mother.getDataFolder();
			if (!directory.exists())
				directory.mkdir();
			File sub_dir = new File(directory, "Totals");
			if (!sub_dir.exists())
				sub_dir.mkdir();

			// 낚시
			File fish_dir = new File(sub_dir, "Fishing");
			if (!fish_dir.exists())
				fish_dir.mkdir();
			File saveto = new File(fish_dir, start_time + "~" + end_time + ".txt");
			if (!saveto.exists())
				saveto.createNewFile();
			// 파일 작성
			FileConfiguration file = YamlConfiguration.loadConfiguration(saveto);
			file.load(saveto);
			Iterator<String> iter = total_fishing.keySet().iterator();
			while(iter.hasNext())
			{
				String name = iter.next();
				file.set(name, total_fishing.get(name).toString() + "회");
			}
			file.save(saveto);

			// 사냥
			File kill_dir = new File(sub_dir, "Killing");
			if (!kill_dir.exists())
				kill_dir.mkdir();
			saveto = new File(kill_dir, start_time + "~" + end_time + ".txt");
			if (!saveto.exists())
				saveto.createNewFile();
			// 파일 작성
			file = YamlConfiguration.loadConfiguration(saveto);
			file.load(saveto);
			iter = total_killing.keySet().iterator();
			while(iter.hasNext())
			{
				String name = iter.next();
				file.set(name, total_killing.get(name).toString() + "회");
			}
			file.save(saveto);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void PlayerFishing(PlayerFishEvent _event)
	{
		if (_event.getState() != PlayerFishEvent.State.CAUGHT_FISH)
			return;
		
		String name = _event.getPlayer().getName();
		if (total_fishing.containsKey(name))
			total_fishing.put(name, total_fishing.get(name) + 1);
		else
			total_fishing.put(name, 1);
	}

	@EventHandler
	public void PlayerKilling(EntityDeathEvent _event)
	{
		if (_event.getEntity() instanceof Monster)
		{
			if (_event.getEntity().getKiller() != null)
			{
				String name = _event.getEntity().getKiller().getName();
				if (total_killing.containsKey(name))
					total_killing.put(name, total_killing.get(name) + 1);
				else
					total_killing.put(name, 1);
			}
		}
	}
}
