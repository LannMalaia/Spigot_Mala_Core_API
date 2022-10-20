package laylia_core.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;

public class ItemParser
{
	public static Content getItemContent(ItemStack item)
	{
		List<TextComponent> list = new ArrayList<TextComponent>();
		ItemMeta meta = item.getItemMeta();
		
		if (meta.hasDisplayName())
			list.add(new TextComponent(meta.getDisplayName()));
		else
			list.add(new TextComponent(Lang.Localize(item.getType())));
		list.add(new TextComponent("\n"));
		
		boolean hasEnchant = false;
		for (Enchantment enchant : item.getEnchantments().keySet())
		{
			hasEnchant = true;
			String enchStr = Lang.Localize(enchant) + " " + Lang.Localize_Level(item.getEnchantmentLevel(enchant));
			list.add(new TextComponent("¡×7" + enchStr + "\n"));
		}
		if (hasEnchant)
			list.add(new TextComponent("\n"));

		if (meta.hasLore())
		{
			for (String str : meta.getLore())
				list.add(new TextComponent(str + "\n"));
		}

		Content content = new Text(list.toArray(TextComponent[]::new));
		return content;
	}
}
