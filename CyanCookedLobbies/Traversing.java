import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;


public class Traversing extends Node {

	@Override
	public boolean activate() {
		return !Areas.FishingArea.contains(Players.getLocal().getLocation())
				&& !Areas.NoteGuyArea.contains(Players.getLocal().getLocation())
				&& !Areas.TreeArea.contains(Players.getLocal().getLocation())
				&& Inventory.getCount() == 27 || Inventory.getCount() == 1;
	}

	@Override
	public void execute() {
		
		if(Inventory.getCount(381) > 0) {
			Variables.status = "Going back to fishing";
			Walking.newTilePath(Paths.BackToFish).traverse();
		}
		
		if(Inventory.getCount(379) == 26) {
			Variables.status = "Going to note guy";
			Walking.newTilePath(Paths.TreesToNoteGuy).traverse();
		}
		
		if(Inventory.getCount() == 1) {
			Variables.status = "Going back to fishing";
			Walking.newTilePath(Paths.NoteGuyToFish).traverse();
		}
		
		if(Inventory.getCount(377) > 0) {
			Variables.status = "Going to cook lobbies";
			Walking.newTilePath(Paths.FishToTrees).traverse();
		}
	}

}
