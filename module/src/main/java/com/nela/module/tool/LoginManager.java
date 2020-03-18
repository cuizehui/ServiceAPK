package com.nela.module.tool;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Log;

import com.nela.module.NApplication;
import com.nela.module.service.SchedulerService;

public class LoginManager {

    private final static String TAG = LoginManager.class.getSimpleName();
    public static Integer sScheduleNum = 0;
    private final static int SCHEDULE_ID = 19871201;
    private final static int MIN_SCHEDULE_TIME = 2000;
    private static long sNextScheduledTime = MIN_SCHEDULE_TIME;
    public final static String SCHEDULE_NUM = "SCHEDULE_NUM";

    //计时登录
    public static void scheduleLogin() {
        long delayMilTime = getPolicyDelayTime();
        // 根据策略获得延时，如果是0则可以继续执行，>0则延后执行
        if (delayMilTime > 0) {
            Log.d(TAG, "policy delay " + delayMilTime);
            sNextScheduledTime = delayMilTime;
            cancelSchedule();
            scheduleJob();
            return;
        }
    }

    private static void scheduleJob() {
        // 无网情况下，无需自动切网或者已经第一次尝试登录过则不起 job
        if (!NetUtils.checkNet(NApplication.sContext)) {
            Log.d(TAG, "not scheduleJob " + sScheduleNum);
            return;
        }
        // 要运行的任务的标识符 Service组件的类名
        JobInfo.Builder builder = new JobInfo.Builder(SCHEDULE_ID, new ComponentName(NApplication.sContext, SchedulerService.class));
        //设置最少延迟多久后执行，单位毫秒 相当于 post delay
        builder.setMinimumLatency(sNextScheduledTime);
        //设置任务最晚的延迟时间 如果到了规定的时间时其他条件还未满足，任务也会被启动
        builder.setOverrideDeadline(sNextScheduledTime);
        PersistableBundle bundle = new PersistableBundle(1);
        bundle.putInt(LoginManager.SCHEDULE_NUM, ++sScheduleNum);
        builder.setExtras(bundle);
        JobInfo ji = builder.build();
        JobScheduler js = (JobScheduler) NApplication.sContext.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        js.schedule(ji);
    }

    private static long getPolicyDelayTime() {
        long now = System.currentTimeMillis();
        if (Utils.getLoginForbiddenTimesToday() > Utils.getMaxLoginForbiddenOneday()) {
            //    MtcLog.log(TAG, "forbidden over times");
            return 24 * 3600 * 1000 - now % (24 * 3600 * 1000);
        }
        if (Utils.getLoginTimesOneHour() > Utils.getMaxLoginOneHour()) {
            //    MtcLog.log(TAG, "one hour over times");
            return 3600 * 1000 - now % (3600 * 1000);
        }
        if (Utils.getRetryTimesToday() > Utils.getMaxRetryOneToday()) {
            //      MtcLog.log(TAG, "retry over times");
            return 24 * 3600 * 1000 - now % (24 * 3600 * 1000);
        }
        return 500;
    }

    private static void cancelSchedule() {
        JobScheduler js = (JobScheduler) NApplication.sContext.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        js.cancel(SCHEDULE_ID);
    }
}
