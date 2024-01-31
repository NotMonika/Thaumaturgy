package just.monika.thaumaturgy.things.item;

import just.monika.thaumaturgy.Thaumaturgy;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemsGen {
    public static List<Item> itemList = new ArrayList<>();

    static {
        itemList.add(
                new Item()
                        .setRegistryName(Thaumaturgy.modid, "testitem")
                        .setTranslationKey("testitem")
                        .setCreativeTab(ItemRegister.CREATIVE_TAB)
                        .setMaxStackSize(0)
        );
        itemList.add(
                new Item() {
                    @Override
                    @SideOnly(Side.CLIENT)
                    public void addInformation(@Nonnull ItemStack p_77624_1_, @Nullable World p_77624_2_, @Nonnull List<String> p_77624_3_, @Nonnull ITooltipFlag p_77624_4_) {
                        p_77624_3_.add(I18n.format("information.fernflower"));
                    }
                }
                        .setRegistryName(Thaumaturgy.modid, "fernflower")
                        .setTranslationKey("fernflower")
                        .setCreativeTab(ItemRegister.CREATIVE_TAB)
        );
        itemList.add(
                new Item()
                        .setRegistryName(Thaumaturgy.modid, "powercore")
                        .setTranslationKey("powercore")
                        .setCreativeTab(ItemRegister.CREATIVE_TAB)
        );
        itemList.add(
                new Item()
                        .setRegistryName(Thaumaturgy.modid, "powercore_powered")
                        .setTranslationKey("powercore_powered")
                        .setCreativeTab(ItemRegister.CREATIVE_TAB)
        );
        itemList.add(
                new Item()
                        .setRegistryName(Thaumaturgy.modid, "lava_crystal")
                        .setTranslationKey("lava_crystal")
                        .setCreativeTab(ItemRegister.CREATIVE_TAB)
        );
        itemList.add(
                new ItemToolEX(114514, 0, Item.ToolMaterial.DIAMOND, new HashSet<>())
                        .setMaxDamage(0)
                        .setNoRepair()
                        .setRegistryName(Thaumaturgy.modid, "sword1")
                        .setTranslationKey("sword1")
                        .setCreativeTab(ItemRegister.CREATIVE_TAB)
        );
        itemList.add(
                new Item() {
                    @Override
                    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
                        p_77644_2_.setPosition(p_77644_3_.posX, -3, p_77644_3_.posZ);
                        return super.hitEntity(p_77644_1_, p_77644_2_, p_77644_3_);
                        //return false;
                    }
                }
                        .setRegistryName(Thaumaturgy.modid, "void_axe")
                        .setTranslationKey("void_axe")
                        .setCreativeTab(ItemRegister.CREATIVE_TAB)
                        .setMaxStackSize(1)
        );
    }

    static class ItemToolEX extends ItemTool {

        public ItemToolEX(float p_i46745_1_, float p_i46745_2_, ToolMaterial p_i46745_3_, Set<Block> p_i46745_4_) {
            super(p_i46745_1_, p_i46745_2_, p_i46745_3_, p_i46745_4_);
            attackDamage = p_i46745_1_;
        }
    }
}
