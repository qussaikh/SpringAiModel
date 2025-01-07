package com.ai.QussaiAiModel.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TravelPlan {
    private final ChatModel chatModel;

    public TravelPlan(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createTravel(String destination,
                               String interests,
                               String days,
                               String budget){
        var templet = """
                    I am planning a trip to {destination}.
                    My interests include: {interests}.
                    My budget for this trip is: {budget}.
                    I will be spending {days} days on this trip.
                    Please provide me with a detailed travel itinerary including activities, accommodations, and must-see attractions.
                    """;

        PromptTemplate promptTemplate = new PromptTemplate(templet);
        Map<String, Object> params = Map.of(
                "destination", destination,
                "interests", interests,
                "days", days,
                "budget", budget);

        Prompt prompt = promptTemplate.create(params);
        return chatModel.call(prompt).getResult().getOutput().getContent();
    }

}
