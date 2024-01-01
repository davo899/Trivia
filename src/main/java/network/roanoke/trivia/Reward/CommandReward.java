package network.roanoke.trivia.Reward;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.network.ServerPlayerEntity;
import network.roanoke.trivia.Trivia;

public class CommandReward extends Reward {

    private final String command;

    public CommandReward(String displayName, String command) {
        super(displayName);
        this.command = command;
    }

    @Override
    public void giveTo(ServerPlayerEntity player) {
        try {
            player.server.getCommandManager().getDispatcher().execute(
                command.replaceAll("%player%", player.getGameProfile().getName()),
                player.server.getCommandSource()
            );

        } catch (CommandSyntaxException e) {
            Trivia.LOGGER.error("Could not run: " + command);
            Trivia.LOGGER.error(e.getMessage());
        }
    }

}
