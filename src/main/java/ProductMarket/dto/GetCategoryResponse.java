package ProductMarket.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "id",
            "products",
            "title"
    })
    @Data
    public class GetCategoryResponse {

        @JsonProperty("id")
        private Integer id;
        @JsonProperty("products")
        private List<Product> products = null;
        @JsonProperty("title")
        private String title;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    }
