package ganymedes01.thaumcraftgates.triggers;

import ganymedes01.thaumcraftgates.ThaumcraftGates;

import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import thaumcraft.api.aspects.IAspectSource;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.gates.ITriggerProvider;
import buildcraft.api.transport.IPipe;

/**
 * Thaumcraft Gates
 * 
 * @author ganymedes01
 * 
 */

public class TriggerProvider implements ITriggerProvider {

	@Override
	public LinkedList<ITrigger> getPipeTriggers(IPipe pipe) {
		LinkedList<ITrigger> list = new LinkedList<ITrigger>();

		World world = pipe.getContainer().worldObj;
		int x = pipe.getContainer().xCoord;
		int y = pipe.getContainer().yCoord;
		int z = pipe.getContainer().zCoord;

		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity tile = world.getBlockTileEntity(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
			if (tile instanceof IAspectSource) {
				list.add(ThaumcraftGates.aspectTriggerMinus8);
				list.add(ThaumcraftGates.aspectTrigger08);
				list.add(ThaumcraftGates.aspectTrigger16);
				list.add(ThaumcraftGates.aspectTrigger32);
				list.add(ThaumcraftGates.aspectTrigger64);
			}
		}

		return list;
	}

	@Override
	public LinkedList<ITrigger> getNeighborTriggers(Block block, TileEntity tile) {
		return null;
	}
}