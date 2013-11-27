package yesiwearpants.craftingfurnace.gui;

import yesiwearpants.craftingfurnace.Refer;
import yesiwearpants.craftingfurnace.tile.TileEntityCraftingFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiCraftingFurnace extends GuiContainer{
	
	public TileEntityCraftingFurnace furnace;
	
	public static final ResourceLocation texture = new ResourceLocation(Refer.GUI_TEX_PATH, "GuiBackground");

	public GuiCraftingFurnace(InventoryPlayer inventory, TileEntityCraftingFurnace entity) {
		super(new ContainerCraftingFurnace(inventory, entity));
		
		this.furnace = entity;
	}

	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		
	}

}
