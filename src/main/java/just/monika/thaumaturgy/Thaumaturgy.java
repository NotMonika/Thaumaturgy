package just.monika.thaumaturgy;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

@Mod(
        modid = Thaumaturgy.modid,
        name = "Thaumaturgy",
        version = "0.0.0-dev",
        useMetadata = true
)
public class Thaumaturgy implements IEarlyMixinLoader, IFMLLoadingPlugin
{
    public static Thaumaturgy INSTANCE = new Thaumaturgy();

    //public static DimensionType myDim;
    public Thaumaturgy(){

    }
    public static final String modid = "thaumaturgy";
    //public static int dimID = new Random(modid.hashCode()).nextInt(Short.MAX_VALUE);
    @Mod.InstanceFactory
    public static Thaumaturgy getInstance(){
        FluidRegistry.enableUniversalBucket();
        //Items.KNOWLEDGE_BOOK.setCreativeTab(ItemRegister.CREATIVE_TAB);
        return INSTANCE;
    }

    @Mod.EventHandler
    public void preInit(FMLPostInitializationEvent event) {

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
