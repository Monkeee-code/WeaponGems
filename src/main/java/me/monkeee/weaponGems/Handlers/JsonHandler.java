package me.monkeee.weaponGems.Handlers;

import me.monkeee.weaponGems.WeaponGems;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;

public class JsonHandler {


    public static String String_reader(String gem, String path) {
        File file = new File(WeaponGems.getInstance().getDataFolder(), "items.json");
        try {
            String content = Files.readString(file.toPath());
            JSONObject jsonContent = new JSONObject(content);
            JSONObject gemObject = jsonContent.getJSONObject(gem);
            return gemObject.getString(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONArray ListReader(String gem) {
        File file = new File(WeaponGems.getInstance().getDataFolder(), "items.json");
        try {
            String content = Files.readString(file.toPath());
            JSONObject jsonObject = new JSONObject(content);
            JSONObject gemObject = jsonObject.getJSONObject(gem);
            return gemObject.getJSONArray("lore");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int SpawnChanceReader(String gem, String path) {
        File file = new File(WeaponGems.getInstance().getDataFolder(), "items.json");
        try {
            String content = Files.readString(file.toPath());
            JSONObject jsonObject = new JSONObject(content);
            JSONObject gemObject = jsonObject.getJSONObject(gem);
            JSONObject spawnObject = gemObject.getJSONObject("spawnrate");
            return spawnObject.getInt(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
