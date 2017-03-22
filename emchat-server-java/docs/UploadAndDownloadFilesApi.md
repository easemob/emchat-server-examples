# UploadAndDownloadFilesApi

All URIs are relative to *http://a1.easemob.com*

Method | HTTP request | Description
------------- | ------------- | -------------
[**orgNameAppNameChatfilesFilestreamGet**](UploadAndDownloadFilesApi.md#orgNameAppNameChatfilesFilestreamGet) | **GET** /{org_name}/{app_name}/chatfiles/{filestream} | Download the voice / image file
[**orgNameAppNameChatfilesPost**](UploadAndDownloadFilesApi.md#orgNameAppNameChatfilesPost) | **POST** /{org_name}/{app_name}/chatfiles | Upload a File
[**orgNameAppNameChatfilesUuidGet**](UploadAndDownloadFilesApi.md#orgNameAppNameChatfilesUuidGet) | **GET** /{org_name}/{app_name}/chatfiles/{uuid} | Download a File


<a name="orgNameAppNameChatfilesFilestreamGet"></a>
# **orgNameAppNameChatfilesFilestreamGet**
> File orgNameAppNameChatfilesFilestreamGet(orgName, appName, authorization, filestream, shareSecret)

Download the voice / image file

It should be noted that the need to bring in the HTTP header to return the share-secret and the current login user&#39;s token can be downloaded, and pay attention to the implementation of the header accept value needs to be set to application / octet-stream.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UploadAndDownloadFilesApi;


UploadAndDownloadFilesApi apiInstance = new UploadAndDownloadFilesApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String filestream = "filestream_example"; // String | uuid from previous successful uploading response
String shareSecret = "0t40OpUNEea9vDPsBxmoM5Ii0rXsTrGqev67_tSj5uPgumQg"; // String | share-secret from previous successful uploading response
try {
    File result = apiInstance.orgNameAppNameChatfilesFilestreamGet(orgName, appName, authorization, filestream, shareSecret);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UploadAndDownloadFilesApi#orgNameAppNameChatfilesFilestreamGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **filestream** | **String**| uuid from previous successful uploading response |
 **shareSecret** | **String**| share-secret from previous successful uploading response | [default to 0t40OpUNEea9vDPsBxmoM5Ii0rXsTrGqev67_tSj5uPgumQg]

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/octet-stream

<a name="orgNameAppNameChatfilesPost"></a>
# **orgNameAppNameChatfilesPost**
> String orgNameAppNameChatfilesPost(orgName, appName, authorization, file, restrictAccess)

Upload a File

Warning: File cannot be over 10MB, otherwise will fail. Important: Be sure keep the \&quot;uuid\&quot; and \&quot;share-secret\&quot;, which are requirement when downloading particular file.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UploadAndDownloadFilesApi;


UploadAndDownloadFilesApi apiInstance = new UploadAndDownloadFilesApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
File file = new File(""); // File | 
Boolean restrictAccess = ; // Boolean | \"restrict-access\" determines whether later the file require \"share-secret\" for access or allow public access. 
try {
    String result = apiInstance.orgNameAppNameChatfilesPost(orgName, appName, authorization, file, restrictAccess);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UploadAndDownloadFilesApi#orgNameAppNameChatfilesPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **file** | **File**|  | [default to ]
 **restrictAccess** | **Boolean**| \&quot;restrict-access\&quot; determines whether later the file require \&quot;share-secret\&quot; for access or allow public access.  | [optional] [default to ]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="orgNameAppNameChatfilesUuidGet"></a>
# **orgNameAppNameChatfilesUuidGet**
> File orgNameAppNameChatfilesUuidGet(orgName, appName, authorization, uuid, shareSecret, thumbnail)

Download a File

Important: 1. Include the share-secret from HTTP header  2. Include uuid from the response of a successful file uploading, which is the file identifier 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UploadAndDownloadFilesApi;


UploadAndDownloadFilesApi apiInstance = new UploadAndDownloadFilesApi();
String orgName = "orgName_example"; // String | Organization ID
String appName = "appName_example"; // String | Application name
String authorization = "Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4"; // String | Bearer ${token}
String uuid = "uuid_example"; // String | uuid, the file identifier, from previous successful uploading reponse
String shareSecret = "K0nDupReEeaIDxMU2xxA1MQRdbbpSbahSXyalxCt8LFY1he0"; // String | share-secret from previous successful uploading response
Boolean thumbnail = ; // Boolean | The request will return thumbnail if the tag of “thumbnail: true” is explicitly put in the header
try {
    File result = apiInstance.orgNameAppNameChatfilesUuidGet(orgName, appName, authorization, uuid, shareSecret, thumbnail);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UploadAndDownloadFilesApi#orgNameAppNameChatfilesUuidGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgName** | **String**| Organization ID |
 **appName** | **String**| Application name |
 **authorization** | **String**| Bearer ${token} | [default to Bearer YWMtLU9T4JRGEea0-Vvai3EzjAAAAVkGz4dZKNSpsVdRvVix2OfSm42w5-IaUL4]
 **uuid** | **String**| uuid, the file identifier, from previous successful uploading reponse |
 **shareSecret** | **String**| share-secret from previous successful uploading response | [default to K0nDupReEeaIDxMU2xxA1MQRdbbpSbahSXyalxCt8LFY1he0]
 **thumbnail** | **Boolean**| The request will return thumbnail if the tag of “thumbnail: true” is explicitly put in the header | [optional] [default to ]

### Return type

[**File**](File.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/octet-stream

