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
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

@Manifest(authors = { "ICyanide" }, description = "Makes Mist Runes", name = "CyanMistRunes")
public class main extends ActiveScript implements PaintListener{

	private Tree container = null;
	private List<Node> jobs = new ArrayList<Node>();
	public static Timer timer = new Timer(0);
	WidgetChild ChatFilter = Widgets.get(751).getChild(34);
	
	public void onStart() {
		System.out.println("Welcom to CyanMistRunes");
		
		jobs.add(new Banking());
		jobs.add(new Traversing());
		jobs.add(new Crafting());
		container = new Tree(jobs.toArray((new Node[jobs.size()])));
	}
	
	public void onStop() {
		System.out.println("");
	}
	
	@Override
	public int loop() {

		if(container != null) {
			final Node job = container.state();
			if(job != null) {
				container.set(job);
				getContainer().submit(job);
				job.join();
			}
		}
		return Random.nextInt(10, 60);
	}

	@Override
	public void onRepaint(Graphics g1) {
		final Graphics2D g = (Graphics2D) g1;
		final Point p = Mouse.getLocation();
		String currentStatus = Variables.status;
		int totalMistRunes = Variables.numMistRunes;
		int totalRuns = Variables.numRuns;
		int runsPH = (totalRuns * 3600000) / (int)timer.getElapsed();
		int profit = Variables.price * totalMistRunes;
		int profitPH = (3600000 / (int)timer.getElapsed()) * profit;
		int totalProfit = profit - (Variables.totalCost * totalMistRunes);
		
		g.setColor(Mouse.isPressed() ? Color.BLUE : Color.CYAN);
		g.fillOval(p.x - 4, p.y - 4, 8, 8);
		g.setColor(Color.BLACK);
		g.drawOval(p.x - 4, p.y - 4, 8, 8);
		g.setColor(Color.RED);
		g.fillOval(p.x - 2, p.y - 2, 4, 4);
		
		g.setColor(Color.black);
		g.fillRect(7, 395, 505, 128);
		
		g.setColor(Color.green);
		g.drawString(String.format("Time Running: %s", timer.toElapsedString()), 15, 410);
		g.drawString("Status: "+ currentStatus, 15, 425);
		g.drawString("Mist runes made: "+ totalMistRunes, 15, 440);
		g.drawString("Total runs: "+ totalRuns, 15, 455);
		g.drawString("Runs per hour: " + runsPH, 15, 470);
		g.drawString("Profit: "+ profit, 15, 485);
		g.drawString("Profit per hour: " + profitPH, 15, 500);
		g.drawString("Price of Mist rune: " +  Variables.price, 230, 410);
		g.drawString("Total cost per rune: " +  Variables.totalCost, 230, 425);
		g.drawString("Total Profit per rune: " + (Variables.price - Variables.totalCost), 230, 440);
		g.drawString("Player is : " + Players.getLocal().isIdle(), 230, 455);
		g.drawString("Total Profit: " +  totalProfit, 400, 515);
	}
}
