package org.dto.response;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Result {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTitle() {
        return title;
    }
}
