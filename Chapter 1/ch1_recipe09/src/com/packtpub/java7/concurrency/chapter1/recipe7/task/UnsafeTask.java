package com.packtpub.java7.concurrency.chapter1.recipe7.task;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Class that shows the problem generate when some Thread objects
 * share a data structure
 *
 */
public class UnsafeTask implements Runnable{

	/**
	 * Date shared by all threads
	 * 通常情况下，所有的线程共享数据
	 */
	private Date startDate;
	
	/**
	 * Main method of the class. Saves the start date and writes
	 * it to the console when it starts and when it ends
	 * 
	 * 开始时间不同，结束时间相同。前提是在有结束前，三个线程都先启动了。在线程结束前，共享的变量被别人改变了。通过单步调试要快才能看出来。
	 */
	@Override
	public void run() {
		startDate=new Date();
		System.out.printf("Starting Thread: %s : %s\n",Thread.currentThread().getId(),startDate);
		try {
			TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Thread Finished: %s : %s\n",Thread.currentThread().getId(),startDate);
	}

}
