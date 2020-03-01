package com.brynnexvii.alpha.events;

import com.brynnexvii.alpha.Alpha;
import com.brynnexvii.alpha.init.BlockInit;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Alpha.MOD_ID, bus = Bus.FORGE)
public class ExampleJumpEvent {
	@SubscribeEvent
	public static void ExampleJumpEvent(LivingJumpEvent event) {
		Alpha.LOGGER.info("Test jump event fired");
		LivingEntity livingentity = event.getEntityLiving();
		World world = livingentity.getEntityWorld(); //can use world.XXXXX to modify the world
		//world.setBlockState(livingentity.getPosition().add(0, 7, 0), BlockInit.blue_block.getDefaultState());
		livingentity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 255));
		livingentity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 5000, 255));
		//livingentity.setFire(2);
	}
}
