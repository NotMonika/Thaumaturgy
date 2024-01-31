package just.monika.thaumaturgy.things.block.blocks;

import just.monika.thaumaturgy.Thaumaturgy;
import just.monika.thaumaturgy.things.power.EVE;
import just.monika.thaumaturgy.things.item.utils.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

//@Mod.EventBusSubscriber(modid = "thaumaturgy")
public class EVEConverter extends Block {
    public EVEConverter(){
        super(Material.IRON);
        setRegistryName(Thaumaturgy.modid,"converter").setTranslationKey("eve_converter");
    }

    @Override
    public boolean hasTileEntity(@Nonnull IBlockState blockState){
        return true;
    }
    @Override
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state){
        return new $TileEntity();
    }
    public static class $TileEntity extends TileEntity implements ITickable,EVE.EVEInterface{

        private ItemStackHandler inventory = new ItemStackHandler(1);

        @Override
        public void update() {
            addPower(world,pos,1);
        }
        @Override
        public boolean hasCapability(@Nonnull Capability<?> cap, EnumFacing facing) {
            return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(cap, facing);
        }

        @Override
        public <T> T getCapability(@Nonnull Capability<T> cap, EnumFacing facing) {
            if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new ItemHandler(inventory,new int[]{1},new int[]{}));
            }
            return super.getCapability(cap, facing);
        }
    }
}
