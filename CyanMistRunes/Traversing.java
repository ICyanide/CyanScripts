import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;


public class Traversing extends Node {

	@Override
	public boolean activate() {
		return !Areas.BankArea.contains(Players.getLocal().getLocation()) &&
				!Areas.AlterEntranceArea.contains(Players.getLocal().getLocation()) &&
				!Areas.InsideAlterArea.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {	
		NPC musician = NPCs.getNearest(8699);
		int energy = Walking.getEnergy();
		
		if(energy < 30 && musician != null && musician.isOnScreen()) {
			musician.interact("Listen-to");
			Variables.status = "Resting...";
			sleep(20, 50);
			Camera.turnTo(musician);
			sleep(20000, 30000);
		
			while(Variables.twoMinutes.isRunning()) {
					if(energy == Random.nextInt(90, 100)) {
					break;
				}
			}
		}
		
		if(Inventory.getCount(Variables.PureEssence) == 26) {
			Variables.status = "Walking to Alter Entrance...";
			Walking.newTilePath(Paths.BankToAlterEntrance).traverse();
		} else if(Inventory.getCount(Variables.PureEssence) == 0) {
			Variables.status = "Walking to Bank...";
			Walking.newTilePath(Paths.AlterEntranceToBank).traverse();
		}
	}
}
