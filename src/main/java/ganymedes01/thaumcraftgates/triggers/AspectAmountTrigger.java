package ganymedes01.thaumcraftgates.triggers;

import ganymedes01.thaumcraftgates.lib.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.IStatementParameter;
import buildcraft.api.statements.StatementParameterItemStack;

/**
 * Thaumcraft Gates
 *
 * @author ganymedes01
 *
 */

public class AspectAmountTrigger extends StatementBase {

	private final int amount;

	public AspectAmountTrigger(int amount) {
		super(Reference.MOD_ID + ":" + "aspectTrigger" + amount, "Aspect " + (amount > 0 ? ">= " : amount < 0 ? "<= " : "= ") + Math.abs(amount));
		this.amount = amount;
	}

	@Override
	public void registerIcons(IIconRegister reg) {
		icon = reg.registerIcon(Reference.MOD_ID + ":triggers/aspectTrigger" + Math.abs(amount));
	}

	@Override
	public boolean isTriggerActive(TileEntity target, ForgeDirection side, IStatementContainer source, IStatementParameter[] parameters) {
		if (target instanceof IAspectContainer) {
			AspectList aspects = ((IAspectContainer) target).getAspects();
			if (aspects != null) {
				Aspect aspect = aspects.getAspectsSortedAmount()[0];

				// If trigger has a parameter
				if (parameters != null && parameters.length > 0) {
					IStatementParameter par = parameters[0];
					if (par instanceof StatementParameterItemStack) {
						StatementParameterItemStack parameter = (StatementParameterItemStack) par;
						if (parameter != null && parameter.getItemStack() != null) {
							ItemStack stack = parameter.getItemStack();
							if (stack.getItem() instanceof IEssentiaContainerItem) {
								AspectList itemAspects = ((IEssentiaContainerItem) stack.getItem()).getAspects(stack);
								if (itemAspects != null)
									aspect = itemAspects.getAspectsSortedAmount()[0];
							}
						}
					}
				}

				return testAspect(aspects, aspect, amount);
			}
		}
		return false;
	}

	private boolean testAspect(AspectList aspects, Aspect aspect, int amount) {
		return amount > 0 ? aspects.getAmount(aspect) >= amount : aspects.getAmount(aspect) <= Math.abs(amount);
	}

	@Override
	public int maxParameters() {
		return 1;
	}

	@Override
	public IStatementParameter createParameter(int index) {
		return new StatementParameterItemStack();
	}

}