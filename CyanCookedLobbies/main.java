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
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.SceneObject;

@Manifest(authors = { "ICyanide" }, description = "Fishes, cooks, notes cooked lobbies", name = "CyanCookedLobbies")
public class main extends ActiveScript implements PaintListener {

	private Tree container = null;
	private List<Node> jobs = new ArrayList<Node>();
	
	public void onStart() {
		System.out.println("Welcome to CyanCookedLobbies");
		
		jobs.add(new Fishing());
		jobs.add(new Cooking());;
		jobs.add(new Noting());
		jobs.add(new Traversing());
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
		String bonFireStatus = "null";
		SceneObject bonFire = SceneEntities.getNearest(70755);
		
		if(bonFire != null) {
			bonFireStatus = "There is one";
		} else {
			bonFireStatus = "There isn't one";
		}

		g.setColor(Mouse.isPressed() ? Color.BLUE : Color.CYAN);
		g.fillOval(p.x - 4, p.y - 4, 8, 8);
		g.setColor(Color.BLACK);
		g.drawOval(p.x - 4, p.y - 4, 8, 8);
		g.setColor(Color.RED);
		g.fillOval(p.x - 2, p.y - 2, 4, 4);
		
		g.setColor(Color.black);
		g.fillRect(7, 395, 505, 128);
		
		g.setColor(Color.CYAN); 
		g.drawString(String.format("Time Running: %s", Variables.timer.toElapsedString()), 15, 410);
		g.drawString("Status: "+ currentStatus, 15, 425);
		g.drawString("Animation: "+ Players.getLocal().getAnimation(), 15, 440);
		g.drawString("Bonfire Check: "+ bonFireStatus, 15, 455);
		
	}

}
