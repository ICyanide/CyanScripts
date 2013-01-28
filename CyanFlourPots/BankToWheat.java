import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class BankToWheat extends Node {

	@Override
	public boolean activate() {
		return (Inventory.getCount(1931) == 14 && Inventory.getCount() == 14)
				&& !Areas.BankArea.contains(Players.getLocal().getLocation())
				&& !Areas.WheatArea.contains(Players.getLocal().getLocation())
				&& !Areas.GuildFloorOneArea.contains(Players.getLocal().getLocation())
				&& !Areas.GuildFloorTwoArea.contains(Players.getLocal().getLocation())
				&& !Areas.GuildFloorThreeArea.contains(Players.getLocal().getLocation())
				&& Players.getLocal().isIdle();
	}

	@Override
	public void execute() {
		Variables.status = "Walking to Wheat";
		SceneObject GATE = SceneEntities.getNearest(Variables.GATEOPEN_IDS);
		
		Walking.newTilePath(Paths.BankToWheat).traverse();
		
		sleep(200, 300);
		if(Players.getLocal().getAnimation() == -1 && GATE != null) {
			if(Calculations.distanceTo(GATE) == 0) {
				Task.sleep(200);
				GATE.click(true);
				Task.sleep(500);
		}
		}
		Walking.newTilePath(Paths.intoWheatArea).traverse();
		
	}

}
