package network.roanoke.trivia.Reward;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class RewardFactory {

    public Reward fromJson(JsonElement jsonElement) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String displayName = jsonObject.get("display_name").getAsString();
        return switch (jsonObject.get("type").getAsString()) {
            case "item" -> {
                String itemName = jsonObject.get("item_name").getAsString();
                int quantity = jsonObject.get("quantity").getAsInt();
                ItemReward reward = new ItemReward(displayName, itemName, quantity);
                yield (reward.itemStack == null) ? null : reward;
            }
            case "command" -> {
                String command = jsonObject.get("command").getAsString();
                yield new CommandReward(displayName, command);
            }
            default -> null;
        };
    }

}
