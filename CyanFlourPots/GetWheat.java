import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class GetWheat extends Node {

	@Override
	public boolean activate() {
		return Areas.WheatArea.contains(Players.getLocal().getLocation())
				&& Inventory.getCount(1947) <= 14;
	}

	@Override
	public void execute() {
		Variables.status = "Picking Wheat";
		SceneObject GATEOPEN = SceneEntities.getNearest(Variables.GATEOPEN_IDS);
		SceneObject WHEATS = SceneEntities.getNearest(Variables.WHEAT);
		
		if(Inventory.getCount(1947) < 14) {
			if(WHEATS.click(false) && Menu.isOpen() && Menu.contains("Pick")) {
			WHEATS.interact("Pick");
			} else { 
				Walking.walk(Variables.RandomTile);
			}
			sleep(1500);
			if(Inventory.getCount(1947) < 14) {
				while(Variables.TwentySeconds.isRunning()) {
					if(Players.getLocal().getAnimation() == -1) {
						break;
					}
				}
			}
		}
		
		if(Inventory.getCount(1947) == 14) {
			Task.sleep(200);
			if(SceneEntities.getNearest(15511) == null && SceneEntities.getNearest(15513) == null) {
				GATEOPEN.interact("Open");
				Task.sleep(1200);
			}
			if(Players.getLocal().getAnimation() == -1) {
				Walking.newTilePath(Paths.OutOfWheatArea).traverse();
			}
		}
	}
}
