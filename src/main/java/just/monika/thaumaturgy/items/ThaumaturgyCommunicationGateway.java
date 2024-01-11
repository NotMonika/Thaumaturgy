package just.monika.thaumaturgy.items;

import just.monika.thaumaturgy.Thaumaturgy;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;

public class ThaumaturgyCommunicationGateway extends Item {
    public static ThaumaturgyCommunicationGateway self = new ThaumaturgyCommunicationGateway();
    public ThaumaturgyCommunicationGateway(){
        setCreativeTab(ItemRegister.CREATIVE_TAB);
        setRegistryName(Thaumaturgy.modid,"thaumaturgy_communication_gateway");
        setMaxStackSize(1);
        setTranslationKey("thaumaturgy_communication_gateway");
    }
}
