package yesiwearpants.craftingfurnace.API;

import net.minecraft.item.ItemStack;
import yesiwearpants.craftingfurnace.tile.RecipeManager;

public abstract class APIRecipe extends RecipeManager {
	
	/** Selects Animation to use when smelting
	* static boolean is_metal;
	*   Number of Ticks Item will take to smelt
	* static int itemProgress;
	*   Initalized in Another File */
	
	public abstract void addAPIRecipe(boolean is_Metal, int _itemProgress, ItemStack output, Object... params);

}
