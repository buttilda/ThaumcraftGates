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
import buildcraft.api.gates.IGate;
import buildcraft.api.gates.IStatementParameter;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.gates.ITriggerParameter;
import buildcraft.api.gates.StatementManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Thaumcraft Gates
 *
 * @author ganymedes01
 *
 */

public class EmptyWandTrigger implements ITrigger {

	@SideOnly(Side.CLIENT)
	private IIcon icon;
	private final String uniqueTag = Reference.MOD_ID + ":emptyWandTrigger";

	public EmptyWandTrigger() {
		StatementManager.statements.put(uniqueTag, this);
	}

	@Override
	public String getUniqueTag() {
		return uniqueTag;
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
	public boolean isTriggerActive(IGate gate, ITriggerParameter[] parameters) {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity tile = gate.getPipe().getAdjacentTile(dir);
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