package ru.bestfoxy.econom.common;

import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;
import ru.bestfoxy.econom.ModInfo;
import ru.bestfoxy.econom.server.network.SyncConfigMessage;
import ru.bestfoxy.econom.utils.FakeStack;

public class CommonProxy {
	
	public static SimpleNetworkWrapper network;
	public static HashSet<FakeStack> ITEMS = new HashSet();
	
	public void preInit(FMLPreInitializationEvent event) {
	}
	
	public void Init(FMLInitializationEvent event) {
		network = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MODID);
		network.registerMessage(SyncConfigMessage.class, SyncConfigMessage.class, 0, Side.CLIENT);
	}

	public void postInit(FMLPostInitializationEvent event) {
	}
}