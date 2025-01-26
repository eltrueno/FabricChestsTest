package es.eltrueno.eufoniachests.mixin;

import es.eltrueno.eufoniachests.interfaces.IChestBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChestBlock.class)
public class ChestBlockMixin {
    /*Escuchamos al evento de cuando un cofre es puesto en el mundo*/
    @Inject(method = "onPlaced", at = @At("HEAD"))
    public void onChestPlaced(World world, BlockPos pos, BlockState state, LivingEntity entity, ItemStack itemStack, CallbackInfo ci) {
        if(entity instanceof ServerPlayerEntity player){
            GameMode gameMode = player.interactionManager.getGameMode();
            /*Comprobamos si esta en survival y si lo esta lo marcamos como used*/
            if(gameMode==GameMode.SURVIVAL){
                /*Casteamos a nuestra interfaz custom del mixin*/
                ChestBlockEntity cbe = (ChestBlockEntity) world.getBlockEntity(pos);
                IChestBlockEntity icbe = (IChestBlockEntity) cbe;
                icbe.markAsUsed();
            }
        }
    }
}