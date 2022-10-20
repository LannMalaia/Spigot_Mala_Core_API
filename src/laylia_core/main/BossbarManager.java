package laylia_core.main;

import java.util.HashMap;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;

public class BossbarManager
{
	public HashMap<String, BossbarElement> BossbarList = new HashMap<String, BossbarElement>();
	
	public BossbarElement createBossbar(String id, String name, BarColor color, BarStyle style, BarFlag flags, double min, double max)
	{
		BossbarElement e = new BossbarElement(name, color, style, flags, min, max);
		BossbarList.put(id, e);
		return e;
	}
	
	public BossbarElement getBossbar(String id)
	{
		return BossbarList.get(id);
	}
	
	public boolean removeBossbar(String id)
	{
		BossbarElement e = BossbarList.remove(id);
		if(e != null)
		{
			e.Remove_Bar();
			return true;
		}
		else
			return false;
	}
	
	public void removeAll()
	{
		for(BossbarElement be : BossbarList.values())
		{
			be.Remove_Bar();
		}
		BossbarList.clear();
	}
}
