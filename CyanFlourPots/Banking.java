import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Banking extends Node {

	@Override
	public boolean activate() {
				return Areas.BankArea.contains(Players.getLocal().getLocation())
						&&  (Inventory.getCount(1931) != 14 || Inventory.getCount() != 14);
	}

	@Override
	public void execute() {
		Variables.status = "Banking";
		final NPC Banker = NPCs.getNearest(3418);
		
		if(!Banker.isOnScreen()) {
			Camera.turnTo(Banker);
		}
		
		if(Random.nextInt(0, 7) % 2 ==0) {
			if(Banker.click(false) && Menu.isOpen() && Menu.contains("Bank")) {
				Menu.select("Bank");
				Task.sleep(1500);
				while(Variables.TwentySeconds.isRunning()) {
					if(Calculations.distanceTo(Banker) <  4) {
						break;
					}
				}
				sleep(500, 1000);
			} 
			
			if(Widgets.get(762).validate()) {
				
				if(Inventory.getCount(1933) == 14) {
					Variables.NumberOfFlourPotsBNK = Variables.NumberOfFlourPotsBNK + 14;
					Variables.NumberOfFlourPotsINV = 0;
				}
				
				Bank.depositInventory();				
				Task.sleep(300);
				Bank.withdraw(1931, 14);
				Task.sleep(200);
				Bank.close();
				Task.sleep(200);
			}	
		}
		if(Inventory.getCount(1931) == 14 ) {
		 Walking.newTilePath(Paths.OutOfBankArea).traverse();
		}

		}
}