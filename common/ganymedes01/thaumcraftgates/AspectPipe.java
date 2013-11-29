package ganymedes01.thaumcraftgates;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
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
		return tile != null && tile instanceof IAspectContainer;
	}
}