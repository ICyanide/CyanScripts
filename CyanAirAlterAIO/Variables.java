import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class Variables {
	public static int TALISMAN_ID;
	public static int ELEMENTALRUNE_ID;
	public static int CRAFTEDRUNE_ID;
	public static int ESSENCE_ID;
	public static int BINDINGNECKY_ID = 5521;
	
	public static SceneObject ALTERENTRENCE = SceneEntities.getNearest(2452);
	public static SceneObject EXITPORTAL = SceneEntities.getNearest(2465);
	public static SceneObject BANKBOOTH = SceneEntities.getNearest(782);
	public static SceneObject ALTER = SceneEntities.getNearest(2478);
	
	public static NPC MUSICIAN = NPCs.getNearest(8699);
	
	public static Timer twoMinutes = new Timer(200000);
	public static Timer fiveSeconds = new Timer(5000);
	
	public static String status = "Loading";
	public static String dynamicSleepStatus = "Not In";
	
	
	public static boolean neckyCheck() {
		if(Bank.isOpen()) {
			Widgets.get(762).getChild(120).interact("Show Equipment Stats");
			Task.sleep(50, 80);
			if(Widgets.get(667).getChild(190).getChild(2).getActions() != null) {
				Widgets.get(667).getChild(13).interact("Show Bank");
				Task.sleep(50, 80);
				return true;
			} else {
				Widgets.get(667).getChild(13).interact("Show Bank");
				Task.sleep(50, 80);
			}
		}
		return false;
	}
	
}
