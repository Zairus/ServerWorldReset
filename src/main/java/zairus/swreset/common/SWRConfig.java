package zairus.swreset.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class SWRConfig
{
	public static Configuration configuration;
	
	public static boolean reset_on_start = false;
	public static int number_of_worlds = 1;
	
	public static List<ResetWorldInfo> worlds = new ArrayList<ResetWorldInfo>();
	
	public static void init(File cFile)
	{
		configuration = new Configuration(cFile);
		
		configuration.load();
		
		configuration.addCustomCategoryComment("SETTINGS", "Define settings to control the behavior of the mod.");
		configuration.addCustomCategoryComment("TEMPLATES", "Define paths for templates, paths are relative to the directory the server is running. start with no diagonal and use double diaggonal to separate directories, for example: saves\\world1, or world_templates\\world1, or world_templates\\category\\world1");
		
		reset_on_start = configuration.getBoolean("reset_on_start", "SETTINGS", reset_on_start, "Indicates whether to reset all worlds on start. Defaults to false. Can be set to true in game with the command sw_resetonstart, or to false with the command sw_noresetonstart");
		number_of_worlds = configuration.getInt("number_of_worlds", "SETTINGS", number_of_worlds, 0, 255, "Indicates how many worlds will be processed on the TEMPLATES section.");
		
		for (int i = 0; i < number_of_worlds; ++i)
		{
			String wDir = configuration.getString("world_dir" + i, "TEMPLATES", "", "Set directory path for world relative to server run dir, example: world1, or saves\\world1");
			String wTemp = configuration.getString("world_template" + i, "TEMPLATES", "", "Ser directory path for template relative to server run dir, example: world_templates\\world1");
			
			worlds.add(new ResetWorldInfo().setWorldDir(wDir).setTemplateDir(wTemp));
		}
		
		configuration.save();
	}
	
	public static void setResetOnStart(boolean reset)
	{
		File cFile = new File("config" + File.separator + "swreset.cfg");
		
		configuration = new Configuration(cFile);
		
		configuration.load();
		
		Property prop = configuration.get("SETTINGS", "reset_on_start", reset_on_start);
		
		prop.set(reset);
		reset_on_start = reset;
		
		configuration.save();
	}
	
	public static class ResetWorldInfo
	{
		public String worldDir;
		public String templateDir;
		
		public ResetWorldInfo setWorldDir(String dir)
		{
			this.worldDir = dir;
			return this;
		}
		
		public ResetWorldInfo setTemplateDir(String dir)
		{
			this.templateDir = dir;
			return this;
		}
	}
}
