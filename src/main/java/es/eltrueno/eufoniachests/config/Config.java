package es.eltrueno.eufoniachests.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Config {

    private HashMap<String, List<String>> biomeLoot;

    public Config(){
        biomeLoot = new HashMap<String, List<String>>();
        biomeLoot.put("default", Arrays.asList("minecraft:chests/simple_dungeon"));
        biomeLoot.put("minecraft:snowy_plains", Arrays.asList("minecraft:chests/bastion_bridge", "minecraft:chests/ancient_city"));
    }

    public HashMap<String, List<String>> getBiomeLoot() {
        return biomeLoot;
    }

    public void setBiomeLoot(HashMap<String, List<String>> biomeLoot) {
        this.biomeLoot = biomeLoot;
    }
}
