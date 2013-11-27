package ganymedes01.thaumcraftgates.triggers;

import ganymedes01.thaumcraftgates.lib.Reference;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraftforge.common.ForgeDirection;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectSource;
import buildcraft.api.gates.ITrigger;
import buildcraft.api.gates.ITriggerParameter;

/**
 * Thaumcraft Gates
 * 
 * @author ganymedes01
 * 
 */

public class AspectAmountParamTrigger implements ITrigger {

	private Icon icon;
	private final String uniqueTag, description;
	private final int amount;

	public AspectAmountParamTrigger(int amount) {
		uniqueTag = Reference.MOD_ID + ":" + "aspectTrigger" + amount;
		description = "Aspect > " + amount;
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
		icon = reg.registerIcon(Reference.MOD_ID + ":triggers/aspectTrigger" + amount);
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean isTriggerActive(ForgeDirection side, TileEntity tile, ITriggerParameter parameter) {
		if (tile instanceof IAspectSource) {
			IAspectSource source = (IAspectSource) tile;
			AspectList aspects = source.getAspects();
			if (aspects != null && aspects.size() == 1)
				return aspects.getAmount(aspects.getAspects()[0]) >= amount;
		}
		return false;
	}

	@Override
	public boolean hasParameter() {
		return true;
	}

	@Override
	public boolean requiresParameter() {
		return true;
	}

	@Override
	public ITriggerParameter createParameter() {
		return null;
	}

	@Override
	public int getLegacyId() {
		return 0;
	}
}