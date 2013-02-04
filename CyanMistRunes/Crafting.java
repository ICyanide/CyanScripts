import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class Crafting extends Node {
	
	@Override
	public boolean activate() {
		return Areas.AlterEntranceArea.contains(Players.getLocal().getLocation()) ||
				Areas.InsideAlterArea.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		SceneObject alterEntrance = SceneEntities.getNearest(2452);
		SceneObject alter = SceneEntities.getNearest(2478);
		SceneObject portal = SceneEntities.getNearest(2465);
		
		if(Inventory.getCount(Variables.PureEssence) == 26) {
			if(Areas.AlterEntranceArea.contains(Players.getLocal().getLocation())) {
				if(alterEntrance != null) {
					Variables.status = "Entering Alter...";
					Camera.turnTo(alterEntrance);
					alterEntrance.interact("Enter");
					sleep(4000, 5000);
				}
			}

			if(Areas.InsideAlterArea.contains(Players.getLocal().getLocation())) {
				if(alter != null) {
					Variables.status = "Crafting runes...";
					Camera.turnTo(alter);
					sleep(20, 50);
					Inventory.selectItem(Variables.WaterTalisman);
					alter.interact("Use");
					Variables.numMistRunes += 26;
					Variables.numRuns += 1;
					sleep(2000, 2500);
					
					while(Variables.twentySeconds.isRunning()) {
						if(Inventory.getItem(Variables.MistRune).getStackSize() == 26) {
							break;
						}
					}
				}
			}
		}
		
		if(Inventory.getItem(Variables.PureEssence) == null) {
			if(Areas.InsideAlterArea.contains(Players.getLocal().getLocation())) {
				if(portal != null) {
					Variables.status = "Leaving alter...";
					Camera.turnTo(portal);
					portal.interact("Enter");
					sleep(1000, 1700);
					
					while(Variables.twentySeconds.isRunning()) {
						if(Areas.AlterEntranceArea.contains(Players.getLocal().getLocation())) {
							break;
						}
					}
					
				}
			} else if(Areas.AlterEntranceArea.contains(Players.getLocal().getLocation())) {
				Walking.walk(new Tile(3141, 3413, 0));
				sleep(1500, 2000);
				
				while(Variables.twentySeconds.isRunning()) {
					if(!Players.getLocal().isMoving()) {
						break;
					}
				}
			}
		}
	}
}
