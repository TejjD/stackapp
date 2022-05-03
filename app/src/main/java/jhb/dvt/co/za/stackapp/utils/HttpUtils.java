package jhb.dvt.co.za.stackapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import jhb.dvt.co.za.stackapp.constants.Constants;

public class HttpUtils {

    public static String makeServiceCall(String urlString) {
        String response = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = StringUtils.convertStreamToString(in, Constants.DEFAULT_STREAM_ENCODING);
        } catch (Exception e) {
            Log.e(Constants.APP_NAME, "Exception: " + e.getMessage());
        }
        return response;
    }

    /**
     * Referenced from existing project work done previously.
     **/
    public static boolean isHTTPConnectionPossible(Context context) {

        if (context == null)
            throw new IllegalArgumentException("context is null");

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean wifiOK = false;
        boolean mobileDataOK = false;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null) {
            mobileDataOK = (netInfo.isAvailable() && netInfo.isConnected());
        }

        NetworkInfo netWifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (netWifiInfo != null) {
            wifiOK = (netWifiInfo.isAvailable() && netWifiInfo.isConnected());
        }

        return wifiOK || mobileDataOK;
    }
}