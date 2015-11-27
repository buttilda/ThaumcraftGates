package ganymedes01.thaumcraftgates.pipes;

import buildcraft.transport.PipeTransportStructure;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.IAspectContainer;

public class AspectPipe extends PipeTransportStructure {

	@Override
	public boolean canPipeConnect(TileEntity tile, ForgeDirection side) {
		return tile instanceof IAspectContainer;
	}
}