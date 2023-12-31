package just.monika.thaumaturgy.items;

import just.monika.thaumaturgy.Thaumaturgy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = "thaumaturgy")
public class BasicMachine extends Block {
    static BasicMachine self = new BasicMachine();
    public BasicMachine() {
        super(Material.IRON);
        setCreativeTab(ItemRegister.CREATIVE_TAB);
        setRegistryName(Thaumaturgy.modid,"basic_machine");
        setTranslationKey(Thaumaturgy.modid+".basicMachine");
    }
    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new MachineTileEntity();
    }
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof MachineTileEntity && !world.isRemote) {
            player.setHeldItem(hand,
                    ((MachineTileEntity)tile).onRightClick(player.getHeldItem(hand),player,
                    pos.getX(),pos.getY(),pos.getZ()));
            return true;
        }
        return false;
    }
    @SubscribeEvent
    public static void registrySelf(RegistryEvent.Register<Block> event){
        event.getRegistry().register(self);
        GameRegistry.registerTileEntity(MachineTileEntity.class, new ResourceLocation(Thaumaturgy.modid, "basic_machine"));

    }

    public static class MachineTileEntity extends TileEntity{

        private int fuel;
        @Override
        public void readFromNBT(NBTTagCompound tag) {
            super.readFromNBT(tag);
            this.fuel = tag.getInteger("Fuel");
        }

        @Override
        public NBTTagCompound writeToNBT(NBTTagCompound tag) {
            tag.setInteger("Fuel", this.fuel);
            return super.writeToNBT(tag);
        }
        public ItemStack onRightClick(ItemStack stack,EntityPlayer player,int x,int y, int z) {
            if (stack.getItem() == Items.LAVA_BUCKET) {
                this.fuel += 1000;
                return !player.isCreative()?(new ItemStack(Items.BUCKET)):stack;
            } else if ((stack.getItem() == just.monika.thaumaturgy.items.Material.get("uflatium").getBlockItem()) && fuel >=100 ) {
                this.fuel -= 100;
                player.getHeldItem(EnumHand.MAIN_HAND).setCount(player.getHeldItem(EnumHand.MAIN_HAND).getCount()-1);
                world.spawnEntity(new EntityItem(world,x,y+1,z,new ItemStack(just.monika.thaumaturgy.items.Material.get("netherite").getNugget())));
                return stack;
            } else {
                return stack;
            }
        }

        public int getFuel() {
            return this.fuel;
        }

    }
}
