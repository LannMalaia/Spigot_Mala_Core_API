package laylia_core.main;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class ActionbarManager
{
	public static void sendActionBar(Player player, String msg)
	{
		player.sendMessage(msg);
        // player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(msg));
	}
}
