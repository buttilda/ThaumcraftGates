package ganymedes01.thaumcraftgates.triggers;

import buildcraft.api.statements.IStatement;
import buildcraft.api.statements.IStatementParameter;
import buildcraft.api.statements.ITriggerExternal;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public abstract class StatementBase implements ITriggerExternal {

	protected IIcon icon;
	protected final String id, description;

	protected StatementBase(String id, String description) {
		this.id = id;
		this.description = description;
	}

	@Override
	public String getUniqueTag() {
		return id;
	}

	@Override
	public String getDescription() {
		return StatCollector.translateToLocal(description);
	}

	@Override
	public IIcon getIcon() {
		return icon;
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

	@Override
	public IStatement rotateLeft() {
		return this;
	}
}