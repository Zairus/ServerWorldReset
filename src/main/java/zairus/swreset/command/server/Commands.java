package zairus.swreset.command.server;

import net.minecraft.command.CommandBase;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class Commands
{
	public static final CommandBase SET_RESET = new CommandResetOnStart();
	public static final CommandBase SET_NORESET = new CommandNoResetOnStart();
	public static final CommandBase SW_STOP = new CommandStopExtended();
	
	public static void registerCommands(FMLServerStartingEvent event)
	{
		event.registerServerCommand(SET_RESET);
		event.registerServerCommand(SET_NORESET);
		event.registerServerCommand(SW_STOP);
	}
}
