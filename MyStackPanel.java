import java.awt.Color;
import java.awt.Label;
import java.awt.Panel;


public class MyStackPanel extends Panel{
	Label[] stack;
	
	public MyStackPanel(int n) {
		setLayout(null);
		setVisible(true);
		stack = new Label[n];
		
		for(int i=0;i<stack.length;i++){
			stack[i] = new Label();
			stack[i].setBounds(0,i*37,150,37);
			//if(i%2==0){
				stack[i].setBackground(Color.blue);
			//}else{
			//	stack[i].setBackground(Color.GREEN);
			//}
			add(stack[i]);
		}
	}
}
