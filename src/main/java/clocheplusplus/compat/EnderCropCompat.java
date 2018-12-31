package clocheplusplus.compat;

import blusunrize.immersiveengineering.api.ComparableItemStack;
import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import clocheplusplus.util.WeightedItemStack;
import clocheplusplus.util.WeightedPlantHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;

import java.util.Random;

public class EnderCropCompat {

	public static float seedWeight = 0.25f;
	public static float pearlWeight = 1f;
	public static int minPearls = 1;
	public static int maxPearls = 2;

	public static void preInit(Configuration config)
	{
		int epStackLimit = Items.ENDER_PEARL.getItemStackLimit(new ItemStack(Items.ENDER_PEARL));
		seedWeight = config.getFloat("seedWeight", "Ender Crop", 0.25f, 0.0001f, 1, "The weight of a seed dropping. Must be between 0.0001 and 1");
		pearlWeight = config.getFloat("pearlWeight", "Ender Crop", 1f, 0.0001f, 1, "The weight of an Ender Pearl dropping. Must be between 0.0001 and 1");
		minPearls = config.getInt("minPearls", "Ender Crop", 1, 1, epStackLimit, "The minimum number of Ender Pearls to drop. Must be between 1 and the max stack size of Ender Pearls");
		maxPearls = config.getInt("maxPearls", "Ender Crop", 2, 1, epStackLimit, "The maximum number of Ender Pearls to drop. Must be between 1 and the max stack size of Ender Pearls");
	}

	public static void init()
	{
		BelljarHandler.registerHandler(new EnderCropPlantHandler());
	}

	private static class EnderCropPlantHandler extends WeightedPlantHandler
	{

		Item enderSeeds = null;

		public EnderCropPlantHandler()
		{
			enderSeeds = Item.REGISTRY.getObject(new ResourceLocation("endercrop", "ender_seeds"));

			this.seed = new ComparableItemStack(new ItemStack(enderSeeds), false, false);
			this.soil = new ComparableItemStack(new ItemStack(Blocks.END_STONE), false, false);
			this.crop = Block.REGISTRY.getObject(new ResourceLocation("endercrop", "ender_crop"));

		}


		@Override
		public ItemStack[] getOutput(ItemStack seed, ItemStack soil, TileEntity tile) {
			int numPearls = rand.nextInt(maxPearls - minPearls) + minPearls;

			WeightedItemStack[] stacks = new WeightedItemStack[] {new WeightedItemStack(new ItemStack(enderSeeds), seedWeight), new WeightedItemStack(new ItemStack(Items.ENDER_PEARL, numPearls), pearlWeight)};

			return WeightedItemStack.getRandomDrops(rand, stacks);

		}
	}

}
