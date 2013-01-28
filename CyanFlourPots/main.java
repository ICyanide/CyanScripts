import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

@Manifest(authors = { "ICyanide" }, description = "Makes pots of flour", name = "CyanFlourPots")
public class main extends ActiveScript implements PaintListener {
	
	private Tree container = null;
	private List<Node> jobs = new ArrayList<Node>();
	public static Timer timer = new Timer(0);
	WidgetChild ChatFilter = Widgets.get(751).getChild(34);
	
	public void onStart() {		
		System.out.println("Welcome to CyanFlourPots");
		jobs.add(new Banking());
		jobs.add(new BankToWheat());
		jobs.add(new GetWheat());
		jobs.add(new WheatToGuild());
		jobs.add(new MakeFlour());
		jobs.add(new GuildToBank());
		container = new Tree(jobs.toArray((new Node[jobs.size()])));
	}

	public void onStop() {
		System.out.println("Thanks for using CyanWheatPicker!");
		System.out.println("");
		
	}
	
	@Override
	public int loop() {
		if(ChatFilter.getText() == "All") {
			ChatFilter.interact("Filter");
		}
		
		if(container != null) {
			final Node job = container.state();
			if(job != null) {
				container.set(job);
				getContainer().submit(job);
				job.join();
			}
		}

		
		Variables.NumberOfFlourPotsINV = Inventory.getCount(1933);
		return Random.nextInt(10, 60);
	}
	
	public void onRepaint(Graphics g1) {
		final Graphics2D g = (Graphics2D) g1;
		final Point p = Mouse.getLocation();
		int TotalFlourPots = Variables.NumberOfFlourPotsINV + Variables.NumberOfFlourPotsBNK;
		int FlourPotsPerHour = (int) ((TotalFlourPots * 3600000D) / timer.getElapsed());
		String currentStatus = Variables.status;
		
		g.setColor(Mouse.isPressed() ? Color.BLUE : Color.CYAN);
		g.drawOval(p.x - 4, p.y - 4, 8, 8);
		
		g.setColor(Color.BLACK); 
		g.drawString(String.format("Time Running: %s", timer.toElapsedString()), 15, 410);
		g.drawString("FlourPots Per Hour: " + FlourPotsPerHour, 15, 425);
		g.drawString("FlourPots In Total: " + TotalFlourPots, 15, 440);
		g.drawString("Status: "+ currentStatus, 15, 455);
		
	}

}
