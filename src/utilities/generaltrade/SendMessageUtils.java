package utilities.generaltrade;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ProgressBar;

import com.redfoot.iota.MainApp;

public class SendMessageUtils {
	 // =========================================================================
	 // TODO Variable
	 // =========================================================================
	private static final int TEXTLIMIT = 150;
	private static final int DEBUGWITHDEVICE = 0;
	private static final String TESTING_NUMBER = "5558"; 
	 // =========================================================================
	 // TODO for SendMessage Functions 
	 // =========================================================================
	public static ArrayList<String> divideMainMessage(Context core, String MAPCODE, String MAINMESSAGE){
		
		String IncrematalID = String.valueOf(MainApp.getTextID(core));
		String HeaderMap = MAPCODE;
		int HeaderLIMIT = HeaderMap.length() + 4 + IncrematalID.length();
		int MessageLIMIT = TEXTLIMIT - HeaderLIMIT;
		//
		int textpage = 0;
		int incremental = 1;
		//
		for(int x = 1 ; x < MAINMESSAGE.length(); x += MessageLIMIT){
			
			textpage = incremental;
			incremental++;
		}
		//
		ArrayList<String> TextMap = new ArrayList<String>();
		//
		for (int y = 0; y < textpage; y++){
			//
			int limit = MAINMESSAGE.length() > MessageLIMIT ? MessageLIMIT : MAINMESSAGE.length();
			int incY = y+1;
			String tempMap = HeaderMap +incY+"-"+textpage+" "+ IncrematalID +" " + MAINMESSAGE.substring(0,limit);
			TextMap.add(tempMap);
			//
			MAINMESSAGE = MAINMESSAGE.substring(limit);
		}
		
		return TextMap;
	}
	// =========================================================================
	public static void sendTextPart(Context core, int index, String sNum, String message, ProgressBar loading){
		//
		loading.setVisibility(View.VISIBLE);
		//
		SmsManager sms = SmsManager.getDefault();
		Intent intent = new Intent(core.getPackageName() + MainApp.INTENT_ACTION_SENT);
		intent.putExtra("id"+index, index);
		//
		String NumberToSend = MainApp.DEBUG == 0? sNum : TESTING_NUMBER;
		if(MainApp.DEBUG == 1){
			NumberToSend = DEBUGWITHDEVICE == 1? sNum: TESTING_NUMBER;
		}
		//
		PendingIntent pi1 = PendingIntent.getBroadcast(core, index, intent, 0);
		sms.sendTextMessage(NumberToSend, null, message, pi1, null);
	}
	// =========================================================================
//	public static void resendTextAgain(final Context core, final int index, final String sNum, final String textMessage){
//		
//		final int count = index +1;
//		
//		new AlertDialog.Builder(core)
//		.setTitle("Send Warnings!")
//		.setMessage("Message Part "+count+" not send,\n Resend again?")
//		.setPositiveButton("YES",new OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				SendMessageUtils.sendTextPart(core, count, sNum, textMessage);
//			}
//		} )
//		.setNegativeButton("Cancel", null)
//		.show();
//	}
	// =========================================================================
	// =========================================================================
	// Final Code
}
