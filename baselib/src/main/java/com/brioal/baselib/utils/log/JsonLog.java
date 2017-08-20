package com.brioal.baselib.utils.log;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JsonLog {

    public static void printJson(String tag, String msg, String headString) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(BLog.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(BLog.JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        BLogUtil.printLine(tag, true);
        message = headString + BLog.LINE_SEPARATOR + message;
        String[] lines = message.split(BLog.LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        BLogUtil.printLine(tag, false);
    }
}
