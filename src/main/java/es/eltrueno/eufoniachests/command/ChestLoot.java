package es.eltrueno.eufoniachests.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import es.eltrueno.eufoniachests.Eufoniachests;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.io.IOException;

public class ChestLoot {

    public static void registerCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            LiteralArgumentBuilder<ServerCommandSource> chestlootcmd = CommandManager.literal("chestloot")
                    .then(CommandManager.literal("reload")
                            .requires(source -> source.hasPermissionLevel(2))
                            .executes(context -> {
                                /*LLamamos al metodo para (re)cargar la config*/
                                try {
                                    Eufoniachests.loadConfig();
                                    context.getSource().sendFeedback(Text.literal("La configuracion del loot ha sido recargada correctamente"), false);
                                } catch (IOException e) {
                                    Eufoniachests.LOGGER.error("Ha ocurrido un error cargando la configuración");
                                    throw new RuntimeException(e);
                                }

                                return Command.SINGLE_SUCCESS;
                            }));

            dispatcher.register(chestlootcmd);
        });
    }

}
