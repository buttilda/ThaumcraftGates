package ganymedes01.thaumcraftgates.triggers;

import ganymedes01.thaumcraftgates.lib.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraftforge.common.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.gates.ITriggerParameter;

/**
 * Thaumcraft Gates
 * 
 * @author ganymedes01
 * 
 */

public class AspectAmountTrigger implements ITrigger {

	private Icon icon;
	private final String uniqueTag, description;
	private final int amount;

	public AspectAmountTrigger(int amount) {
		uniqueTag = Reference.MOD_ID + ":" + "aspectTrigger" + amount;
		description = "Aspect " + (amount > 0 ? ">= " : "< ") + Math.abs(amount);
		this.amount = amount;
	}

	@Override
	public String getUniqueTag() {
		return uniqueTag;
	}

	@Override
	public Icon getIcon() {
		return icon;
	}

	@Override
	public void registerIcons(IconRegister reg) {
		icon = reg.registerIcon(Reference.MOD_ID + ":triggers/aspectTrigger" + Math.abs(amount));
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean isTriggerActive(ForgeDirection side, TileEntity tile, ITriggerParameter parameter) {
		if (tile instanceof IAspectContainer) {
			IAspectContainer source = (IAspectContainer) tile;
			AspectList aspects = source.getAspects();
			if (aspects != null) {
				Aspect aspect = aspects.getAspectsSortedAmount()[0];
				return testAspect(aspects, aspect, amount);
			}
		}
		return false;
	}

	private boolean testAspect(AspectList aspects, Aspect aspect, int amount) {
		return amount > 0 ? aspects.getAmount(aspect) >= amount : aspects.getAmount(aspect) < Math.abs(amount);
	}

	@Override
	public boolean hasParameter() {
		return false;
		// TODO return true;
	}

	@Override
	public boolean requiresParameter() {
		return false;
	}

	@Override
	public ITriggerParameter createParameter() {
		return new AspectTriggerParameter();
	}

	@Override
	public int getLegacyId() {
		return 0;
	}

	// TODO Add after BuildCraft fix problem with NBT and Gates
	// if (parameter != null) {
	// ItemStack paramStack = parameter.getItemStack();
	// if (paramStack != null) {
	// Item paramItem = paramStack.getItem();
	// if (paramItem instanceof IEssentiaContainerItem) {
	// aspects = ((IEssentiaContainerItem)
	// paramItem).getAspects(paramStack);
	// if (aspects != null) {
	// for (int i = 0; i < aspects.size(); i++)
	// Logger.getLogger(Reference.MOD_ID).log(Level.WARNING,
	// aspects.getAspects()[0].toString());
	// if (aspects != null)
	// aspect = aspects.getAspectsSortedAmount()[0];
	// }
	// }
	// }
	// }
	//
	// if (aspects != null)
}