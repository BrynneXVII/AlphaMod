package com.brynnexvii.alpha.objects.items;

import java.util.List;

import com.brynnexvii.alpha.util.helpers.KeyboardHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class AdvancedItem extends Item{

	public AdvancedItem(Properties properties) {
		super(properties);
	}
	
	//examples of things you can do
	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
	
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		if(KeyboardHelper.isHoldingShift()) {
			tooltip.add(new StringTextComponent("Test information"));
			//can repeat for a tip in a new line
		} else {
			tooltip.add(new StringTextComponent("Hold" + "\u00A7e" + "SHIFT" + "\u00A77" + "for more information."));
			// shows how to add colour with the minecraft colour codes
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 500, 255));
		worldIn.setRainStrength(1.0f);
		//worldIn.setTimeLightningFlash(1);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	
	// ctrl space to find out about different methods you can modify or make your own!
}
