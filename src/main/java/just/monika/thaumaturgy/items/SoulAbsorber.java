package just.monika.thaumaturgy.items;

import just.monika.thaumaturgy.Thaumaturgy;
import just.monika.thaumaturgy.utils.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = "thaumaturgy")
public class SoulAbsorber extends Block {
    public static SoulAbsorber self = new SoulAbsorber();
    public SoulAbsorber(){
        super(Material.IRON);
        setRegistryName(Thaumaturgy.modid,"soulabsorber");
        setTranslationKey(Objects.requireNonNull(getRegistryName()).getPath());
        setCreativeTab(ItemRegister.CREATIVE_TAB);
    }

    @Override
    public boolean hasTileEntity(@Nonnull IBlockState state) {
        return true;
    }
    @Override
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        return new $TileEntity();
    }
    @SubscribeEvent
    public static void onBlockRegistration(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(self);
        GameRegistry.registerTileEntity($TileEntity.class, new ResourceLocation(Thaumaturgy.modid,"soulabsorber"));
    }
    @SubscribeEvent
    public static void registryItem(RegistryEvent.Register<Item> event){
        event.getRegistry().register(new ItemBlock(self).setRegistryName(Objects.requireNonNull(self.getRegistryName())));
    }
    @Override
    public boolean onBlockActivated(World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull EntityPlayer player, @Nonnull EnumHand hand, @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof $TileEntity && !world.isRemote) {
            if(player.getHeldItem(hand).getItem() == ItemRegister.entitySoul){
                EntityItem entityItem = new EntityItem(world);
                entityItem.setItem((($TileEntity) tileEntity).inventory.getStackInSlot(0));
                entityItem.posX=pos.getX();
                entityItem.posY=pos.getY()+1;
                entityItem.posZ=pos.getZ();
                world.spawnEntity(entityItem);
                (($TileEntity) tileEntity).inventory.setStackInSlot(0,
                        new ItemStack(player.getHeldItem(hand).getItem(),1,player.getHeldItem(hand).getItemDamage()));
                player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount()-1);
                return true;
            }
        }
        return false;
    }
    public static class $TileEntity extends TileEntity implements ITickable {

        /*
        * 0:target
        * 1:generated
        */
        private final ItemStackHandler inventory = new ItemStackHandler(2);
        private int progress = 0;
        @Override
        public void update() {
            if(world.isRemote) return;
            if(world.isDaytime()) return;
            int level = 0;
            if (!inventory.getStackInSlot(0).isEmpty()) {
                level = inventory.getStackInSlot(0).getItemDamage();
            }
            progress++;
            if(world.isRaining()) progress++;
            if(progress>=20*20){
                progress=0;
                if (inventory.getStackInSlot(1).isEmpty() && RANDOM.nextBoolean()){
                    inventory.insertItem(1,new ItemStack(ItemRegister.entitySoul,1,level),false);
                    return;
                }
                inventory.getStackInSlot(1).setCount(inventory.getStackInSlot(1).getCount()+1);
            }
        }
        @Override
        public boolean hasCapability(@Nonnull Capability<?> cap, EnumFacing facing) {
            return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(cap, facing);
        }

        @Override
        public <T> T getCapability(@Nonnull Capability<T> cap, EnumFacing facing) {
            if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(new ItemHandler(inventory,new int[]{},new int[]{1}));
            }
                return super.getCapability(cap, facing);
            }

        @Override
        public void readFromNBT(@Nonnull NBTTagCompound tag) {
            super.readFromNBT(tag);
            this.inventory.deserializeNBT(tag.getCompoundTag("Inventory"));
        }

        @Nonnull
        @Override
        public NBTTagCompound writeToNBT(NBTTagCompound tag) {
            tag.setTag("Inventory", this.inventory.serializeNBT());
            return super.writeToNBT(tag);
        }
    }
}
