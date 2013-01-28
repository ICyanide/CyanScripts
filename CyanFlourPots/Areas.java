import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;


public class Areas {

	public final static Area WheatArea = new Area(new Tile[] {
			new Tile(3144, 3457, 0), new Tile(3138, 3458, 0),
			new Tile(3138, 3460, 0), new Tile(3137, 3461, 0),
			new Tile(3137, 3464, 0), new Tile(3141, 3463, 0),
			new Tile(3141, 3464, 0), new Tile(3141, 3465, 0),
			new Tile(3144, 3464, 0), new Tile(3145, 3464, 0),
			new Tile(3145, 3461, 0)
	});
	
	public final static Area GuildFloorOneArea = new Area(new Tile[] {
			new Tile(3139, 3452, 0), new Tile(3147, 3452, 0),
			new Tile(3147, 3447, 0), new Tile(3144, 3444, 0),
			new Tile(3142, 3444, 0), new Tile(3138, 3448, 0),
			new Tile(3139, 3452, 0)  
	});
	
	public final static Area GuildFloorTwoArea = new Area(new Tile[] {
			new Tile(3139, 3452, 1), new Tile(3147, 3452, 1),
			new Tile(3147, 3447, 1), new Tile(3144, 3444, 1),
			new Tile(3142, 3444, 1), new Tile(3138, 3448, 1),
			new Tile(3139, 3452, 1) 
	});
	
	public final static Area GuildFloorThreeArea = new Area(new Tile[] {
			new Tile(3138, 3451, 2), new Tile(3140, 3453, 2),
			new Tile(3145, 3453, 2), new Tile(3145, 3451, 2),
			new Tile(3146, 3451, 2), new Tile(3146, 3446, 2),
			new Tile(3140, 3446, 2), new Tile(3139, 3452, 2),
			new Tile(3138, 3448, 2)
			  
	});
	
	public final static Area BankArea = new Area(new Tile[] {
			new Tile(3178, 3484, 0), new Tile(3185, 3484, 0),
			new Tile(3185, 3479, 0), new Tile(3185, 3474, 0),
			new Tile(3181, 3471, 0), new Tile(3176, 3472, 0),
			new Tile(3176, 3477, 0), new Tile(3176, 3482, 0)
	});
}
