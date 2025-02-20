package charble.betterminingenchants.attribute;

import charble.betterminingenchants.BetterMiningEnchants;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModAttributes {

    public static final RegistryEntry<EntityAttribute> PLAYER_OBSIDIAN_MINING_EFFICIENCY =
            register("player.obsidian_mining_efficiency", new ClampedEntityAttribute("attribute.name.player.obsidian_mining_efficiency", 0.0f, 0.0f, 1024.0f).setTracked(true));


    private static RegistryEntry<EntityAttribute> register(String name, EntityAttribute attribute)
    {
        return Registry.registerReference(Registries.ATTRIBUTE, Identifier.of(BetterMiningEnchants.MOD_ID, name), attribute);
    }
}
