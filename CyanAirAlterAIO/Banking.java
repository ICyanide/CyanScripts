import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.Tile;


public class Banking extends Node {

	@Override
	public boolean activate() {
		return Areas.Bank.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		if(!Bank.isOpen() && Inventory.getItem(Variables.ELEMENTALRUNE_ID) != null && 
				Inventory.getItem(Variables.TALISMAN_ID) != null &&
				Inventory.getItem(Variables.ESSENCE_ID) != null) {
			Variables.status = "Traversing To Alter...";
			Walking.findPath(new Tile(3175, 3428, 0)).traverse();
		} else if(!Bank.isOpen()) {
			Variables.status = "Opening Bank...";
			sleep(100, 150);
			SceneEntities.getNearest(782).interact("Bank");
			
			Variables.SleepName = "OpenBank";
			sleep(10, 20);
			Variables.DyanmicSleep();
		}
		
		if(Bank.isOpen()) {
			Variables.status = "Banking...";
			sleep(30, 70);
			
			// Withdraws Elemental Runes
			if(Variables.ELEMENTALRUNE_ID != 556) {
				if(Inventory.getItem(Variables.ELEMENTALRUNE_ID) == null) {
					Bank.withdraw(Variables.ELEMENTALRUNE_ID, 100000);
					sleep(20, 50);
				}
			}
			sleep(30, 60);
			// Deposites Crafted Runes
			if(Inventory.getItem(Variables.CRAFTEDRUNE_ID) != null) {
				Bank.deposit(Variables.CRAFTEDRUNE_ID, 999);
				sleep(20, 30);
			}
			// Checks for Necky
			if(!Variables.neckyCheck()) {
				Bank.withdraw(Variables.BINDINGNECKY_ID, 1);
				sleep(100, 150);
				Inventory.getItem(Variables.BINDINGNECKY_ID).getWidgetChild().interact("Wear");
				sleep(10, 20);
			}
			// Withdraws Essences
			if(Inventory.getItem(Variables.ESSENCE_ID) == null) {
				if(Variables.ESSENCE_ID == 1426) {
					Bank.withdraw(Variables.ESSENCE_ID, 28);
					sleep(10, 20);
				} else {
					Bank.withdraw(Variables.ESSENCE_ID, 26);
					sleep(10, 20);
				}
			}
			// Withdraws Talisman
			if(Variables.ESSENCE_ID != 1426 && Inventory.getItem(Variables.TALISMAN_ID) == null) {
				Bank.withdraw(Variables.TALISMAN_ID, 1);
				sleep(10, 20);
			}
		}
		Bank.close();
		sleep(10, 20);
	}
}
