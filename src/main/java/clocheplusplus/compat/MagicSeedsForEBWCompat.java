package clocheplusplus.compat;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import blusunrize.immersiveengineering.api.tool.BelljarHandler;

public class MagicSeedsForEBWCompat {

	public static void postInit()
	{

		Item seeds = Item.REGISTRY.getObject(new ResourceLocation("t3s4ebw", "magic_seed"));
		Item crystalFlower = Item.REGISTRY.getObject(new ResourceLocation("ebwizardry", "crystal_flower"));
		Block crystalCrop = Block.REGISTRY.getObject(new ResourceLocation("t3s4ebw", "crystal_crop"));

		BelljarHandler.cropHandler.register(new ItemStack(seeds), new ItemStack[]{new ItemStack(crystalFlower)}, new ItemStack(Blocks.DIRT), crystalCrop.getDefaultState());

	}

}
