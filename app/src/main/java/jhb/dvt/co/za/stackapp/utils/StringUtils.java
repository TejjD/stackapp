package jhb.dvt.co.za.stackapp.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import jhb.dvt.co.za.stackapp.constants.Constants;

class StringUtils {

    static String convertStreamToString(InputStream stream, String encoding) {

        if (stream == null)
            throw new IllegalArgumentException("Empty Stream");
        if (StringUtils.isNullOrWhiteSpace(encoding))
            encoding = Constants.DEFAULT_STREAM_ENCODING;

        String result = null;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    stream, encoding), 8);

            StringBuilder sb = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            stream.close();

            result = sb.toString();

        } catch (IOException e) {
            Log.e(Constants.APP_NAME,
                    "Unable to convert - " + e.toString());
        }

        return result;
    }

    private static boolean isNullOrWhiteSpace(String string) {

        boolean retVal = false;

        if (string == null) {
            retVal = true;
        } else if (string.isEmpty()) {
            retVal = true;
        } else if (string.trim().equalsIgnoreCase("")) {
            retVal = true;
        } else if (string.length() == 0) {
            retVal = true;
        }

        return retVal;
    }

}