package ganymedes01.thaumcraftgates;

import ganymedes01.thaumcraftgates.lib.Reference;
import ganymedes01.thaumcraftgates.pipes.ThaumiumPipe;
import ganymedes01.thaumcraftgates.research.ResearchPipe;
import ganymedes01.thaumcraftgates.triggers.AspectAmountTrigger;
import ganymedes01.thaumcraftgates.triggers.EmptyWandTrigger;
import ganymedes01.thaumcraftgates.triggers.FullWandTrigger;
import ganymedes01.thaumcraftgates.triggers.TriggerProvider;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigResearch;
import buildcraft.BuildCraftTransport;
import buildcraft.api.gates.ActionManager;
import buildcraft.api.gates.ITrigger;
import buildcraft.core.CreativeTabBuildCraft;
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

/**
 * Thaumcraft Gates
 * 
 * @author ganymedes01
 * 
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION_NUMBER, dependencies = Reference.DEPENDENCIES)
public class ThaumcraftGates {

	@Instance(Reference.MOD_ID)
	public static ThaumcraftGates instance;

	public static ITrigger aspectTrigger64 = new AspectAmountTrigger(64);
	public static ITrigger aspectTrigger32 = new AspectAmountTrigger(32);
	public static ITrigger aspectTrigger16 = new AspectAmountTrigger(16);
	public static ITrigger aspectTrigger08 = new AspectAmountTrigger(8);
	public static ITrigger aspectTrigger00 = new AspectAmountTrigger(0);
	public static ITrigger aspectTriggerMinus8 = new AspectAmountTrigger(-8);
	public static ITrigger fullWandTrigger = new FullWandTrigger();
	public static ITrigger emptyWandTrigger = new EmptyWandTrigger();

	public static Item thaumiumPipe;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);

		// Create and register Pipe
		thaumiumPipe = BuildCraftTransport.buildPipe(ThaumiumPipe.class, "", CreativeTabBuildCraft.PIPES);
		thaumiumPipe.setUnlocalizedName(Reference.MOD_ID + ".thaumiumPipe");
		thaumiumPipe.setCreativeTab(Thaumcraft.tabTC);

		// Register pipe renderer
		if (event.getSide() == Side.CLIENT)
			MinecraftForgeClient.registerItemRenderer(thaumiumPipe, TransportProxyClient.pipeItemRenderer);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// Register BuildCraft triggers
		ActionManager.registerTrigger(aspectTriggerMinus8);
		ActionManager.registerTrigger(aspectTrigger00);
		ActionManager.registerTrigger(aspectTrigger08);
		ActionManager.registerTrigger(aspectTrigger16);
		ActionManager.registerTrigger(aspectTrigger32);
		ActionManager.registerTrigger(aspectTrigger64);
		ActionManager.registerTrigger(fullWandTrigger);
		ActionManager.registerTrigger(emptyWandTrigger);

		ActionManager.registerTriggerProvider(new TriggerProvider());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// Create AspectList used to craft a Thaumium Pipe
		AspectList list = new AspectList().add(Aspect.ORDER, 2).add(Aspect.EARTH, 2).add(Aspect.FIRE, 1).add(Aspect.AIR, 1).add(Aspect.ENTROPY, 1);

		// Register Thaumium Pipe recipe
		ConfigResearch.recipes.put("THAUMIUM_PIPE", ThaumcraftApi.addArcaneCraftingRecipe("THAUMIUM_PIPE", new ItemStack(thaumiumPipe, 8), list, "xyx", 'x', ItemApi.getItem("itemResource", 2), 'y', Blocks.glass));

		// Register Thaumium Pipe research
		new ResearchPipe();
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void loadTextures(TextureStitchEvent.Pre evt) {
		// Register mod icons
		if (evt.map.getTextureType() == 1) {
			aspectTrigger64.registerIcons(evt.map);
			aspectTrigger32.registerIcons(evt.map);
			aspectTrigger16.registerIcons(evt.map);
			aspectTrigger08.registerIcons(evt.map);
			aspectTriggerMinus8.registerIcons(evt.map);
			fullWandTrigger.registerIcons(evt.map);
			emptyWandTrigger.registerIcons(evt.map);
		} else if (evt.map.getTextureType() == 0)
			ThaumiumPipe.registerIcons(evt.map);
	}
}