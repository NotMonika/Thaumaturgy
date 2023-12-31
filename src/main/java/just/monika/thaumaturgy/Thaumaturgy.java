package just.monika.thaumaturgy;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventBus;

@Mod(
        modid = Thaumaturgy.modid,
        name = "Thaumaturgy",
        version = "0.0.0-dev",
        useMetadata = true
)
public enum Thaumaturgy {
    INSTANCE;
    Thaumaturgy(){

    }
    public static final String modid = "thaumaturgy";
    @Mod.InstanceFactory
    public static Thaumaturgy getInstance(){
        return INSTANCE;
    }


    static{
        FluidRegistry.enableUniversalBucket();
    }
}
