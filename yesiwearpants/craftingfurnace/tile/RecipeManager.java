package yesiwearpants.craftingfurnace.tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yesiwearpants.craftingfurnace.common.RecipeSorter;
import yesiwearpants.craftingfurnace.tile.recipe.CraftingRecipe;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeManager {
	
	/** Selects Animation to use when smelting */
	static boolean is_metal;
	/** Number of Ticks Item will take to smelt */
	static int itemProgress;
	/** Crafting Result */
	static ItemStack output = null;
	
	private static final RecipeManager instance = new RecipeManager();
	
	static final RecipeManager getInstance() {
		return instance;
	}

    private List recipes = new ArrayList();
    
    public RecipeManager() {
    	Collections.sort(this.recipes, new RecipeSorter(this));
    }
    
    public List getRecipeList() {
    	return this.recipes;
    }

    public void addAPIRecipe(boolean is_Metal, int _itemProgress, ItemStack result, Object... input) {
    	is_metal = is_Metal;
        itemProgress = _itemProgress;
        output = result;
        RecipeManager recipe = new RecipeManager();
        recipe.Step1(output, input);
	}
    
    public static void addAPIOreRecipe(boolean is_Metal, int _itemProgress, ItemStack result, Object... input) {
    	is_metal = is_Metal;
        itemProgress = _itemProgress;
        output = result;
        RecipeManager recipe = new RecipeManager();
        recipe.Step1a(output, input);
	}
    
    public void Step2() {}
    
    private void Step1(ItemStack par1ItemStack, Object ... par2ArrayOfObj) {
        ArrayList arraylist = new ArrayList();
        Object[] aobject = par2ArrayOfObj;
        int i = par2ArrayOfObj.length;

        for (int j = 0; j < i; ++j)
        {
            Object object1 = aobject[j];

            if (object1 instanceof ItemStack)
            {
                arraylist.add(((ItemStack)object1).copy());
            }
            else if (object1 instanceof Item)
            {
                arraylist.add(new ItemStack((Item)object1));
            }
            else
            {
                if (!(object1 instanceof Block))
                {
                    throw new RuntimeException("Invalid shapeless recipy!");
                }

                arraylist.add(new ItemStack((Block)object1));
            }
        }

        this.recipes.add(new CraftingRecipe(par1ItemStack, arraylist));
    }
  
    public boolean findMatch(ItemStack[] inv) {
        if (inv.length != 9)
            return false;
        for (ItemStack ingredient : _matrix) {
            int missing = ingredient.stackSize;
            for (ItemStack itemInGrid : inv) {
            	if (ItemStack.areItemStacksEqual(ingredient, itemInGrid))
            		missing = missing - itemInGrid.stackSize;
            	if (missing <= 0)
            		break;
            }
            if (missing > 0)
            	return false;
        }
        return true;
    }
    
    public RecipeManager copy() {
        return new RecipeManager(_matrix, output, _handler, itemProgress);
    }
}
