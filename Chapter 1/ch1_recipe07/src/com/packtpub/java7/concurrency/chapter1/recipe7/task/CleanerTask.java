package com.packtpub.java7.concurrency.chapter1.recipe7.task;

import java.util.Date;
import java.util.Deque;

import com.packtpub.java7.concurrency.chapter1.recipe7.event.Event;

/**
 * Class that review the Event data structure and delete
 * the events older than ten seconds
 *
 */
public class CleanerTask extends Thread {

	/**
	 * Data structure that stores events
	 */
	private Deque<Event> deque;

	/**
	 * Constructor of the class
	 * @param deque data structure that stores events
	 */
	public CleanerTask(Deque<Event> deque) {
		this.deque = deque;
		// Establish that this is a Daemon Thread
		//把这个线程作为后台运行的线程
//		用户线程：Java虚拟机在它所有非守护线程已经离开后自动离开。
//		守护线程：守护线程则是用来服务用户线程的，如果没有其他用户线程在运行，
//		那么就没有可服务对象，也就没有理由继续下去。
		setDaemon(true);
	}


	/**
	 * Main method of the class
	 */
	@Override
	public void run() {
		while (true) {
			Date date = new Date();
			clean(date);
		}
	}

	/**
	 * Method that review the Events data structure and delete
	 * the events older than ten seconds
	 * @param date
	 */
	private void clean(Date date) {
		long difference;
		boolean delete;
		
		if (deque.size()==0) {
			return;
		}
		
		delete=false;
		do {
			Event e = deque.getLast();
			difference = date.getTime() - e.getDate().getTime();
			if (difference > 10000) {
				System.out.printf("Cleaner: %s\n",e.getEvent());
				deque.removeLast();
				delete=true;
			}	
		} while (difference > 10000);
		if (delete){
			System.out.printf("Cleaner: Size of the queue: %d\n",deque.size());
		}
	}
}
