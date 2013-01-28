import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.interactive.NPC;


public class Noting extends Node {

	@Override
	public boolean activate() {
		return (Inventory.getCount(379) == 24 || Inventory.getCount(379) == 0)
			&& Areas.NoteGuyArea.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		NPC stiles = NPCs.getNearest(11267);
		
		if(Inventory.getCount(379) == 24) {
			if(stiles.isOnScreen()) {
				stiles.interact("Exchange");
				
				while(Inventory.getCount(379) > 0) {
					sleep(10, 20);
				}
			}
			sleep(200, 300);
			
			while(Variables.twentySeconds.isRunning()){
				if(Players.getLocal().isIdle()) {
					break;
				}
			}
		}
		
		if(Inventory.getCount(379) == 0) {
			while(!Areas.FishingArea.contains(Players.getLocal().getLocation())) {
				Walking.newTilePath(Paths.NoteGuyToFish).traverse();
			}
		}
	}

}
