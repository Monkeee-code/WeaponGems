package me.monkeee.weaponGems.Handlers;

import me.monkeee.weaponGems.WeaponGems;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListHandler {

    public static List<String> getGemList() {
        File file = new File(WeaponGems.getInstance().getDataFolder(), "items.json");
        try {
            String content = Files.readString(file.toPath());
            JSONObject json = new JSONObject(content);
            return new ArrayList<>(json.keySet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> GetBetterList(List<String> list, String[] args, int argStage) {
        List<String> completions = null;
        String input = args[argStage];
        for (String s : list) {
            if (s.toLowerCase().startsWith(input) || s.toUpperCase().startsWith(input)) {
                if (completions == null) {
                    completions = new ArrayList<>();
                }
                completions.add(s);
            }
        }
        if (completions != null) Collections.sort(completions);
        return completions;
    }
}
