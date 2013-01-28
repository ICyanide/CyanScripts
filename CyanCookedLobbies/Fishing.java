import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.WidgetChild;


public class Fishing extends Node {
	
	WidgetChild nextButton = Widgets.get(1186).getChild(7);
	
	@Override
	public boolean activate() {
		return (Inventory.getCount(377) + Inventory.getCount(379)) <= 26
				&& Players.getLocal().getAnimation() != 619
				&& Areas.FishingArea.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		System.out.println("Fishing");
		
		if(Inventory.getCount(381) > 0) {
			for(Item i : Inventory.getItems() ) {
				if(i.getId() == 381) {
					i.getWidgetChild().interact("Drop");
				}
			}
		}
		
		Camera.turnTo(NPCs.getNearest(324));
		
		if(((Inventory.getCount(377) + Inventory.getCount(379)) < 26)) {
			NPC lobbySpot = NPCs.getNearest(Variables.fishingSpot);
			
			if(lobbySpot != null && lobbySpot.isOnScreen() && Inventory.getCount(377) + Inventory.getCount(379) < 26) {
				lobbySpot.interact("Cage");
				sleep(200, 300);
				
				while(Variables.twentySeconds.isRunning()) {
					if(Players.getLocal().getAnimation() == 619) {
						break;
					}
				}
			}
			sleep(400, 500);
			
			while(Inventory.getCount(377) + Inventory.getCount(379) != 26) {
				if(Players.getLocal().getAnimation() == -1) {
					break;
				}
				sleep(10, 15);
			}
			sleep(100, 200);
			
		}
		
		if(Inventory.getCount(377) + Inventory.getCount(379) == 26) {
			Walking.walk(new Tile(2922, 3169, 0));
		}
	} 
}