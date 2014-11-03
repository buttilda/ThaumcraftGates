package ganymedes01.thaumcraftgates.research;

import ganymedes01.thaumcraftgates.ThaumcraftGates;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigResearch;

public class ResearchPipe extends ResearchItem {

	public ResearchPipe() {
		super("THAUMIUM_PIPE", "ALCHEMY", new AspectList().add(Aspect.METAL, 2).add(Aspect.MAGIC, 1).add(Aspect.MECHANISM, 1), 4, 4, 3, new ItemStack(ThaumcraftGates.thaumiumPipe));
		setPages(new ResearchPage[] { new ResearchPage("tc.research_page.THAUMIUM_PIPE"), new ResearchPage((ShapedArcaneRecipe) ConfigResearch.recipes.get("THAUMIUM_PIPE")) });
		setParents(new String[] { "TUBES", "THAUMIUM" });
		registerResearchItem();
		setHidden();
	}
}