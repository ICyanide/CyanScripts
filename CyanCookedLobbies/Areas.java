import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;


public class Areas {

	public static final Area FishingArea = new Area(new Tile[] {
			new Tile(2918, 3186, 0), new Tile(2923, 3185, 0),
			new Tile(2928, 3184, 0), new Tile(2930, 3179, 0),
			new Tile(2930, 3174, 0), new Tile(2926, 3171, 0),
			new Tile(2921, 3171, 0), new Tile(2917, 3174, 0)
	});
	
	public static final Area TreeArea = new Area(new Tile[] {
			new Tile(2940, 3155, 0), new Tile(2948, 3155, 0),
			new Tile(2947, 3144, 0), new Tile(2942, 3144, 0)
	});
	
	public static final Area NoteGuyArea = new Area(new Tile[] {
			new Tile(2846, 3150, 0), new Tile(2861, 3150, 0),
			new Tile(2860, 3142, 0), new Tile(2849, 3141, 0)
	});
}
