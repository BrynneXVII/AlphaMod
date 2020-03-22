package com.brynnexvii.alpha.objects.blocks;

import com.brynnexvii.alpha.init.AlphaTileEntityTypes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class AlphaQuarryBlock extends Block {

	public AlphaQuarryBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return AlphaTileEntityTypes.QUARRY.get().create();
	}

}
