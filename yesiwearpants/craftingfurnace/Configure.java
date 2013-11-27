package yesiwearpants.craftingfurnace;

import yesiwearpants.craftingfurnace.gui.GuiHandler;
import yesiwearpants.craftingfurnace.tile.CraftingFurnaceBlock;
import yesiwearpants.craftingfurnace.tile.TileEntityCraftingFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Configure {

	public static void preInit() {
		// TODO Auto-generated method stub
		
	}

	public static void init() {
		GameRegistry.registerTileEntity(TileEntityCraftingFurnace.class, "entityCraftingFurnace");
		NetworkRegistry.instance().registerGuiHandler(Refer.MOD_ID, new GuiHandler());
	}
	
	public static void postInit() {
		
	}
	
	public static Block blockIdle;			//Idle State
	public static Block craftingFurnace;	//Active State
	public static Block alloyFurnace;		//Active State
	
	public static final StepSound soundMetalFootstep = new StepSound("stone", 1.0F, 1.5F);
	
	public static void configureBlocks(Configuration config) {
		blockIdle = new CraftingFurnaceBlock(config.getBlock("BlockIdle", 3000).getInt(), false, false).setUnlocalizedName("blockIdle").setHardness(3.5F).setStepSound(soundMetalFootstep);
		craftingFurnace = new CraftingFurnaceBlock(config.getBlock("CraftingFurnace", 3001).getInt(), true, false).setUnlocalizedName("craftingFurnace").setHardness(3.5F).setStepSound(soundMetalFootstep).setLightValue(0.8F);
		alloyFurnace = new CraftingFurnaceBlock(config.getBlock("AlloyFurnace", 3002).getInt(), true, true).setUnlocalizedName("alloyfurnace").setHardness(3.5F).setStepSound(soundMetalFootstep);
		
		//multi-block freezer-version (packed-ice) dont-animate run off eu/mj
	}
	
	public static void registerBlock(GameRegistry reg) {
		reg.registerBlock(blockIdle, ItemBlock.class, "blockIdle");
		reg.registerBlock(craftingFurnace, "craftingFurnace");
	}
	
	public static void setNames(LanguageRegistry reg) {
		reg.addName(blockIdle, "Crafting Furnace");
		
	}
	
}
