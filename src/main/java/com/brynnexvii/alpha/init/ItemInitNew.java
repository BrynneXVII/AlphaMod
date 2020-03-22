package com.brynnexvii.alpha.init;

import com.brynnexvii.alpha.Alpha;
import com.brynnexvii.alpha.Alpha.ExampleItemGroup;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInitNew {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Alpha.MOD_ID);
	
	public static final RegistryObject<Item> DEF_ITEM = ITEMS.register("def_item", () -> new Item(new Item.Properties().group(ExampleItemGroup.instance)));
}
