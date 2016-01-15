package com.easemob.server.example;

import java.util.ArrayList;
import java.util.List;

import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.BodyWrapper;
import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.EasemobRestAPIFactory;
import com.easemob.server.example.comm.body.IMUserBody;
import com.easemob.server.example.comm.body.IMUsersBody;

public class Main {

	public static void main(String[] args) {
		EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		
		IMUserAPI user = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		/*
		ChatMessageAPI chat = (ChatMessageAPI)factory.newInstance(EasemobRestAPIFactory.MESSAGE_CLASS);
		FileAPI file = (FileAPI)factory.newInstance(EasemobRestAPIFactory.FILE_CLASS);
		SendMessageAPI message = (SendMessageAPI)factory.newInstance(EasemobRestAPIFactory.SEND_MESSAGE_CLASS);
		ChatGroupAPI chatgroup = (ChatGroupAPI)factory.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
		ChatRoomAPI chatroom = (ChatRoomAPI)factory.newInstance(EasemobRestAPIFactory.CHATROOM_CLASS);
		*/
		
		// Create a IM user
		BodyWrapper userBody = new IMUserBody("User001", "123456", "HelloWorld");
		user.createNewIMUserSingle(userBody);
		
		// Create some IM users
		List<IMUserBody> users = new ArrayList<IMUserBody>();
		users.add(new IMUserBody("User002", "123456", null));
		users.add(new IMUserBody("User003", "123456", null));
		BodyWrapper usersBody = new IMUsersBody(users);
		user.createNewIMUserBatch(usersBody);
		
		// Get a IM user
		user.getIMUsersByUserName("User001");
		
		// Get a fake user
		user.getIMUsersByUserName("FakeUser001");
		
		// Get 12 users
		user.getIMUsersBatch(null, null);
	}

}
