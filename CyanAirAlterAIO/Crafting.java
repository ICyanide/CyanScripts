import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;


public class Crafting extends Node {

	@Override
	public boolean activate() {
		return Areas.AlterEntrence.contains(Players.getLocal().getLocation()) || Areas.InsideAlter.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		
		
		// OUTSIDE ALTER
		if(Areas.AlterEntrence.contains(Players.getLocal().getLocation())) {
			if(Inventory.getItem(Variables.ESSENCE_ID) != null && Variables.ALTERENTRENCE != null) {
				Variables.status = "Entering the Alter...";
				Walking.findPath(Variables.ALTERENTRENCE).traverse();
				sleep(100, 150);
				
				Camera.turnTo(Variables.ALTERENTRENCE);
				sleep(20, 40);
				Variables.ALTERENTRENCE.interact("Enter");
				
				while(Variables.fiveSeconds.isRunning()) {
					Area Outside = Areas.AlterEntrence;
					Variables.dynamicSleepStatus = "In Line 30";
					if(!Outside.contains(Players.getLocal().getLocation())) {
						Variables.dynamicSleepStatus = "Not In";
						break;
					}
				}
			} else {
				Walking.findPath(new Tile(3142, 3413, 0)).traverse();
				sleep(1500);
			}
		}
		// INSIDE ALTER
		if(Areas.InsideAlter.contains(Players.getLocal().getLocation())) {
			if(Inventory.getItem(Variables.ESSENCE_ID) != null && Variables.ALTER != null) {
				Variables.status = "Crafting Runes...";
				Walking.findPath(Variables.ALTER).traverse();
				sleep(200, 500);
				
				while(Variables.twoMinutes.isRunning()) {
					Variables.dynamicSleepStatus = "In Line 49";
					if(Players.getLocal().isIdle()) {
						Variables.dynamicSleepStatus = "Not In";
						break;
					}
				}
				if(Variables.ESSENCE_ID == 1436) {
					Variables.ALTER.interact("Craft-rune");
					
					while(Variables.twoMinutes.isRunning()) {
						Variables.dynamicSleepStatus = "In Line 59";
						if(Inventory.getItem(Variables.ESSENCE_ID) == null) {
							Variables.dynamicSleepStatus = "Not In";
							break;
						}
					}
				} else {
					Inventory.selectItem(Variables.TALISMAN_ID);
					sleep(20, 40);
					Variables.ALTER.interact("Use");
					
					while(Variables.fiveSeconds.isRunning()) {
						Variables.dynamicSleepStatus = "In Line 71";
						if(Inventory.getItem(Variables.ESSENCE_ID) == null) {
							Variables.dynamicSleepStatus = "Not In";
							break;
						}
					}
				}
			} else {
				if(Variables.EXITPORTAL != null ) {
					sleep(100, 150);
					Walking.findPath(Variables.EXITPORTAL).traverse();
					sleep(100, 150);
					Variables.EXITPORTAL.interact("Enter");
				
					while(Variables.twoMinutes.isRunning()) {
						Variables.dynamicSleepStatus = "In Line 86";
						if(!Areas.InsideAlter.contains(Players.getLocal().getLocation())) {
							Variables.dynamicSleepStatus = "Not In";
							break;
						}
					}
				}
			}
		}
	}
}
