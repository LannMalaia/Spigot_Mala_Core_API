package laylia_core.main;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;


public class BossbarElement
{
	public BossBar bar;
	
	public String bar_title;
	public double min_value;
	public double max_value;
	
	public BossbarElement(String title, BarColor color, BarStyle style, BarFlag flags, double min, double max)
	{
		min_value = min;
		max_value = max;
		
		bar = Bukkit.createBossBar(bar_title, BarColor.RED, BarStyle.SEGMENTED_10, BarFlag.PLAY_BOSS_MUSIC);
		bar.setProgress(min_value / max_value);
		bar.setVisible(true);
	}
	
	public void Remove_Bar()
	{
		List<Player> temp = bar.getPlayers();
		for(int i = 0; i < temp.size(); i++)
		{
			bar.removePlayer(temp.get(i));
		}
		bar.removeAll();
		bar.setVisible(false);
	}
	
	public void Change_Color(BarColor bc)
	{
		bar.setColor(bc);
	}
	
	public void Change_Title(String name)
	{
		bar_title = name;
		bar.setTitle(bar_title);
	}
	
	public void Change_Style(BarStyle bs)
	{
		bar.setStyle(bs);
	}
	
	public void Change_Visible(boolean visible)
	{
		bar.setVisible(visible);
	}
	
	@Deprecated
	public void Change_Progress(double min, double max)
	{
		min_value = min;
		max_value = max;
		bar.setProgress(min_value / max_value);
	}
	
	public void Add_Player(Player player)
	{
		bar.addPlayer(player);
	}
	
	public void Update()
	{
		List<Player> temp = bar.getPlayers();
		for(int i = 0; i < temp.size(); i++)
		{
			if(!temp.get(i).isOnline())
				bar.removePlayer(temp.get(i));
		}
	}
}
