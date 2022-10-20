package laylia_core.main;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import io.lumine.mythic.lib.MythicLib;
import io.lumine.mythic.lib.api.player.EquipmentSlot;
import io.lumine.mythic.lib.damage.AttackMetadata;
import io.lumine.mythic.lib.damage.DamageMetadata;
import io.lumine.mythic.lib.damage.DamageType;
import io.lumine.mythic.lib.manager.ElementManager;
import io.lumine.mythic.lib.player.PlayerMetadata;
import net.Indyuce.mmocore.MMOCore;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.Indyuce.mmoitems.api.Element;

public class Damage
{
	/**
	 * @author Malaia Garnet
	 * @version 220327
	 * @apiNote 공격이 가능한지에 대한 여부 체크
	 * @param attacker
	 * @param target
	 * @return
	 */
	public static boolean Is_Possible(Entity attacker, Entity target)
	{
		if (attacker == target)
			return false;
		if (attacker instanceof Player && target instanceof Player)
		{
			Player a = (Player)attacker;
			Player b = (Player)target;
			PlayerData data = MMOCore.plugin.dataProvider.getDataManager().get(a);
			if (data.getParty() != null)
			{
				if (data.getParty().hasMember(b))
					return false;
			}
		}
		MalaDamageByEntityEvent ede = new MalaDamageByEntityEvent(attacker, target, DamageCause.ENTITY_ATTACK, 0);
		Bukkit.getPluginManager().callEvent(ede);
		return !ede.isCancelled();
	}
	
	/**
	 * @author Malaia Garnet
	 * @version 210116
	 * @apiNote 플레이어의 공격, mmocore 버전
	 * @param _player
	 * @param _target
	 * @param _damage
	 * @param _types
	 */
	public static void Attack(Player _player, LivingEntity _target, double _damage, DamageType... _types)
	{
		PlayerData data = MMOCore.plugin.dataProvider.getDataManager().get(_player);
		if (_target instanceof Player)
		{
			Player target = (Player)_target;

			if (data.getParty() != null)
			{
				if (data.getParty().hasMember(target))
					return;
			}
		}
		PlayerMetadata stats = data.getMMOPlayerData().getStatMap().cache(EquipmentSlot.MAIN_HAND);
		AttackMetadata amd = new AttackMetadata(new DamageMetadata(_damage, _types), stats);
		MythicLib.plugin.getDamage().damage(amd, _target);
	}
}