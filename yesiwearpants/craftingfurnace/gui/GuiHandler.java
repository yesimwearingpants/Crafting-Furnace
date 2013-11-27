package yesiwearpants.craftingfurnace.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import yesiwearpants.craftingfurnace.Refer;
import yesiwearpants.craftingfurnace.tile.TileEntityCraftingFurnace;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
	
	

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(entity !=null) {
			switch(ID){
				case Refer.guiIDCraftFurn:
					if (entity instanceof TileEntityCraftingFurnace) {
						return new GuiCraftingFurnace(player.inventory, (TileEntityCraftingFurnace) entity);
					}
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity entity = world.getBlockTileEntity(x, y, z);
		if(entity !=null) {
			switch(ID){
				case Refer.guiIDCraftFurn:
					if (entity instanceof TileEntityCraftingFurnace) {
						return new GuiCraftingFurnace(player.inventory, (TileEntityCraftingFurnace) entity);
					}
			}
		}
		return null;
	}

}
