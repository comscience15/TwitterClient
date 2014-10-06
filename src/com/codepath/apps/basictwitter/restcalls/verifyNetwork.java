package com.codepath.apps.basictwitter.restcalls;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class verifyNetwork {
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo nInfo = cm.getActiveNetworkInfo();
		return nInfo != null && nInfo.isConnectedOrConnecting();
	}
}
