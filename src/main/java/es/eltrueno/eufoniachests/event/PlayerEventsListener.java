package es.eltrueno.eufoniachests.event;

import es.eltrueno.eufoniachests.ChestLootManager;
import es.eltrueno.eufoniachests.interfaces.IChestBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class PlayerEventsListener {

    public ActionResult useChest(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult){
        if(!player.isSpectator()){
            BlockState block = world.getBlockState(hitResult.getBlockPos());
            if(block.getBlock() instanceof ChestBlock){
                /*Casteamos a nuestra interfaz custom del mixin*/
                ChestBlockEntity cbe = (ChestBlockEntity) world.getBlockEntity(hitResult.getBlockPos());
                IChestBlockEntity icbe = (IChestBlockEntity) cbe;
                /*Comprobamos si esta marcado como used*/
                if(!icbe.isUsed()){
                    /*Obtenemos el Id del bioma*/
                    Biome biome = world.getBiome(hitResult.getBlockPos()).value();
                    Identifier biomeId = world.getRegistryManager().get(RegistryKeys.BIOME).getId(biome);

                    /*Llamamos a nuestro metodo que devuelve el id de una loot chest*/
                    Identifier lootId = ChestLootManager.getLootFromBiome(biomeId);
                    //Identifier lootId = new Identifier("minecraft", "chests/simple_dungeon");

                    /*Aplicamos el la loot table y marcamos el cofre como used*/
                    cbe.setLootTable(lootId, world.random.nextLong());
                    icbe.markAsUsed();
                }
            }
        }
        return ActionResult.PASS;
    }

}
