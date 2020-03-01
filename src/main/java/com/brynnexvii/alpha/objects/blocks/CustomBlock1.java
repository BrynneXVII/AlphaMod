package com.brynnexvii.alpha.objects.blocks;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;


public class CustomBlock1 extends Block {

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;//gives the item a rotation
	
	private static final VoxelShape SHAPE_N = Stream.of(
			Block.makeCuboidShape(4, 0, 4, 12, 2, 12),
			Block.makeCuboidShape(5, 2, 5, 11, 4, 11),
			Block.makeCuboidShape(6, 4, 6, 10, 6, 10),
			Block.makeCuboidShape(7, 6, 7, 9, 8, 9)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	private static final VoxelShape SHAPE_W = Stream.of(
			Block.makeCuboidShape(4, 0, 4, 12, 2, 12),
			Block.makeCuboidShape(5, 2, 5, 11, 4, 11),
			Block.makeCuboidShape(6, 4, 6, 10, 6, 10),
			Block.makeCuboidShape(7, 6, 7, 9, 8, 9)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	private static final VoxelShape SHAPE_S = Stream.of(
			Block.makeCuboidShape(4, 0, 4, 12, 2, 12),
			Block.makeCuboidShape(5, 2, 5, 11, 4, 11),
			Block.makeCuboidShape(6, 4, 6, 10, 6, 10),
			Block.makeCuboidShape(7, 6, 7, 9, 8, 9)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	private static final VoxelShape SHAPE_E = Stream.of(
			Block.makeCuboidShape(4, 0, 4, 12, 2, 12),
			Block.makeCuboidShape(5, 2, 5, 11, 4, 11),
			Block.makeCuboidShape(6, 4, 6, 10, 6, 10),
			Block.makeCuboidShape(7, 6, 7, 9, 8, 9)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	
	public CustomBlock1(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	
	//for bounding boxes?
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch(state.get(FACING)) {
		case NORTH:
			return SHAPE_N;
		case SOUTH:
			return SHAPE_S;
		case EAST:
			return SHAPE_E;
		case WEST:
			return SHAPE_W;
		default:
			return SHAPE_N;
			
		}
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()); //get it to face towards you
	}
	
	//the following 2 aren't really needed, for compatibility with other mods if they want to rotate your blocks (although shouldn't since deprecated)
	//He said its deprecated??
	@Override 
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING,  rot.rotate(state.get(FACING)));
	}
	
	//also deprecated
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}
	
	//needed to implement the new block property
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
	//SPAWN LIGHTNING ON RIGHT CLICK
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {
		if(!worldIn.isRemote()) { //remote = client, not remote = server
			ServerWorld serverWorld = (ServerWorld)worldIn;
			LightningBoltEntity entity = new LightningBoltEntity(worldIn, pos.getX(), pos.getY(),pos.getZ(), false); //not super accurate x y z but can't really change that, doesn't know why false but it always is for minecraft
			serverWorld.addLightningBolt(entity);
		}
		return ActionResultType.SUCCESS;
	}
	
	
}
