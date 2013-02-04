import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.util.net.GeItem;


public class Variables {
	
	public static String status = "Loading...";

	static GeItem MistRuneGE = GeItem.lookup(4695);
	static GeItem PureEssenceGE = GeItem.lookup(7936);
	static GeItem WaterTalismanGE = GeItem.lookup(1444);
	static GeItem WaterRuneGE = GeItem.lookup(555);
	static GeItem BindingNeckyGE = GeItem.lookup(5521);
	
	public static int PureEssence = 7936;
	public static int MistRune = 4695;
	public static int WaterTalisman = 1444;
	public static int numMistRunes = 0;
	public static int numRuns = 0;
	public static int price = MistRuneGE.getPrice();
	public static int totalCost = PureEssenceGE.getPrice() + (WaterTalismanGE.getPrice() / 26) + WaterRuneGE.getPrice() + ((BindingNeckyGE.getPrice() / 15) / 26);
	
	public static Timer twentySeconds = new Timer(20000);
	public static Timer twoMinutes = new Timer(200000);
	
	public static boolean neckyCheck() {
		if(Bank.isOpen()) {
			Widgets.get(762).getChild(120).interact("Show Equipment Stats");
			Task.sleep(20, 50);
			if(Widgets.get(667).getChild(190).getChild(2).getActions() == null) {
				Widgets.get(667).getChild(13).interact("Show Bank");
				return false;
			} else {
				Widgets.get(667).getChild(13).interact("Show Bank");
				return true;
			}
		}
		return false;
	}
}
