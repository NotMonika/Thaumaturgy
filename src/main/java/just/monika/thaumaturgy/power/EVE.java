package just.monika.thaumaturgy.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class EVE { //反正用这个也不会去脱离这个mod
    public static long getPower(NBTTagCompound nbt){
        return nbt.getLong("EVE");
    }
    public static void setPower(NBTTagCompound nbt,long value){
        nbt.setLong("EVE",value);
    }
    public static void addPower(NBTTagCompound nbt,long value){
        setPower(nbt,getPower(nbt)+value);
    }
    public static long getPower(TileEntity tileEntity){
        return getPower(tileEntity.getTileData());
    }
    public static void setPower(TileEntity tileEntity,long value){
        setPower(tileEntity.getTileData(),value);
    }
    public static void addPower(TileEntity tileEntity,long value){
        addPower(tileEntity.getTileData(),value);
    }
    public interface EVEInterface{
        default long getPower(World world, BlockPos pos){
            return EVE.getPower(Objects.requireNonNull(world.getTileEntity(pos)));
        }
        default void setPower(World world, BlockPos pos, long value){
            EVE.setPower(Objects.requireNonNull(world.getTileEntity(pos)),value);
        }
        default void addPower(World world, BlockPos pos, long value){
            EVE.addPower(Objects.requireNonNull(world.getTileEntity(pos)),value);
        }
    }
}
