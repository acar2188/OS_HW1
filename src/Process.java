
public class Process {

	 public enum State {
	     Create,
	     Ready,
	     Run,
	     Terminated;
	 };
	 
	int PID;
	int Priority;
	int ArriveTime;
	int BrustTime;
	State ProcessState;
	
	Process(int pid, int priority, int arriveTime, int brustTime)
	{
		PID = pid;
		Priority = priority;
		ArriveTime = arriveTime;
		BrustTime = brustTime;
		ProcessState = State.Create;
	}
	
	void Run(int sysTime)
	{		
		if(ProcessState == State.Create)
		{
			// proses başladı
			System.out.printf("%d sn proses başladı.          (id:%d öncelik:%d kalan süre:%d sn)\r\n", sysTime, 
																									PID,
																									Priority,
																									BrustTime);
			ProcessState = State.Run;
		}
		else if(BrustTime == 1)
		{
			// proses sonlandı 
			System.out.printf("%d sn proses sonlandı.          (id:%d öncelik:%d kalan süre:%d sn)\r\n", sysTime, 
																									 PID,
																									 Priority,
																									 BrustTime);
			ProcessState = State.Terminated;
		}
		else
		{			
			// proses yürütülüyor
			System.out.printf("%d sn proses yürütülüyor.          (id:%d öncelik:%d kalan süre:%d sn)\r\n", sysTime, 
					 																					PID,
					 																					Priority,
					 																					BrustTime);
		}
		BrustTime--;
	}
}
