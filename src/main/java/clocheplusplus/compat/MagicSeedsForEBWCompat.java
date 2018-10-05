package clocheplusplus.compat;

import blusunrize.immersiveengineering.api.ComparableItemStack;
import clocheplusplus.util.WeightedItemStack;
import clocheplusplus.util.WeightedPlantHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;

public class MagicSeedsForEBWCompat {

	public static float seedWeight = 0.25f;
	public static float flowerWeight = 1f;

	public static void preInit(Configuration config)
	{
		seedWeight = config.getFloat("seedWeight", "Magic Seeds Compat", 0.25f, 0.0001f, 1f, "The weight of a seed dropping. Must be between 0.0001 and 1");
		flowerWeight = config.getFloat("flowerWeight", "Magic Seeds Compat", 1f, 0.0001f, 1f, "The weight of a flower dropping. Must be between 0.0001 and 1");
	}


	public static void init()
	{

		BelljarHandler.registerHandler(new MagicSeedsPlantHandler());;

	}

	private static class MagicSeedsPlantHandler extends WeightedPlantHandler {

		public MagicSeedsPlantHandler()
		{
			Item seeds = Item.REGISTRY.getObject(new ResourceLocation("t3s4ebw", "magic_seed"));
			Item crystalFlower = Item.REGISTRY.getObject(new ResourceLocation("ebwizardry", "crystal_flower"));
			crop = Block.REGISTRY.getObject(new ResourceLocation("t3s4ebw", "crystal_crop"));

			this.seed = new ComparableItemStack(new ItemStack(seeds), false, false);
			this.drops = new WeightedItemStack[] {new WeightedItemStack(seed.stack, MagicSeedsForEBWCompat.seedWeight), new WeightedItemStack(new ItemStack(crystalFlower), MagicSeedsForEBWCompat.flowerWeight)};
			this.soil = new ComparableItemStack(new ItemStack(Blocks.DIRT), false, false);
		}

	}

}
