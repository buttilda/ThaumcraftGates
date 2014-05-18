package ganymedes01.thaumcraftgates.triggers;

import ganymedes01.thaumcraftgates.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileWandPedestal;
import buildcraft.api.gates.ITileTrigger;
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

public class EmptyWandTrigger implements ITileTrigger {

	@SideOnly(Side.CLIENT)
	private IIcon icon;

	@Override
	public String getUniqueTag() {
		return Reference.MOD_ID + ":emptyWandTrigger";
	}

	@Override
	public IIcon getIcon() {
		return icon;
	}

	@Override
	public void registerIcons(IIconRegister reg) {
		icon = reg.registerIcon(Reference.MOD_ID + ":triggers/emptyWand");
	}

	@Override
	public String getDescription() {
		return "Wand is Empty";
	}

	@Override
	public boolean isTriggerActive(ForgeDirection side, TileEntity tile, ITriggerParameter parameter) {
		if (tile != null && tile instanceof TileWandPedestal) {
			TileWandPedestal pedestal = (TileWandPedestal) tile;
			ItemStack stack = pedestal.func_70301_a(0);
			if (stack != null && stack.getItem() instanceof ItemWandCasting) {
				ItemWandCasting wand = (ItemWandCasting) stack.getItem();
				AspectList aspects = wand.getAllVis(stack);
				if (aspects != null) {
					for (Aspect aspect : aspects.getAspects())
						if (wand.getVis(stack, aspect) <= 0)
							continue;
						else
							return false;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasParameter() {
		return false;
	}

	@Override
	public boolean requiresParameter() {
		return false;
	}

	@Override
	public ITriggerParameter createParameter() {
		return null;
	}

	@Override
	public ITrigger rotateLeft() {
		return this;
	}
}