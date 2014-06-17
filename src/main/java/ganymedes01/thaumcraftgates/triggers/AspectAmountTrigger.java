package ganymedes01.thaumcraftgates.triggers;

import ganymedes01.thaumcraftgates.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import buildcraft.api.gates.IGate;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.gates.ITriggerParameter;
import buildcraft.api.gates.TriggerParameterItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Thaumcraft Gates
 *
 * @author ganymedes01
 *
 */

public class AspectAmountTrigger implements ITrigger {

	@SideOnly(Side.CLIENT)
	private IIcon icon;

	private final String uniqueTag, description;
	private final int amount;

	public AspectAmountTrigger(int amount) {
		uniqueTag = Reference.MOD_ID + ":" + "aspectTrigger" + amount;
		description = "Aspect " + (amount > 0 ? ">= " : amount < 0 ? "<= " : "= ") + Math.abs(amount);
		this.amount = amount;
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
		icon = reg.registerIcon(Reference.MOD_ID + ":triggers/aspectTrigger" + Math.abs(amount));
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean isTriggerActive(IGate gate, ITriggerParameter[] parameters) {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity tile = gate.getPipe().getAdjacentTile(dir);
			if (tile != null && tile instanceof IAspectContainer) {
				AspectList aspects = ((IAspectContainer) tile).getAspects();
				if (aspects != null) {
					Aspect aspect = aspects.getAspectsSortedAmount()[0];

					// If trigger has a parameter
					if (parameters != null && parameters.length > 0) {
						ITriggerParameter par = parameters[0];
						if (par instanceof TriggerParameterItemStack) {
							TriggerParameterItemStack parameter = (TriggerParameterItemStack) par;
							if (parameter != null && parameter.getItemStackToDraw() != null) {
								Item paramItem = parameter.getItemStackToDraw().getItem();
								if (paramItem != null && paramItem instanceof IEssentiaContainerItem) {
									AspectList itemAspects = ((IEssentiaContainerItem) paramItem).getAspects(parameter.getItemStackToDraw());
									if (itemAspects != null)
										aspect = itemAspects.getAspectsSortedAmount()[0];
								}
							}
						}
					}

					return testAspect(aspects, aspect, amount);
				}
			}
		}
		return false;
	}

	private boolean testAspect(AspectList aspects, Aspect aspect, int amount) {
		return amount > 0 ? aspects.getAmount(aspect) >= amount : aspects.getAmount(aspect) <= Math.abs(amount);
	}

	@Override
	public ITrigger rotateLeft() {
		return this;
	}

	@Override
	public int maxParameters() {
		return 1;
	}

	@Override
	public int minParameters() {
		return 0;
	}

	@Override
	public ITriggerParameter createParameter(int index) {
		return new TriggerParameterItemStack();
	}
}