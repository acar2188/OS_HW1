
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
	
}
