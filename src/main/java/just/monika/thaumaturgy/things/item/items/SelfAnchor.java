package just.monika.thaumaturgy.things.item.items;

import just.monika.thaumaturgy.Thaumaturgy;
import net.minecraft.item.Item;

//@Mod.EventBusSubscriber(modid = Thaumaturgy.modid)
public class SelfAnchor extends Item {
    public SelfAnchor(){
        setRegistryName(Thaumaturgy.modid,"selfanchor");
    }

}
