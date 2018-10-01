package clocheplusplus;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import clocheplusplus.compat.*;

@Mod(modid = ClochePlusPlus.MODID, name = ClochePlusPlus.NAME, version = ClochePlusPlus.VERSION, dependencies = ClochePlusPlus.DEPENDENCIES)
public class ClochePlusPlus {

	public static final String MODID = "clocheplusplus";
	public static final String NAME = "Cloche++";
	public static final String VERSION = "0.1";
	public static final String DEPENDENCIES="required-after:immersiveengineering;after:weeeflowers;after:t3s4ebw";

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		if (Loader.isModLoaded("t3s4ebw"))
		{
			MagicSeedsForEBWCompat.postInit();
		}

		if(Loader.isModLoaded("weeeflowers"))
		{
			WeeeFlowersCompat.postInit();
		}
	}

}
