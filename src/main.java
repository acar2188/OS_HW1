import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.lang.Thread;

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
		JobDispatchList.add(new Process(2, 0 , 5, 6));
		JobDispatchList.add(new Process(3, 0 , 2, 8));
		JobDispatchList.add(new Process(4, 0 , 18, 12));
		JobDispatchList.add(new Process(5, 0 , 6, 5));		
		
		int tick = 0;
		for(;;)
		{
			if(!JobDispatchList.isEmpty())
			{
				for(int i =0 ; i<JobDispatchList.size(); i++ )
				{
					int arriveTime = JobDispatchList.get(i).ArriveTime;
					if(arriveTime >= tick)
					{
						//System.out.printf("%d PID Run ArriveTime:%d tick=%d\n", JobDispatchList.get(i).PID, arriveTime, tick);
						
						Process process = JobDispatchList.get(i);						
						
						// Proses ilgili kuyruğa eklenir.
						switch(process.Priority)
						{
							case 0: Queue_FCFS.add(process);
									break;
							case 1: Queue_RQ0.add(process);
									break;
							case 2: Queue_RQ1.add(process);
									break;
							case 3: Queue_RQ2.add(process);
									break;
						}
						
						// Başlatılacak görev listesinden kaldırır.
						JobDispatchList.remove(i);
						break;
					}
				}
			}
			else if(Queue_FCFS.isEmpty() &&
					Queue_RQ0.isEmpty() && 
					Queue_RQ1.isEmpty() &&
					Queue_RQ2.isEmpty())
			{
				
				System.out.printf("Program Sonlandi.");
				break;
			}
			
			// FCFS Yüksek öncelikli bir proses varsa
			if(!Queue_FCFS.isEmpty())
			{
				Process runProcess = Queue_FCFS.peek();				
				
				// Proses Start edilir.				
				runProcess.Run(tick);		
				
				// Proses bittiyse listeden çıkarılır.
				if(runProcess.ProcessState == Process.State.Terminated)
				{				
					Queue_FCFS.remove();			
				}
			}
				
			try
			{
				Thread.sleep((long)1);
			}
			catch (Exception e) {
		           
	            // catching the exception
	            System.out.println(e);
	        }		
			tick++;
		}
	}

}
