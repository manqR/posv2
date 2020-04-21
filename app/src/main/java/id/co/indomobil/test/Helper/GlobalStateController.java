package id.co.indomobil.test.Helper;

public class GlobalStateController {
    public static boolean isNetworkConnected;

    public static boolean activityVisible; // Variable that will check the
    // current activity state

    public static boolean isActivityVisible() {
        return activityVisible; // return true or false
    }

    public static void activityResumed() {
        activityVisible = true;// this will set true when activity resumed

    }

}
