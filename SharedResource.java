
public class SharedResource {
	int a[];
	int top;
	Semaphore mutex;
	Semaphore empty;
	Semaphore full; 
	SharedResource(int size){
		top = -1;
		a = new int[size];
		mutex = new Semaphore(1);
		empty = new Semaphore(size);
		full = new Semaphore (0);	
	}
	synchronized void display()
	{
		
		for(int i=0;i<top;i++)
		{
			System.out.println(a[i]);
		}
	}

}
