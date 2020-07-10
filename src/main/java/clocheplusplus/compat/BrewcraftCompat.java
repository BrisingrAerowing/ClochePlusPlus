package clocheplusplus.compat;
import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class BrewcraftCompat {

    public static void init()
    {
        Item hopsSeeds = Item.REGISTRY.getObject(new ResourceLocation("brewcraft", "hopsseeditem"));
        Item hopsItem = Item.REGISTRY.getObject(new ResourceLocation("brewcraft", "hopsitem"));
        Block hopsCrop = Block.REGISTRY.getObject(new ResourceLocation("brewcraft", "pamhopscrop"));

        BelljarHandler.cropHandler.register(new ItemStack(hopsSeeds), new ItemStack[]{new ItemStack(hopsItem, 3), new ItemStack(hopsSeeds)}, new ItemStack(Blocks.DIRT), hopsCrop.getDefaultState());

    }

}
