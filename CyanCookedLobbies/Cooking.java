import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;


public class Cooking extends Node {

	
	int fireSpot = Random.nextInt(0, 7);
	WidgetChild cookButton = Widgets.get(1370).getChild(38);
	SceneObject tree = SceneEntities.getNearest(1276);
	
	
	@Override
	public boolean activate() {
		return Inventory.getCount(377) + Inventory.getCount(381) + Inventory.getCount(379) == 24
				&& Areas.TreeArea.contains(Players.getLocal().getLocation())
				&& !Areas.FishingArea.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		SceneObject bonFire = SceneEntities.getNearest(70755);
		
		if(tree != null && Inventory.getCount(1511) == 0 && bonFire == null && Inventory.getCount(377) > 0) {
			tree.interact("Chop");
	
			sleep(300, 500);
	
			while(Variables.twentySeconds.isRunning()) {
				if(Players.getLocal().getAnimation() == -1) {
					break;
				}
			}
		}
		
		if(Inventory.getCount(1511) == 1 && bonFire == null) {
			Inventory.getItem(1511).getWidgetChild().interact("Light");
			sleep(400, 500);
		}
		
		sleep(200, 300);
		if(bonFire != null) {
			if(bonFire.isOnScreen() && Inventory.getCount(377) > 0 ) {
				WidgetChild lobby = Inventory.getItem(377).getWidgetChild();
				
				lobby.interact("Use");
				sleep(50, 150);
				bonFire.click(true);
				sleep(20, 50);

			}
			cookButton.click(true);
			sleep(200, 400);
			
			while(Variables.sixtyfiveSeconds.isRunning()) {
				if(Players.getLocal().getAnimation() == -1) {
					break;
				}
			}
			
			
			
			
		}
		
		while(Variables.sixtyfiveSeconds.isRunning()) {
			if(Players.getLocal().isIdle()) {
				break;
			}
		}
		sleep(200, 300);
		
		if(Inventory.getCount(377) == 0) {
			Walking.walk(new Tile(2928, 3151, 0));
		}
	}
}
