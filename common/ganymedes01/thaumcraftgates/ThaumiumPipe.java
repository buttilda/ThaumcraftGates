package ganymedes01.thaumcraftgates;

import ganymedes01.thaumcraftgates.lib.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.core.IIconProvider;
import buildcraft.transport.Pipe;
import buildcraft.transport.PipeIconProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Thaumcraft Gates
 * 
 * @author ganymedes01
 * 
 */

public class ThaumiumPipe extends Pipe {

	@SideOnly(Side.CLIENT)
	private static Icon icon;

	public ThaumiumPipe(int itemID) {
		super(new AspectPipe(), itemID);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIconProvider getIconProvider() {
		return new PipeIconProvider() {

			@Override
			@SideOnly(Side.CLIENT)
			public Icon getIcon(int pipeIconIndex) {
				return ThaumiumPipe.icon;
			}
		};
	}

	@Override
	public int getIconIndex(ForgeDirection direction) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public static void registerIcons(IconRegister reg) {
		icon = reg.registerIcon(Reference.MOD_ID + ":" + "thaumiumPipe");
	}
}