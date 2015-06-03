/**
 * Utility class
 */
package com.example.hardik.knapsack.BL;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;

public class Utils {

    /**
     * Store sim card information in shared preference.
     *
     * @param context activity context
     */
    public static void storeSIMData(Context context) {

        final SharedPreferences pref = context.getSharedPreferences(Global.PREFERENCE, Context.MODE_PRIVATE);
        if (pref.getString(Global.PREF_SIM_SERIAL, null) != null &&
                pref.getString(Global.PREF_SIM_SERIAL, null).length() > 0) {

            final TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            pref.edit().putString(Global.PREF_SIM_SERIAL, telephonyManager.getSimSerialNumber()).commit();
            pref.edit().putString(Global.PREF_SIM_OPERATOR, telephonyManager.getSimOperator()).commit();
            pref.edit().putString(Global.PREF_SIM_OPERATOR_NAME, telephonyManager.getSimOperatorName()).commit();
            pref.edit().putString(Global.PREF_SIM_NUMBER, telephonyManager.getLine1Number()).commit();
            pref.edit().putString(Global.PREF_SIM_IMEI, telephonyManager.getDeviceId()).commit();


        }

    }
}
