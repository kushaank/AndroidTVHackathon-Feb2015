package com.sdkcdg.cahphone;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.sdkcdg.bluetooth.BluetoothService;
import com.sdkcdg.proto.CardsProto.CardsMessage;
import com.sdkcdg.proto.PlayerCommands;
import com.sdkcdg.proto.PlayerProto.PlayerMessage;

public class PlayerUtils 
{
	public static List<CardsMessage> mHand = new ArrayList<CardsMessage>();
	public static CardsMessage selectedCards = null;
	public static String mName = "";
	public static int mCounter = 0;
	
	public static void addCard(PlayerMessage p)
	{
		try 
		{
			CardsMessage c = CardsMessage.parseFrom(p.getMessageByteString());
			mHand.add(c);
		} 
		catch (InvalidProtocolBufferException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void sendCardToServer()
	{
		if(selectedCards == null)
		{
			selectedCards = mHand.get(0);
			//mHand.remove(0);
		}
		PlayerMessage msg = PlayerMessage.newBuilder()
				.setMName(mName)
				.setMAction(PlayerCommands.PLAY_CARD)
				.setMessageByteString(selectedCards.toByteString())
				.build();
		BluetoothService.sendMessage(msg);
		selectedCards = null;
	}
	
	public static void selectCards(int card)
	{
		selectedCards = mHand.get(card);
	}
	
	public static void setBlackCard(PlayerMessage pm)
	{
		try 
		{
			CardsMessage cm = CardsMessage.parseFrom(pm.getMessageByteString());
			MainActivity.blackCardTV.setText(cm.getFirstCard());
		} 
		catch (InvalidProtocolBufferException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
}
