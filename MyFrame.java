import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MyFrame extends Frame implements ActionListener{
	
	SharedResource sharedResource;
	
	Label full_semaphore,empty_semaphore,main_mutex;
	
	Button produce,consume,reset;
	
	Checkbox me;
	
	MyStackPanel mystackpanel,myprocesspanel;
	
	public MyFrame(){
		super("Producer Consumer Problem");
		
		//Instantiation of Layout
		//3/27/2017
		//Created by Kaustubh
		
		setLayout(null);
		setVisible(true);
		setSize(700,650);//Leave margin of 50 from left and right side and 50 from top
		addWindowListener(new WindowAdapter(){  
            public void windowClosing(WindowEvent e) {  
                dispose();  
            }  
        });
		
		//End Instantiation
		
		//Start 3 Labels for Semaphore Values
		//3/27/2017
		//Created by Kaustubh
		
		full_semaphore = new Label();
		//full_semaphore.setBounds(x, y, width, height);
		//x = 50 + (600/8)
		//x = 125
		//y = 50 + (600/16)
		//y = 87.5
		//y = 87
		full_semaphore.setAlignment(Label.CENTER);
		full_semaphore.setBounds(125,87,75,75);
		full_semaphore.setBackground(Color.LIGHT_GRAY);
		add(full_semaphore);
		
		main_mutex = new Label();
		main_mutex.setAlignment(Label.CENTER);
		main_mutex.setBounds(312,87,75,75);//125 + 75 + 75 + 37.5
		main_mutex.setBackground(Color.LIGHT_GRAY);
		add(main_mutex);
		
		empty_semaphore = new Label();
		empty_semaphore.setAlignment(Label.CENTER);
		empty_semaphore.setBounds(500,87,75,75);//312 + 75 + 75 + 37.5
		empty_semaphore.setBackground(Color.LIGHT_GRAY);
		add(empty_semaphore);
		
		//End Semaphore Labels
		
		//Start 3 Buttons for Semaphore Values
		//3/27/2017
		//Created by Kaustubh
		
		produce = new Button("PRODUCE");
		produce.setBounds(87,237,150,75);//50 + 37//75+75+37.5+50
		produce.setBackground(Color.LIGHT_GRAY);
		produce.setActionCommand("PRODUCE");
		produce.addActionListener(this);
		add(produce);
		
		consume = new Button("CONSUME");
		consume.setBounds(87,350,150,75);//237 + 75 + 37.5
		consume.setBackground(Color.LIGHT_GRAY);
		consume.setActionCommand("CONSUME");
		consume.addActionListener(this);
		consume.disable();
		add(consume);
		
		reset = new Button("RESET");
		reset.setBounds(87,462,150,75);//350 + 75 + 37.5
		reset.setBackground(Color.LIGHT_GRAY);
		reset.setActionCommand("RESET");
		reset.addActionListener(this);
		add(reset);
		
		//End Buttons
		
		//Start CheckBox
		//3/27/2017
		//Created by Kaustubh
				
		me = new Checkbox();
		me.setBounds(500,537,75,75);//6*75 + 50//6*75 + 37.5 + 50
		me.setBackground(Color.LIGHT_GRAY);
		add(me);
		
		//End CheckBox
		
		
		//Start MyStackPanel
		//3/27/2017
		//Created by Kaustubh
				
		mystackpanel = new MyStackPanel(11);
		mystackpanel.setBounds(275, 200, 150, 412);//3*75 + 50//150 + 50//150//5*75 + 37.5
		mystackpanel.setBackground(Color.LIGHT_GRAY);
		add(mystackpanel);
				
		//End MyStackPanel
		
		
		sharedResource = new SharedResource(11);
		
		
		//Start MyProcessPanel
		//3/27/2017
		//Created by Kaustubh
						
		myprocesspanel = new MyStackPanel(5);
		myprocesspanel.setBounds(462, 237, 150, 187);//3*75 + 50//150 + 50//150//5*75 + 37.5
		myprocesspanel.setBackground(Color.LIGHT_GRAY);
		add(myprocesspanel);
						
		//End MyProcessPanel
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String s = ae.getActionCommand();
		String p;
		
		if(s.equals("PRODUCE")){
			
			for(int i=0;i<myprocesspanel.stack.length;i++){
				myprocesspanel.stack[i].setText("");
			}
			
			sharedResource.empty.down();
			System.out.println(p = "Producer "+"empty.down()");
			myprocesspanel.stack[1].setText(p);
			empty_semaphore.setText(Integer.toString(sharedResource.empty.s));
			new javax.swing.Timer(30000, new ActionListener(){
			    public void actionPerformed(ActionEvent e) {
//			        System.exit(0);
			    }
			}).start();
			
			sharedResource.mutex.down();
			System.out.println(p = "Producer "+"mutex.down()");
			myprocesspanel.stack[2].setText(p);
			main_mutex.setText(Integer.toString(sharedResource.mutex.s));
			new javax.swing.Timer(30000, new ActionListener(){
			    public void actionPerformed(ActionEvent e) {
//			        System.exit(0);
			    }
			}).start();
			
			
			
			sharedResource.a[++sharedResource.top] = new Random().nextInt();
			
			mystackpanel.stack[sharedResource.top].setText(Integer.toString(sharedResource.a[sharedResource.top]));
			mystackpanel.stack[sharedResource.top].setBackground(Color.GREEN);
			
			if(sharedResource.top == 0){
				consume.enable();
				myprocesspanel.stack[0].setText("Consumer Wakes Up");
			}
			
			if(sharedResource.top == sharedResource.a.length - 1){
				produce.disable();
				myprocesspanel.stack[0].setText("Producer Sleeps");
			}
			
			new javax.swing.Timer(30000, new ActionListener(){
			    public void actionPerformed(ActionEvent e) {
//			        System.exit(0);
			    }
			}).start();
			
			
			//System.out.println(sharedResource.a[sharedResource.top]+" "+name);
			sharedResource.mutex.up();
			System.out.println(p = "Producer "+"mutex.up()");
			myprocesspanel.stack[3].setText(p);
			main_mutex.setText(Integer.toString(sharedResource.mutex.s));
			new javax.swing.Timer(30000, new ActionListener(){
			    public void actionPerformed(ActionEvent e) {
//			        System.exit(0);
			    }
			}).start();
			
			
			sharedResource.full.up();
			System.out.println(p = "Producer "+"full.up()");
			myprocesspanel.stack[4].setText(p);
			System.out.println();
			full_semaphore.setText(Integer.toString(sharedResource.full.s));
			new javax.swing.Timer(30000, new ActionListener(){
			    public void actionPerformed(ActionEvent e) {
//			        System.exit(0);
			    }
			}).start();
			
			
			
		}else if(s.equals("CONSUME")){
			
			for(int i=0;i<myprocesspanel.stack.length;i++){
				myprocesspanel.stack[i].setText("");
			}
			
			sharedResource.full.down();
			System.out.println(p = "Consumer "+"full.down()");
			myprocesspanel.stack[1].setText(p);
			full_semaphore.setText(Integer.toString(sharedResource.full.s));
			new javax.swing.Timer(30000, new ActionListener(){
			    public void actionPerformed(ActionEvent e) {
//			        System.exit(0);
			    }
			}).start();
			
			
			sharedResource.mutex.down();
			System.out.println(p = "Consumer "+"mutex.down()");
			myprocesspanel.stack[2].setText(p);
			main_mutex.setText(Integer.toString(sharedResource.mutex.s));
			new javax.swing.Timer(30000, new ActionListener(){
			    public void actionPerformed(ActionEvent e) {
//			        System.exit(0);
			    }
			}).start();
			
			
			int item = sharedResource.a[sharedResource.top--];
			mystackpanel.stack[sharedResource.top+1].setText("");
			mystackpanel.stack[sharedResource.top+1].setBackground(Color.BLUE);
			
			if(sharedResource.top == -1){
				consume.disable();
				myprocesspanel.stack[0].setText("Consumer Sleeps");
			}
			
			if(sharedResource.top == sharedResource.a.length - 2){
				produce.enable();
				myprocesspanel.stack[0].setText("Producer Wakes Up");
			}
			
			new javax.swing.Timer(30000, new ActionListener(){
			    public void actionPerformed(ActionEvent e) {
			        //System.exit(0);
			    }
			}).start();
			
			
//			System.out.println(item+" "+name);
			sharedResource.mutex.up();
			System.out.println(p = "Consumer "+"mutex.up()");
			myprocesspanel.stack[3].setText(p);
			main_mutex.setText(Integer.toString(sharedResource.mutex.s));
			new javax.swing.Timer(30000, new ActionListener(){
			    public void actionPerformed(ActionEvent e) {
			        //System.exit(0);
			    }
			}).start();
			
			
			sharedResource.empty.up();
			System.out.println(p = "Consumer "+"empty.up()");
			myprocesspanel.stack[4].setText(p);
			System.out.println();
			empty_semaphore.setText(Integer.toString(sharedResource.empty.s));
			new javax.swing.Timer(30000, new ActionListener(){
			    public void actionPerformed(ActionEvent e) {
			        //System.exit(0);
			    }
			}).start();
			
			
			
		}else if(s.equals("RESET")){
			
		}else{
			
		}
	}
	
}
