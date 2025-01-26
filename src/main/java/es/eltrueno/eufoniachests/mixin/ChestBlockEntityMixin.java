package es.eltrueno.eufoniachests.mixin;


import es.eltrueno.eufoniachests.Eufoniachests;
import es.eltrueno.eufoniachests.interfaces.IChestBlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChestBlockEntity.class)
public class ChestBlockEntityMixin implements IChestBlockEntity {

    private boolean isUsed = false;

    @Inject(method = "writeNbt", at = @At("TAIL"))
    public void writeNbt(NbtCompound nbt, CallbackInfo ci) {
        if(isUsed)nbt.putBoolean(Eufoniachests.MOD_ID+":used", true);
    }

    @Inject(method = "readNbt", at = @At("TAIL"))
    private void readNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains(Eufoniachests.MOD_ID+":used")) {
            isUsed = true;
        }
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void markAsUsed() {
        isUsed = true;
    }
}