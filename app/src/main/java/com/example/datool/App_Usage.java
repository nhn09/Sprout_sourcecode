package com.example.datool;

import androidx.appcompat.app.AppCompatActivity;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class App_Usage extends AppCompatActivity {

    int trackDuration ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app__usage);

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