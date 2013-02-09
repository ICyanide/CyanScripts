import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class Crafting extends Node {

	@Override
	public boolean activate() {
		return Areas.AlterEntrence.contains(Players.getLocal().getLocation()) || Areas.InsideAlter.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		Area OutsideAlter = Areas.AlterEntrence;
		Area InsideAlter = Areas.InsideAlter;
		
		// OUTSIDE ALTER
		if(OutsideAlter.contains(Players.getLocal().getLocation())) {
			if(Inventory.getItem(Variables.ESSENCE_ID) != null && SceneEntities.getNearest(2452) != null) {
				Variables.status = "Entering the Alter...";
				Walking.findPath(SceneEntities.getNearest(2452)).traverse();
				sleep(100, 150);
				
				Camera.turnTo(SceneEntities.getNearest(2452));
				sleep(100, 120);
				SceneEntities.getNearest(2452).interact("Enter");
				
				Variables.SleepName = "EnterAlter";
				sleep(10, 20);
				Variables.DyanmicSleep();
				
			} else {
				Walking.findPath(new Tile(3142, 3413, 0)).traverse();
				sleep(1500);
			}
		}
		// INSIDE ALTER
		if(InsideAlter.contains(Players.getLocal().getLocation())) {
			sleep(20, 50);
			if(Inventory.getItem(Variables.ESSENCE_ID) != null) {
				sleep(20, 50);
				Variables.status = "Crafting Runes...";
				Walking.findPath(new Tile(2843, 4832, 0)).traverse();
				sleep(200, 500);
				
				Variables.SleepName = "PlayerIsIdle";
				sleep(10, 20);
				Variables.DyanmicSleep();
				
				
				if(Variables.ESSENCE_ID == 1436) {
					SceneEntities.getNearest(2478).interact("Craft-rune");
					
					Variables.SleepName = "EssenceInInventory";
					sleep(10, 20);
					Variables.DyanmicSleep();
					
				} else {
					Inventory.selectItem(Variables.TALISMAN_ID);
					sleep(20, 40);
					SceneEntities.getNearest(2478).interact("Use");
					
					Variables.SleepName = "EssenceInInventory";
					sleep(10, 20);
					Variables.DyanmicSleep();
				}
					
			} else {
				SceneObject ExitPortal = SceneEntities.getNearest(2465);
				if(ExitPortal != null ) {
					sleep(100, 150);
					Walking.findPath(new Tile(2841, 4829, 0)).traverse();
					sleep(400, 500);
					ExitPortal.interact("Enter");
				
					Variables.SleepName = "ExitPortal";
					sleep(10, 20);
					Variables.DyanmicSleep();
				}
			}
		}
	}
}
