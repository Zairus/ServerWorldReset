package zairus.swreset.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import zairus.swreset.SWReset;
import zairus.swreset.common.SWRConfig;
import zairus.swreset.common.SWRConfig.ResetWorldInfo;

public class SWRWorldHandler
{
	public static void doReset()
	{
		for (int i = 0; i < SWRConfig.number_of_worlds; ++i)
		{
			ResetWorldInfo wInfo = SWRConfig.worlds.get(i);
			
			if (wInfo.worldDir == null || wInfo.templateDir == null)
				break;
			
			if (wInfo.worldDir == "" || wInfo.templateDir == "")
				break;
			
			String pathWorld = wInfo.worldDir.replace("//", File.separator);
			pathWorld = wInfo.worldDir.replace("\\\\", File.separator);
			
			String pathTemplate = wInfo.templateDir.replace("//", File.separator);
			pathTemplate = wInfo.templateDir.replace("\\\\", File.separator);
			
			File worldDir = new File(pathWorld);
			File templateDir = new File(pathTemplate);
			
			SWReset.log("#######################################");
			SWReset.log("## Server World Reset - Processing - ##");
			SWReset.log("World [" + pathWorld + "]: " + worldDir.exists());
			SWReset.log("Template [" + pathTemplate + "] " + templateDir.exists());
			
			if (!worldDir.exists() || !templateDir.exists())
			{
				SWReset.log(">> Error, a path doesn't exist. Aborting.");
				return;
			}
			
			SWReset.log(">> Deleting world directory.");
			deleteFolder(worldDir);
			
			try {
				SWReset.log(">> Copying world from template.");
				FileUtils.copyDirectory(templateDir, worldDir);
			} catch (IOException e) {
				SWReset.log("" + e.getStackTrace());
			}
			
			SWReset.log("#######################################");
		}
		
		SWRConfig.setResetOnStart(false);
	}
	
	public static void deleteFolder(File folder)
	{
		File[] files = folder.listFiles();
		
		if(files != null) //some JVMs return null for empty dirs
		{
			for(File f: files)
			{
				if(f.isDirectory())
				{
					deleteFolder(f);
				}
				
				f.delete();
			}
		}
	}
}
