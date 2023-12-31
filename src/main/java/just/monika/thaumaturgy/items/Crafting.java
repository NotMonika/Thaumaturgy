package just.monika.thaumaturgy.items;

import just.monika.thaumaturgy.Thaumaturgy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = "thaumaturgy")
public class Crafting {
    public static NonNullList<Ingredient> getI2B(Item item){
        NonNullList<Ingredient> ingredients=NonNullList.create();
        for(int ignored : new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}) ingredients.add(Ingredient.fromItems(item));
        return ingredients;
    }
    public static NonNullList<Ingredient> getI2B(ItemStack item){
        NonNullList<Ingredient> ingredients=NonNullList.create();
        for(int ignored : new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}) ingredients.add(Ingredient.fromStacks(item));
        return ingredients;
    }
    public static NonNullList<Ingredient> getB2I(Item item){
        NonNullList<Ingredient> ingredients=NonNullList.create();
        ingredients.add(Ingredient.fromItems(item));
        return ingredients;
    }
    @SubscribeEvent
    public static void registerRecipe(RegistryEvent.Register<IRecipe> event){
        for(int i=0;i<6;i++){

            event.getRegistry().register(new ShapelessRecipes(
                    Thaumaturgy.modid,
                    new ItemStack(ItemRegister.entitySoul,1,i+1),
                    Crafting.getI2B(new ItemStack(ItemRegister.entitySoul,1,i))
            ).setRegistryName("entitySoul_"+i));

        }
    }
}
