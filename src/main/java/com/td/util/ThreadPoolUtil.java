package com.td.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName: ThreadPoolUtil
 * @Description: 简单线程池
 * @author 米雪铭
 * @date: 2017年7月18日 上午11:52:33
 */
public class ThreadPoolUtil {

	/**
	 * 定长线程池
	 */
	private static final ExecutorService CACHEDTHREADPOOL = Executors.newFixedThreadPool(8);

	public static void execute(Runnable runnable) {
		if (StringUtil.isNotNull(runnable))
			CACHEDTHREADPOOL.execute(runnable);
	}

	public static <T> Future<T> submit(Callable<T> callable) {
		if (StringUtil.isNotNull(callable))
			return CACHEDTHREADPOOL.submit(callable);
		return null;
	}
}
