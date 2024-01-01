package network.roanoke.trivia.Reward;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.server.network.ServerPlayerEntity;
import network.roanoke.trivia.Trivia;
import network.roanoke.trivia.Quiz.Question;

import java.util.ArrayList;
import java.util.HashMap;

public class RewardManager {

    private HashMap<String, ArrayList<Reward>> rewardPools = new HashMap<>();

    public RewardManager(JsonObject rewardsObj) {

        Trivia.LOGGER.info("Loading rewards...");

        for (String difficulty : rewardsObj.keySet()) {
            JsonArray questionsArr = rewardsObj.get(difficulty).getAsJsonArray();

            // Loop over the questions in the difficulty level
            for (JsonElement rewardElem : questionsArr) {

                // Create the TriviaQuestion object and add it to the list
                Reward reward = new RewardFactory().fromJson(rewardElem);
                if (reward == null) continue;
                if (rewardPools.containsKey(difficulty)) {
                    rewardPools.get(difficulty).add(reward);
                } else {
                    ArrayList<Reward> rewards = new ArrayList<>();
                    rewards.add(reward);
                    rewardPools.put(difficulty, rewards);
                }
            }
        }

        // output the amount of rewards loaded
        for (String difficulty : rewardPools.keySet()) {
            System.out.println("Loaded " + rewardPools.get(difficulty).size() + " rewards for difficulty " + difficulty);
        }
    }

    // give the winner a random reward from the difficulty pool
    public Reward giveReward(ServerPlayerEntity player, Question question) {
        if (rewardPools.containsKey(question.difficulty)) {
            ArrayList<Reward> rewards = rewardPools.get(question.difficulty);
            Reward reward = rewards.get((int) (Math.random() * rewards.size()));
            reward.giveTo(player);
            return reward;
        }
        return null;
    }

}
