package just.monika.thaumaturgy.things.item.items;

import just.monika.thaumaturgy.Thaumaturgy;
import just.monika.thaumaturgy.things.item.ItemRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ThaumaturgyCommunicationGateway extends Item {
    public static ThaumaturgyCommunicationGateway self = new ThaumaturgyCommunicationGateway();

    public ThaumaturgyCommunicationGateway() {
        setCreativeTab(ItemRegister.CREATIVE_TAB);
        setRegistryName(Thaumaturgy.modid, "thaumaturgy_communication_gateway");
        setMaxStackSize(1);
        setTranslationKey("thaumaturgy_communication_gateway");
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list) {
        if (this.isInCreativeTab(tab)) {
            list.add(new ItemStack(self));
            list.add(new ItemStack(Items.KNOWLEDGE_BOOK));
            list.add(new ItemStack(Blocks.COMMAND_BLOCK));
            list.add(new ItemStack(Blocks.CHAIN_COMMAND_BLOCK));
            list.add(new ItemStack(Blocks.REPEATING_COMMAND_BLOCK));

        }
    }
}
