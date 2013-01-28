import org.powerbot.game.api.wrappers.Tile;


public class Paths {

	public final static Tile[] BankToWheat = {
		new Tile(3173, 3470, 0), new Tile(3168, 3468, 0),
		new Tile(3165, 3464, 0), new Tile(3163, 3459, 0),
		new Tile(3158, 3457, 0), new Tile(3153, 3456, 0),
		new Tile(3148, 3456, 0), new Tile(3141, 3457, 0)
	};
	
	public final static Tile[] WheatToGuild = {
		new Tile(3141, 3455, 0), new Tile(3143, 3443, 0)
	};
	
	public final static Tile[] GuildToBank = {
			new Tile(3142, 3441, 0), new Tile(3147, 3440, 0),
			new Tile(3150, 3444, 0), new Tile(3152, 3449, 0),
			new Tile(3154, 3454, 0), new Tile(3157, 3458, 0),
			new Tile(3161, 3461, 0), new Tile(3165, 3466, 0),
			new Tile(3169, 3469, 0), new Tile(3173, 3472, 0),
			new Tile(3178, 3473, 0) 
	};
	
	public final static Tile[] OutOfBankArea = {
			new Tile(3180, 3474, 0), new Tile(3173, 3471, 0)
	};
	
	public final static Tile[] intoWheatArea = {
		new Tile(3141, 3458, 0), new Tile(3141, 3459, 0)
	};
	
	public final static Tile[] OutOfWheatArea = {
		new Tile(3141, 3459, 0), new Tile(3141, 3455, 0)
	};
}
