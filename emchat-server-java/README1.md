# swagger-java-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-java-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-java-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.AuthenticationApi;

import java.io.File;
import java.util.*;

public class AuthenticationApiExample {

    public static void main(String[] args) {
        
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
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://a1.easemob.com*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*AuthenticationApi* | [**orgNameAppNameTokenPost**](docs/AuthenticationApi.md#orgNameAppNameTokenPost) | **POST** /{org_name}/{app_name}/token | Request an Authentication Token
*ChatHistoryApi* | [**orgNameAppNameChatmessagesGet**](docs/ChatHistoryApi.md#orgNameAppNameChatmessagesGet) | **GET** /{org_name}/{app_name}/chatmessages | Get Message History
*ChatHistoryApi* | [**orgNameAppNameChatmessagesTimeGet**](docs/ChatHistoryApi.md#orgNameAppNameChatmessagesTimeGet) | **GET** /{org_name}/{app_name}/chatmessages/{time} | Get Message History by Hour
*ChatRoomsApi* | [**orgNameAppNameChatroomsChatroomIdDelete**](docs/ChatRoomsApi.md#orgNameAppNameChatroomsChatroomIdDelete) | **DELETE** /{org_name}/{app_name}/chatrooms/{chatroom_id} | Delete a Chat Room
*ChatRoomsApi* | [**orgNameAppNameChatroomsChatroomIdGet**](docs/ChatRoomsApi.md#orgNameAppNameChatroomsChatroomIdGet) | **GET** /{org_name}/{app_name}/chatrooms/{chatroom_id} | Get Chat Room Details
*ChatRoomsApi* | [**orgNameAppNameChatroomsChatroomIdPut**](docs/ChatRoomsApi.md#orgNameAppNameChatroomsChatroomIdPut) | **PUT** /{org_name}/{app_name}/chatrooms/{chatroom_id} | Update Chat Room Details
*ChatRoomsApi* | [**orgNameAppNameChatroomsChatroomIdUsersPost**](docs/ChatRoomsApi.md#orgNameAppNameChatroomsChatroomIdUsersPost) | **POST** /{org_name}/{app_name}/chatrooms/{chatroom_id}/users | Add Chat Room Members in Batch
*ChatRoomsApi* | [**orgNameAppNameChatroomsChatroomIdUsersUsernameDelete**](docs/ChatRoomsApi.md#orgNameAppNameChatroomsChatroomIdUsersUsernameDelete) | **DELETE** /{org_name}/{app_name}/chatrooms/{chatroom_id}/users/{username} | Remove a Chat Room Member
*ChatRoomsApi* | [**orgNameAppNameChatroomsChatroomIdUsersUsernamePost**](docs/ChatRoomsApi.md#orgNameAppNameChatroomsChatroomIdUsersUsernamePost) | **POST** /{org_name}/{app_name}/chatrooms/{chatroom_id}/users/{username} | Add a Chat Room Member
*ChatRoomsApi* | [**orgNameAppNameChatroomsChatroomIdUsersUsernamesDelete**](docs/ChatRoomsApi.md#orgNameAppNameChatroomsChatroomIdUsersUsernamesDelete) | **DELETE** /{org_name}/{app_name}/chatrooms/{chatroom_id}/users/{usernames} | Remove Chat Room Members in Batch
*ChatRoomsApi* | [**orgNameAppNameChatroomsGet**](docs/ChatRoomsApi.md#orgNameAppNameChatroomsGet) | **GET** /{org_name}/{app_name}/chatrooms | Get All the Chat Rooms
*ChatRoomsApi* | [**orgNameAppNameChatroomsPost**](docs/ChatRoomsApi.md#orgNameAppNameChatroomsPost) | **POST** /{org_name}/{app_name}/chatrooms | Create a Chat Room
*GroupsApi* | [**orgNameAppNameChatgroupsGet**](docs/GroupsApi.md#orgNameAppNameChatgroupsGet) | **GET** /{org_name}/{app_name}/chatgroups | Get All the Groups
*GroupsApi* | [**orgNameAppNameChatgroupsGroupIdBlocksUsersGet**](docs/GroupsApi.md#orgNameAppNameChatgroupsGroupIdBlocksUsersGet) | **GET** /{org_name}/{app_name}/chatgroups/{group_id}/blocks/users | Get Group Blocked Users
*GroupsApi* | [**orgNameAppNameChatgroupsGroupIdBlocksUsersPost**](docs/GroupsApi.md#orgNameAppNameChatgroupsGroupIdBlocksUsersPost) | **POST** /{org_name}/{app_name}/chatgroups/{group_id}/blocks/users | Block Group Members in Batch
*GroupsApi* | [**orgNameAppNameChatgroupsGroupIdBlocksUsersUsernameDelete**](docs/GroupsApi.md#orgNameAppNameChatgroupsGroupIdBlocksUsersUsernameDelete) | **DELETE** /{org_name}/{app_name}/chatgroups/{group_id}/blocks/users/{username} | Unblock a Group Member
*GroupsApi* | [**orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamePost**](docs/GroupsApi.md#orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamePost) | **POST** /{org_name}/{app_name}/chatgroups/{group_id}/blocks/users/{username} | Block a Group Member
*GroupsApi* | [**orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamesDelete**](docs/GroupsApi.md#orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamesDelete) | **DELETE** /{org_name}/{app_name}/chatgroups/{group_id}/blocks/users/{usernames} | Unblock Group Members in Batch
*GroupsApi* | [**orgNameAppNameChatgroupsGroupIdDelete**](docs/GroupsApi.md#orgNameAppNameChatgroupsGroupIdDelete) | **DELETE** /{org_name}/{app_name}/chatgroups/{group_id} | Delete a Group
*GroupsApi* | [**orgNameAppNameChatgroupsGroupIdPut**](docs/GroupsApi.md#orgNameAppNameChatgroupsGroupIdPut) | **PUT** /{org_name}/{app_name}/chatgroups/{group_id} | Update Group Details
*GroupsApi* | [**orgNameAppNameChatgroupsGroupIdUsersGet**](docs/GroupsApi.md#orgNameAppNameChatgroupsGroupIdUsersGet) | **GET** /{org_name}/{app_name}/chatgroups/{group_id}/users | Get a List of Members of Group
*GroupsApi* | [**orgNameAppNameChatgroupsGroupIdUsersMembersDelete**](docs/GroupsApi.md#orgNameAppNameChatgroupsGroupIdUsersMembersDelete) | **DELETE** /{org_name}/{app_name}/chatgroups/{group_id}/users/{members} | Remove multiple Member from the Group
*GroupsApi* | [**orgNameAppNameChatgroupsGroupIdUsersPost**](docs/GroupsApi.md#orgNameAppNameChatgroupsGroupIdUsersPost) | **POST** /{org_name}/{app_name}/chatgroups/{group_id}/users | Add Multiple Members to Group
*GroupsApi* | [**orgNameAppNameChatgroupsGroupIdUsersUsernameDelete**](docs/GroupsApi.md#orgNameAppNameChatgroupsGroupIdUsersUsernameDelete) | **DELETE** /{org_name}/{app_name}/chatgroups/{group_id}/users/{username} | Remove a Member from the Group
*GroupsApi* | [**orgNameAppNameChatgroupsGroupIdUsersUsernamePost**](docs/GroupsApi.md#orgNameAppNameChatgroupsGroupIdUsersUsernamePost) | **POST** /{org_name}/{app_name}/chatgroups/{group_id}/users/{username} | Add a Member to Group
*GroupsApi* | [**orgNameAppNameChatgroupsGroupIdsGet**](docs/GroupsApi.md#orgNameAppNameChatgroupsGroupIdsGet) | **GET** /{org_name}/{app_name}/chatgroups/{group_ids} | Get Group(s) Details
*GroupsApi* | [**orgNameAppNameChatgroupsGroupidPut**](docs/GroupsApi.md#orgNameAppNameChatgroupsGroupidPut) | **PUT** /{org_name}/{app_name}/chatgroups/{groupid} | Update Group Owner
*GroupsApi* | [**orgNameAppNameChatgroupsPost**](docs/GroupsApi.md#orgNameAppNameChatgroupsPost) | **POST** /{org_name}/{app_name}/chatgroups | Create a Group
*MessagesApi* | [**orgNameAppNameMessagesPost**](docs/MessagesApi.md#orgNameAppNameMessagesPost) | **POST** /{org_name}/{app_name}/messages | Send a Message
*UploadAndDownloadFilesApi* | [**orgNameAppNameChatfilesFilestreamGet**](docs/UploadAndDownloadFilesApi.md#orgNameAppNameChatfilesFilestreamGet) | **GET** /{org_name}/{app_name}/chatfiles/{filestream} | Download the voice / image file
*UploadAndDownloadFilesApi* | [**orgNameAppNameChatfilesPost**](docs/UploadAndDownloadFilesApi.md#orgNameAppNameChatfilesPost) | **POST** /{org_name}/{app_name}/chatfiles | Upload a File
*UploadAndDownloadFilesApi* | [**orgNameAppNameChatfilesUuidGet**](docs/UploadAndDownloadFilesApi.md#orgNameAppNameChatfilesUuidGet) | **GET** /{org_name}/{app_name}/chatfiles/{uuid} | Download a File
*UsersApi* | [**orgNameAppNameUsersDelete**](docs/UsersApi.md#orgNameAppNameUsersDelete) | **DELETE** /{org_name}/{app_name}/users | Delete Users in Batch
*UsersApi* | [**orgNameAppNameUsersGet**](docs/UsersApi.md#orgNameAppNameUsersGet) | **GET** /{org_name}/{app_name}/users | Get Users in Batch
*UsersApi* | [**orgNameAppNameUsersOwnerUsernameBlocksUsersBlockedUsernameDelete**](docs/UsersApi.md#orgNameAppNameUsersOwnerUsernameBlocksUsersBlockedUsernameDelete) | **DELETE** /{org_name}/{app_name}/users/{owner_username}/blocks/users/{blocked_username} | Unblock User(s)
*UsersApi* | [**orgNameAppNameUsersOwnerUsernameBlocksUsersGet**](docs/UsersApi.md#orgNameAppNameUsersOwnerUsernameBlocksUsersGet) | **GET** /{org_name}/{app_name}/users/{owner_username}/blocks/users | Get a List of Blocked Users
*UsersApi* | [**orgNameAppNameUsersOwnerUsernameBlocksUsersPost**](docs/UsersApi.md#orgNameAppNameUsersOwnerUsernameBlocksUsersPost) | **POST** /{org_name}/{app_name}/users/{owner_username}/blocks/users | Block User(s)
*UsersApi* | [**orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernameDelete**](docs/UsersApi.md#orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernameDelete) | **DELETE** /{org_name}/{app_name}/users/{owner_username}/contacts/users/{friend_username} | Remove Contact from User
*UsersApi* | [**orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernamePost**](docs/UsersApi.md#orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernamePost) | **POST** /{org_name}/{app_name}/users/{owner_username}/contacts/users/{friend_username} | Add Contact for User
*UsersApi* | [**orgNameAppNameUsersOwnerUsernameContactsUsersGet**](docs/UsersApi.md#orgNameAppNameUsersOwnerUsernameContactsUsersGet) | **GET** /{org_name}/{app_name}/users/{owner_username}/contacts/users | Get a List of Contacts
*UsersApi* | [**orgNameAppNameUsersOwnerUsernameOfflineMsgCountGet**](docs/UsersApi.md#orgNameAppNameUsersOwnerUsernameOfflineMsgCountGet) | **GET** /{org_name}/{app_name}/users/{owner_username}/offline_msg_count | Get Offline Message Count
*UsersApi* | [**orgNameAppNameUsersPost**](docs/UsersApi.md#orgNameAppNameUsersPost) | **POST** /{org_name}/{app_name}/users | Create a User
*UsersApi* | [**orgNameAppNameUsersUsernameActivatePost**](docs/UsersApi.md#orgNameAppNameUsersUsernameActivatePost) | **POST** /{org_name}/{app_name}/users/{username}/activate | Activate User Account
*UsersApi* | [**orgNameAppNameUsersUsernameDeactivatePost**](docs/UsersApi.md#orgNameAppNameUsersUsernameDeactivatePost) | **POST** /{org_name}/{app_name}/users/{username}/deactivate | Deactivate User Account
*UsersApi* | [**orgNameAppNameUsersUsernameDelete**](docs/UsersApi.md#orgNameAppNameUsersUsernameDelete) | **DELETE** /{org_name}/{app_name}/users/{username} | Delete a User
*UsersApi* | [**orgNameAppNameUsersUsernameDisconnectGet**](docs/UsersApi.md#orgNameAppNameUsersUsernameDisconnectGet) | **GET** /{org_name}/{app_name}/users/{username}/disconnect | Logout User
*UsersApi* | [**orgNameAppNameUsersUsernameGet**](docs/UsersApi.md#orgNameAppNameUsersUsernameGet) | **GET** /{org_name}/{app_name}/users/{username} | Get a User
*UsersApi* | [**orgNameAppNameUsersUsernameJoinedChatgroupsGet**](docs/UsersApi.md#orgNameAppNameUsersUsernameJoinedChatgroupsGet) | **GET** /{org_name}/{app_name}/users/{username}/joined_chatgroups | Get a List of Groups of User Joined
*UsersApi* | [**orgNameAppNameUsersUsernameJoinedChatroomsGet**](docs/UsersApi.md#orgNameAppNameUsersUsernameJoinedChatroomsGet) | **GET** /{org_name}/{app_name}/users/{username}/joined_chatrooms | Get All the Chat Rooms of User Joined
*UsersApi* | [**orgNameAppNameUsersUsernameOfflineMsgStatusMsgIdGet**](docs/UsersApi.md#orgNameAppNameUsersUsernameOfflineMsgStatusMsgIdGet) | **GET** /{org_name}/{app_name}/users/{username}/offline_msg_status/{msg_id} | Get Offline Message Status
*UsersApi* | [**orgNameAppNameUsersUsernamePasswordPut**](docs/UsersApi.md#orgNameAppNameUsersUsernamePasswordPut) | **PUT** /{org_name}/{app_name}/users/{username}/password | Reset User&#39;s Password
*UsersApi* | [**orgNameAppNameUsersUsernamePut**](docs/UsersApi.md#orgNameAppNameUsersUsernamePut) | **PUT** /{org_name}/{app_name}/users/{username} | Update User&#39;s APNs Display Name
*UsersApi* | [**orgNameAppNameUsersUsernameStatusGet**](docs/UsersApi.md#orgNameAppNameUsersUsernameStatusGet) | **GET** /{org_name}/{app_name}/users/{username}/status | Get User Online Status


## Documentation for Models

 - [Chatroom](docs/Chatroom.md)
 - [Group](docs/Group.md)
 - [ModifyChatroom](docs/ModifyChatroom.md)
 - [ModifyGroup](docs/ModifyGroup.md)
 - [Msg](docs/Msg.md)
 - [MsgContent](docs/MsgContent.md)
 - [NewOwner](docs/NewOwner.md)
 - [NewPassword](docs/NewPassword.md)
 - [Nickname](docs/Nickname.md)
 - [RegisterUsers](docs/RegisterUsers.md)
 - [Token](docs/Token.md)
 - [User](docs/User.md)
 - [UserName](docs/UserName.md)
 - [UserNames](docs/UserNames.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author



