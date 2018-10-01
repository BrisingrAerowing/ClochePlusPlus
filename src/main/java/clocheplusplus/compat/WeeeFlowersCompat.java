package clocheplusplus.compat;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import blusunrize.immersiveengineering.api.tool.BelljarHandler;

public class WeeeFlowersCompat {

	public static void postInit()
	{

		String[] colors = {"black", "blue", "brown", "cyan", "darkgray", "green", "lightblue",
		  				   "lightgray", "lime", "magenta", "orange", "pink", "purple", "red",
						   "white", "yellow"};

		for(String color: colors)
		{
			Item seed = Item.REGISTRY.getObject(new ResourceLocation("weeeflowers", color + "seeditem"));
			Item flower = Item.REGISTRY.getObject(new ResourceLocation("weeeflowers", color + "flower"));
			Block flowerCrop = Block.REGISTRY.getObject(new ResourceLocation("weeeflowers", color + "flower"));

			BelljarHandler.cropHandler.register(new ItemStack(seed), new ItemStack[]{new ItemStack(flower), new ItemStack(seed)}, new ItemStack(Blocks.DIRT), flowerCrop.getDefaultState());

		}

	}

}
