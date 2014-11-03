package ganymedes01.thaumcraftgates.pipes;

import ganymedes01.thaumcraftgates.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import buildcraft.api.core.IIconProvider;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeIconProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SuppressWarnings("rawtypes")
public class ThaumiumPipe extends Pipe {

	@SideOnly(Side.CLIENT)
	private static IIcon icon;

	@SuppressWarnings("unchecked")
	public ThaumiumPipe(Item item) {
		super(new AspectPipe(), item);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIconProvider getIconProvider() {
		return new PipeIconProvider() {

			@Override
			@SideOnly(Side.CLIENT)
			public IIcon getIcon(int pipeIconIndex) {
				return ThaumiumPipe.icon;
			}
		};
	}

	@Override
	public int getIconIndex(ForgeDirection direction) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public static void registerIcons(IIconRegister reg) {
		icon = reg.registerIcon(Reference.MOD_ID + ":" + "thaumiumPipe");
	}
}