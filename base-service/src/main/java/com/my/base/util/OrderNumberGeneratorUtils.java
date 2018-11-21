package com.my.base.util;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 订单号生成工具
 * 流水号： 系统+表+年月日时分秒+3位自增循环数（000-999）
 * 
 */
public class OrderNumberGeneratorUtils {
    private static final ConcurrentLinkedQueue<Integer> queue          = new ConcurrentLinkedQueue<Integer>();
    private static final CountDownLatch                 latch          = new CountDownLatch(1);
    /**
     * 每毫秒生成订单号数量最大值，约定取整百，整千。
     */
    public static final int                             maxPerMSECSize = 1000;

    private static void init() {
        for (int i = 0; i < maxPerMSECSize; i++) {
            queue.offer(i);
        }
        latch.countDown();
    }

    public static Integer poll() {
        try {
            if (latch.getCount() > 0) {
                init();
                latch.await(1, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer i = queue.poll();
        queue.offer(i);
        return i;
    }

    public static String get() {
        long nowLong = Long
            .parseLong(DateUtils.getDateTime(new Date(), DateUtils.DATA_FORMAT_PATTERN_5));
        String number = maxPerMSECSize + poll() + "";
        return nowLong + number.substring(1);
    }

    public static String get(String system) {
        long nowLong = Long
            .parseLong(DateUtils.getDateTime(new Date(), DateUtils.DATA_FORMAT_PATTERN_5));
        String number = maxPerMSECSize + poll() + "";
        return system + nowLong + number.substring(1);
    }

    public static String get(String system, String tableName) {
        long nowLong = Long
            .parseLong(DateUtils.getDateTime(new Date(), DateUtils.DATA_FORMAT_PATTERN_5));
        String number = maxPerMSECSize + poll() + "";
        return system + tableName + nowLong + number.substring(1);
    }
}
