package just.monika.thaumaturgy.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;

public class ItemHandler implements IItemHandler {
    private final ItemStackHandler inventory;
    private final int[] insertable;
    private final int[] extractable;

    public ItemHandler(ItemStackHandler inventory, int[] insertable, int[] extractable){

        this.inventory = inventory;
        this.insertable = insertable;
        this.extractable = extractable;
    }
    @Override
    public int getSlots() {
        return inventory.getSlots();
    }
    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory.getStackInSlot(slot);
    }
    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (ArrayUtils.contains(insertable,slot)) {
            return inventory.insertItem(slot, stack, simulate);
        }
        return stack;
    }
    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        // 仅当对 1 号槽位尝试提取物品时允许听过，否则拒绝提取
        if (ArrayUtils.contains(extractable,slot)) {
            return inventory.extractItem(slot, amount, simulate);
        }
        return ItemStack.EMPTY;
    }
    @Override
    public int getSlotLimit(int slot) {
        return inventory.getSlotLimit(slot);
    }
    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return false;
    }
}
