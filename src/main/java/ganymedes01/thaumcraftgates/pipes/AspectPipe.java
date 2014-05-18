package ganymedes01.thaumcraftgates.pipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import thaumcraft.api.aspects.IAspectContainer;
import buildcraft.transport.PipeTransportStructure;

/**
 * Thaumcraft Gates
 * 
 * @author ganymedes01
 * 
 */

public class AspectPipe extends PipeTransportStructure {

	@Override
	public boolean canPipeConnect(TileEntity tile, ForgeDirection side) {
		return tile != null && tile instanceof IAspectContainer && !(tile instanceof IInventory) && !(tile instanceof IFluidHandler);
	}
}