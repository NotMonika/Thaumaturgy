package just.monika.thaumaturgy;

import just.monika.thaumaturgy.items.ItemRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemsGen {
    public static List<Item> itemList = new ArrayList<>();

    static {
        itemList.add(
                new Item()
                        .setRegistryName(Thaumaturgy.modid,"testitem")
                        .setTranslationKey("testitem")
                        .setCreativeTab(ItemRegister.CREATIVE_TAB)
        );
        itemList.add(
                new Item(){
                    @Override
                    @SideOnly(Side.CLIENT)
                    public void addInformation(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, @Nonnull List<String> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
                        p_77624_3_.add(I18n.format("information.fernflower"));
                    }
                }
                        .setRegistryName(Thaumaturgy.modid,"fernflower")
                        .setTranslationKey("fernflower")
                        .setCreativeTab(ItemRegister.CREATIVE_TAB)
        );
        itemList.add(
                new Item()
                        .setRegistryName(Thaumaturgy.modid,"powercore")
                        .setTranslationKey("powercore")
                        .setCreativeTab(ItemRegister.CREATIVE_TAB)
        );
    }
}
