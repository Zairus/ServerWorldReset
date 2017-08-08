package zairus.swreset.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import zairus.swreset.common.SWRConfig;

public class CommandResetOnStart extends CommandBase
{
	@Override
	public String getCommandName()
	{
		return "sw_resetonstart";
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
		SWRConfig.setResetOnStart(true);
		notifyCommandListener(sender, this, "Set the worlds to reset on next start.", new Object[0]);
	}
}
