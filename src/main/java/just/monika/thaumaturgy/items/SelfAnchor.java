package just.monika.thaumaturgy.items;

import just.monika.thaumaturgy.Thaumaturgy;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Thaumaturgy.modid)
public class SelfAnchor extends Item {
    public SelfAnchor(){
        setRegistryName(Thaumaturgy.modid,"selfanchor");
    }

}
