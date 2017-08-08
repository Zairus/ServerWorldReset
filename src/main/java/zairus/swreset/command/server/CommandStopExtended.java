package zairus.swreset.command.server;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandStopExtended extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "sw_stop";
	}
	
	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "";
	}
	
	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		long time = 0;
		
		if (args.length > 0)
		{
			time = parseLong(args[0]);
		}
		
		if (time == 0)
		{
			if (server.worldServers != null)
			{
				notifyCommandListener(sender, this, "Stopping the server now", new Object[0]);
			}
			
			server.initiateShutdown();
		}
		else
		{
			if (server.worldServers != null)
			{
				notifyCommandListener(sender, this, "Stopping the server in " + time + " milliseconds.", new Object[0]);
			}
			
			Timer timer = new Timer();
			
			timer.schedule(new TimerTask() {
				@Override
				public void run()
				{
					server.initiateShutdown();
				}
			}, time);
		}
	}
}
