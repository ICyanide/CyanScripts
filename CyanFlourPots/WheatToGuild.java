import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class WheatToGuild extends Node {

	@Override
	public boolean activate() {
		return Inventory.getCount(1947) == 14
				&& Inventory.getCount(1931) == 14
				&& !Areas.GuildFloorOneArea.contains(Players.getLocal().getLocation())
				&& !Areas.GuildFloorTwoArea.contains(Players.getLocal().getLocation())
				&& !Areas.GuildFloorThreeArea.contains(Players.getLocal().getLocation());
	}

	@Override
	public void execute() {
		Variables.status = "Walking to Guild";
		SceneObject DOOR = SceneEntities.getNearest(Variables.DOOR);
		
		Walking.newTilePath(Paths.WheatToGuild).traverse();
		sleep(200, 300);
		
		while(Variables.TwentySeconds.isRunning()) {
			if(!Players.getLocal().isMoving()) {
				break;
			}
		}

		if(Calculations.distanceTo(DOOR) == 0) {
			DOOR.interact("Open");
		}
	}
}
