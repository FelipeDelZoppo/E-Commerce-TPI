package tpi.backend.e_commerce.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;



import tpi.backend.e_commerce.models.Issue;

public class GitHubMapper {

    @SuppressWarnings("unchecked")
    public static List<Issue> toEntities(List<Map<String,Object>> issues) {

        return issues.stream().map(issue -> {
            Issue issueToSave = Issue.builder()
                .url((String) issue.get("url"))
                .number((Integer) issue.get("number"))
                .title((String) issue.get("title"))
                .locked((boolean) issue.get("locked"))
                .user(((Map<String, Object>) issue.get("user")).get("login").toString())
                .state((String) issue.get("state"))
                .creation_at(convertToLocalDateTime((String) issue.get("created_at")))
                .update_at(convertToLocalDateTime((String) issue.get("updated_at")))
                .closed_at(convertToLocalDateTime((String) issue.get("closed_at")))
                .build();
            
            return issueToSave;
        }).toList();
    }
    
    private static LocalDateTime convertToLocalDateTime(String dateTime) {
    return dateTime != null 
        ? LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME) 
        : null;
    }    
}
