import java.util.ArrayList;
import java.util.List;

import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.util.Random;

@Manifest(authors = { "ICyanide" }, description = "Fishes, cooks, notes cooked lobbies", name = "CyanCookedLobbies")
public class main extends ActiveScript {

	private Tree container = null;
	private List<Node> jobs = new ArrayList<Node>();
	
	public void onStart() {
		System.out.println("Welcome to CyanCookedLobbies");
		
		jobs.add(new Fishing());
		jobs.add(new FishToTrees());
		jobs.add(new Cooking());
		jobs.add(new BackToFish());
		jobs.add(new ToNoting());
		jobs.add(new Noting());
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

}
