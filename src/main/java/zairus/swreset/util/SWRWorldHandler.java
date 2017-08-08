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
			
			File worldDir = new File(".\\" + wInfo.worldDir);
			File templateDir = new File(".\\" + wInfo.templateDir);
			
			deleteFolder(worldDir);
			
			try {
				FileUtils.copyDirectory(templateDir, worldDir);
			} catch (IOException e) {
				SWReset.log("" + e.getStackTrace());
			}
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
