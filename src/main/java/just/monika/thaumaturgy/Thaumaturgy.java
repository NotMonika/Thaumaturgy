package just.monika.thaumaturgy;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEmptyDrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Mod(
        modid = Thaumaturgy.modid,
        name = "Thaumaturgy",
        version = "0.0.0-dev",
        useMetadata = true
)
public class Thaumaturgy implements IEarlyMixinLoader, IFMLLoadingPlugin
{
    public static Thaumaturgy INSTANCE = new Thaumaturgy();
    public Thaumaturgy(){

    }
    public static final String modid = "thaumaturgy";
    @Mod.InstanceFactory
    public static Thaumaturgy getInstance(){
        FluidRegistry.enableUniversalBucket();
        return INSTANCE;
    }


    @Override
    public String[] getASMTransformerClass() {return new String[0];}

    @Override
    public String getModContainerClass() {return null;}

    @Nullable
    @Override
    public String getSetupClass() {return null;}

    @Override
    public void injectData(Map<String, Object> map) {}

    @Override
    public String getAccessTransformerClass() {return null;}

    @Override
    public List<String> getMixinConfigs() {
        return List.of("mixins.thaumaturgy.json");
    }
}
