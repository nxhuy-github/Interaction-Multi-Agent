package material;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Agent extends Thread{
	private int index;
	private int sizeGrill;
	private Position currentPosition;
	private Position terminalPosition;
	
	private static ConcurrentHashMap<Agent, ArrayList<String>> table = new ConcurrentHashMap<Agent, ArrayList<String>>();
	
	public Agent(int id, int size, Position currPos, Position terPos) {
		this.index = id;
		this.currentPosition = currPos;
		this.sizeGrill = size;
		this.terminalPosition = terPos; 
		table.putIfAbsent(this, new ArrayList<String>());
	}
	
	@Override
	public void run() {
		System.out.println("Send message("+this.index+")");
		try {
			sendMessage("Can "+this.index+" move " + this.terminalPosition.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ArrayList<String> value = table.get(this);
		    System.out.println("Messages what "+this.index+" need to handle: "+value.toString());
		}
	}
	
	public void handleMessage() {
		
	}
	
	public void sendMessage(String message) {
		synchronized(this) {
			try {
				ConcurrentHashMap.KeySetView<Agent, ArrayList<String>> keySetView = table.keySet();
				Iterator<Agent> ite = keySetView.iterator();
				while (ite.hasNext()) {
					Agent a = ite.next();
					if (a.getIndex() != this.index) {
						ArrayList<String> tmp = table.get(a);
						tmp.add(message);
						table.replace(a, tmp);
						//table.get(a).add(message);
					}
				}
				//Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}

	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getSizeGrill() {
		return sizeGrill;
	}

	public void setSizeGrill(int sizeGrill) {
		this.sizeGrill = sizeGrill;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Position getTerminalPosition() {
		return terminalPosition;
	}

	public void setTerminalPosition(Position terminalPosition) {
		this.terminalPosition = terminalPosition;
	}
}
