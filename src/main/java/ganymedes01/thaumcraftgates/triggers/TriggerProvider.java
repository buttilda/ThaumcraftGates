package ganymedes01.thaumcraftgates.triggers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.ITriggerExternal;
import buildcraft.api.statements.ITriggerInternal;
import buildcraft.api.statements.ITriggerProvider;
import buildcraft.api.statements.StatementManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.common.tiles.TileWandPedestal;

public class TriggerProvider implements ITriggerProvider {

	public static ITriggerExternal aspectTrigger64 = new AspectAmountTrigger(64);
	public static ITriggerExternal aspectTrigger32 = new AspectAmountTrigger(32);
	public static ITriggerExternal aspectTrigger16 = new AspectAmountTrigger(16);
	public static ITriggerExternal aspectTrigger08 = new AspectAmountTrigger(8);
	public static ITriggerExternal aspectTrigger00 = new AspectAmountTrigger(0);
	public static ITriggerExternal aspectTriggerMinus8 = new AspectAmountTrigger(-8);

	public static ITriggerExternal fullWandTrigger = new FullWandTrigger();
	public static ITriggerExternal emptyWandTrigger = new EmptyWandTrigger();

	public TriggerProvider() {
		StatementManager.registerStatement(aspectTrigger64);
		StatementManager.registerStatement(aspectTrigger32);
		StatementManager.registerStatement(aspectTrigger16);
		StatementManager.registerStatement(aspectTrigger08);
		StatementManager.registerStatement(aspectTrigger00);
		StatementManager.registerStatement(aspectTriggerMinus8);

		StatementManager.registerStatement(fullWandTrigger);
		StatementManager.registerStatement(emptyWandTrigger);

		StatementManager.registerTriggerProvider(this);
	}

	@Override
	public Collection<ITriggerInternal> getInternalTriggers(IStatementContainer container) {
		return null;
	}

	@Override
	public Collection<ITriggerExternal> getExternalTriggers(ForgeDirection side, TileEntity tile) {
		List<ITriggerExternal> list = new ArrayList<ITriggerExternal>();

		if (tile instanceof TileWandPedestal) {
			list.add(fullWandTrigger);
			list.add(emptyWandTrigger);
		} else if (tile instanceof IAspectContainer) {
			list.add(aspectTriggerMinus8);
			list.add(aspectTrigger00);
			list.add(aspectTrigger08);
			list.add(aspectTrigger16);
			list.add(aspectTrigger32);
			list.add(aspectTrigger64);
		}

		return list;
	}
}