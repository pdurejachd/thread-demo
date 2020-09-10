package com.pd.demo;

import java.util.ArrayList;
import java.util.List;

public class TaskExecutor {
	
	private int count = 0;
	private List<Task> taskList = new ArrayList<Task>();
	
	public static void main(String[] args) {
		TaskExecutor taskExecutor = new TaskExecutor();
		taskExecutor.initTaskList();
		long startTime = System.currentTimeMillis();
		taskExecutor.doWork();
		long completionTime = System.currentTimeMillis();
		taskExecutor.drawLine();
		System.out.println("TOTAL TIME(in seconds): "+(completionTime-startTime)/1000);
		System.out.println("ALL TASKS COMPLETED...EXITING APPLICATION... ");
		taskExecutor.drawLine();
		System.exit(0);
	}
	
	public List<Task> initTaskList() {
		System.out.println("Initializing Task list...");
		drawLine();
		for(int i=0; i < 20; i++) {
			Task task = new Task("TASK-"+i);
			System.out.println(task.getTaskName()+" CREATED");
			taskList.add(task);
		}
		drawLine();
		return taskList;
	}
	
	public  void processTask() {
		if(getCount() < 20) {
		Task task = taskList.get(getCount());
		if(task.getStatus().equalsIgnoreCase("NEW")) {
			task.setStatus("STARTED");
			System.out.println(Thread.currentThread().getName()+"   "+task.getTaskName()+" "+task.getStatus());
			
			try {
				//int randomDelay = (int)(1000.0 * Math.random());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			task.setStatus("COMPLETED");
			System.out.println(Thread.currentThread().getName()+"   "+task.getTaskName()+" "+task.getStatus());
		}else {
			increment();
		}
		}
	}
	
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				while(count < 20) {
					processTask();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				
				while(count < 20) {
					processTask();
				}
				
			}
		});
		
		t1.start();
		t2.start();
		
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized void increment() {
		count++;
	}
	
	public synchronized int getCount() {
		return count;
	}
	
	public void drawLine() {
		for(int i=0; i<80; i++)
			System.out.print("=");
		System.out.println();
	
	}

}
