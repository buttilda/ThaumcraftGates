package ganymedes01.thaumcraftgates.triggers;

import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.IStatementParameter;
import ganymedes01.thaumcraftgates.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileWandPedestal;

public class EmptyWandTrigger extends StatementBase {

	public EmptyWandTrigger() {
		super(Reference.MOD_ID + ":emptyWandTrigger", "trigger.thaumcraftgates.wandEmpty");
	}

	@Override
	public void registerIcons(IIconRegister reg) {
		icon = reg.registerIcon(Reference.MOD_ID + ":triggers/emptyWand");
	}

	@Override
	public boolean isTriggerActive(TileEntity target, ForgeDirection side, IStatementContainer source, IStatementParameter[] parameters) {
		if (target instanceof TileWandPedestal) {
			TileWandPedestal pedestal = (TileWandPedestal) target;
			ItemStack stack = pedestal.func_70301_a(0);
			if (stack != null && stack.getItem() instanceof ItemWandCasting) {
				ItemWandCasting wand = (ItemWandCasting) stack.getItem();
				AspectList aspects = wand.getAllVis(stack);
				if (aspects != null) {
					for (Aspect aspect : aspects.getAspects())
						if (wand.getVis(stack, aspect) > 0)
							return false;
					return true;
				}
			}
		}
		return false;
	}
}