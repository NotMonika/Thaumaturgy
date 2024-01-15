package just.monika.thaumaturgy.items;

import just.monika.thaumaturgy.Thaumaturgy;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static just.monika.thaumaturgy.stage.Stage1.names_stage1;

@Mod.EventBusSubscriber(modid = "thaumaturgy")
public class Material {
    private static final List<Material> materials=new ArrayList<>();
    private final String translationName;
    public String name;
    private final Color color;
    private final Item ingot;
    private final Item nugget;
    private final Item gear;
    private final Block block;
    private final Item blockItem;

    public Material(String translationName,Color color){
        this(translationName,color,translationName);
    }

    public Material(String translationName,Color color,String name){
        this.translationName=translationName;
        this.name=name;
        this.color=color;
        this.ingot=new Item(){
            @Override
            public String  getItemStackDisplayName(ItemStack stack){
                return I18n.format("material.ingot",Material.this.name).trim();
            }
        }.setRegistryName(Thaumaturgy.modid,translationName+"_ingot")
                .setTranslationKey(Thaumaturgy.modid+"."+translationName+"_ingot");
        this.nugget=new Item(){
            @Override
            public String  getItemStackDisplayName(ItemStack stack){
                return I18n.format("material.nugget",Material.this.name).trim();
            }
        }.setRegistryName(Thaumaturgy.modid,translationName+"_nugget")
                .setTranslationKey(Thaumaturgy.modid+"."+translationName+"_nugget");
        this.block=new Block(net.minecraft.block.material.Material.IRON)
                .setRegistryName(Thaumaturgy.modid,translationName+"_block")
                .setTranslationKey(Thaumaturgy.modid+"."+translationName+"_block")
                .setCreativeTab(ItemRegister.CREATIVE_TAB);
        this.block.setHarvestLevel("pickaxe", 2);
        this.blockItem=new ItemBlock(this.block){
            @Override
            public String  getItemStackDisplayName(ItemStack stack){
                return I18n.format("material.block",Material.this.name).trim();
            }
        }.setRegistryName(Thaumaturgy.modid,translationName+"_block").setTranslationKey(Thaumaturgy.modid+"."+translationName+"_block").setCreativeTab(ItemRegister.CREATIVE_TAB);
        this.gear=new Item(){
            @Override
            public String  getItemStackDisplayName(ItemStack stack){
                return I18n.format("material.gear",Material.this.name).trim();
            }
        }.setRegistryName(Thaumaturgy.modid,translationName+"_gear")
                .setCreativeTab(ItemRegister.CREATIVE_TAB);
        materials.add(this);

    }

    public String getTranslationName() {
        return translationName;
    }
    public Color getColor() {
        return color;
    }

    public Item getIngot() {
        return ingot;
    }

    public Item getNugget() {
        return nugget;
    }

