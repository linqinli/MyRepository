package cn.linqinli;

import java.util.Queue;
import java.util.ArrayDeque;

public class WorkQueue {
	private Queue<Runnable> tasks;
	
	public WorkQueue() {
		tasks = new ArrayDeque<Runnable>();
		for(int i = 0; i < 3; i++) {
			Thread th = new MyThread();
			th.setName("Thread " + i + ": ");
			th.start();
		}
	}
	
	public void addTask(Runnable task) {
		tasks.add(task);
	}
	
	private class MyThread extends Thread {
		private Runnable r;

		public void run() {
			while (true) {
				r = null;
				if (!tasks.isEmpty()) {
					synchronized (tasks) {
						if (!tasks.isEmpty()) {
							r = tasks.poll();
						}
						tasks.notify();
					}
					if (r != null)
						r.run();
				}
			}
		}
	}

	public static void main(String[] args) {
		WorkQueue q = new WorkQueue();
		q.addTask(new Runnable() {
			public void run() {
				try {
					Thread.currentThread().sleep(3000);
					System.out.print(Thread.currentThread().getName());
					System.out.println(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		q.addTask(new Runnable() {
			public void run() {
				try {
					Thread.currentThread().sleep(2000);
					System.out.print(Thread.currentThread().getName());
					System.out.println(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		q.addTask(new Runnable() {
			public void run() {
				try {
					Thread.currentThread().sleep(1000);
					System.out.print(Thread.currentThread().getName());
					System.out.println(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		System.out.println(0);
	}

}
