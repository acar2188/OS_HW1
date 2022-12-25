import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Process> JobDispatchList = new LinkedList<Process>();
		Queue<Process> Queue_FCFS = new LinkedList<Process>();
		Queue<Process> Queue_RQ0 = new LinkedList<Process>();
		Queue<Process> Queue_RQ1 = new LinkedList<Process>();
		Queue<Process> Queue_RQ2 = new LinkedList<Process>();
		
		// TODO: DOSYADAN OKUNACAK
		
		JobDispatchList.add(new Process(1, 0 , 0, 10));
		JobDispatchList.add(new Process(2, 1 , 5, 6));
		JobDispatchList.add(new Process(3, 2 , 2, 8));
		JobDispatchList.add(new Process(4, 3 , 18, 12));
		JobDispatchList.add(new Process(5, 1 , 6, 5));		
		
		int tick = 0;
		for(;;)
		{
			if(!JobDispatchList.isEmpty())
			{
				for(int i =0 ; i<JobDispatchList.size(); i++ )
				{
					int arriveTime = JobDispatchList.get(i).ArriveTime;
					if(arriveTime < tick)
					{
						System.out.printf("%d PID Run ArriveTime:%d tick=%d\n", JobDispatchList.get(i).PID, arriveTime, tick);
						JobDispatchList.remove(i);
						break;
					}
				}
			}
			else
				break;
			
			
			//Thread.sleep(1000);	
			tick++;
		}
		
		
		//myList.PID
		System.out.printf("JAVA PROJESI Bitti");

	}

}
