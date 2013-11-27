package yesiwearpants.craftingfurnace.tile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntityFurnace;
import yesiwearpants.craftingfurnace.common.SimpleInv;
import yesiwearpants.craftingfurnace.common.TileExtender;
import static yesiwearpants.craftingfurnace.common.SimpleInv.slots;
import static yesiwearpants.craftingfurnace.common.SimpleInv.sides_top;
import static yesiwearpants.craftingfurnace.common.SimpleInv.slots_sides;
import static yesiwearpants.craftingfurnace.common.SimpleInv.slots_bottom;

public class TileEntityCraftingFurnace extends TileExtender implements IInventory{
	
	private SimpleInv _inv = new SimpleInv(11, "      ", 64);
	
	private int speed = 150;
	// True when working on something
    public boolean hasWork = false;
	/** The number of ticks that the furnace will keep burning */
	private int fuel;
	/** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
	private int currentItemBurnTime;
		
	public int getSizeInventory() {
		return _inv.getSizeInventory();
	}

	public ItemStack getStackInSlot(int i) {
		return _inv.getStackInSlot(i);
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		return _inv.decrStackSize(i, j);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	private boolean hasWork() {
		return this.fuel > 0;
	}

	public void updateEntity() {
		boolean flag = this.hasWork();
		boolean flag1 = false;
		
		if(this.hasWork()) {
			--this.fuel;
		
		}
		if(!this.worldObj.isRemote) {
			if(this.fuel == 0 && this.canSmelt()) {
				this.currentItemBurnTime = this.fuel = TileEntityFurnace.getItemBurnTime(slots[9]);
				
				if(this.hasWork()) {
					flag1 = true;
					if(slots[9] != null) {
						slots[9].stackSize--;
						
						if(slots[9].stackSize == 0) {
							slots[9] = slots[9].getItem().getContainerItemStack(slots[9]);
						}
					}
				}
			}
			if(this.hasWork() && canSmelt()) {
				RecipeManager.itemProgress--;
				this.smeltItem();
				flag1 = true;
				}
				else {
					RecipeManager.itemProgress = 0;
			}
			if(flag != this.hasWork()) {
				flag1 = true;
				CraftingFurnaceBlock.updateBlockState(this.fuel > 0, this.worldObj,this.xCoord, this.yCoord, this.zCoord);
			}
		}
		if(flag1) {
			this.onInventoryChanged();
		}
	}

	public void openChest() {}

	public void closeChest() {}

	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i == 10 ? false : (i == 9 ? TileEntityFurnace.isItemFuel(itemstack) : true);
	}

	public int[] getAccessibleSlotsFromSide(int par1) {
		return _inv.getAccessibleSlotsFromSide(par1);
	}
	
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return _inv.canInsertItem(i, itemstack, j);
	}

	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return _inv.canExtractItem(i, itemstack, j);
	}

	@Override
	public int getLightLevel() {
		return heat > 0 ? (hasWork ? 13 : 10) : 0;
		return 0;
	}

	@Override
	public boolean onBlockActivated(EntityPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onBlockPlacedBy(EntityLivingBase player, ItemStack item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CraftingFurnaceBlock getType() {
		return null;
	}

	@Override
	public Packet getDescriptionPacket() {
		// TODO Auto-generated method stub
		return null;
	}
}
