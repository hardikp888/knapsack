package com.example.hardik.knapsack.BL;

import java.util.ArrayList;

/**
 * Created by Stranger on 1/7/2015.
 */
public class Global {

    public static final String PREFERENCE = "knapsack_pref";
    public static final String PREF_SIM_SERIAL = "pref_sim_serial";
    public static final String PREF_SIM_OPERATOR = "pref_sim_operator";
    public static final String PREF_SIM_OPERATOR_NAME = "pref_sim_operator_name";
    public static final String PREF_SIM_IMEI = "pref_sim_imei";
    public static final String PREF_SIM_NUMBER = "pref_sim_number";

    public static final ArrayList<String> getExpenseType() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Food");
        list.add("Travel");
        list.add("Petrol");
        list.add("Medicine");
        list.add("Entertainment");
        return list;
    }
}
