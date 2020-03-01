package com.brynnexvii.alpha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.brynnexvii.alpha.init.BlockInit;
import com.brynnexvii.alpha.world.gen.AlphaOreGen;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// Makes MC realize this is a mod, takes your "mod ID", all lowercase, must be unique to all other mods you use
@Mod("alpha")
@Mod.EventBusSubscriber(modid = Alpha.MOD_ID, bus = Bus.MOD)
public class Alpha
{
    // Define logger, used to output things to the log, this is a nice to have
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "alpha";
    public static Alpha instance;

    public Alpha() {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        instance = this;
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent //makes sure events actually are fired
    public void onServerStarting(FMLServerStartingEvent event) {

    }
    
    @SubscribeEvent //this input allows it to be put in before the server starts -> no crashes
    public static void loadCompleteEvent (FMLLoadCompleteEvent event) {
    	AlphaOreGen.generateOre();
    }
    
    // make a new item group (used to be called creative tab)
    public static class ExampleItemGroup extends ItemGroup{
    	
    	public static final ExampleItemGroup instance = new ExampleItemGroup(ItemGroup.GROUPS.length,"exampletab");
    	
    	private ExampleItemGroup (int index, String label) {
    		super(index, label);
    	}
    	
    	@Override
    	public ItemStack createIcon() {
    		return new ItemStack(BlockInit.example_block);
    	}
    }

}
