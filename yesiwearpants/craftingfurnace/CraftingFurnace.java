package yesiwearpants.craftingfurnace;

import net.minecraftforge.common.Configuration;
import yesiwearpants.compressionmod.common.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

	@Mod(modid = Refer.MOD_ID, name = Refer.MOD_NAME, version = Refer.MOD_VERSION)
	@NetworkMod(serverSideRequired = false, clientSideRequired = true)
	public class CraftingFurnace {
	
	public static CraftingFurnace instance;
	
	@SidedProxy(clientSide=Refer.ClientProxyClass, 
			serverSide=Refer.ServerProxyClass)
		public static CommonProxy proxy;
		
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		config.save();
		Configure.preInit();

	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		Configure.init();
		proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		Configure.postInit();
	}
}
