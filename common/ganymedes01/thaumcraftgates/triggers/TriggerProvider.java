package ganymedes01.thaumcraftgates.triggers;

import ganymedes01.thaumcraftgates.ThaumcraftGates;

import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.IFluidHandler;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.common.tiles.TileWandPedestal;
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
	public LinkedList<ITrigger> getNeighborTriggers(Block block, TileEntity tile) {
		LinkedList<ITrigger> list = new LinkedList<ITrigger>();

		if (tile != null && tile instanceof IAspectContainer && !(tile instanceof IInventory) && !(tile instanceof IFluidHandler)) {
			list.add(ThaumcraftGates.aspectTriggerMinus8);
			list.add(ThaumcraftGates.aspectTrigger00);
			list.add(ThaumcraftGates.aspectTrigger08);
			list.add(ThaumcraftGates.aspectTrigger16);
			list.add(ThaumcraftGates.aspectTrigger32);
			list.add(ThaumcraftGates.aspectTrigger64);
		}

		if (tile instanceof TileWandPedestal) {
			list.add(ThaumcraftGates.fullWandTrigger);
			list.add(ThaumcraftGates.emptyWandTrigger);
		}

		return list;
	}

	@Override
	public LinkedList<ITrigger> getPipeTriggers(IPipe pipe) {
		return null;
	}
}