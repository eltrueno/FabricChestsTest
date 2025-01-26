package es.eltrueno.eufoniachests;

import es.eltrueno.eufoniachests.config.Config;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.List;

public class ChestLootManager {

    /*Metodo que devuelve directamente un identificador de una loot table*/
    public static Identifier getLootFromBiome(Identifier biomeId){
        Config cfg = Eufoniachests.getConfig();
        /*Se obtiene del objeto directamente mapeado de la config la lista de strings asociadas a esa key (bioma)

            Si no se obtiene nada (es nulo) se asignara la lista por defecto
         */
        List<String> lootTables = cfg.getBiomeLoot().get(biomeId.toString());
        if(lootTables==null){
            lootTables = cfg.getBiomeLoot().get("default");
        }
        //Se comprueba si hay más de un item en la lsita y si es asi se obtiene uno aleatorio
        if(lootTables.size()>1){
            Collections.shuffle(lootTables);
        }
        String selectedLootTable = lootTables.get(0);
        //Separamos el namespace
        String[] splittedTable = selectedLootTable.split(":");
        return new Identifier(splittedTable[0], splittedTable[1]);
    }


}
