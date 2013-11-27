package yesiwearpants.craftingfurnace.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SimpleInv implements ISidedInventory{
	
	public static ItemStack[] slots = new ItemStack[11];
	public static final int[] slots_top = new int[] {9};
	public static final int[] slots_sides = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8};
	public static final int[] slots_bottom = new int[] {10, 9};
	
	private final String localizedName;
    private ItemStack[] contents;
    private final int maxStack;

    public SimpleInv(int size, String name, int stackLimit) {
        contents = new ItemStack[size];
        localizedName = name;
        maxStack = stackLimit;
    }

	@Override
	public int getSizeInventory() {
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return slots[1];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if(slots[i] == null)
			return null;
		if (slots[i].stackSize > j) {
			ItemStack ret = slots[i].splitStack(j);
			return ret;
		}
		ItemStack ret = slots[i];
		slots[i] = null;
		return ret;
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

	@Override
	public void onInventoryChanged() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private int onInsert(int i, ItemStack stack) {
		ItemStack slot = slots[i];
		if(slot == null) {
			slots[1] = stack.copy();
			return stack.stackSize;
		}
		if (ItemStack.areItemStacksEqual(slot, stack)) {
			slot.stackSize += stack.stackSize;
			if (slot.stackSize > 127) {
				int res = stack.stackSize = (slot.stackSize = 127);
				slot.stackSize = 127;
				return res;
			}
			else {
				return stack.stackSize;
			}
		}
		else {
			return 0;
		}
	}
	
	public int addCompressed(ItemStack stack) {
        if (stack == null)
            return 0;
        stack = stack.copy();
        for (int i = 0; i < this.slots.length; i++) {
            if (stack.stackSize <= 0) {
                break;
            }
            if (slots[i] == null)
                continue; // Skip Empty Slots on first attempt.
            int added = onInsert(i, stack);
            stack.stackSize -= added;
        }
        for (int i = 0; i < this.slots.length; i++) {
            if (stack.stackSize <= 0) {
                break;
            }
            int added = onInsert(i, stack);
            stack.stackSize -= added;
        }
        onInventoryChanged();
        return stack.stackSize;
    }

	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return par1 == 10 ? slots_bottom : par1 == 9 ? slots_top : slots_sides;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isItemValidForSlot(i, itemstack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return j != 0 || i != 1 || itemstack.itemID == Item.bucketEmpty.itemID;
	}
	
	public ItemStack[] getContents() {
        return this.slots;
    }	
}