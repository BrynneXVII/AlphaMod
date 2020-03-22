package com.brynnexvii.alpha.init;

import com.brynnexvii.alpha.Alpha;
import com.brynnexvii.alpha.Alpha.ExampleItemGroup;
import com.brynnexvii.alpha.objects.blocks.AlphaCraftingTableBlock;
import com.brynnexvii.alpha.objects.blocks.AlphaQuarryBlock;
import com.brynnexvii.alpha.objects.blocks.CustomBlock1;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Alpha.MOD_ID, bus = Bus.MOD)
@ObjectHolder(Alpha.MOD_ID) 
public class BlockInit {
	public static final Block example_block = null;
	public static final Block blue_block = null;
	public static final Block custom_model_block_1 = null;
	//public static final FlowerPotBlock tall_flower_pot = null;
	
	//crafting table attempt CraftingTableBlock and furnace attempt
	public static final Block test_crafting_table = null;
	//public static final Block test_furnace = null;
	
	//tile entity
	public static final Block quarry = null;
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new Block(Block.Properties.create(Material.EARTH).lightValue(7).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE)).setRegistryName("example_block"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.EARTH).sound(SoundType.GROUND)).setRegistryName("blue_block"));
		event.getRegistry().register(new CustomBlock1(Block.Properties.create(Material.EARTH).sound(SoundType.GROUND)).setRegistryName("custom_model_block_1"));
		//event.getRegistry().register(new FlowerPotBlock(empty, Material.MISCELLANEOUS, Block.Properties.from(empty)).setRegistryName("tall_flower_pot"));
	
		//crafting table
		event.getRegistry().register(new AlphaCraftingTableBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5F).sound(SoundType.WOOD)).setRegistryName("test_crafting_table"));
		//event.getRegistry().register(new FurnaceBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5F).lightValue(13)).setRegistryName("test_furnace"));
	
		//tile entity
		event.getRegistry().register(new AlphaQuarryBlock(Block.Properties.create(Material.IRON)).setRegistryName("quarry"));
		
	}
	
	@SubscribeEvent
	public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new BlockItem(example_block, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("example_block"));
		event.getRegistry().register(new BlockItem(blue_block, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("blue_block"));
		event.getRegistry().register(new BlockItem(custom_model_block_1, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("custom_model_block_1"));
		//event.getRegistry().register(new BlockItem(tall_flower_pot, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("tall_flower_pot"));
	
		//crafting table
		event.getRegistry().register(new BlockItem(test_crafting_table, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("test_crafting_table"));
		//event.getRegistry().register(new BlockItem(test_furnace, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("test_furnace"));
		
		//tile entity
		event.getRegistry().register(new BlockItem(quarry, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("quarry"));
	}
}
