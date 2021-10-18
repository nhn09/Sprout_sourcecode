package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class App_Usage extends AppCompatActivity {
    int trackDuration ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app__usage);


        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 14);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

// every night at 2am you run your task
        Timer timer = new Timer();
        timer.schedule(new YourTask(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period: 1 day


    }

    private void getUsageStatistics(long start_time, long end_time) {
        UsageEvents.Event currentEvent;
        //  List<UsageEvents.Event> allEvents = new ArrayList<>();
        HashMap<String, AppUsageInfo> map = new HashMap<>();
        HashMap<String, List<UsageEvents.Event>> sameEvents = new HashMap<>();

        @SuppressLint("WrongConstant") UsageStatsManager mUsageStatsManager = (UsageStatsManager) getSystemService("usagestats");

        if (mUsageStatsManager != null) {
            // Get all apps data from starting time to end time
            UsageEvents usageEvents = mUsageStatsManager.queryEvents(start_time, end_time);

            // Put these data into the map
            while (usageEvents.hasNextEvent()) {
                currentEvent = new UsageEvents.Event();
                usageEvents.getNextEvent(currentEvent);
                if (currentEvent.getEventType() == UsageEvents.Event.ACTIVITY_RESUMED ||
                        currentEvent.getEventType() == UsageEvents.Event.ACTIVITY_PAUSED) {
                    //  allEvents.add(currentEvent);
                    String key = currentEvent.getPackageName();
                    if (map.get(key) == null) {
                        map.put(key, new AppUsageInfo(key));
                        sameEvents.put(key,new ArrayList<UsageEvents.Event>());
                    }
                    sameEvents.get(key).add(currentEvent);
                }
            }

            // Traverse through each app data which is grouped together and count launch, calculate duration
            for (Map.Entry<String,List<UsageEvents.Event>> entry : sameEvents.entrySet()) {
                int totalEvents = entry.getValue().size();
                if (totalEvents > 1) {
                    for (int i = 0; i < totalEvents - 1; i++) {
                        UsageEvents.Event E0 = entry.getValue().get(i);
                        UsageEvents.Event E1 = entry.getValue().get(i + 1);

                        if (E1.getEventType() == 1 || E0.getEventType() == 1) {
                            map.get(E1.getPackageName()).launchCount++;
                        }

                        if (E0.getEventType() == 1 && E1.getEventType() == 2) {
                            long diff = E1.getTimeStamp() - E0.getTimeStamp();
                            map.get(E0.getPackageName()).timeInForeground += diff;
                        }
                    }
                }

                // If First eventtype is ACTIVITY_PAUSED then added the difference of start_time and Event occuring time because the application is already running.
                if (entry.getValue().get(0).getEventType() == 2) {
                    long diff = entry.getValue().get(0).getTimeStamp() - start_time;
                    map.get(entry.getValue().get(0).getPackageName()).timeInForeground += diff;
                }

                // If Last eventtype is ACTIVITY_RESUMED then added the difference of end_time and Event occuring time because the application is still running .
                if (entry.getValue().get(totalEvents - 1).getEventType() == 1) {
                    long diff = end_time - entry.getValue().get(totalEvents - 1).getTimeStamp();
                    map.get(entry.getValue().get(totalEvents - 1).getPackageName()).timeInForeground += diff;
                }
            }

            ArrayList<AppUsageInfo> smallInfoList = new ArrayList<>(map.values());

            // Concatenating data to show in a text view. You may do according to your requirement
            String strMsg = null;
            for (AppUsageInfo appUsageInfo : smallInfoList)
            {
                // Do according to your requirement
                strMsg = strMsg.concat(appUsageInfo.packageName + " : " + appUsageInfo.launchCount + "\n\n");
            }

            TextView tvMsg = findViewById(R.id.MA_TvMsg);
            tvMsg.setText(strMsg);

        } else {
            //Toast.makeText(context, "Sorry...", Toast.LENGTH_SHORT).show();
        }
    }

    private class YourTask extends TimerTask {
        public void run() {
            long hour_in_mil = 1000*60*60*24; // In Milliseconds
            long end_time = System.currentTimeMillis();
            long start_time = end_time - hour_in_mil;

            getUsageStatistics(start_time, end_time);
        }
    }

//    long startTime = new GregorianCalendar(2014, 0, 1).getTimeInMillis();
//    long endTime = new GregorianCalendar(2016, 0, 1).getTimeInMillis();

//    UsageStatsManager usageStatsManager = (UsageStatsManager)context.getSystemService(Context.USAGE_STATS_SERVICE);
//    List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime);
//
//     for(UsageStats us : queryUsageStats) {
//        Log.d(TAG, us.getPackageName() + " = " + us.getTotalTimeInForeground());
//    }




//    String dt = "2021-01-04";  // Start date
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    Calendar c = Calendar.getInstance();
//    try {
//        c.setTime(sdf.parse(dt));
//    }
//    catch (
//    ParseException e) {
//        e.printStackTrace();
//    }
//    c.add(Calendar.DATE, 40);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
//    SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
//    String output = sdf1.format(c.getTime());

}