package ganymedes01.thaumcraftgates.pipes;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import buildcraft.transport.ItemPipe;

/**
 * Thaumcraft Gates
 * 
 * @author ganymedes01
 * 
 */

public class ItemThaumiumPipe extends ItemPipe {

	public ItemThaumiumPipe(int id) {
		super(id);
	}

	@Override
	public String getItemDisplayName(ItemStack stack) {
		return ("" + StatCollector.translateToLocal(getUnlocalizedNameInefficiently(stack) + ".name")).trim();
	}
}