package clocheplusplus.compat;

import blusunrize.immersiveengineering.api.ComparableItemStack;
import clocheplusplus.util.WeightedItemStack;
import clocheplusplus.util.WeightedPlantHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import net.minecraftforge.common.config.Configuration;

public class WeeeFlowersCompat {

	public static float seedWeight = 0.25f;
	public static float flowerWeight = 1f;

	public static void preInit(Configuration config)
	{
		seedWeight = config.getFloat("seedWeight", "Weee Flowers Compat", 0.25f, 0.0001f, 1f, "The weight of a seed dropping. Must be between 0.0001 and 1");
		flowerWeight = config.getFloat("flowerWeight", "Weee Flowers Compat", 1f, 0.0001f, 1f, "The weight of a flower dropping. Must be between 0.0001 and 1");
	}

	public static void init()
	{

		String[] colors = {"black", "blue", "brown", "cyan", "darkgray", "green", "lightblue",
		  				   "lightgray", "lime", "magenta", "orange", "pink", "purple", "red",
						   "white", "yellow"};

		for(String color: colors)
		{
			Item seed = Item.REGISTRY.getObject(new ResourceLocation("weeeflowers", color + "seeditem"));
			Item flower = Item.REGISTRY.getObject(new ResourceLocation("weeeflowers", color + "flower"));
			Block flowerCrop = Block.REGISTRY.getObject(new ResourceLocation("weeeflowers", color + "flower"));

			BelljarHandler.registerHandler(new WeeeFlowersPlantHandler(new ItemStack(seed), new ItemStack(flower), flowerCrop));

		}

	}

	private static class WeeeFlowersPlantHandler extends WeightedPlantHandler {


		public WeeeFlowersPlantHandler(ItemStack seed, ItemStack flower, Block crop)
		{
			this.seed = new ComparableItemStack(seed, false, false);
			this.crop = crop;

			this.drops = new WeightedItemStack[] {new WeightedItemStack(seed, WeeeFlowersCompat.seedWeight), new WeightedItemStack(flower, WeeeFlowersCompat.flowerWeight)};
			this.soil = new ComparableItemStack(new ItemStack(Blocks.DIRT), false, false);
		}
	}

}
