import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;


public class Cooking extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount() >= 27 
				&& Areas.TreeArea.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		WidgetChild cookButton = Widgets.get(1370).getChild(38);
		SceneObject tree = SceneEntities.getNearest(1276);
		SceneObject bonFire = SceneEntities.getNearest(70755);
		
		if(bonFire == null) {
			if(Inventory.getCount(1511) == 0 && tree != null && tree.isOnScreen()) {
				Variables.status = "Getting logs";
				tree.interact("Chop");
				
				while(Variables.twentySeconds.isRunning()) {
					if(Players.getLocal().getAnimation() == 879) {
						break;
					} else {
						tree.interact("Chop");
					}
				}
				
				sleep(3000, 4000);
				
			} else if(Inventory.getCount(1511) == 1) {
				Variables.status = "Making a bonfire";
				Inventory.getItem(1511).getWidgetChild().interact("Light");
				sleep(200, 300);
			}

			while(Variables.twentySeconds.isRunning()) {
				if(Players.getLocal().getAnimation() == -1) {
					break;
				}
			}
		}
		
		if(bonFire != null && bonFire.isOnScreen()) {
			if(Inventory.getCount(377) > 0 
					&& !Widgets.get(1251).getChild(17).isOnScreen()) {
				Variables.status = "Cooking the Lobbies";
				
				Camera.turnTo(bonFire);
				Inventory.getItem(377).getWidgetChild().interact("Use");
				sleep(100, 200);
				bonFire.interact("Use");
				sleep(150, 200);
				
				while(!cookButton.isOnScreen()){
					sleep(10);
				}
				
				cookButton.click(true);
				sleep(5000, 6000);
				
			}
		}
		
		if(Inventory.getCount(377) == 0) {
			Variables.status = "Leaving tree area";
			Walking.findPath(new Tile(2928, 3152, 0)).traverse();
		}
	}
}
