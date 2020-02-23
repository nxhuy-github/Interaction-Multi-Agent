package app;

import java.io.IOException;

import material.Agent;
import material.Position;

public class App {

	public static void main(String[] args) throws IOException{
		Position p1 = new Position(0, 0);
		Position p2 = new Position(0, 2);
		Position p3 = new Position (1, 2);
		Thread t1 = new Agent(1, 9, p1, p2);
		Thread t2 = new Agent(2, 9, p2, new Position(2, 2));
		Thread t3 = new Agent(3, 9, p3, p1);
		Thread t4 = new Agent(4, 9, new Position(2, 1), new Position(1, 1));
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}
