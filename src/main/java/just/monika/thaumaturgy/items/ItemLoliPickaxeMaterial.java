package just.monika.thaumaturgy.items;

import just.monika.thaumaturgy.Thaumaturgy;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoliPickaxeMaterial extends Item {

    private final int subCount;
    private final String name;

    public ItemLoliPickaxeMaterial(String name, int subCount, boolean differentEnd) {
        this.name=name;
        this.subCount = subCount;
        this.setCreativeTab(ItemRegister.CREATIVE_TAB);
        this.setHasSubtypes(subCount != 1);
        if (differentEnd) {
            this.addPropertyOverride(new ResourceLocation(Thaumaturgy.modid, "end"), (stack, world, entity) -> stack.getItemDamage() == this.subCount - 1 ? 1 : 0);
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            for (int i = 0; i < subCount; i++) {
                ItemStack stack = new ItemStack(this, 1, i);
                items.add(stack);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack) {
        if (getHasSubtypes()) {
            return I18n.format("item.loliMaterialFormat", I18n.format(name + ".name")," x"+(stack.getItemDamage()+1));
        } else {
            return super.getItemStackDisplayName(stack);
        }
    }


}