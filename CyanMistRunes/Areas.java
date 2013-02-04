import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;


public class Areas {

	public static final Area BankArea = new Area(new Tile[] {
			new Tile(3176, 3448, 0), new Tile(3195, 3447, 0),
			new Tile(3195, 3431, 0), new Tile(3178, 3431, 0)
	});
	
	public static final Area MusicianArea = new Area(new Tile[] {
			new Tile(3144, 3428, 0), new Tile(3162, 3425, 0),
			new Tile(3160, 3412, 0), new Tile(3145, 3413, 0)
	});
	
	public static final Area AlterEntranceArea = new Area(new Tile[] {
			new Tile(3119, 3411, 0), new Tile(3132, 3411, 0),
			new Tile(3135, 3399, 0), new Tile(3124, 3397, 0)
	});
	
	public static final Area InsideAlterArea = new Area(new Tile[] {
			new Tile(2835, 4826, 0), new Tile(2835, 4840, 0),
			new Tile(2850, 4840, 0), new Tile(2850, 4827, 0)
	});
}
