package me.monkeee.weaponGems.Handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GemItemHandler {

    public static List<String> DarkstoneItems = new ArrayList<>();
    public static List<String> DeflectionEyeItems = new ArrayList<>();
    public static List<String> DivanCoreItems = new ArrayList<>();
    public static List<String> JadeItems = new ArrayList<>();
    public static List<String> RubyItems = new ArrayList<>();
    public static List<String> SpiderFangItems = new ArrayList<>();
    public static List<String> LightStoneItems = new ArrayList<>();
    public static List<String> ShadowStoneItems = new ArrayList<>();
    public static List<String> AngelFeatherItems = new ArrayList<>();

    public static Map<String, List<String>> MapOfItems = new HashMap<>();

    public static void ApplyItemsToList() {
        DarkstoneItems.add("sword");
        DarkstoneItems.add("axe");
        DarkstoneItems.add("mace");

        DeflectionEyeItems.add("helmet");

        DivanCoreItems.add("pickaxe");
        DivanCoreItems.add("shovel");
        DivanCoreItems.add("axe");
        DivanCoreItems.add("hoe");
        DivanCoreItems.add("shears");

        JadeItems.add("chestplate");

        RubyItems.add("sword");
        RubyItems.add("axe");

        SpiderFangItems.add("sword");
        SpiderFangItems.add("axe");
        SpiderFangItems.add("mace");

        LightStoneItems.add("chestplate");

        ShadowStoneItems.add("boots");

        AngelFeatherItems.add("boots");

        MapOfItems = Map.of(
                "darkstone", DarkstoneItems,
                "deflection_exe", DeflectionEyeItems,
                "divan_core", DivanCoreItems,
                "jade", JadeItems,
                "ruby", RubyItems,
                "spider_fang", SpiderFangItems,
                "lightstone", LightStoneItems,
                "shadow_stone", ShadowStoneItems,
                "angel_feather", AngelFeatherItems
        );
    }
}
