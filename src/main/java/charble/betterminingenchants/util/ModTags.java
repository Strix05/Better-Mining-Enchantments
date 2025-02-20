package charble.betterminingenchants.util;

import charble.betterminingenchants.BetterMiningEnchants;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    //Block Tags
    public static class Blocks
    {
        public static final TagKey<Block> OBSIDIAN_EFFICIENCY_MINEABLE = createTag("obsidian_efficiency_mineable");

        public static TagKey<Block> createTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(BetterMiningEnchants.MOD_ID, name));
        }
    }

    //Item Tags
    public static class Items
    {
        public static final TagKey<Item> SMELTING_DROP = createTag("smelting_drop");

        public static TagKey<Item> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(BetterMiningEnchants.MOD_ID, name));
        }
    }

    //Enchantment Tags
    public static class Enchantments
    {
        public static final TagKey<Enchantment> BETTER_MINING_EXCLUSIVE = createTag("exclusive_set/better_mining_exclusive");

        public static TagKey<Enchantment> createTag(String name)
        {
            return TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(BetterMiningEnchants.MOD_ID, name));
        }
    }
}
