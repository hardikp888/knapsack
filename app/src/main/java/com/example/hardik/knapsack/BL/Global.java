/**
 * Global class which contain Constant data
 */

package com.example.hardik.knapsack.BL;

import java.util.ArrayList;

public class Global {

    public static final String PREFERENCE = "knapsack_pref";
    public static final String PREF_SIM_SERIAL = "pref_sim_serial";
    public static final String PREF_SIM_OPERATOR = "pref_sim_operator";
    public static final String PREF_SIM_OPERATOR_NAME = "pref_sim_operator_name";
    public static final String PREF_SIM_IMEI = "pref_sim_imei";
    public static final String PREF_SIM_NUMBER = "pref_sim_number";
    public static final String EXPENSE_ID = "expense_id";

    public static final ArrayList<String> getExpenseType() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Food");
        list.add("Travel");
        list.add("Petrol");
        list.add("Medicine");
        list.add("Entertainment");
        list.add("Electronic");
        list.add("Cloth shopping");
        list.add("Rent");
        list.add("Bill payment");
        list.add("Study");
        list.add("Gifts");
        list.add("Insurance");
        list.add("Vehicle service");
        list.add("Stationery");
        return list;
    }
}
