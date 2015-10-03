/*package com.example.EZList;

import EZListDatabase.EZListDatabaseAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;


 * This class receives and processes SMS messages
 * This was generated from the tutorial found at 
 * http://androidexample.com/Incomming_SMS_Broadcast_Receiver_-_Android_Example/index.php?view=article_discription&aid=62&aaid=87
 
public class IncomingSMSTest extends BroadcastReceiver
{
	final SmsManager sms = SmsManager.getDefault();
	private EZListDatabaseAdapter dbAdapter;;

	@Override
	public void onReceive(Context context, Intent intent)
	{
		final Bundle bundle = intent.getExtras();
		dbAdapter = new EZListDatabaseAdapter(context);
		dbAdapter = dbAdapter.open();
		boolean EZList = false;
		String listId = null;
		String messageItems = null;

		try
		{
			if(bundle != null)
			{
				final Object[] pdusObj = (Object[]) bundle.get("pdus");

				for (int i = 0; i < pdusObj.length; i++) 
				{

					SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
					String phoneNumber = currentMessage.getDisplayOriginatingAddress();
					String senderNum = phoneNumber;
					String message = currentMessage.getDisplayMessageBody();

					if(message.startsWith("EZList")) EZList = true;
					if(EZList)
					{
						Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
						messageItems += message;
					}
				}
			}

			String[] items = messageItems.split("\n");
			listId = dbAdapter.insertList(items[1]);

			if(items.length > 2)
			{
				for(int j = 2; j < items.length; j++)
				{	
					if(!items[j].trim().isEmpty())
					{
						dbAdapter.insertItem(listId, items[j]);
					}
				}	    
			}
		}
		catch (Exception e) 
		{
			Log.e("SmsReceiver", "Exception smsReceiver" +e);
		}
		dbAdapter.close();
	}

}


*/