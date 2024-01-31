package just.monika.thaumaturgy.things.craftings;

import just.monika.thaumaturgy.Thaumaturgy;
import just.monika.thaumaturgy.things.Material;
import just.monika.thaumaturgy.things.item.ItemRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static just.monika.thaumaturgy.things.fluids.Garcinol.selfStack;

@Mod.EventBusSubscriber(modid = "thaumaturgy")
public class Crafting {
    @GameRegistry.ObjectHolder("thaumaturgy:powercore")
    public static Item PC;
    @GameRegistry.ObjectHolder("thaumaturgy:powercore_powered")
    public static Item PCP;


    public static NonNullList<Ingredient> getI2B(Item item) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        for (int ignored : new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}) ingredients.add(Ingredient.fromItems(item));
        return ingredients;
    }

    public static NonNullList<Ingredient> getI2B(ItemStack item) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        for (int ignored : new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}) ingredients.add(Ingredient.fromStacks(item));
        return ingredients;
    }

    public static NonNullList<Ingredient> getB2I(Item item) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(Ingredient.fromItems(item));
        return ingredients;
    }

    public static NonNullList<Ingredient> getCraft(ItemStack[] stack) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        for (ItemStack itemStack : stack) {
            ingredients.add((itemStack != null) ? Ingredient.fromStacks(itemStack) : Ingredient.EMPTY);
        }
        return ingredients;
    }

    @SubscribeEvent
    public static void registerRecipe(RegistryEvent.Register<IRecipe> event) {
        for (int i = 0; i < 6; i++) {

            event.getRegistry().register(new ShapelessRecipes(
                    Thaumaturgy.modid,
                    new ItemStack(ItemRegister.entitySoul, 1, i + 1),
                    Crafting.getI2B(new ItemStack(ItemRegister.entitySoul, 1, i))
            ).setRegistryName("entitySoul_" + i));
        }
        GameRegistry.addSmelting(Blocks.BEDROCK, Material.get("bedrockium").getIngot().getDefaultInstance(), 10);
        BrewingRecipeRegistry.addRecipe(new ItemStack(PC), new ItemStack(ItemRegister.entitySoul), new ItemStack(PCP));

        BrewingRecipeRegistry.addRecipe(new ItemStack(Items.MILK_BUCKET), new ItemStack(Items.GOLDEN_APPLE, 1, 1), selfStack);
    }
}
