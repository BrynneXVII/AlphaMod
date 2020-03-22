package com.brynnexvii.alpha.tileentity;

import javax.annotation.Nullable;

import com.brynnexvii.alpha.init.AlphaTileEntityTypes;
import com.brynnexvii.alpha.util.helpers.NBTHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class QuarryTileEntity extends TileEntity implements ITickableTileEntity{
	//implementing the tickable ability allows it to do something on every tick
	
	public int x, y, z, tick;
	boolean initialized = false;
	
	public QuarryTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public QuarryTileEntity() {
		this(AlphaTileEntityTypes.QUARRY.get());
	}
	
	@Override
	public void tick() {
		if(!initialized) init();
		tick++;
		if(tick == 40) {
			tick = 0;
			if(y > 2) execute();
		}
	}

	private void init() {
		initialized = true;
		x = this.pos.getX() - 1;
		y = this.pos.getY() - 1;
		z = this.pos.getZ() - 1;
		tick = 0;
	}

	private void execute() {
		int index = 0;
		Block[] blocksRemoved = new Block[25]; //quarry for a 5x5 hence 25
		//not best way, is quite laggy
		for (int x = 0; x < 5; x++) {
			for(int z = 0; z < 5; z++) {
				BlockPos posToBreak = new BlockPos(this.x + x, this.y, this.z + z);
				blocksRemoved[index] = this.world.getBlockState(posToBreak).getBlock();
				destroyBlock(posToBreak, true, null);
				index++;
			}
		}
		this.y--;
	}

	private boolean destroyBlock(BlockPos pos, boolean dropBlock, @Nullable Entity entity) {
		BlockState blockstate = world.getBlockState(pos);
		if(blockstate.isAir(world, pos)) return false;
		else {
			IFluidState ifluidstate = world.getFluidState(pos);
			world.playEvent(2001, pos, Block.getStateId(blockstate)); //2001 because in play event it is the one to play the sound when it is broken
			if(dropBlock) {
				TileEntity tileentity = blockstate.hasTileEntity() ? world.getTileEntity(pos) : null;
				Block.spawnDrops(blockstate, world, this.pos.add(0, 1.5, 0), tileentity, entity, ItemStack.EMPTY);
			}
			return world.setBlockState(pos, ifluidstate.getBlockState(), 3); //3 is update fluids?
		}
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("initvalues", NBTHelper.toNBT(this));
		return super.write(compound);
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		CompoundNBT initValues = compound.getCompound("initValues");
		if(initValues != null) {
			this.x = initValues.getInt("x");
			this.y = initValues.getInt("y");
			this.z = initValues.getInt("z");
			this.tick = 0;
			initialized = true;
			return;
		}
		init();
	}

}
