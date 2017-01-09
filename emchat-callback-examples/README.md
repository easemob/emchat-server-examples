## 实时消息回调示例代码

实时消息回调功能可以满足用户对消息实时性的需求。一条实时消息的具体流程是，APP Server向Easemob Server发送消息请求，由Easemob Server完成消息发送之后向APP Server发送回调，APP Server接收回调之后并响应。具体详见[实时消息回调官方文档](http://docs.easemob.com/im/000quickstart/70rtmsgcallback)。

我们为开发者提供了响应消息回调的示例代码。本项目是[Spring Boot Web](https://spring.io/docs)项目，运行`ApplicationServer`可启动服务器，通过`http://localhost:8081`访问。
### 文本消息回调
```
curl -X POST --header 'Content-Type: application/json' -d '{"callId":"123","eventType":"chat_offline","timestamp":0,"chat_type":"groupchat","group_id":"","from":"John","to":"Aliy","msg_id":"","payload":{"bodies":[{"msg":"hello world", "type": "txt"}],"ext":{"key1": "value1"}},"ecurityVersion":"1.0.0","security":"1231234560"}' -X POST 'http://localhost:8081'
```
### 图片消息回调
```
curl  --header 'Content-Type: application/json' -d '{"callId":"123","eventType":"chat_offline","timestamp":0,"chat_type":"groupchat","group_id":"","from":"John","to":"Aliy","msg_id":"","payload":{"bodies":[{"url":"https://a1.easemob.com/org/app/chatfiles/uuid", "type": "img", "secret":"secret", "filename":"file123", "size" : {"width":10, "height":10}}],"ext":{"key1": "value1"}},"ecurityVersion":"1.0.0","security":"1231234560"}' -X POST 'http://localhost:8081'
```
### 语音消息回调
```
curl  --header 'Content-Type: application/json' -d '{"callId":"123","eventType":"chat_offline","timestamp":0,"chat_type":"groupchat","group_id":"","from":"John","to":"Aliy","msg_id":"","payload":{"bodies":[{"url":"https://a1.easemob.com/org/app/chatfiles/uuid", "type": "audio", "secret":"secret", "filename":"file123", "length":10, "file_length":10 }],"ext":{"key1": "value1"}},"ecurityVersion":"1.0.0","security":"1231234560"}' -X POST 'http://localhost:8081'
```
### 视频消息回调
```
curl  --header 'Content-Type: application/json' -d '{"callId":"123","eventType":"chat_offline","timestamp":0,"chat_type":"groupchat","group_id":"","from":"John","to":"Aliy","msg_id":"","payload":{"bodies":[{"url":"https://a1.easemob.com/org/app/chatfiles/uuid", "type": "audio", "secret":"secret", "filename":"file123", "length":10, "file_length":10, "thumb":"", "thumb_secret":"" }],"ext":{"key1": "value1"}},"ecurityVersion":"1.0.0","security":"1231234560"}' -X POST 'http://localhost:8081'
```