package com.brynnexvii.alpha.objects.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AlphaFuelItem extends Item{
	
	private final int burnTime;
	
	public AlphaFuelItem(Properties properties, int burnTimeIn) {
		super(properties);
		burnTime = burnTimeIn;
	}

	@Override
	public int getBurnTime(ItemStack itemStack) {
		return this.burnTime; //sets the burn time in seconds? (ticks?), not sure how its calculated
	}
}

