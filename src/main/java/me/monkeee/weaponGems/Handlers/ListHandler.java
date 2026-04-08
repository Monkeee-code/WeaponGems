package me.monkeee.weaponGems.Handlers;

import me.monkeee.weaponGems.API.GemRegistry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListHandler {

    /**
     * Returns all registered gem IDs — built-in and addon gems alike.
     */
    public static List<String> getGemList() {
        return new ArrayList<>(GemRegistry.getAllIDs());
    }

    public static List<String> GetBetterList(List<String> list, String[] args, int argStage) {
        if (argStage >= args.length) return List.of();
        List<String> completions = null;
        String input = args[argStage].toLowerCase();
        for (String s : list) {
            if (s.toLowerCase().startsWith(input)) {
                if (completions == null) {
                    completions = new ArrayList<>();
                }
                completions.add(s);
            }
        }
        if (completions != null) Collections.sort(completions);
        return completions != null ? completions : List.of();
    }
}