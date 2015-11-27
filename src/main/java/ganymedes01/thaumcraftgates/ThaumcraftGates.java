package ganymedes01.thaumcraftgates;

import buildcraft.api.statements.StatementManager;
import buildcraft.core.BCCreativeTab;
import buildcraft.transport.BlockGenericPipe;
import buildcraft.transport.TransportProxyClient;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.thaumcraftgates.lib.Reference;
import ganymedes01.thaumcraftgates.pipes.ThaumiumPipe;
import ganymedes01.thaumcraftgates.research.ResearchPipe;
import ganymedes01.thaumcraftgates.triggers.TriggerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigResearch;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION_NUMBER, dependencies = Reference.DEPENDENCIES)
public class ThaumcraftGates {

	@Instance(Reference.MOD_ID)
	public static ThaumcraftGates instance;

	public static Item thaumiumPipe;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);

		// Create and register Pipe
		thaumiumPipe = BlockGenericPipe.registerPipe(ThaumiumPipe.class, BCCreativeTab.get("pipes"));
		thaumiumPipe.setUnlocalizedName(Reference.MOD_ID + ".thaumiumPipe");

		// Register pipe renderer
		if (event.getSide() == Side.CLIENT)
			MinecraftForgeClient.registerItemRenderer(thaumiumPipe, TransportProxyClient.pipeItemRenderer);

		// Register BuildCraft triggers
		StatementManager.registerTriggerProvider(new TriggerProvider());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// Create AspectList used to craft a Thaumium Pipe
		AspectList list = new AspectList().add(Aspect.ORDER, 2).add(Aspect.EARTH, 2).add(Aspect.FIRE, 1).add(Aspect.AIR, 1).add(Aspect.ENTROPY, 1);

		// Register Thaumium Pipe recipe
		ConfigResearch.recipes.put("THAUMIUM_PIPE", ThaumcraftApi.addArcaneCraftingRecipe("THAUMIUM_PIPE", new ItemStack(thaumiumPipe, 8), list, "xyx", 'x', "ingotThaumium", 'y', "blockGlassColorless"));

		// Register Thaumium Pipe research
		new ResearchPipe();
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void loadTextures(TextureStitchEvent.Pre evt) {
		if (evt.map.getTextureType() == 0)
			ThaumiumPipe.registerIcons(evt.map);
	}
}