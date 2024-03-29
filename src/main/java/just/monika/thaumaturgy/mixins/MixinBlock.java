package just.monika.thaumaturgy.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.annotation.Nonnull;
import java.util.Random;

@Mixin(Block.class)
public abstract class MixinBlock {
    @Shadow
    private static void registerBlock(int p_176219_0_, String p_176219_1_, Block p_176219_2_) {
    }

    @Redirect(
            method = "registerBlocks()V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;registerBlock(ILjava/lang/String;Lnet/minecraft/block/Block;)V")
    )
    private static void redirectRegisterBlocks(int p_176219_0_, String p_176219_1_, Block p_176219_2_) {
        if (p_176219_1_.equals("bedrock")) {
            p_176219_2_ = new Block(Material.ROCK) {
                @Nonnull
                @Override
                public Item getItemDropped(IBlockState state, Random rand, int fortune) {
                    return Item.getItemFromBlock(this);
                }

                @Override
                public int quantityDropped(Random random) {
                    return 1;
                }

                @Override
                public int quantityDroppedWithBonus(int fortune, Random random) {
                    return 1;
                }

            }
                    .setResistance(6000000.0F)
                    .setSoundType(SoundType.STONE)
                    .setTranslationKey("bedrock")
                    .setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
                    .setHardness(100);
            p_176219_2_.setHarvestLevel("pickaxe", 2);
        }
        registerBlock(p_176219_0_, new ResourceLocation(p_176219_1_), p_176219_2_);
    }

    @Shadow
    private static void registerBlock(int p1762190, ResourceLocation resourceLocation, Block p1762192) {
    }
}
