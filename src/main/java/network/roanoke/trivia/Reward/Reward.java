package network.roanoke.trivia.Reward;

import net.minecraft.server.network.ServerPlayerEntity;

public abstract class Reward {

    private final String displayName;

    public Reward(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public abstract void giveTo(ServerPlayerEntity player);

}
