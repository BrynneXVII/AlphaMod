package com.brynnexvii.alpha.init;

import java.util.function.Supplier;

import com.brynnexvii.alpha.Alpha;
import com.brynnexvii.alpha.Alpha.ExampleItemGroup;
import com.brynnexvii.alpha.objects.items.AdvancedItem;
import com.brynnexvii.alpha.objects.items.AlphaFuelItem;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Alpha.MOD_ID, bus = Bus.MOD)
@ObjectHolder(Alpha.MOD_ID) 
public class ItemInit {
	// basic items
	public static final Item example_item = null;
	public static final Item example_food = null;
	public static final Item special_item = null;
	
	
	// tools
	public static final Item blue_sword = null;
	public static final Item blue_pickaxe = null;
	public static final Item blue_axe = null;
	public static final Item blue_shovel = null;
	public static final Item blue_hoe = null;
	
	//armour
	public static final Item test_helmet = null;
	public static final Item test_chestplate = null;
	public static final Item test_leggings = null;
	public static final Item test_boots = null;
	
	//fuel
	public static final Item fuel_1 = null;
	public static final Item fuel_2 = null;
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName("example_item"));
		event.getRegistry().register(new Item(new Item.Properties().group(ExampleItemGroup.instance).food(new Food.Builder().hunger(6).saturation(2f).effect(new EffectInstance(Effects.ABSORPTION, 6000, 4), 0.6f).effect(new EffectInstance(Effects.GLOWING, 6000), 1f).build())).setRegistryName("example_food"));
		event.getRegistry().register(new AdvancedItem(new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("special_item"));
		
		// tools
		event.getRegistry().register(new SwordItem(ExampleItemTier.EXAMPLE, 8, 5.0f, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("blue_sword"));
		event.getRegistry().register(new PickaxeItem(ExampleItemTier.EXAMPLE, 3, 5.0f, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("blue_pickaxe"));
		event.getRegistry().register(new ShovelItem(ExampleItemTier.EXAMPLE, 3, 5.0f, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("blue_shovel"));
		event.getRegistry().register(new AxeItem(ExampleItemTier.EXAMPLE, 6, 5.0f, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("blue_axe"));
		event.getRegistry().register(new HoeItem(ExampleItemTier.EXAMPLE, 5.0f, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("blue_hoe"));
	
		// armour
		event.getRegistry().register(new ArmorItem(AlphaArmorMaterial.TEST, EquipmentSlotType.HEAD, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("test_helmet"));
		event.getRegistry().register(new ArmorItem(AlphaArmorMaterial.TEST, EquipmentSlotType.CHEST, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("test_chestplate"));
		event.getRegistry().register(new ArmorItem(AlphaArmorMaterial.TEST, EquipmentSlotType.LEGS, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("test_leggings"));
		event.getRegistry().register(new ArmorItem(AlphaArmorMaterial.TEST, EquipmentSlotType.FEET, new Item.Properties().group(ExampleItemGroup.instance)).setRegistryName("test_boots"));
	
		// fuel
		event.getRegistry().register(new AlphaFuelItem(new Item.Properties().group(ExampleItemGroup.instance), 60).setRegistryName("fuel_1"));
		event.getRegistry().register(new AlphaFuelItem(new Item.Properties().group(ExampleItemGroup.instance), 600).setRegistryName("fuel_2"));
	}
	
	public enum ExampleItemTier implements IItemTier{
		EXAMPLE(4,1500,15.0f, 8.0f, 250, () -> {
			return Ingredient.fromItems(BlockInit.blue_block);
		}); // harvest, uses, efficiency, attack damage, enchantability, repair material
		
		private final int harvestLevel;
		private final int maxUses;
		private final float efficiency;
		private final float attackDamage;
		private final int enchantability;
		private final LazyValue<Ingredient> repairMaterial;
		
		private ExampleItemTier (int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
			this.harvestLevel = harvestLevel;
			this.maxUses = maxUses;
			this.efficiency = efficiency;
			this.attackDamage = attackDamage;
			this.enchantability = enchantability;
			this.repairMaterial = new LazyValue<>(repairMaterial);
		}

		@Override
		public int getMaxUses() {
			return this.maxUses;
		}

		@Override
		public float getEfficiency() {
			return this.efficiency;
		}

		@Override
		public float getAttackDamage() {
			return this.attackDamage;
		}

		@Override
		public int getHarvestLevel() {
			return this.harvestLevel;
		}

		@Override
		public int getEnchantability() {
			return this.enchantability;
		}

		@Override
		public Ingredient getRepairMaterial() {
			return this.repairMaterial.getValue();
		}
	}
	
	public enum AlphaArmorMaterial implements IArmorMaterial{
		TEST(Alpha.MOD_ID + ":test", 5, new int[] {7, 9, 11, 7}, 420, SoundEvents.field_226124_Y_, 6.9f, () -> {
			return Ingredient.fromItems(ItemInit.example_item);
		}); // : texture layer 1 for head chest legs? and 2 for boots
		//numbers above are max damage factor, damage reduction boots legs chest head, enchantability, sound, toughness, repair material
		private static final int[] MAX_DAMAGE_ARRAY = new int[] {16, 16, 16, 16};
		private final String name;
		private final int maxDamageFactor;
		private final int[] damageReductionAmountArray;
		private final int enchantability;
		private final SoundEvent soundEvent;
		private final float toughness;
		private final LazyValue<Ingredient> repairMaterial;
		
		private AlphaArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountArrayIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn, Supplier<Ingredient> repairMaterialIn){
			this.name = nameIn;
			this.maxDamageFactor = maxDamageFactorIn;
			this.damageReductionAmountArray = damageReductionAmountArrayIn;
			this.enchantability = enchantabilityIn;
			this.soundEvent = soundEventIn;
			this.toughness = toughnessIn;
			this.repairMaterial = new LazyValue<>(repairMaterialIn);
		}

		@Override
		public int getDurability(EquipmentSlotType slotIn) {
			return MAX_DAMAGE_ARRAY[slotIn.getIndex()]*this.maxDamageFactor; //each armor type has a slot number
		}

		@Override
		public int getDamageReductionAmount(EquipmentSlotType slotIn) {
			return this.damageReductionAmountArray[slotIn.getIndex()];
		}

		@Override
		public int getEnchantability() {
			return this.enchantability;
		}

		@Override
		public SoundEvent getSoundEvent() {
			return this.soundEvent;
		}
		
		@Override
		public Ingredient getRepairMaterial() {
			return this.repairMaterial.getValue();
		}
		
		@OnlyIn(Dist.CLIENT) //makes sure this only runs client side
		@Override
		public String getName() {
			return this.name;
		}

		@Override
		public float getToughness() {
			return this.toughness;
		}
	}
}
