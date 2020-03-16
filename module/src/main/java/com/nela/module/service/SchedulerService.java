package com.nela.module.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

import com.nela.module.tool.LoginManager;

public class SchedulerService extends JobService {

    private final static String TAG = SchedulerService.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Intent intent = new Intent("com.nela.action.schedule_login");
        intent.setPackage(getPackageName());
        intent.putExtra(LoginManager.SCHEDULE_NUM, jobParameters.getExtras().getInt(LoginManager.SCHEDULE_NUM));
        sendBroadcast(intent);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        // 有且仅有onStartJob返回值为true时，才会调用onStopJob来销毁job
        // 返回false来销毁这个工作
        return false;
    }
}
