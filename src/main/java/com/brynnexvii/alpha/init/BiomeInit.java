package com.brynnexvii.alpha.init;

import com.brynnexvii.alpha.Alpha;
import com.brynnexvii.alpha.world.biomes.ExampleBiome;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, Alpha.MOD_ID);

	public static final RegistryObject<Biome> EXAMPLE_BIOME = BIOMES.register("example_biome",
			() -> new ExampleBiome(new Biome.Builder().precipitation(RainType.SNOW).scale(1.2f).temperature(-0.5f)
					.waterColor(8070339).waterFogColor(13481215)
					.surfaceBuilder(SurfaceBuilder.DEFAULT,
							new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(),
									BlockInit.blue_block.getDefaultState(), Blocks.NETHER_BRICKS.getDefaultState()))
					.category(Category.FOREST).downfall(0.5f).depth(0.175f).parent(null)));// scale is size of the biome
	// in the Surface builder config it uses 3 block states, 1 = top block, main
	// biome block (grass) 2 = middle block (dirt), 3 = bottom block (underwater)
	// category = what biomes it will spawn it next to, picking a more common biome
	// will make it more common
	// downfall = how often it rains
	// depth = how low or high the biome is, default value is 0.125 **be careful it
	// can really affect things
	// parent = the parent biome
	
	public static void registerBiomes() {
		registerBiome(EXAMPLE_BIOME.get(), Type.FOREST, Type.OVERWORLD, Type.MAGICAL);
	}
	
	private static void registerBiome(Biome biome, Type... types) {
		BiomeDictionary.addTypes(biome, types);
		//BiomeManager.addSpawnBiome(biome);
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 100));
	}
}
