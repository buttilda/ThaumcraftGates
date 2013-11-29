package ganymedes01.thaumcraftgates.triggers;

import ganymedes01.thaumcraftgates.ThaumcraftGates;

import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.aspects.IAspectContainer;
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
		return null;
	}

	@Override
	public LinkedList<ITrigger> getNeighborTriggers(Block block, TileEntity tile) {
		LinkedList<ITrigger> list = new LinkedList<ITrigger>();

		if (tile instanceof IAspectContainer) {
			list.add(ThaumcraftGates.aspectTriggerMinus8);
			list.add(ThaumcraftGates.aspectTrigger08);
			list.add(ThaumcraftGates.aspectTrigger16);
			list.add(ThaumcraftGates.aspectTrigger32);
			list.add(ThaumcraftGates.aspectTrigger64);
		}

		return list;
	}
}