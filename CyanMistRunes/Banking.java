import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

@Manifest(authors = { "ICyanide" }, description = "Makes Mist runes at the air alter", name = "CyanMistRunes")
public class Banking extends Node {
	
	@Override
	public boolean activate() {
		return Areas.BankArea.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		SceneObject bankBooth = SceneEntities.getNearest(782);

		if(!Inventory.isFull() || Inventory.getItem(5521) != null) {
			if(!Bank.isOpen()) {
				if(bankBooth != null) {
					Variables.status = "Opening Bank...";
					bankBooth.interact("Bank");
					sleep(2000, 2500);
				}
			}
			
			while(Variables.twentySeconds.isRunning()) {
				if(Bank.isOpen()) {
					sleep(20, 50);
					break;
				}
			}
			
			if(Inventory.getItem(5521) != null) {
				Inventory.getItem(5521).getWidgetChild().interact("Wear");
			}
			
			if(Inventory.getItem(Variables.MistRune) != null) {
				Bank.deposit(Variables.MistRune, 26);
			} else if(Inventory.getItem(Variables.PureEssence) == null) {
				Bank.withdraw(Variables.PureEssence, 26);
			} else if(Variables.neckyCheck() == false) {
				Bank.withdraw(5521, 1);
				sleep(20, 50);
				Inventory.getItem(5521).getWidgetChild().interact("Wear");
			} else if(Inventory.getItem(Variables.WaterTalisman) == null) {
				sleep(50, 80);
				Bank.withdraw(Variables.WaterTalisman, 1);
			}
		}else if(Inventory.getCount(Variables.PureEssence) == 26 &&
			Inventory.getCount(Variables.WaterTalisman) == 1) {
			Variables.status = "Walking to Alter Entrance...";
			Bank.close();
			sleep(20, 50);
			Walking.walk(new Tile(3171, 3429, 0));
			sleep(1000, 1500);
			
			while(Variables.twentySeconds.isRunning()) {
				if(!Players.getLocal().isMoving()) {
					break;
				}
			}
		}
	}
}
