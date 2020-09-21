package clocheplusplus.util;

import blusunrize.immersiveengineering.api.ComparableItemStack;
import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public abstract class WeightedPlantHandler implements BelljarHandler.IPlantHandler
{

	protected ComparableItemStack seed;
	protected WeightedItemStack[] drops;
	protected Block crop;
	protected ComparableItemStack soil;
	protected Random rand = new Random();

	@Override
	public boolean isCorrectSoil(ItemStack seed, ItemStack soil) {
		return new ComparableItemStack(soil, false, false).equals(this.soil);
	}

	@Override
	public float getGrowthStep(ItemStack seed, ItemStack soil, float growth, TileEntity tile, float fertilizer, boolean render) {
		return 0.003125f * fertilizer;
	}

	@Override
	public ItemStack[] getOutput(ItemStack seed, ItemStack soil, TileEntity tile) {
		return WeightedItemStack.getRandomDrops(rand, drops);
	}

	@Override
	public boolean isValid(ItemStack seed) {
		return new ComparableItemStack(seed, false, false).equals(this.seed);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IBlockState[] getRenderedPlant(ItemStack seed, ItemStack soil, float growth, TileEntity tile)
	{
			List<IBlockState> states = new ArrayList<IBlockState>();
			states.add(crop.getDefaultState());

			IBlockState[] ret = new IBlockState[states.size()];
			for(int i = 0; i < states.size(); i++)
				if(states.get(i) !=null)
					if(states.get(i).getBlock() instanceof BlockCrops)
					{
						int max = ((BlockCrops)states.get(i).getBlock()).getMaxAge();
						ret[i] = ((BlockCrops)states.get(i).getBlock()).withAge(Math.min(max, Math.round(max*growth)));
					}
					else
					{
						for(IProperty prop : states.get(i).getPropertyKeys())
							if("age".equals(prop.getName())&&prop instanceof PropertyInteger)
							{
								int max = 0;
								for(Integer allowed : ((PropertyInteger)prop).getAllowedValues())
									if(allowed!=null&&allowed > max)
										max = allowed;
								ret[i] = states.get(i).withProperty(prop, Math.min(max, Math.round(max*growth)));
							}
						if(ret[i]==null)
							ret[i] = states.get(i);
					}
			return ret;

	}

}
