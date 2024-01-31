package just.monika.thaumaturgy.things.craftings;

import just.monika.thaumaturgy.things.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = "thaumaturgy")
public class AnvilCrafting {
    //left,right,output
    public static final Item[][] crafts = {
            {
                Material.get("uflatium").getIngot(),
                Items.NETHERBRICK,
                Material.get("netherite").getIngot()
            },{
                Items.BOOK,
                Material.get("iekrine").getIngot(),
                Items.KNOWLEDGE_BOOK
            },


    };
    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event){
        for(Item[] craft : crafts){
            if((event.getLeft().getItem() == craft[0]) && (event.getRight().getItem() == craft[1])){
                event.setOutput(new ItemStack(craft[2]));
                event.setCost(1);
                event.setMaterialCost(1);
            }
        }
    }
}
