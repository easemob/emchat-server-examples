package im.baas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import im.baas.model.MsgModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ChatAction {
    private static final Logger log = LoggerFactory.getLogger(ChatAction.class);

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBody> callback(HttpEntity<Object> requestEntity) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("content-type", "application/json;charset=UTF-8");
        ResponseBody body = new ResponseBody();
        Object requestBody = requestEntity.getBody();
        JsonNode rootNode = ResponseBodyUtils.ResponseBodyToJsonNode(requestBody);
        try {
            packageModel(rootNode);
            body.setAccpet("true");
            log.info("Send message successfully");
        } catch (Exception e) {
            log.error(e.getMessage());
            body.setAccpet("false");
            body.setReason(e.getMessage());
        }
        body.setCallId(rootNode.get("callId").toString());
        body.setSecurity(body.getCallId() + "654321" + "true");
        return new ResponseEntity<>(body, httpHeaders, HttpStatus.OK);
    }

    private void packageModel(JsonNode rootNode) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MsgModel msgModel = mapper.treeToValue(rootNode, MsgModel.class);
    }
}
