package yesiwearpants.craftingfurnace.common;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import yesiwearpants.craftingfurnace.tile.CraftingFurnaceBlock;

public abstract class TileExtender extends TileEntity{
	
	public TileExtender() {}
	
	public abstract int getLightLevel();
    
	public abstract boolean onBlockActivated(EntityPlayer player);

    public abstract CraftingFurnaceBlock getType();
    
    public abstract void onBlockPlacedBy(EntityLivingBase player, ItemStack item);

    public abstract Packet getDescriptionPacket();
        
	
	

}
