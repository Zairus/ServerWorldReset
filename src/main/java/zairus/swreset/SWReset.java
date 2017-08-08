package zairus.swreset;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import zairus.swreset.command.server.Commands;
import zairus.swreset.common.SWRConfig;
import zairus.swreset.common.SWRConstants;
import zairus.swreset.util.SWRWorldHandler;

@Mod(modid = SWRConstants.MOD_ID, name = SWRConstants.MOD_NAME, version = SWRConstants.MOD_VERSION, acceptableRemoteVersions = "*")
public class SWReset
{
	public static Logger logger;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		SWRConfig.init(event.getSuggestedConfigurationFile());
		
		if (SWRConfig.reset_on_start)
		{
			SWRWorldHandler.doReset();
		}
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		;
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		;
	}
	
	@Mod.EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		Commands.registerCommands(event);
	}
	
	public static void log(String text)
	{
		logger.info(text);
	}
}
