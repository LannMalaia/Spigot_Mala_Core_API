package laylia_core.main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import org.apache.http.annotation.Obsolete;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;

import com.google.gson.*;

public class Lang
{
	static JsonObject locale_names = null;
	
	public Lang()
	{

		File f = Laylia_API.mother.getDataFolder();
		if (!f.exists())
		{
			f.mkdir();
		}
		String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

		File lang_file = new File(f, version + "_lang.json");
		if (!lang_file.exists())
		{
			Bukkit.getConsoleSender().sendMessage("§c큰일이다!! 언어 파일이 없다!!");
			Bukkit.getConsoleSender().sendMessage("§c" + version + "_lang.json 이라는 이름의 언어 파일이 필요해요!");
			Bukkit.getConsoleSender().sendMessage("§c플러그인 폴더에 들어가면 언어 파일을 취득하는 방법이 있으니 서둘러 언어 파일을 긁어오새오!");
			return;
		}

		try
		{
			FileInputStream file_reader = new FileInputStream(lang_file.getAbsolutePath());
			byte[] read_buffer = new byte[file_reader.available()];
			while(file_reader.read(read_buffer) != -1) ;
			file_reader.close();
		
			String json_string = new String(read_buffer);
			
			locale_names = JsonParser.parseString(json_string).getAsJsonObject();
			
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}

	public static String Localize(Enchantment _enchant)
	{
		String main = "enchantment";
		NamespacedKey key = _enchant.getKey();
		return locale_names.get(main + "." + key.getNamespace() + "." + key.getKey()).getAsString();
	}
	public static String Localize_Level(int _level)
	{
		String main = "enchantment";
		return locale_names.get(main + "." + "level." + _level).getAsString();
	}
	public static String Localize(Material _material)
	{
		String main = _material.isBlock() ? "block" : "item";
		NamespacedKey key = _material.getKey();
		return locale_names.get(main + "." + key.getNamespace() + "." + key.getKey()).getAsString();
	}
}
