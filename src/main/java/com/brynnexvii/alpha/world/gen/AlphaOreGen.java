package com.brynnexvii.alpha.world.gen;

import com.brynnexvii.alpha.init.BlockInit;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class AlphaOreGen {
	public static void generateOre() {
		for(Biome biome : ForgeRegistries.BIOMES) { //goes through every biome registered to forge -> into mod ones if they're registered!
			ConfiguredPlacement customConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 5, 5, 25)); //(how common(20 > coal), bottom offset, top offset, max height (then - offset))
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
							BlockInit.blue_block.getDefaultState(),10)).withPlacement(customConfig)); //last numbers are max vein size
		}
	}
}
