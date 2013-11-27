package ganymedes01.thaumcraftgates.triggers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import buildcraft.api.gates.TriggerParameter;

/**
 * Thaumcraft Gates
 * 
 * @author ganymedes01
 * 
 */

public class AspectTriggerParameter extends TriggerParameter {

	@Override
	public void writeToNBT(NBTTagCompound data) {
		NBTTagCompound tagCompound = new NBTTagCompound();
		stack.writeToNBT(tagCompound);
		data.setCompoundTag("stack", tagCompound);
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		stack = ItemStack.loadItemStackFromNBT(data.getCompoundTag("stack"));
	}
}