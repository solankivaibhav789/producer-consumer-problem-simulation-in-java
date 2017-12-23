
public class Semaphore {
int s;
	
	Semaphore(int s){
		this.s = s;
	}
	
	synchronized void up(){
		s++;
		notify();
	}
	
	synchronized void down(){
		if(s <= 0){
			try{
					wait();
			}
			catch(InterruptedException e){
				System.out.println("Thread intrrupted.");
			}
		}
		
		s--;
	}
}
