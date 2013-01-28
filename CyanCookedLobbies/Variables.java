import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Variables {
	
	static Timer twentySeconds = new Timer(2000);
	static Timer sixtyfiveSeconds = new Timer(6500);
	
	
	static Filter<NPC> fishingSpot = new Filter<NPC>(){
		@Override
		public boolean accept(NPC npc) {
			return npc.getId() == 324;
		}
	};
}
