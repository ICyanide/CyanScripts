import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;


public class Traversing extends Node {

	@Override
	public boolean activate() {
		return !Areas.InsideAlter.contains(Players.getLocal().getLocation()) &&
				!Areas.Bank.contains(Players.getLocal().getLocation()) &&
				!Areas.AlterEntrence.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		
		if(Walking.getEnergy() <= 30 && Variables.MUSICIAN.isOnScreen() 
				&& Calculations.distanceTo(Variables.MUSICIAN) < 15) {
			Walking.findPath(Variables.MUSICIAN).traverse();
			Variables.MUSICIAN.interact("Listen-To", Variables.MUSICIAN.getName());
			Variables.status = "Resting...";
			sleep(5000, 8000);
			
			while(Variables.twoMinutes.isRunning() && Players.getLocal().getAnimation() != -1) {
				if(Walking.getEnergy() == Random.nextInt(90, 100)) {
					break;
				}
			}
		}
		
		if(Inventory.getItem(Variables.ESSENCE_ID) == null) {
			Variables.status = "Traversing To Bank...";
			Walking.newTilePath(Paths.AlterToBank).traverse();
		} else {
			Variables.status = "Traversing To Alter...";
			Walking.newTilePath(Paths.BankToAlter).traverse();
		}
	}
}