    public Block getBlock() {
        return block;
    }
    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        for(Material material:materials){
            event.getRegistry().registerAll(
                    material.ingot.setCreativeTab(ItemRegister.CREATIVE_TAB),
                    material.nugget.setCreativeTab(ItemRegister.CREATIVE_TAB),
                    material.blockItem.setCreativeTab(ItemRegister.CREATIVE_TAB),
                    material.gear.setCreativeTab(ItemRegister.CREATIVE_TAB)
            );
        }
    }
    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        for(Material material:materials){
            event.getRegistry().registerAll(material.block);
        }
    }
    @SubscribeEvent
    public static void registerRecipe(RegistryEvent.Register<IRecipe> event) {
        for(Material material:materials){

            event.getRegistry().register(new ShapelessRecipes(
                    Thaumaturgy.modid,
                    new ItemStack(material.blockItem),
                    Crafting.getI2B(material.ingot)
            ).setRegistryName(material.translationName+"_i2b"));
            event.getRegistry().register(new ShapelessRecipes(
                    Thaumaturgy.modid,
                    new ItemStack(material.ingot),
                    Crafting.getI2B(material.nugget)
            ).setRegistryName(material.translationName+"_n2i"));
            event.getRegistry().register(new ShapelessRecipes(
                    Thaumaturgy.modid,
                    new ItemStack(material.ingot,9),
                    Crafting.getB2I((material.blockItem))
            ).setRegistryName(material.translationName+"_b2i"));
            event.getRegistry().register(new ShapelessRecipes(
                    Thaumaturgy.modid,
                    new ItemStack(material.nugget,9),
                    Crafting.getB2I((material.ingot))
            ).setRegistryName(material.translationName+"_i2n"));
            event.getRegistry().register(new ShapedRecipes(
                    Thaumaturgy.modid,
                    9,9,
                    Crafting.getCraft(new ItemStack[]{
                            ItemStack.EMPTY, material.ingot.getDefaultInstance(),ItemStack.EMPTY,
                            material.ingot.getDefaultInstance(), Items.NETHERBRICK.getDefaultInstance(),material.ingot.getDefaultInstance(),
                            ItemStack.EMPTY, material.ingot.getDefaultInstance(),ItemStack.EMPTY
                    }),
                    material.gear.getDefaultInstance()
            ).setRegistryName(material.translationName+"_gear"));
        }
        event.getRegistry().register(new ShapelessRecipes(
                Thaumaturgy.modid,
                get("quprine").nugget.getDefaultInstance(),
                Crafting.getI2B(Item.getItemFromBlock((Blocks.IRON_BLOCK)))
        ).setRegistryName("iron2quprine"));
        for(int i=0;i< names_stage1.length-1;i++){
            event.getRegistry().register(new ShapelessRecipes(
                    Thaumaturgy.modid,
                    new ItemStack(get(names_stage1[i+1].toLowerCase()).nugget),
                    Crafting.getI2B(Item.getItemFromBlock((get(names_stage1[i].toLowerCase()).block)))
            ).setRegistryName(names_stage1[i].toLowerCase()+"2"+names_stage1[i+1].toLowerCase()));
        }
    }
    @SubscribeEvent
    public static void itemColors(ColorHandlerEvent.Item event) {
        for(Material material:materials) {
            event.getItemColors().registerItemColorHandler(
                    ((stack, tintIndex) -> material.color.getRGB()),
                    material.ingot,material.nugget,material.blockItem,material.gear
            );
        }

        BlockColors blockColorHandler = event.getBlockColors();
    }
    @SubscribeEvent
    public static void blockColors(ColorHandlerEvent.Block event) {
        for(Material material:materials) {
            event.getBlockColors().registerBlockColorHandler(
                    ((state, worldIn, pos, tintIndex) -> material.color.getRGB()),
                    material.block
            );
        }

        BlockColors blockColorHandler = event.getBlockColors();
    }
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelReg(ModelRegistryEvent event) {
        for(Material material:materials){
            ModelLoader.setCustomModelResourceLocation(material.ingot, 0, new ModelResourceLocation(Thaumaturgy.modid+":template/ingot", "inventory"));
            ModelLoader.setCustomModelResourceLocation(material.gear, 0, new ModelResourceLocation(Thaumaturgy.modid+":template/gear", "inventory"));
            ModelLoader.setCustomModelResourceLocation(material.nugget, 0, new ModelResourceLocation(Thaumaturgy.modid+":template/nugget", "inventory"));
            ModelLoader.setCustomStateMapper(material.block, new StateMapperBase() {
                @Override
                protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                    return new ModelResourceLocation(new ResourceLocation(Thaumaturgy.modid+":template/block"),"inventory");
                }
            });
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(material.block), 0, new ModelResourceLocation(new ResourceLocation(Thaumaturgy.modid+":template/block"), "inventory"));
        }
    }
    public static Material get(String translationName){
        for(Material material:materials){
            if(Objects.equals(material.translationName, translationName)){
                return material;
            }
        }
        return null;
    }
    static {
        for(String name: names_stage1){
            new Material(name.toLowerCase(),new Color(name.hashCode()),name);
        }
        new Material("netherite",Color.DARK_GRAY,"Netherite");
        new Material("telekill",new Color(0x98fb98),"Telekill Alloy");
    }

    public Item getBlockItem() {
        return blockItem;
    }
    @SubscribeEvent
    public static void onOreGen(OreGenEvent.Post event) {

    }
}


