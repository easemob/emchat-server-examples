# GroupsApi

All URIs are relative to *http://a1.easemob.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**orgNameAppNameChatgroupsGet**](GroupsApi.md#orgNameAppNameChatgroupsGet) | **GET** /{org_name}/{app_name}/chatgroups | Get All the Groups
[**orgNameAppNameChatgroupsGroupIdBlocksUsersGet**](GroupsApi.md#orgNameAppNameChatgroupsGroupIdBlocksUsersGet) | **GET** /{org_name}/{app_name}/chatgroups/{group_id}/blocks/users | Get Group Blocked Users
[**orgNameAppNameChatgroupsGroupIdBlocksUsersPost**](GroupsApi.md#orgNameAppNameChatgroupsGroupIdBlocksUsersPost) | **POST** /{org_name}/{app_name}/chatgroups/{group_id}/blocks/users | Block Group Members in Batch
[**orgNameAppNameChatgroupsGroupIdBlocksUsersUsernameDelete**](GroupsApi.md#orgNameAppNameChatgroupsGroupIdBlocksUsersUsernameDelete) | **DELETE** /{org_name}/{app_name}/chatgroups/{group_id}/blocks/users/{username} | Unblock a Group Member
[**orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamePost**](GroupsApi.md#orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamePost) | **POST** /{org_name}/{app_name}/chatgroups/{group_id}/blocks/users/{username} | Block a Group Member
[**orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamesDelete**](GroupsApi.md#orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamesDelete) | **DELETE** /{org_name}/{app_name}/chatgroups/{group_id}/blocks/users/{usernames} | Unblock Group Members in Batch
[**orgNameAppNameChatgroupsGroupIdDelete**](GroupsApi.md#orgNameAppNameChatgroupsGroupIdDelete) | **DELETE** /{org_name}/{app_name}/chatgroups/{group_id} | Delete a Group
[**orgNameAppNameChatgroupsGroupIdPut**](GroupsApi.md#orgNameAppNameChatgroupsGroupIdPut) | **PUT** /{org_name}/{app_name}/chatgroups/{group_id} | Update Group Details
[**orgNameAppNameChatgroupsGroupIdUsersGet**](GroupsApi.md#orgNameAppNameChatgroupsGroupIdUsersGet) | **GET** /{org_name}/{app_name}/chatgroups/{group_id}/users | Get a List of Members of Group
[**orgNameAppNameChatgroupsGroupIdUsersMembersDelete**](GroupsApi.md#orgNameAppNameChatgroupsGroupIdUsersMembersDelete) | **DELETE** /{org_name}/{app_name}/chatgroups/{group_id}/users/{members} | Remove multiple Member from the Group
[**orgNameAppNameChatgroupsGroupIdUsersPost**](GroupsApi.md#orgNameAppNameChatgroupsGroupIdUsersPost) | **POST** /{org_name}/{app_name}/chatgroups/{group_id}/users | Add Multiple Members to Group
[**orgNameAppNameChatgroupsGroupIdUsersUsernameDelete**](GroupsApi.md#orgNameAppNameChatgroupsGroupIdUsersUsernameDelete) | **DELETE** /{org_name}/{app_name}/chatgroups/{group_id}/users/{username} | Remove a Member from the Group
[**orgNameAppNameChatgroupsGroupIdUsersUsernamePost**](GroupsApi.md#orgNameAppNameChatgroupsGroupIdUsersUsernamePost) | **POST** /{org_name}/{app_name}/chatgroups/{group_id}/users/{username} | Add a Member to Group
[**orgNameAppNameChatgroupsGroupIdsGet**](GroupsApi.md#orgNameAppNameChatgroupsGroupIdsGet) | **GET** /{org_name}/{app_name}/chatgroups/{group_ids} | Get Group(s) Details
[**orgNameAppNameChatgroupsGroupidPut**](GroupsApi.md#orgNameAppNameChatgroupsGroupidPut) | **PUT** /{org_name}/{app_name}/chatgroups/{groupid} | Update Group Owner
[**orgNameAppNameChatgroupsPost**](GroupsApi.md#orgNameAppNameChatgroupsPost) | **POST** /{org_name}/{app_name}/chatgroups | Create a Group


<a name="orgNameAppNameChatgroupsGet"></a>
# **orgNameAppNameChatgroupsGet**
> String orgNameAppNameChatgroupsGet(orgName, appName, authorization)

Get All the Groups



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
try {
    String result = apiInstance.orgNameAppNameChatgroupsGet(orgName, appName, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGet");
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

<a name="orgNameAppNameChatgroupsGroupIdBlocksUsersGet"></a>
# **orgNameAppNameChatgroupsGroupIdBlocksUsersGet**
> String orgNameAppNameChatgroupsGroupIdBlocksUsersGet(orgName, appName, authorization, groupId)

Get Group Blocked Users

Get the blacklist of blocked users. Blocked user cannot see nor receive group message.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String groupId = "groupId_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameChatgroupsGroupIdBlocksUsersGet(orgName, appName, authorization, groupId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGroupIdBlocksUsersGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **groupId** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatgroupsGroupIdBlocksUsersPost"></a>
# **orgNameAppNameChatgroupsGroupIdBlocksUsersPost**
> String orgNameAppNameChatgroupsGroupIdBlocksUsersPost(orgName, appName, authorization, groupId, body)

Block Group Members in Batch

Block multiple group members by adding the users to the group blacklist. Max 60 users at a time. Blocked users will receive an event, \&quot;You are kicked out of the group {groupid}\&quot;. Blocked user will not able to see nor receive group message.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String groupId = "groupId_example"; // String | 
UserNames body = new UserNames(); // UserNames | Separate usernames by ','
try {
    String result = apiInstance.orgNameAppNameChatgroupsGroupIdBlocksUsersPost(orgName, appName, authorization, groupId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGroupIdBlocksUsersPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **groupId** | **String**|  |
 **body** | [**UserNames**](UserNames.md)| Separate usernames by &#39;,&#39; |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatgroupsGroupIdBlocksUsersUsernameDelete"></a>
# **orgNameAppNameChatgroupsGroupIdBlocksUsersUsernameDelete**
> String orgNameAppNameChatgroupsGroupIdBlocksUsersUsernameDelete(orgName, appName, authorization, groupId, username)

Unblock a Group Member

Unblock group member by removing the user from group blacklist. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | testapp
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String groupId = "groupId_example"; // String | 
String username = "username_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameChatgroupsGroupIdBlocksUsersUsernameDelete(orgName, appName, authorization, groupId, username);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGroupIdBlocksUsersUsernameDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| testapp |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **groupId** | **String**|  |
 **username** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamePost"></a>
# **orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamePost**
> String orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamePost(orgName, appName, authorization, groupId, username)

Block a Group Member

Block a group member by adding the user to the group blacklist.  Blocked user will receive an event, \&quot;You are kicked out of the group {groupid}\&quot;. Blocked user will not able to see nor  receive group message. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String groupId = "groupId_example"; // String | 
String username = "username_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamePost(orgName, appName, authorization, groupId, username);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **groupId** | **String**|  |
 **username** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamesDelete"></a>
# **orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamesDelete**
> String orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamesDelete(orgName, appName, authorization, groupId, usernames)

Unblock Group Members in Batch



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String groupId = "groupId_example"; // String | 
String usernames = "usernames_example"; // String | Separate usernames by ','
try {
    String result = apiInstance.orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamesDelete(orgName, appName, authorization, groupId, usernames);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamesDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **groupId** | **String**|  |
 **usernames** | **String**| Separate usernames by &#39;,&#39; |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatgroupsGroupIdDelete"></a>
# **orgNameAppNameChatgroupsGroupIdDelete**
> String orgNameAppNameChatgroupsGroupIdDelete(orgName, appName, authorization, groupId)

Delete a Group



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String groupId = "groupId_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameChatgroupsGroupIdDelete(orgName, appName, authorization, groupId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGroupIdDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **groupId** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatgroupsGroupIdPut"></a>
# **orgNameAppNameChatgroupsGroupIdPut**
> String orgNameAppNameChatgroupsGroupIdPut(orgName, appName, authorization, groupId, body)

Update Group Details

The message body only allows groupname, description, and maxusers.  Note: Use &#39;+&#39; to replace space if modifying groupname ​and description. E.g., use \&quot;test+group\&quot; instead of \&quot;test group\&quot;.  Warning: If group cannot be found or operation failed, then the response will still return HTTP code 200, but key-value are \&quot;maxusers\&quot;&#x3D;false, \&quot;groupname\&quot;&#x3D;false, and \&quot;description\&quot;&#x3D;false. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String groupId = "groupId_example"; // String | 
ModifyGroup body = new ModifyGroup(); // ModifyGroup | 
try {
    String result = apiInstance.orgNameAppNameChatgroupsGroupIdPut(orgName, appName, authorization, groupId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGroupIdPut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **groupId** | **String**|  |
 **body** | [**ModifyGroup**](ModifyGroup.md)|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatgroupsGroupIdUsersGet"></a>
# **orgNameAppNameChatgroupsGroupIdUsersGet**
> String orgNameAppNameChatgroupsGroupIdUsersGet(orgName, appName, authorization, groupId)

Get a List of Members of Group



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String groupId = "groupId_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameChatgroupsGroupIdUsersGet(orgName, appName, authorization, groupId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGroupIdUsersGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **groupId** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatgroupsGroupIdUsersMembersDelete"></a>
# **orgNameAppNameChatgroupsGroupIdUsersMembersDelete**
> String orgNameAppNameChatgroupsGroupIdUsersMembersDelete(orgName, appName, authorization, groupId, members)

Remove multiple Member from the Group



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token
String groupId = "groupId_example"; // String | 
String members = "members_example"; // String | Use ',' to separate usernames
try {
    String result = apiInstance.orgNameAppNameChatgroupsGroupIdUsersMembersDelete(orgName, appName, authorization, groupId, members);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGroupIdUsersMembersDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **groupId** | **String**|  |
 **members** | **String**| Use &#39;,&#39; to separate usernames |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatgroupsGroupIdUsersPost"></a>
# **orgNameAppNameChatgroupsGroupIdUsersPost**
> String orgNameAppNameChatgroupsGroupIdUsersPost(orgName, appName, authorization, groupId, body)

Add Multiple Members to Group

Warning: Add max 60 members once at a time. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String groupId = "groupId_example"; // String | 
UserNames body = new UserNames(); // UserNames | Separate usernames by ','
try {
    String result = apiInstance.orgNameAppNameChatgroupsGroupIdUsersPost(orgName, appName, authorization, groupId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGroupIdUsersPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **groupId** | **String**|  |
 **body** | [**UserNames**](UserNames.md)| Separate usernames by &#39;,&#39; |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatgroupsGroupIdUsersUsernameDelete"></a>
# **orgNameAppNameChatgroupsGroupIdUsersUsernameDelete**
> String orgNameAppNameChatgroupsGroupIdUsersUsernameDelete(orgName, appName, authorization, groupId, username)

Remove a Member from the Group



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String groupId = "groupId_example"; // String | 
String username = "username_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameChatgroupsGroupIdUsersUsernameDelete(orgName, appName, authorization, groupId, username);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGroupIdUsersUsernameDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **groupId** | **String**|  |
 **username** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatgroupsGroupIdUsersUsernamePost"></a>
# **orgNameAppNameChatgroupsGroupIdUsersUsernamePost**
> String orgNameAppNameChatgroupsGroupIdUsersUsernamePost(orgName, appName, authorization, groupId, username)

Add a Member to Group



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String groupId = "groupId_example"; // String | 
String username = "username_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameChatgroupsGroupIdUsersUsernamePost(orgName, appName, authorization, groupId, username);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGroupIdUsersUsernamePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **groupId** | **String**|  |
 **username** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatgroupsGroupIdsGet"></a>
# **orgNameAppNameChatgroupsGroupIdsGet**
> String orgNameAppNameChatgroupsGroupIdsGet(orgName, appName, authorization, groupIds)

Get Group(s) Details



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String groupIds = "groupIds_example"; // String | Separate group ID by ','. e.g. {group_id1},{group_id2}
try {
    String result = apiInstance.orgNameAppNameChatgroupsGroupIdsGet(orgName, appName, authorization, groupIds);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGroupIdsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **groupIds** | **String**| Separate group ID by &#39;,&#39;. e.g. {group_id1},{group_id2} |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatgroupsGroupidPut"></a>
# **orgNameAppNameChatgroupsGroupidPut**
> String orgNameAppNameChatgroupsGroupidPut(orgName, appName, authorization, groupid, body)

Update Group Owner

Transfer group ownership by changing owner. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String groupid = "groupid_example"; // String | 
NewOwner body = new NewOwner(); // NewOwner | Use the key \"newowner\" and ${new_owner_user} is the username of new group owner
try {
    String result = apiInstance.orgNameAppNameChatgroupsGroupidPut(orgName, appName, authorization, groupid, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsGroupidPut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **groupid** | **String**|  |
 **body** | [**NewOwner**](NewOwner.md)| Use the key \&quot;newowner\&quot; and ${new_owner_user} is the username of new group owner |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameChatgroupsPost"></a>
# **orgNameAppNameChatgroupsPost**
> String orgNameAppNameChatgroupsPost(orgName, appName, authorization, body)

Create a Group

Group settings: 1. Group name 2. Group description 3. Public vs. Private Group 4. Max number of member (including admin) 5. If public group, allow join freely vs. require permission from admin 6. allowinvites property. If public group, then allowinvites is false. If private group, then allowinvites is true. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GroupsApi;


GroupsApi apiInstance = new GroupsApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
Group body = new Group(); // Group | \"desc\": group description. Note: Use key \"desc\".                                                                                                                                           public vs. private: group type.                                                                       maxusers: (Optional Attribute, default is 200). The max number of group members.                                     approval: (Optional Attribute, default is true). Does user need permission to join the group?                          owner: The owner (admin) of the group.                                                          members: (Optional Attribute) Group members. Ignore the attribute if no member to be added. The group owner does not represent as a group member. 
try {
    String result = apiInstance.orgNameAppNameChatgroupsPost(orgName, appName, authorization, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GroupsApi#orgNameAppNameChatgroupsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **body** | [**Group**](Group.md)| \&quot;desc\&quot;: group description. Note: Use key \&quot;desc\&quot;.                                                                                                                                           public vs. private: group type.                                                                       maxusers: (Optional Attribute, default is 200). The max number of group members.                                     approval: (Optional Attribute, default is true). Does user need permission to join the group?                          owner: The owner (admin) of the group.                                                          members: (Optional Attribute) Group members. Ignore the attribute if no member to be added. The group owner does not represent as a group member.  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

