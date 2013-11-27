package yesiwearpants.craftingfurnace.tile;


import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import yesiwearpants.craftingfurnace.Configure;
import yesiwearpants.craftingfurnace.Refer;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CraftingFurnaceBlock extends BlockContainer {
	
	private final boolean isActive;
	
	private Icon iconFront;

	public CraftingFurnaceBlock(int par1, boolean isActive, boolean is_Metal) {
		super(par1, Material.iron);
		
		this.isActive = isActive;
		Recipe.is_metal = is_Metal;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCraftingFurnace();
	}

	public void registerIcon(IconRegister icon) {
		this.blockIcon = icon.registerIcon(Refer.MOD_ID + ":" + "machines/" + "blockidle");
		this.iconFront = icon.registerIcon(Refer.MOD_ID + ":" + "machines/" + (getUnlocalizedName().substring(5)));
		
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		return side ==meta ? this.iconFront : this.blockIcon;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tads, List list) {
		for(int i = 0; i < 2; i++) {
			list.add(new ItemStack(par1, i, i));
		}
	}
	
	public int idDropped(int par1, Random rand, int par2) {
		return Configure.blockIdle.blockID;
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefaultDirection(world, x, y ,z);
	}
	
	private void setDefaultDirection(World world, int x, int y, int z) {
		if(!world.isRemote) {
		int i = world.getBlockId(x, y, z - 1);
		int il = world.getBlockId(x, y, z + 1);
		int jl = world.getBlockId(x - 1, y, z);
		int kl = world.getBlockId(x + 1, y, z);
		byte b0 = 3;
			
			if(Block.opaqueCubeLookup[il] && Block.opaqueCubeLookup[i]) {
				b0 = 2;	
			}
			if(Block.opaqueCubeLookup[i] && Block.opaqueCubeLookup[il]) {
				b0 = 3;	
			}
			if(Block.opaqueCubeLookup[jl] && Block.opaqueCubeLookup[kl]) {
				b0 = 4;	
			}
			if (Block.opaqueCubeLookup[kl] && Block.opaqueCubeLookup[jl]) {
				b0 = 5;
			}
			
			world.setBlockMetadataWithNotify(x, y, z, b0, z);
		}
	}
	
	public void onBockPlackedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0f / 360.0f) + 0.5D) & 3;		
		if(l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}	
		if(l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}
		if(l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		if(l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
		if(stack.hasDisplayName()) {
			((TileEntityCraftingFurnace)world.getBlockTileEntity(x, y, z)).setGuiDisplayName(stack.getDisplayName());
		}
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			FMLNetworkHandler.openGui(player, Refer.MOD_ID, Refer.guiIDCraftFurn, world, x, y, z);
		}
		return true;
	}
	
	
}
