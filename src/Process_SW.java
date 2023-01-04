
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.ProcessBuilder;
import java.util.Objects;

import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;

public class Process_SW {

	 public static final String ANSI_RESET = "\u001B[0m";
	  
	    // Declaring the color
	    // Custom declaration
	    public static final String ANSI_YELLOW = "\u001B[33m";
	    
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
	boolean FirstRun;
	ProcessBuilder PB;
	Process HW_PB;
	
	Process_SW(int pid, int priority, int arriveTime, int brustTime)
	{
		PID = pid;
		Priority = priority;
		ArriveTime = arriveTime;
		BrustTime = brustTime;
		ProcessState = State.Create;
		FirstRun = true;
	}
	
	void Create(int sysTime)
	{
		if(ProcessState == State.Create)
		{
			// proses başladı			
			PB = new ProcessBuilder();
			ProcessState = State.Ready;		
		}
		
	}
	
	void Run(int sysTime)
	{		
		
		if(FirstRun)
		{
			System.out.printf("\u001B[38;5;%dm %d sn proses başladı.              (id:%d öncelik:%d kalan süre:%d sn)\r\n" + ANSI_RESET, 
																									PID*8+30,
																									sysTime, 
																									PID,
																									Priority,
																									BrustTime);			
			FirstRun = false;
			try
			{
				
				//PB.environment().put("Var", "Proceess OK");
				PB.command("notepad.exe","cikis.txt");
				HW_PB = PB.start();				
				int acar1=1;
				acar1++;
				
				//System.out.printf("PID: %d  \r\n", HW_PB.pid());
				//HW_PB.destroy();
			}
			catch (Exception e) {
		           
	            // catching the exception
	            System.out.println(e);
	        }		
			
		}
		else
		{
			// proses yürütülüyor
			System.out.printf("\u001B[38;5;%dm %d sn proses yürütülüyor.          (id:%d öncelik:%d kalan süre:%d sn)\r\n" + ANSI_RESET, 
																											PID*8+30,
																											sysTime, 
							 																				PID,
						 																					Priority,
						 																					BrustTime);
			synchronized(HW_PB){
				HW_PB.notify();
			}
		}
		
		ProcessState = State.Run;
							
		
		BrustTime--;
		// proses start
	}
	
	void Stop(int sysTime)
	{		
		if(BrustTime == 0)
		{
			// proses sonlandı 
			System.out.printf("\u001B[38;5;%dm %d sn proses sonlandı.             (id:%d öncelik:%d kalan süre:%d sn)\r\n"  + ANSI_RESET, 
																											PID*8+30, 	
																											sysTime, 
																									     	PID,
																									     	Priority,																						     	
																									     	BrustTime);
		    //HW_PB.destroy();
			ProcessState = State.Terminated;
			HW_PB.destroy();
			
		}
		else
		{
			System.out.printf("\u001B[38;5;%dm %d sn proses askıya alındı.        (id:%d öncelik:%d kalan süre:%d sn)\r\n" + ANSI_RESET, 
																												PID*8+30, 	
																												sysTime, 
																												PID,
																												Priority,
																												BrustTime);
			ProcessState = Process_SW.State.Ready;
			try
			{
				//HW_PB.wait();
				
				synchronized(HW_PB){
					HW_PB.wait(1);
				}
					
			}
			catch (Exception e) {
		           
	            // catching the exception
	            System.out.println(e);
	        }	
		}		
	}
}
