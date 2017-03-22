# UsersApi

All URIs are relative to *http://a1.easemob.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**orgNameAppNameUsersDelete**](UsersApi.md#orgNameAppNameUsersDelete) | **DELETE** /{org_name}/{app_name}/users | Delete Users in Batch
[**orgNameAppNameUsersGet**](UsersApi.md#orgNameAppNameUsersGet) | **GET** /{org_name}/{app_name}/users | Get Users in Batch
[**orgNameAppNameUsersOwnerUsernameBlocksUsersBlockedUsernameDelete**](UsersApi.md#orgNameAppNameUsersOwnerUsernameBlocksUsersBlockedUsernameDelete) | **DELETE** /{org_name}/{app_name}/users/{owner_username}/blocks/users/{blocked_username} | Unblock User(s)
[**orgNameAppNameUsersOwnerUsernameBlocksUsersGet**](UsersApi.md#orgNameAppNameUsersOwnerUsernameBlocksUsersGet) | **GET** /{org_name}/{app_name}/users/{owner_username}/blocks/users | Get a List of Blocked Users
[**orgNameAppNameUsersOwnerUsernameBlocksUsersPost**](UsersApi.md#orgNameAppNameUsersOwnerUsernameBlocksUsersPost) | **POST** /{org_name}/{app_name}/users/{owner_username}/blocks/users | Block User(s)
[**orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernameDelete**](UsersApi.md#orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernameDelete) | **DELETE** /{org_name}/{app_name}/users/{owner_username}/contacts/users/{friend_username} | Remove Contact from User
[**orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernamePost**](UsersApi.md#orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernamePost) | **POST** /{org_name}/{app_name}/users/{owner_username}/contacts/users/{friend_username} | Add Contact for User
[**orgNameAppNameUsersOwnerUsernameContactsUsersGet**](UsersApi.md#orgNameAppNameUsersOwnerUsernameContactsUsersGet) | **GET** /{org_name}/{app_name}/users/{owner_username}/contacts/users | Get a List of Contacts
[**orgNameAppNameUsersOwnerUsernameOfflineMsgCountGet**](UsersApi.md#orgNameAppNameUsersOwnerUsernameOfflineMsgCountGet) | **GET** /{org_name}/{app_name}/users/{owner_username}/offline_msg_count | Get Offline Message Count
[**orgNameAppNameUsersPost**](UsersApi.md#orgNameAppNameUsersPost) | **POST** /{org_name}/{app_name}/users | Create a User
[**orgNameAppNameUsersUsernameActivatePost**](UsersApi.md#orgNameAppNameUsersUsernameActivatePost) | **POST** /{org_name}/{app_name}/users/{username}/activate | Activate User Account
[**orgNameAppNameUsersUsernameDeactivatePost**](UsersApi.md#orgNameAppNameUsersUsernameDeactivatePost) | **POST** /{org_name}/{app_name}/users/{username}/deactivate | Deactivate User Account
[**orgNameAppNameUsersUsernameDelete**](UsersApi.md#orgNameAppNameUsersUsernameDelete) | **DELETE** /{org_name}/{app_name}/users/{username} | Delete a User
[**orgNameAppNameUsersUsernameDisconnectGet**](UsersApi.md#orgNameAppNameUsersUsernameDisconnectGet) | **GET** /{org_name}/{app_name}/users/{username}/disconnect | Logout User
[**orgNameAppNameUsersUsernameGet**](UsersApi.md#orgNameAppNameUsersUsernameGet) | **GET** /{org_name}/{app_name}/users/{username} | Get a User
[**orgNameAppNameUsersUsernameJoinedChatgroupsGet**](UsersApi.md#orgNameAppNameUsersUsernameJoinedChatgroupsGet) | **GET** /{org_name}/{app_name}/users/{username}/joined_chatgroups | Get a List of Groups of User Joined
[**orgNameAppNameUsersUsernameJoinedChatroomsGet**](UsersApi.md#orgNameAppNameUsersUsernameJoinedChatroomsGet) | **GET** /{org_name}/{app_name}/users/{username}/joined_chatrooms | Get All the Chat Rooms of User Joined
[**orgNameAppNameUsersUsernameOfflineMsgStatusMsgIdGet**](UsersApi.md#orgNameAppNameUsersUsernameOfflineMsgStatusMsgIdGet) | **GET** /{org_name}/{app_name}/users/{username}/offline_msg_status/{msg_id} | Get Offline Message Status
[**orgNameAppNameUsersUsernamePasswordPut**](UsersApi.md#orgNameAppNameUsersUsernamePasswordPut) | **PUT** /{org_name}/{app_name}/users/{username}/password | Reset User&#39;s Password
[**orgNameAppNameUsersUsernamePut**](UsersApi.md#orgNameAppNameUsersUsernamePut) | **PUT** /{org_name}/{app_name}/users/{username} | Update User&#39;s APNs Display Name
[**orgNameAppNameUsersUsernameStatusGet**](UsersApi.md#orgNameAppNameUsersUsernameStatusGet) | **GET** /{org_name}/{app_name}/users/{username}/status | Get User Online Status


<a name="orgNameAppNameUsersDelete"></a>
# **orgNameAppNameUsersDelete**
> String orgNameAppNameUsersDelete(orgName, appName, authorization, limit, cursor)

Delete Users in Batch

Delete total number of N user accounts in batch. Recommend set N range 100~500 at a time to ensure the performance. Cannot specified user account in deletion, check the response to see which user accounts are deleted.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String limit = "3"; // String | Total number of user accounts to be deleted
String cursor = ""; // String | Cursor value from previous deletion
try {
    String result = apiInstance.orgNameAppNameUsersDelete(orgName, appName, authorization, limit, cursor);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **limit** | **String**| Total number of user accounts to be deleted | [default to 3]
 **cursor** | **String**| Cursor value from previous deletion | [optional] [default to ]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersGet"></a>
# **orgNameAppNameUsersGet**
> String orgNameAppNameUsersGet(orgName, appName, authorization, limit, cursor)

Get Users in Batch

Get a list of users

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String limit = "3"; // String | Get specified number of user by setting the batch limit with parameter \"limit\". Otherwise the API returns the 10 most recent created users by default. 
String cursor = "LTgzNDAxMjM3OTpreS0yeXBSSkVlYWZZODl3bXppMTFn\""; // String | Pagination: If the query results more objects than value of limit, then the response will carry an extra attribute “cursor”, the value points to the next page. There is No cursor on the last page or if the returning objects is less than batch limit value.
try {
    String result = apiInstance.orgNameAppNameUsersGet(orgName, appName, authorization, limit, cursor);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **limit** | **String**| Get specified number of user by setting the batch limit with parameter \&quot;limit\&quot;. Otherwise the API returns the 10 most recent created users by default.  | [optional] [default to 3]
 **cursor** | **String**| Pagination: If the query results more objects than value of limit, then the response will carry an extra attribute “cursor”, the value points to the next page. There is No cursor on the last page or if the returning objects is less than batch limit value. | [optional] [default to LTgzNDAxMjM3OTpreS0yeXBSSkVlYWZZODl3bXppMTFn&quot;]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersOwnerUsernameBlocksUsersBlockedUsernameDelete"></a>
# **orgNameAppNameUsersOwnerUsernameBlocksUsersBlockedUsernameDelete**
> String orgNameAppNameUsersOwnerUsernameBlocksUsersBlockedUsernameDelete(orgName, appName, authorization, ownerUsername, blockedUsername)

Unblock User(s)

Unblock one or multiple users by removing them from blacklist. Max 60 users at a time. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String ownerUsername = "ownerUsername_example"; // String | 
String blockedUsername = "blockedUsername_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameUsersOwnerUsernameBlocksUsersBlockedUsernameDelete(orgName, appName, authorization, ownerUsername, blockedUsername);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersOwnerUsernameBlocksUsersBlockedUsernameDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **ownerUsername** | **String**|  |
 **blockedUsername** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersOwnerUsernameBlocksUsersGet"></a>
# **orgNameAppNameUsersOwnerUsernameBlocksUsersGet**
> String orgNameAppNameUsersOwnerUsernameBlocksUsersGet(orgName, appName, authorization, ownerUsername)

Get a List of Blocked Users

Get a List of Blocked Users by the user.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name 
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String ownerUsername = "ownerUsername_example"; // String | The user who is requesting blacklist
try {
    String result = apiInstance.orgNameAppNameUsersOwnerUsernameBlocksUsersGet(orgName, appName, authorization, ownerUsername);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersOwnerUsernameBlocksUsersGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name  |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **ownerUsername** | **String**| The user who is requesting blacklist |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersOwnerUsernameBlocksUsersPost"></a>
# **orgNameAppNameUsersOwnerUsernameBlocksUsersPost**
> String orgNameAppNameUsersOwnerUsernameBlocksUsersPost(orgName, appName, authorization, ownerUsername, usernames)

Block User(s)

Block one or multiple users by adding to blacklist. Blocked user cannot send message.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String ownerUsername = "ownerUsername_example"; // String | The user who initiated the blocking
UserNames usernames = new UserNames(); // UserNames | Users to be blocked. Use ',' to separate the usernames
try {
    String result = apiInstance.orgNameAppNameUsersOwnerUsernameBlocksUsersPost(orgName, appName, authorization, ownerUsername, usernames);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersOwnerUsernameBlocksUsersPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **ownerUsername** | **String**| The user who initiated the blocking |
 **usernames** | [**UserNames**](UserNames.md)| Users to be blocked. Use &#39;,&#39; to separate the usernames |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernameDelete"></a>
# **orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernameDelete**
> String orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernameDelete(orgName, appName_, authorization, ownerUsername, friendUsername)

Remove Contact from User

Remove contact from user&#39;s contact list. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName_ = "appName__example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String ownerUsername = "ownerUsername_example"; // String | user
String friendUsername = "friendUsername_example"; // String | contact to be removed
try {
    String result = apiInstance.orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernameDelete(orgName, appName_, authorization, ownerUsername, friendUsername);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernameDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName_** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **ownerUsername** | **String**| user |
 **friendUsername** | **String**| contact to be removed |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernamePost"></a>
# **orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernamePost**
> String orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernamePost(orgName, appName, authorization, ownerUsername, friendUsername)

Add Contact for User

Add contact for user. Contact to be added must under the same application as the user.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String ownerUsername = "ownerUsername_example"; // String | user
String friendUsername = "friendUsername_example"; // String | contact to be added
try {
    String result = apiInstance.orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernamePost(orgName, appName, authorization, ownerUsername, friendUsername);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernamePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **ownerUsername** | **String**| user |
 **friendUsername** | **String**| contact to be added |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersOwnerUsernameContactsUsersGet"></a>
# **orgNameAppNameUsersOwnerUsernameContactsUsersGet**
> String orgNameAppNameUsersOwnerUsernameContactsUsersGet(orgName, appName, authorization, ownerUsername)

Get a List of Contacts

Get user&#39;s contact list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String ownerUsername = "ownerUsername_example"; // String | The user who is requesting contact list
try {
    String result = apiInstance.orgNameAppNameUsersOwnerUsernameContactsUsersGet(orgName, appName, authorization, ownerUsername);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersOwnerUsernameContactsUsersGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **ownerUsername** | **String**| The user who is requesting contact list |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersOwnerUsernameOfflineMsgCountGet"></a>
# **orgNameAppNameUsersOwnerUsernameOfflineMsgCountGet**
> String orgNameAppNameUsersOwnerUsernameOfflineMsgCountGet(orgName, appName, authorization, ownerUsername)

Get Offline Message Count

Get the number of offline messages

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String ownerUsername = "ownerUsername_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameUsersOwnerUsernameOfflineMsgCountGet(orgName, appName, authorization, ownerUsername);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersOwnerUsernameOfflineMsgCountGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **ownerUsername** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersPost"></a>
# **orgNameAppNameUsersPost**
> String orgNameAppNameUsersPost(orgName, appName, body, authorization)

Create a User

Register an IM user account respects to org and app keys.  Note: There&#39;re 2 types of registration, &#39;open&#39; and &#39;authorized&#39;. &#39;open&#39; registration does not require admin authentication, but &#39;authorized&#39; does. See the application -&gt; \&quot;overview\&quot; -&gt; \&quot;User Registration Permission\&quot; in Hyphenate console.  &#39;Authorized&#39; registration is recommended, which prevents malicious attempt to create junk user account from the ones obtained the URL. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
RegisterUsers body = new RegisterUsers(); // RegisterUsers | To create multiple users at once: [    {        \"username\": \"user1\",        \"password\": \"123456\"    },    {        \"username\": \"user2\",        \"password\": \"123456\"    }]
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
try {
    String result = apiInstance.orgNameAppNameUsersPost(orgName, appName, body, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **body** | [**RegisterUsers**](RegisterUsers.md)| To create multiple users at once: [    {        \&quot;username\&quot;: \&quot;user1\&quot;,        \&quot;password\&quot;: \&quot;123456\&quot;    },    {        \&quot;username\&quot;: \&quot;user2\&quot;,        \&quot;password\&quot;: \&quot;123456\&quot;    }] |
 **authorization** | **String**| Bearer ${token} | [optional] [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersUsernameActivatePost"></a>
# **orgNameAppNameUsersUsernameActivatePost**
> String orgNameAppNameUsersUsernameActivatePost(orgName, appName, authorization, username)

Activate User Account

Activate a deactivated user account. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String username = "username_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameUsersUsernameActivatePost(orgName, appName, authorization, username);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersUsernameActivatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **username** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersUsernameDeactivatePost"></a>
# **orgNameAppNameUsersUsernameDeactivatePost**
> String orgNameAppNameUsersUsernameDeactivatePost(orgName, appName, authorization, username)

Deactivate User Account

Deactivate a user account. User will not be able to login. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String username = "username_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameUsersUsernameDeactivatePost(orgName, appName, authorization, username);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersUsernameDeactivatePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **username** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersUsernameDelete"></a>
# **orgNameAppNameUsersUsernameDelete**
> String orgNameAppNameUsersUsernameDelete(orgName, appName, authorization, username)

Delete a User

Warning: Delete a user will also delete the group and chat room if the specified user is the admin of the group and chat room. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String username = "username_example"; // String | user to be deleted
try {
    String result = apiInstance.orgNameAppNameUsersUsernameDelete(orgName, appName, authorization, username);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersUsernameDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **username** | **String**| user to be deleted |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersUsernameDisconnectGet"></a>
# **orgNameAppNameUsersUsernameDisconnectGet**
> String orgNameAppNameUsersUsernameDisconnectGet(orgName, appName, authorization, username)

Logout User

Force logout a user

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String username = "username_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameUsersUsernameDisconnectGet(orgName, appName, authorization, username);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersUsernameDisconnectGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **username** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersUsernameGet"></a>
# **orgNameAppNameUsersUsernameGet**
> String orgNameAppNameUsersUsernameGet(orgName, appName, authorization, username)

Get a User



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String username = "username_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameUsersUsernameGet(orgName, appName, authorization, username);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersUsernameGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **username** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersUsernameJoinedChatgroupsGet"></a>
# **orgNameAppNameUsersUsernameJoinedChatgroupsGet**
> String orgNameAppNameUsersUsernameJoinedChatgroupsGet(orgName, appName, authorization, username)

Get a List of Groups of User Joined



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String username = "username_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameUsersUsernameJoinedChatgroupsGet(orgName, appName, authorization, username);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersUsernameJoinedChatgroupsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **username** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersUsernameJoinedChatroomsGet"></a>
# **orgNameAppNameUsersUsernameJoinedChatroomsGet**
> String orgNameAppNameUsersUsernameJoinedChatroomsGet(orgName, appName, authorization, username)

Get All the Chat Rooms of User Joined



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String username = "username_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameUsersUsernameJoinedChatroomsGet(orgName, appName, authorization, username);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersUsernameJoinedChatroomsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **username** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersUsernameOfflineMsgStatusMsgIdGet"></a>
# **orgNameAppNameUsersUsernameOfflineMsgStatusMsgIdGet**
> String orgNameAppNameUsersUsernameOfflineMsgStatusMsgIdGet(orgName, appName, authorization, username, msgId)

Get Offline Message Status

Get offline message status via message ID. Get message ID via the method get message history.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String username = "username_example"; // String | 
String msgId = "msgId_example"; // String | Message ID
try {
    String result = apiInstance.orgNameAppNameUsersUsernameOfflineMsgStatusMsgIdGet(orgName, appName, authorization, username, msgId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersUsernameOfflineMsgStatusMsgIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **username** | **String**|  |
 **msgId** | **String**| Message ID |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersUsernamePasswordPut"></a>
# **orgNameAppNameUsersUsernamePasswordPut**
> String orgNameAppNameUsersUsernamePasswordPut(orgName, appName, username, body, authorization)

Reset User&#39;s Password

To enhance security, we recommend update user&#39;s Hyphenate IM user account password if the user&#39;s app password on developer server is updated.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String username = "username_example"; // String | 
NewPassword body = new NewPassword(); // NewPassword | Set a new password by using key \"newpassword\"
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
try {
    String result = apiInstance.orgNameAppNameUsersUsernamePasswordPut(orgName, appName, username, body, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersUsernamePasswordPut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **username** | **String**|  |
 **body** | [**NewPassword**](NewPassword.md)| Set a new password by using key \&quot;newpassword\&quot; |
 **authorization** | **String**| Bearer ${token} | [optional] [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersUsernamePut"></a>
# **orgNameAppNameUsersUsernamePut**
> String orgNameAppNameUsersUsernamePut(orgName, appName, username, body, authorization)

Update User&#39;s APNs Display Name

Update user&#39;s APNs display name for iOS push notification.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String username = "username_example"; // String | 
Nickname body = new Nickname(); // Nickname | update APNs display name by key \"nickname\"
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
try {
    String result = apiInstance.orgNameAppNameUsersUsernamePut(orgName, appName, username, body, authorization);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersUsernamePut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **username** | **String**|  |
 **body** | [**Nickname**](Nickname.md)| update APNs display name by key \&quot;nickname\&quot; |
 **authorization** | **String**| Bearer ${token} | [optional] [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="orgNameAppNameUsersUsernameStatusGet"></a>
# **orgNameAppNameUsersUsernameStatusGet**
> String orgNameAppNameUsersUsernameStatusGet(orgName, appName, authorization, username)

Get User Online Status



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UsersApi;


UsersApi apiInstance = new UsersApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String username = "username_example"; // String | 
try {
    String result = apiInstance.orgNameAppNameUsersUsernameStatusGet(orgName, appName, authorization, username);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#orgNameAppNameUsersUsernameStatusGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **username** | **String**|  |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

