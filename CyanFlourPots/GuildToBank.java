import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;


public class GuildToBank extends Node {

	@Override
	public boolean activate() {
		return !Areas.GuildFloorOneArea.contains(Players.getLocal().getLocation())
				&& Inventory.getCount(1933) == 14;
	}

	@Override
	public void execute() {
		Variables.status = "Walking to Bank";
		Walking.newTilePath(Paths.GuildToBank).traverse();
	}

}
