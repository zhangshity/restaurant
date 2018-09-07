package com.zcy.restaurant.config;

import com.zcy.restaurant.utils.QueueNumberCreateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ Author: chunyang.zhang
 * @ Description:
 * @ Date: Created in
 * @ Modified: By:
 */
@Component
public class TimeTaskConfig {

    @Scheduled(cron = "0 0 0 1/1 * ? ")//cron表达式,每天24:00过后执行
    public void doTask() {
        QueueNumberCreateUtil.clearTime();
    }


}
