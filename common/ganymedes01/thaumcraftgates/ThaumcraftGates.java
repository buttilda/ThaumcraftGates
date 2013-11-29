package ganymedes01.thaumcraftgates;

import ganymedes01.thaumcraftgates.lib.Reference;
import ganymedes01.thaumcraftgates.triggers.AspectAmountTrigger;
import ganymedes01.thaumcraftgates.triggers.TriggerProvider;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import buildcraft.api.gates.ActionManager;
import buildcraft.api.gates.ITrigger;
import buildcraft.transport.BlockGenericPipe;
import buildcraft.transport.TransportProxyClient;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
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

	public static ITrigger aspectTrigger64 = new AspectAmountTrigger(64);
	public static ITrigger aspectTrigger32 = new AspectAmountTrigger(32);
	public static ITrigger aspectTrigger16 = new AspectAmountTrigger(16);
	public static ITrigger aspectTrigger08 = new AspectAmountTrigger(8);
	public static ITrigger aspectTriggerMinus8 = new AspectAmountTrigger(-8);

	public static Item thaumiumPipe;

	@Instance(Reference.MOD_ID)
	public static ThaumcraftGates instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);

		thaumiumPipe = BlockGenericPipe.registerPipe(2457, ThaumiumPipe.class);
		thaumiumPipe.setUnlocalizedName("thaumiumPipe");

		if (event.getSide() == Side.CLIENT)
			MinecraftForgeClient.registerItemRenderer(thaumiumPipe.itemID, TransportProxyClient.pipeItemRenderer);
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		ActionManager.registerTrigger(aspectTriggerMinus8);
		ActionManager.registerTrigger(aspectTrigger08);
		ActionManager.registerTrigger(aspectTrigger16);
		ActionManager.registerTrigger(aspectTrigger32);
		ActionManager.registerTrigger(aspectTrigger64);
		ActionManager.registerTriggerProvider(new TriggerProvider());
	}

	@ForgeSubscribe
	@SideOnly(Side.CLIENT)
	public void loadTextures(TextureStitchEvent.Pre evt) {
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