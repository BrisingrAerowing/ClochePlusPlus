package clocheplusplus;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import clocheplusplus.compat.*;

@Mod(modid = ClochePlusPlus.MODID, name = ClochePlusPlus.NAME, version = ClochePlusPlus.VERSION, dependencies = ClochePlusPlus.DEPENDENCIES)
public class ClochePlusPlus {

	public static final String MODID = "clocheplusplus";
	public static final String NAME = "Cloche++";
	public static final String VERSION = "0.7";
	public static final String DEPENDENCIES="required-after:immersiveengineering;after:weeeflowers;after:t3s4ebw";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		if(Loader.isModLoaded("t3s4ebw"))
		{
			MagicSeedsForEBWCompat.preInit(config);
		}

		if(Loader.isModLoaded("weeeflowers"))
		{
			WeeeFlowersCompat.preInit(config);
		}

		if(Loader.isModLoaded("endercrop"))
		{
			EnderCropCompat.preInit(config);
		}

		config.save();

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		if (Loader.isModLoaded("t3s4ebw"))
		{
			MagicSeedsForEBWCompat.init();
		}

		if(Loader.isModLoaded("weeeflowers"))
		{
			WeeeFlowersCompat.init();
		}

		if(Loader.isModLoaded("endercrop"))
		{
			EnderCropCompat.init();
		}

		if(Loader.isModLoaded("brewcraft"))
		{
			BrewcraftCompat.init();
		}

	}

}
