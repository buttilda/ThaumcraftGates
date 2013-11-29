package ganymedes01.thaumcraftgates;

import ganymedes01.thaumcraftgates.lib.Reference;
import ganymedes01.thaumcraftgates.pipes.ThaumiumPipe;
import ganymedes01.thaumcraftgates.research.ResearchPipe;
import ganymedes01.thaumcraftgates.triggers.AspectAmountTrigger;
import ganymedes01.thaumcraftgates.triggers.TriggerProvider;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigResearch;
import buildcraft.api.gates.ActionManager;
import buildcraft.api.gates.ITrigger;
import buildcraft.core.utils.Localization;
import buildcraft.transport.BlockGenericPipe;
import buildcraft.transport.TransportProxyClient;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Thaumcraft Gates
 * 
 * @author ganymedes01
 * 
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION_NUMBER, dependencies = Reference.DEPENDENCIES)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = true)
public class ThaumcraftGates {

	@Instance(Reference.MOD_ID)
	public static ThaumcraftGates instance;

	public static ITrigger aspectTrigger64 = new AspectAmountTrigger(64);
	public static ITrigger aspectTrigger32 = new AspectAmountTrigger(32);
	public static ITrigger aspectTrigger16 = new AspectAmountTrigger(16);
	public static ITrigger aspectTrigger08 = new AspectAmountTrigger(8);
	public static ITrigger aspectTriggerMinus8 = new AspectAmountTrigger(-8);

	public static Item thaumiumPipe;
	private static int thaumiumPipeID;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);

		// Load ID config file
		Configuration config = new Configuration(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MOD_ID + ".cfg"));
		try {
			config.load();

			thaumiumPipeID = config.getItem("Thaumium Pipe ID", 2457).getInt(2457);

		} catch (Exception e) {
			FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
			throw new RuntimeException(e);
		} finally {
			config.save();
		}

		// Load BuildCraft localizations
		Localization.addLocalization("/assets/thaumcraftgates/lang/", "en_US");

		// Create and register Pipe
		thaumiumPipe = BlockGenericPipe.registerPipe(thaumiumPipeID, ThaumiumPipe.class);
		thaumiumPipe.setCreativeTab(Thaumcraft.tabTC);

		// Register pipe renderer
		if (event.getSide() == Side.CLIENT)
			MinecraftForgeClient.registerItemRenderer(thaumiumPipe.itemID, TransportProxyClient.pipeItemRenderer);
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		// Register BuildCraft triggers
		ActionManager.registerTrigger(aspectTriggerMinus8);
		ActionManager.registerTrigger(aspectTrigger08);
		ActionManager.registerTrigger(aspectTrigger16);
		ActionManager.registerTrigger(aspectTrigger32);
		ActionManager.registerTrigger(aspectTrigger64);
		ActionManager.registerTriggerProvider(new TriggerProvider());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// Create AspectList used to craft a Thaumium Pipe
		AspectList list = new AspectList().add(Aspect.ORDER, 2).add(Aspect.EARTH, 2).add(Aspect.FIRE, 1).add(Aspect.AIR, 1).add(Aspect.ENTROPY, 1);

		// Register Thaumium Pipe recipe
		ConfigResearch.recipes.put("THAUMIUM_PIPE", ThaumcraftApi.addArcaneCraftingRecipe("THAUMIUM_PIPE", new ItemStack(thaumiumPipe, 8), list, "xyx", 'x', ItemApi.getItem("itemResource", 2), 'y', Block.glass));

		// Register Thaumium Pipe research
		new ResearchPipe();
	}

	@ForgeSubscribe
	@SideOnly(Side.CLIENT)
	public void loadTextures(TextureStitchEvent.Pre evt) {
		// Register mod icons
		if (evt.map.textureType == 1) {
			aspectTrigger64.registerIcons(evt.map);
			aspectTrigger32.registerIcons(evt.map);
			aspectTrigger16.registerIcons(evt.map);
			aspectTrigger08.registerIcons(evt.map);
			aspectTriggerMinus8.registerIcons(evt.map);
		} else if (evt.map.textureType == 0)
			ThaumiumPipe.registerIcons(evt.map);
	}
}