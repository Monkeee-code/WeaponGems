package me.monkeee.weaponGems.Handlers;

import java.util.ArrayList;
import java.util.List;

public class GemItemHandler {

    public static List<String> DarkstoneItems = new ArrayList<>();
    public static List<String> DeflectionEyeItems = new ArrayList<>();
    public static List<String> DivanCoreItems = new ArrayList<>();
    public static List<String> JadeItems = new ArrayList<>();
    public static List<String> RubyItems = new ArrayList<>();
    public static List<String> SpiderFangItems = new ArrayList<>();

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
    }
}
