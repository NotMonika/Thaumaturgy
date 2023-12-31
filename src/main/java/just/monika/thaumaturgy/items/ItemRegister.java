package just.monika.thaumaturgy.items;

import just.monika.thaumaturgy.Thaumaturgy;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = "thaumaturgy")
public final class ItemRegister {
    public static List<Item> items = new ArrayList<>();
    static Item entitySoul=new ItemLoliPickaxeMaterial("entitySoul",7,false)
            .setRegistryName(Thaumaturgy.modid,"entity_soul");
    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs("thaumaturgy_things") {
        // 获得用作标签图标的 ItemStack。你大可以往里面塞各种奇奇怪怪的数据。
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.IRON_INGOT);
        }
    };

    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {

        event.getRegistry().registerAll(
                items.toArray(items.toArray(new Item[0]))
        );
        event.getRegistry().registerAll(
                entitySoul= new ItemLoliPickaxeMaterial("entitySoul",7,false)
                        .setRegistryName(Thaumaturgy.modid,"entity_soul"),
                new ItemBlock(BasicMachine.self).setRegistryName(BasicMachine.self.getRegistryName())
        );
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(entitySoul, 0, new ModelResourceLocation(entitySoul.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(entitySoul, 1, new ModelResourceLocation(entitySoul.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(entitySoul, 2, new ModelResourceLocation(entitySoul.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(entitySoul, 3, new ModelResourceLocation(entitySoul.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(entitySoul, 4, new ModelResourceLocation(entitySoul.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(entitySoul, 5, new ModelResourceLocation(entitySoul.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(entitySoul, 6, new ModelResourceLocation(entitySoul.getRegistryName(), "inventory"));

    }
}
