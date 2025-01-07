package com.ai.QussaiAiModel.services;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JobSeeker {
    private final ChatModel chatModel;

    public JobSeeker(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String searchJob(String jobTitle,
                            String field,
                            String location,
                            String employmentType){
        var template = """
                I am looking for a job that matches the following criteria:
                - Preferred job title or role: {jobTitle}.
                - Industry or field of interest: {industry}.
                - Location preference: {location}.
                - Desired employment type (e.g., full-time, part-time, remote): {employmentType}.
                Please provide a list of suitable job opportunities, including job descriptions, company names, and application links, if available.
                """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of(
                "jobTitle", jobTitle,
                "industry", field,
                "location", location,
                "employmentType", employmentType
        );

        Prompt prompt = promptTemplate.create(params);
        return chatModel.call(prompt).getResult().getOutput().getContent();
    }

}
