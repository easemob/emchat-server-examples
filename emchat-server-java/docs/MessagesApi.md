# MessagesApi

All URIs are relative to *http://a1.easemob.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**orgNameAppNameMessagesPost**](MessagesApi.md#orgNameAppNameMessagesPost) | **POST** /{org_name}/{app_name}/messages | Send a Message


<a name="orgNameAppNameMessagesPost"></a>
# **orgNameAppNameMessagesPost**
> String orgNameAppNameMessagesPost(orgName, appName, authorization, body)

Send a Message

Send a message to user(s) and group(s).                Please refer to the Hyphenate docs for further details about message body. https://docs.hyphenate.io/docs/post-messages

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.MessagesApi;


MessagesApi apiInstance = new MessagesApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
Msg body = new Msg(); // Msg | Message types: Text, image, audio message, video, command, etc. Please refer to the Hyphenate docs for further details about message body. https://docs.hyphenate.io/docs/post-messages
try {
    String result = apiInstance.orgNameAppNameMessagesPost(orgName, appName, authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling MessagesApi#orgNameAppNameMessagesPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **body** | [**Msg**](Msg.md)| Message types: Text, image, audio message, video, command, etc. Please refer to the Hyphenate docs for further details about message body. https://docs.hyphenate.io/docs/post-messages |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

