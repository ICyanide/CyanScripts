import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;


public class ToNoting extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(379) == 24
				&& !Areas.TreeArea.contains(Players.getLocal().getLocation())
				&& !Areas.FishingArea.contains(Players.getLocal().getLocation())
				&& !Areas.NoteGuyArea.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		sleep(150, 200);
		
		Walking.newTilePath(Paths.TreesToNoteGuy).traverse();
	}

}
