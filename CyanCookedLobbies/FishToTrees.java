import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;


public class FishToTrees extends Node {

	@Override
	public boolean activate() {
		return (Inventory.getCount(377) + Inventory.getCount(379) == 24 
				&& Inventory.getCount(379) < 24)
				&& !Areas.FishingArea.contains(Players.getLocal().getLocation())
				&& !Areas.TreeArea.contains(Players.getLocal().getLocation())
				&& !Areas.NoteGuyArea.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		if(Inventory.getCount(377) > 0) {
			Walking.newTilePath(Paths.FishToTrees).traverse();
		}
	}

}
