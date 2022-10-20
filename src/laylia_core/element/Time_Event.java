package laylia_core.element;

import java.util.Calendar;
import java.util.TimeZone;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class Time_Event extends Event implements Cancellable
{
	private Time_Data time_data;
	private static final HandlerList handlers = new HandlerList();
	
	public Time_Event()
	{
		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
		
		time_data = new Time_Data(cld);
	}
	
	public Time_Data Get_Time_Data()
	{
		return time_data;
	}

	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}

	public static HandlerList getHandlerList()
	{
	    return handlers;
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCancelled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
}
