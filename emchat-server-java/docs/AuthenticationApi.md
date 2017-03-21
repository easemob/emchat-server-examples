# AuthenticationApi

All URIs are relative to *http://a1.easemob.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**orgNameAppNameTokenPost**](AuthenticationApi.md#orgNameAppNameTokenPost) | **POST** /{org_name}/{app_name}/token | Request an Authentication Token


<a name="orgNameAppNameTokenPost"></a>
# **orgNameAppNameTokenPost**
> String orgNameAppNameTokenPost(orgName, appName, body)

Request an Authentication Token

All of the Hyphenate API endpoints requires authentication token for session, unless specified otherwise.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.AuthenticationApi;


AuthenticationApi apiInstance = new AuthenticationApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
Token body = new Token(); // Token | client_id and client_secret can be found in the application details page of the Hyphenate console
try {
    String result = apiInstance.orgNameAppNameTokenPost(orgName, appName, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthenticationApi#orgNameAppNameTokenPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **body** | [**Token**](Token.md)| client_id and client_secret can be found in the application details page of the Hyphenate console |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

