package im.baas;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ResponseBodyUtils {
    public static JsonNode ResponseBodyToJsonNode(Object requestBody) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(requestBody);
        JsonNode jsonNode = mapper.readTree(json);
        return jsonNode;
    }
}
