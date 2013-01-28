import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class Variables {

public static Timer TwentySeconds = new Timer(20000);
public static Timer TwoMinutes = new Timer(120000);
public static int NumberOfFlourPotsINV;
public static int NumberOfFlourPotsBNK;
public static String status = "";

public final static int[] WHEAT_IDS = {15506, 15508, 15507};
public final static int[] GATEOPEN_IDS = {15510, 15512};
public final static int[] DOOR_IDS = {2712, 15502};
public final static int[] STAIRS_IDS = {24073, 24074, 24075};
public final static Tile[] RandomTiles = { new Tile(3143, 3463 , 0), new Tile(3142, 3460, 0), new Tile(3140 , 3460 , 0), new Tile(3141, 3461, 0) };

public static Tile RandomTile = RandomTiles[Random.nextInt(1, 4)];



public static final Filter<SceneObject> STAIRS = new Filter<SceneObject>() {
	@Override
	public boolean accept(SceneObject n) {
		
		for(int i : STAIRS_IDS) {
			if (n.getId() == i && n.getLocation().canReach());
				return true;
		}
		return false;
	}
};


public static final Filter<SceneObject> DOOR = new Filter<SceneObject>() {
	@Override
	public boolean accept(SceneObject n) {
		
		for(int i : DOOR_IDS) {
			if (n.getId() == i && n.getLocation().canReach());
				return true;
		}
		return false;
	}
};
 	
	public static final Filter<SceneObject> GATES = new Filter<SceneObject>() {
		@Override
		public boolean accept(SceneObject n) {
			
			for(int i : GATEOPEN_IDS) {
				if (n.getId() == i && n.getLocation().canReach());
					return true;
			}
			return false;
		}
	};


	public static final Filter<SceneObject> WHEAT = new Filter<SceneObject>() {
		@Override
		public boolean accept(SceneObject n) {
			
			for(int i : WHEAT_IDS) {
				if (n.getId() == i && n.getLocation().canReach());
					return true;
			}
			return false;
		}
	};
	
}
