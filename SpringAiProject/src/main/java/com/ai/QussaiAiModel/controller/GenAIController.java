package com.ai.QussaiAiModel.controller;

import com.ai.QussaiAiModel.services.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class GenAIController {

    private final ChatService chatService;
    private final ImageService imageService;
    private final RecipeService recipeService;
    private final TravelPlan travelPlan;
    private final JobSeeker jobSeeker;

    public GenAIController(ChatService chatService, ImageService imageService, RecipeService recipeService, TravelPlan travelPlan, JobSeeker jobSeeker) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
        this.travelPlan = travelPlan;
        this.jobSeeker = jobSeeker;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }

    @GetMapping("generate-image")
    public List<String> generateImages(HttpServletResponse response,
                                       @RequestParam String prompt,
                                       @RequestParam(defaultValue = "hd") String quality,
                                       @RequestParam(defaultValue = "2") int n,
                                       @RequestParam(defaultValue = "1024") int width,
                                       @RequestParam(defaultValue = "1024") int height) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt, quality, n, width, height);

        // Streams to get urls from ImageResponse
        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();

        return imageUrls;
    }


    @GetMapping("recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
                                      @RequestParam(defaultValue = "any") String cuisine,
                                      @RequestParam(defaultValue = "") String dietaryRestriction) {
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestriction);
    }

    @GetMapping("travel-creator")
    public String travelCreator(@RequestParam String destination,
                                @RequestParam(defaultValue = "any") String interests,
                                @RequestParam(defaultValue = "") String days,
                                @RequestParam(defaultValue = "") String budget) {
        return travelPlan.createTravel(destination, interests, days, budget);
    }
    @GetMapping("searching-job")
    public String searchingJob(@RequestParam String jobTitle,
                                @RequestParam(defaultValue = "any") String field,
                                @RequestParam(defaultValue = "") String location,
                                @RequestParam(defaultValue = "") String employmentType) {
        return jobSeeker.searchJob(jobTitle, field, location, employmentType);
    }
}
