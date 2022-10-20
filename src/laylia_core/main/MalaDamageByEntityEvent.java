package laylia_core.main;

import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class MalaDamageByEntityEvent extends EntityDamageByEntityEvent
{
	public MalaDamageByEntityEvent(Entity damager, Entity damagee, DamageCause cause, double damage)
	{
		super(damager, damagee, cause, damage);
	}
}
