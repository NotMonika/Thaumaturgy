package just.monika.thaumaturgy;

import just.monika.thaumaturgy.things.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Mod.EventBusSubscriber(modid = Thaumaturgy.modid)
public class OreGen {
    @SubscribeEvent
    public static void onOreGen(OreGenEvent.Post event) {
        generateOre(Material.get("quprine").getBlock().getDefaultState()
                , 10, 9, 32, 80,
                event.getWorld(), event.getRand(), event.getPos());
    }

    public static void generateOre(IBlockState state, int size, int count, int minY, int maxY, World world, Random random, BlockPos chunkPos) {
        for (int i = 0; i < count; i++) {
            BlockPos pos = chunkPos.add(random.nextInt(16), minY + random.nextInt(maxY - minY), random.nextInt(16));
            new WorldGenMinable(state, size).generate(world, random, pos);
        }
    }
}
