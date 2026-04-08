package me.monkeee.weaponGems.API;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.*;

public class GemDefinition {

    private final String id;
    private final String displayName;
    private final Material material;
    private final List<String> lore;
    private final List<String> applicableItems;
    private final List<NamespacedKey> lootTables;
    private final double spawnChance;

    private final GemTickingAbility tickingAbility;

    private final GemDamageAbility damageAbility;

    private final GemDealerAbility dealerAbility;

    private GemDefinition(Builder b) {
        this.id = b.id;
        this.displayName = b.displayName;
        this.material = b.material;
        this.lore = Collections.unmodifiableList(new ArrayList<>(b.lore));
        this.applicableItems = Collections.unmodifiableList(new ArrayList<>(b.applicableItems));

        this.spawnChance = b.spawnChance;
        this.lootTables = Collections.unmodifiableList(new ArrayList<>(b.lootTables));
        this.tickingAbility = b.tickingAbility;
        this.damageAbility = b.damageAbility;
        this.dealerAbility = b.dealerAbility;
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public String getID() { return id; }
    public String getDisplayName() { return displayName; }
    public Material getMaterial() { return material; }
    public List<String> getLore() { return lore; }
    public List<String> getApplicableItems() { return applicableItems; }
    public double getSpawnChance() { return spawnChance; }
    public GemTickingAbility getTickingAbility() { return tickingAbility; }
    public GemDamageAbility getDamageAbility() { return damageAbility; }
    public GemDealerAbility getDealerAbility() { return dealerAbility; }
    public List<NamespacedKey> getLootTables() { return lootTables; }

    public static class Builder {
        private final String id;
        private String displayName = "Unnamed Gem";
        private Material material = Material.AMETHYST_SHARD;
        private final List<String> lore = new ArrayList<>();
        private final List<String> applicableItems = new ArrayList<>();
        private double spawnChance = 0.0;
        private GemTickingAbility tickingAbility;
        private GemDamageAbility damageAbility;
        private GemDealerAbility dealerAbility;
        private List<NamespacedKey> lootTables = new ArrayList<>();

        private Builder(String id) {
            this.id = Objects.requireNonNull(id, "Gem ID cannot be null").toLowerCase();
        }

        /** The colored display name (supports & color codes). */
        public Builder name(String displayName) {
            this.displayName = displayName;
            return this;
        }

        /** The item material used to represent this gem. */
        public Builder material(Material material) {
            this.material = material;
            return this;
        }

        /** Lore lines (supports & color codes). Call multiple times or pass varargs. */
        public Builder lore(String... lines) {
            this.lore.addAll(Arrays.asList(lines));
            return this;
        }

        /** Item type suffixes this gem can be applied to (e.g. "sword", "chestplate", "axe"). */
        public Builder applicableTo(String... itemSuffixes) {
            this.applicableItems.addAll(Arrays.asList(itemSuffixes));
            return this;
        }

        /** Chance (0.0–1.0) for this gem to appear in loot tables. */
        public Builder spawnChance(double chance) {
            this.spawnChance = chance;
            return this;
        }

        /**
         * Ability that runs every tick cycle (10 server ticks) for each online player.
         * Use for passive/permanent effects like Jade's Reinforcement.
         */
        public Builder tickingAbility(GemTickingAbility ability) {
            this.tickingAbility = ability;
            return this;
        }

        /**
         * Ability that fires when the player wearing the gem takes damage.
         * Use for defensive reactions like ShadowStone's Last Echo.
         */
        public Builder damageAbility(GemDamageAbility ability) {
            this.damageAbility = ability;
            return this;
        }

        /**
         * Ability that fires when the player holding the gem deals damage to another player.
         * Use for offensive procs like Darkstone's Sight Drain.
         */
        public Builder dealerAbility(GemDealerAbility ability) {
            this.dealerAbility = ability;
            return this;
        }

        public Builder lootTables(NamespacedKey... Keys) {
            this.lootTables.addAll(Arrays.asList(Keys));
            return this;
        }

        public GemDefinition build() {
            Objects.requireNonNull(material, "Gem material cannot be null");
            if (id.isBlank()) throw new IllegalArgumentException("Gem ID cannot be blank");
            return new GemDefinition(this);
        }
    }
}


