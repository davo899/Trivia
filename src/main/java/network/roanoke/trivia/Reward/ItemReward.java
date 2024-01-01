package network.roanoke.trivia.Reward;

import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import network.roanoke.trivia.Trivia;

public class ItemReward extends Reward {

    public String itemName;
    public Integer quantity;
    public ItemStack itemStack;

    public ItemReward(String displayName, String itemName, Integer quantity) {
        super(displayName);
        this.itemName = itemName;
        this.quantity = quantity;
        this.itemStack = new ItemStack(Registries.ITEM.get(new Identifier(itemName)), quantity);

        Trivia.LOGGER.info("Reward item: " + displayName + " - itemName : " + itemName + " ItemStack: " + itemStack);
    }

    @Override
    public void giveTo(ServerPlayerEntity player) {
        if (!player.giveItemStack(itemStack.copy())) player.dropItem(itemStack.copy(), false);
    }

}
