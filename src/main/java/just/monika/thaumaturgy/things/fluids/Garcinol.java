package just.monika.thaumaturgy.things.fluids;

import just.monika.thaumaturgy.Thaumaturgy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.awt.*;

@Mod.EventBusSubscriber(modid = "thaumaturgy")
public class Garcinol extends Fluid {
    public static Garcinol self = new Garcinol();
    @GameRegistry.ItemStackHolder(value = "forge:bucketfilled", meta = 0, nbt = "{FluidName:\"garcinol\",Amount: 1000}")
    public static ItemStack selfStack = null;

    public Garcinol() {
        super("garcinol",
                new ResourceLocation(Thaumaturgy.modid + ":blocks/fluid_still"),
                new ResourceLocation(Thaumaturgy.modid + ":blocks/fluid_flow"),
                Color.yellow);
        setGaseous(true).setDensity(Integer.MAX_VALUE);

    }

    @SubscribeEvent
    public static void registrySelf(RegistryEvent.Register<Block> event) {
        FluidRegistry.registerFluid(self);
        FluidRegistry.addBucketForFluid(self);
        event.getRegistry().register(new BlockFluidClassic(self, Material.WATER).setRegistryName(Thaumaturgy.modid, self.getName()));

    }

    @SubscribeEvent
    public static void regFluidSpirit(TextureStitchEvent.Pre event) {
        TextureMap textureMap = event.getMap();
        textureMap.registerSprite(self.getFlowing());
        textureMap.registerSprite(self.getStill());
    }

    @SubscribeEvent
    public static void regModel(ModelRegistryEvent event) {
        ModelLoader.setCustomStateMapper(self.getBlock(), new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(new ResourceLocation(Thaumaturgy.modid, "garcinol"), "garcinol");
            }
        });
    }
}
