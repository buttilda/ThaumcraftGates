package ganymedes01.thaumcraftgates.triggers;

import ganymedes01.thaumcraftgates.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileWandPedestal;
import buildcraft.api.gates.IGate;
import buildcraft.api.gates.IStatementParameter;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.gates.ITriggerParameter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Thaumcraft Gates
 *
 * @author ganymedes01
 *
 */

public class FullWandTrigger implements ITrigger {

	@SideOnly(Side.CLIENT)
	private IIcon icon;

	@Override
	public String getUniqueTag() {
		return Reference.MOD_ID + ":fullWandTrigger";
	}

	@Override
	public IIcon getIcon() {
		return icon;
	}

	@Override
	public void registerIcons(IIconRegister reg) {
		icon = reg.registerIcon(Reference.MOD_ID + ":triggers/fullWand");
	}

	@Override
	public String getDescription() {
		return "Wand is Fully Charged";
	}

	@Override
	public boolean isTriggerActive(IGate gate, ITriggerParameter[] parameters) {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity tile = gate.getPipe().getAdjacentTile(dir);
			if (tile != null && tile instanceof TileWandPedestal) {
				TileWandPedestal pedestal = (TileWandPedestal) tile;
				ItemStack stack = pedestal.func_70301_a(0);
				if (stack != null && stack.getItem() instanceof ItemWandCasting) {
					ItemWandCasting wand = (ItemWandCasting) stack.getItem();
					AspectList aspects = wand.getAspectsWithRoom(stack);
					if (aspects != null)
						return aspects.size() == 0;
				}
			}
		}
		return false;
	}

	@Override
	public ITrigger rotateLeft() {
		return this;
	}

	@Override
	public int maxParameters() {
		return 0;
	}

	@Override
	public int minParameters() {
		return 0;
	}

	@Override
	public IStatementParameter createParameter(int index) {
		return null;
	}
}