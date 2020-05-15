package ie.tudublin;

import processing.core.PApplet;
import processing.core.PFont;
import processing.data.Table;
import processing.data.TableRow;

import java.util.ArrayList;


public class Gantt extends PApplet
{	
	
	ArrayList<Task> tasks = new ArrayList<Task>();
	ArrayList<Float> front = new ArrayList<Float>();
	ArrayList<Float> back = new ArrayList<Float>();
	Table table;
	PFont F;

	public void settings()
	{
		size(800, 600);

	}

	public void loadTasks()
	{
		table = loadTable("tasks.csv" , "header");
	
		for( TableRow row : table.rows())
		{
			Task task = new Task(row);
			tasks.add(task);
			
		}

	}

	public void printTasks()
	{
		for(Task I: tasks )
		{
			System.out.println(I);
		}
	}

	private float Map(float xco){ // map the values of the tasks.csv sheet onto screen resolution

			return map(xco , 0 ,30 , 100 , 750);
	}

	private void fillValues(){ // fills the values from Task into front and back
		
		
		for(Task task : tasks){
			front.add(Map(task.Start()));
			back.add(Map(task.End()));  
		}
	}


	
	public void mousePressed()
	{
		//println("Mouse pressed");
	
	}

	public void mouseDragged()
	{
		//println("Mouse dragged");
		Change();	
	}

	
	
	public void setup() 
	{
		F = createFont("Arial", 16 , true);

		loadTasks();
		printTasks();
		fillValues();
		
	
	}
	
	public void draw()
	{			

		background(0);
		displayTasks();


	}

	public void displayTasks(){ 

		colorMode(HSB);
		int colour = 0;
		float top = 30;
		float bottom = 60;
		int I = 0;
		int C = 0;

		for(float i = 1 ; i < 31 ; i++){ // cerate grid and scale
			noFill();
			textFont(F);
			stroke(100 , 0 , 100);
			
			float X = Map(i);
 
			
			textAlign(CENTER);
			text( I , X , 15);


			rect(X , 30 , 0 , 540 );
			I = I + 1;
		}

		for(Task task : tasks){ // display tasks as colored rectangles and text using front and back
			
			noFill();
			
			
			stroke(colour , 100 , 100 );
			fill(colour , 100 , 100 );

			float start = front.get(C);
			float end = back.get(C);
			String job = task.Job();

			end = end - start;

			textFont(F);
			textAlign(CENTER);
			text( job , 50 , (top + 30 ));

			
			rect(start, top , end , bottom); 

			colour = (colour + 25)%360;
			top = top + 60;
			C = C + 1 ;

		}
	}

	public void Change(){


		float xF = 0;
		float xB = 0;
		

		for(int i = 0 ; i+1 < tasks.size() ; i++)
		{

			float first = front.get(i);
			float last = back.get(i);
			int top = 30 + i*60;
			int bottom = 60 + i*60;
			if((mouseY > top && mouseY < bottom)) // if mouse is in line with a rectange
			{
				
					if(mouseX > first - 5 && mouseX < (first + 20) )
					{
						xF = mouseX;
						if(mouseX > 120 && mouseX < back.get(i)){
						front.set(i , xF);
						}
					}else if(mouseX < last + 5 && mouseX > (last - 20) )
					{
						xB = mouseX;
						if(mouseX < 750 && mouseX > front.get(i)){
						back.set(i , xB);
						}
					}

					
			System.out.println(back.get(i));			
			}	
		}

	}

}
