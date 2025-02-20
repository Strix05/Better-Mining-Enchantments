package charble.betterminingenchants.enchantment;

import charble.betterminingenchants.BetterMiningEnchants;
import charble.betterminingenchants.attribute.ModAttributes;
import charble.betterminingenchants.util.ModTags;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.AttributeEnchantmentEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ModEnchantments {


    public static final RegistryKey<Enchantment> OBSIDIAN_EFFICIENCY =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(BetterMiningEnchants.MOD_ID, "obsidian_efficiency"));

    public static final RegistryKey<Enchantment> SMELTING =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(BetterMiningEnchants.MOD_ID, "smelting"));

    public static final RegistryKey<Enchantment> VEINING =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(BetterMiningEnchants.MOD_ID, "veining"));

    public static void bootstrap(Registerable<Enchantment> registerable){
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, OBSIDIAN_EFFICIENCY, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ItemTags.PICKAXES),
                items.getOrThrow(ItemTags.PICKAXES),
                3,
                3,
                Enchantment.leveledCost(6, 3),
                Enchantment.leveledCost(18, 9),
                6,
                AttributeModifierSlot.MAINHAND))
                .addEffect(
                        EnchantmentEffectComponentTypes.ATTRIBUTES,
                        new AttributeEnchantmentEffect(
                                Identifier.of(BetterMiningEnchants.MOD_ID, "obsidian_efficiency"),
                                ModAttributes.PLAYER_OBSIDIAN_MINING_EFFICIENCY,
                                new EnchantmentLevelBasedValue.LevelsSquared(1.0f),
                                EntityAttributeModifier.Operation.ADD_VALUE
                        )
                ));

        register(registerable, SMELTING, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ItemTags.PICKAXES),
                items.getOrThrow(ItemTags.PICKAXES),
                5,
                1,
                Enchantment.constantCost(12),
                Enchantment.constantCost(18),
                6,
                AttributeModifierSlot.MAINHAND))
                .exclusiveSet(enchantments.getOrThrow(ModTags.Enchantments.BETTER_MINING_EXCLUSIVE))
        );
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }

}
