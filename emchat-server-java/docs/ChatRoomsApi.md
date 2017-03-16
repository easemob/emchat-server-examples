# ChatRoomsApi

All URIs are relative to *http://a1.easemob.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**orgNameAppNameChatroomsChatroomIdDelete**](ChatRoomsApi.md#orgNameAppNameChatroomsChatroomIdDelete) | **DELETE** /{org_name}/{app_name}/chatrooms/{chatroom_id} | Delete a Chat Room
[**orgNameAppNameChatroomsChatroomIdGet**](ChatRoomsApi.md#orgNameAppNameChatroomsChatroomIdGet) | **GET** /{org_name}/{app_name}/chatrooms/{chatroom_id} | Get Chat Room Details
[**orgNameAppNameChatroomsChatroomIdPut**](ChatRoomsApi.md#orgNameAppNameChatroomsChatroomIdPut) | **PUT** /{org_name}/{app_name}/chatrooms/{chatroom_id} | Update Chat Room Details
[**orgNameAppNameChatroomsChatroomIdUsersPost**](ChatRoomsApi.md#orgNameAppNameChatroomsChatroomIdUsersPost) | **POST** /{org_name}/{app_name}/chatrooms/{chatroom_id}/users | Add Chat Room Members in Batch
[**orgNameAppNameChatroomsChatroomIdUsersUsernameDelete**](ChatRoomsApi.md#orgNameAppNameChatroomsChatroomIdUsersUsernameDelete) | **DELETE** /{org_name}/{app_name}/chatrooms/{chatroom_id}/users/{username} | Remove a Chat Room Member
[**orgNameAppNameChatroomsChatroomIdUsersUsernamePost**](ChatRoomsApi.md#orgNameAppNameChatroomsChatroomIdUsersUsernamePost) | **POST** /{org_name}/{app_name}/chatrooms/{chatroom_id}/users/{username} | Add a Chat Room Member
[**orgNameAppNameChatroomsChatroomIdUsersUsernamesDelete**](ChatRoomsApi.md#orgNameAppNameChatroomsChatroomIdUsersUsernamesDelete) | **DELETE** /{org_name}/{app_name}/chatrooms/{chatroom_id}/users/{usernames} | Remove Chat Room Members in Batch
[**orgNameAppNameChatroomsGet**](ChatRoomsApi.md#orgNameAppNameChatroomsGet) | **GET** /{org_name}/{app_name}/chatrooms | Get All the Chat Rooms
[**orgNameAppNameChatroomsPost**](ChatRoomsApi.md#orgNameAppNameChatroomsPost) | **POST** /{org_name}/{app_name}/chatrooms | Create a Chat Room


<a name="orgNameAppNameChatroomsChatroomIdDelete"></a>
# **orgNameAppNameChatroomsChatroomIdDelete**
> String orgNameAppNameChatroomsChatroomIdDelete(orgName, appName, authorization, chatroomId)

Delete a Chat Room



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ChatRoomsApi;


ChatRoomsApi apiInstance = new ChatRoomsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String chatroomId = "chatroomId_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameChatroomsChatroomIdDelete(orgName, appName, authorization, chatroomId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ChatRoomsApi#orgNameAppNameChatroomsChatroomIdDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **chatroomId** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatroomsChatroomIdGet"></a>
# **orgNameAppNameChatroomsChatroomIdGet**
> String orgNameAppNameChatroomsChatroomIdGet(orgName, appName, authorization, chatroomId)

Get Chat Room Details



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ChatRoomsApi;


ChatRoomsApi apiInstance = new ChatRoomsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String chatroomId = "chatroomId_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameChatroomsChatroomIdGet(orgName, appName, authorization, chatroomId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ChatRoomsApi#orgNameAppNameChatroomsChatroomIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **chatroomId** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatroomsChatroomIdPut"></a>
# **orgNameAppNameChatroomsChatroomIdPut**
> String orgNameAppNameChatroomsChatroomIdPut(orgName, appName, authorization, chatroomId, body)

Update Chat Room Details



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ChatRoomsApi;


ChatRoomsApi apiInstance = new ChatRoomsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String chatroomId = "chatroomId_example"; // String | 
ModifyChatroom body = new ModifyChatroom(); // ModifyChatroom | 
try {
    String result = apiInstance.orgNameAppNameChatroomsChatroomIdPut(orgName, appName, authorization, chatroomId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ChatRoomsApi#orgNameAppNameChatroomsChatroomIdPut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **chatroomId** | **String**|  |
 **body** | [**ModifyChatroom**](ModifyChatroom.md)|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatroomsChatroomIdUsersPost"></a>
# **orgNameAppNameChatroomsChatroomIdUsersPost**
> String orgNameAppNameChatroomsChatroomIdUsersPost(orgName, appName, authorization, chatroomId, content)

Add Chat Room Members in Batch

Add max 60 group members at a time.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ChatRoomsApi;


ChatRoomsApi apiInstance = new ChatRoomsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String chatroomId = "chatroomId_example"; // String | 
UserNames content = new UserNames(); // UserNames | 
try {
    String result = apiInstance.orgNameAppNameChatroomsChatroomIdUsersPost(orgName, appName, authorization, chatroomId, content);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ChatRoomsApi#orgNameAppNameChatroomsChatroomIdUsersPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **chatroomId** | **String**|  |
 **content** | [**UserNames**](UserNames.md)|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatroomsChatroomIdUsersUsernameDelete"></a>
# **orgNameAppNameChatroomsChatroomIdUsersUsernameDelete**
> String orgNameAppNameChatroomsChatroomIdUsersUsernameDelete(orgName, appName, authorization, chatroomId, username)

Remove a Chat Room Member



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ChatRoomsApi;


ChatRoomsApi apiInstance = new ChatRoomsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String chatroomId = "chatroomId_example"; // String | 
String username = "username_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameChatroomsChatroomIdUsersUsernameDelete(orgName, appName, authorization, chatroomId, username);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ChatRoomsApi#orgNameAppNameChatroomsChatroomIdUsersUsernameDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **chatroomId** | **String**|  |
 **username** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatroomsChatroomIdUsersUsernamePost"></a>
# **orgNameAppNameChatroomsChatroomIdUsersUsernamePost**
> String orgNameAppNameChatroomsChatroomIdUsersUsernamePost(orgName, appName, authorization, chatroomId, username)

Add a Chat Room Member



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ChatRoomsApi;


ChatRoomsApi apiInstance = new ChatRoomsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String chatroomId = "chatroomId_example"; // String | 
String username = "username_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameChatroomsChatroomIdUsersUsernamePost(orgName, appName, authorization, chatroomId, username);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ChatRoomsApi#orgNameAppNameChatroomsChatroomIdUsersUsernamePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **chatroomId** | **String**|  |
 **username** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatroomsChatroomIdUsersUsernamesDelete"></a>
# **orgNameAppNameChatroomsChatroomIdUsersUsernamesDelete**
> String orgNameAppNameChatroomsChatroomIdUsersUsernamesDelete(orgName, appName, authorization, chatroomId, usernames)

Remove Chat Room Members in Batch



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ChatRoomsApi;


ChatRoomsApi apiInstance = new ChatRoomsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String chatroomId = "chatroomId_example"; // String | 
String usernames = "usernames_example"; // String | Separate usernames by ','
try {
    String result = apiInstance.orgNameAppNameChatroomsChatroomIdUsersUsernamesDelete(orgName, appName, authorization, chatroomId, usernames);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ChatRoomsApi#orgNameAppNameChatroomsChatroomIdUsersUsernamesDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **chatroomId** | **String**|  |
 **usernames** | **String**| Separate usernames by &#39;,&#39; |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatroomsGet"></a>
# **orgNameAppNameChatroomsGet**
> String orgNameAppNameChatroomsGet(orgName, appName, authorization)

Get All the Chat Rooms



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ChatRoomsApi;


ChatRoomsApi apiInstance = new ChatRoomsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
try {
    String result = apiInstance.orgNameAppNameChatroomsGet(orgName, appName, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ChatRoomsApi#orgNameAppNameChatroomsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatroomsPost"></a>
# **orgNameAppNameChatroomsPost**
> String orgNameAppNameChatroomsPost(orgName, appName, authorization, body)

Create a Chat Room



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.ChatRoomsApi;


ChatRoomsApi apiInstance = new ChatRoomsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
Chatroom body = new Chatroom(); // Chatroom | 
try {
    String result = apiInstance.orgNameAppNameChatroomsPost(orgName, appName, authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ChatRoomsApi#orgNameAppNameChatroomsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **body** | [**Chatroom**](Chatroom.md)|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

