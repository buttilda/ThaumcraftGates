package ganymedes01.thaumcraftgates.pipes;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.IAspectContainer;
import buildcraft.transport.PipeTransportStructure;

public class AspectPipe extends PipeTransportStructure {

	@Override
	public boolean canPipeConnect(TileEntity tile, ForgeDirection side) {
		return tile instanceof IAspectContainer;
	}
}