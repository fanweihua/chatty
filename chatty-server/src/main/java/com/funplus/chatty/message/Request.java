/**
 * 
 */
package com.funplus.chatty.message;

import com.funplus.chatty.controller.Session;

/**
 * @author Weihua Fan
 *
 */
public abstract class Request {
    
    public static final int Login = 1;
    public static final int Logout = 2;
    public static final int GetRoomList = 3;
    public static final int JoinRoom = 4;
    public static final int AutoJoin = 5;
    public static final int CreateRoom = 6;
    public static final int GenericMessage = 7;
    public static final int ChangeRoomName = 8;
    public static final int ChangeRoomPassword = 9;
    public static final int ObjectMessage = 10;
    public static final int SetRoomVariables = 11;
    public static final int SetUserVariables = 12;
    public static final int CallExtension = 13;
    public static final int LeaveRoom = 14;
    public static final int SubscribeRoomGroup = 15;
    public static final int UnsubscribeRoomGroup = 16;
    public static final int SpectatorToPlayer = 17;
    public static final int PlayerToSpectator = 18;
    public static final int ChangeRoomCapacity = 19;
    public static final int PublicMessage = 20;
    public static final int PrivateMessage = 21;
    public static final int ModeratorMessage = 22;
    public static final int AdminMessage = 23;
    public static final int KickUser = 24;
    public static final int BanUser = 25;
    public static final int ManualDisconnection = 26;
    public static final int FindRooms = 27;
    public static final int FindUsers = 28;
    public static final int PingPong = 29;
    public static final int SetUserPosition = 30;
    public static final int InitBuddyList = 200;
    public static final int AddBuddy = 201;
    public static final int BlockBuddy = 202;
    public static final int RemoveBuddy = 203;
    public static final int SetBuddyVariables = 204;
    public static final int Presence = 205;
    public static final int InviteUser = 300;
    public static final int InvitationReply = 301;
    public static final int CreateSFSGame = 302;
    public static final int QuickJoinGame = 303;
    
    private int id;
    //private Object content;
    private Session sender;
    private long timeStamp;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
//    public Object getContent() {
//        return content;
//    }
//    
//    public void setContent(Object content) {
//        this.content = content;
//    }
    
    public Session getSender() {
        return sender;
    }
    
    public void setSender(Session sender) {
        this.sender = sender;
    }
    
    public long getTimeStamp() {
        return timeStamp;
    }
    
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    
}
