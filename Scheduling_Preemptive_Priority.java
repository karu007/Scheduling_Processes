import java.util.*;
//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
class SchedulingFunctionsClass implements Comparable<SchedulingFunctionsClass>
{
	private String process_name;
	private int burst_time,arrival_time,waiting_time,turn_around_time,priority;
	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	SchedulingFunctionsClass(String name,int burst_time,int arrival_time,int priority)
	{
		process_name=name;
		this.burst_time=burst_time;
		this.arrival_time=arrival_time;
		this.priority=priority;
	}
	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	static void take_input(SchedulingFunctionsClass task[])
	{
		int n=task.length;
		Scanner sc=new Scanner(System.in);
		System.out.println("________________________________________________________");
		System.out.print("PROCESS\t\tBURST TIME\tARRIVAL TIME\tPRIORITY\n");
		System.out.println("________________________________________________________");
		for(int i=0;i<n;i++)
		{
			System.out.print("P"+(i+1)+"\t\t");
			String name="P"+(i+1);
			int burst_time=sc.nextInt();
			int arrival_time=sc.nextInt();
			int priority=sc.nextInt();
			task[i] = new SchedulingFunctionsClass(name,burst_time,arrival_time,priority);
		}
		System.out.println("________________________________________________________");
	}
	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	public int compareTo(SchedulingFunctionsClass task)
	{
		if(this.priority==task.priority)
			return this.arrival_time - task.arrival_time;
        return this.priority - task.priority;
    }
	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    static void PP(SchedulingFunctionsClass task[])
    {
    	int time=0,n=task.length;
    	for(int i=0;i<n;i++)
    	{
    		task[i].waiting_time=time-task[i].arrival_time;
    		time+=task[i].burst_time;
    		task[i].turn_around_time=time-task[i].arrival_time;
    	}
    }
	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	static double averageWaitingTime(SchedulingFunctionsClass task[])
	{
		int sum=0,n=task.length;
		for(int i=0;i<n;i++)
			sum+=task[i].waiting_time;
		return ((double)sum/n);
	}
	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
	static double averageTurnAroundTime(SchedulingFunctionsClass task[])
	{
		int sum=0,n=task.length;
		for(int i=0;i<n;i++)
			sum+=task[i].turn_around_time;
		return ((double)sum/n);
	}
	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
}
//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
class Scheduling_Preemptive_Priority
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int noOfProcesses;
		System.out.print("Enter Number Of Processes:");
		noOfProcesses=sc.nextInt();

		SchedulingFunctionsClass task[]=new SchedulingFunctionsClass[noOfProcesses];
		SchedulingFunctionsClass.take_input(task);//Input Burst, Arrival Time And Priority
		Arrays.sort(task);//Sorting According To Priority And Then Arrival Time

		SchedulingFunctionsClass.PP(task);//Calculating Waiting And Turn Around Time Of Each Process
		System.out.println("Average Waiting Time: " + SchedulingFunctionsClass.averageWaitingTime(task));
		System.out.println("Average Turn Around Time: " + SchedulingFunctionsClass.averageTurnAroundTime(task));

	}
}
//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
