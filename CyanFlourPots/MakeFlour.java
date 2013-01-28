import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class MakeFlour extends Node {

	@Override
	public boolean activate() {
		return (Areas.GuildFloorOneArea.contains(Players.getLocal().getLocation())
				|| Areas.GuildFloorTwoArea.contains(Players.getLocal().getLocation())
				|| Areas.GuildFloorThreeArea.contains(Players.getLocal().getLocation()))
				&& (Inventory.isFull() || Inventory.getCount(1947) > 0 || Inventory.getCount(1931) > 0 || Inventory.getCount(1933) == 14);
	}

	@Override
	public void execute() {
		Variables.status = "Making Flour";
		if(Areas.GuildFloorOneArea.contains(Players.getLocal().getLocation())
				&& Inventory.getCount(1931) == 14
				&& Inventory.getCount(1947) == 14) {
			SceneObject Stairs = SceneEntities.getNearest(24073);
			Stairs.interact("Climb-up");
			sleep(1000, 1500);
			
			while(Variables.TwentySeconds.isRunning()) {
				if(Areas.GuildFloorTwoArea.contains(Players.getLocal().getLocation())) {
					break;
				}
			}
		}
		
		if(Areas.GuildFloorTwoArea.contains(Players.getLocal().getLocation())
			&& Inventory.getCount(1931) > 0
			&& Inventory.getCount(1947) > 0) {
			SceneObject Stairs = SceneEntities.getNearest(24074);
			Stairs.interact("Climb-up");
			sleep(500, 1000);
			
			while(Variables.TwentySeconds.isRunning()) {
				if(Areas.GuildFloorThreeArea.contains(Players.getLocal().getLocation())) {
					break;
				}
			}
			
			}
		
		if(Areas.GuildFloorThreeArea.contains(Players.getLocal().getLocation())
				&& (Inventory.getCount(1947) > 0) || Inventory.getCount(1931) == 14 ) {
			
			if(Areas.GuildFloorThreeArea.contains(Players.getLocal().getLocation())) {
				
				SceneObject Stairs = SceneEntities.getNearest(24075);
				SceneObject HOPPERCONTROL = SceneEntities.getNearest(24072);
				SceneObject HOPPER = SceneEntities.getNearest(24071);
				
				// walk to hopper
			if( Calculations.distanceTo(HOPPERCONTROL) > 2 && Inventory.getCount() > 14) {
				Walking.walk(new Tile( 3141, 3452, 2));
				sleep(500, 1000);
				
				while(Variables.TwentySeconds.isRunning()) {
					if(!Players.getLocal().isMoving()) {
						break;
					}
				}
			}
			
			if(Calculations.distanceTo(HOPPER) > 5) {
				if(HOPPER != null || HOPPERCONTROL != null) {
					int CamTurnTo = Random.nextInt(1, 2);
					if(CamTurnTo == 1) {
						Camera.turnTo(HOPPER);
					} else {
						Camera.turnTo(HOPPERCONTROL);
					}
				}
			}
			
			if(Areas.GuildFloorThreeArea.contains(Players.getLocal().getLocation()) 
					&& Inventory.getCount(1947) > 0 
					&& Calculations.distanceTo(HOPPERCONTROL) < 2) {
				Inventory.selectItem(1947);
				HOPPER.interact("Use");
				sleep(2000);
				
				while(Variables.TwentySeconds.isRunning()) {
					if(Players.getLocal().getAnimation() == -1) {
						break;
					}
				}
					
				HOPPERCONTROL.interact("Operate");
				sleep(2000);
				
				while(Variables.TwentySeconds.isRunning()) {
					if(Players.getLocal().getAnimation() == -1) {
						break;
					}
				}
			}
			
			if(Areas.GuildFloorThreeArea.contains(Players.getLocal().getLocation())
					&& Inventory.getCount(1931) == 14
					&& !(Inventory.getCount(1947) > 0)) {
				sleep(200, 230);
				Walking.walk(new Tile(3144, 3446, 2));
				
				while(Variables.TwentySeconds.isRunning()) {
					if(!Players.getLocal().isMoving()) {
						break;
					}
				}

				Stairs.interact("Climb-down");
				sleep(2500, 3000);
				}
			}
		}
		
		if(Areas.GuildFloorTwoArea.contains(Players.getLocal().getLocation())
				&& Inventory.getCount(1931) == 14
				&& Inventory.getCount(1974) == 0) {
			SceneObject Stairs = SceneEntities.getNearest(24074);
			Stairs.interact("Climb-down");
			sleep(1500, 2000);
		}
		
		if(Areas.GuildFloorOneArea.contains(Players.getLocal().getLocation())
				&& Inventory.getCount(1931) == 14
				&& !(Inventory.getCount(1947) == 14)) {
			SceneObject FlourBin = SceneEntities.getNearest(954);
			FlourBin.interact("Take-flour");
			sleep(2500, 3000);
			
			while(Variables.TwoMinutes.isRunning()) {
				if(Inventory.getCount(1933) == 14 ) {
					break;
				}
			}
			
			
		}
		
		if(Areas.GuildFloorOneArea.contains(Players.getLocal().getLocation())
				&& Inventory.getCount(1933) == 14) {
			SceneObject Door = SceneEntities.getNearest(2712);
			
			if(Door != null) {
				if(!Door.isOnScreen()) {
					Camera.turnTo(Door);
					sleep(200, 250);
				}
				
				if(Door.click(false) && Menu.isOpen() && Menu.contains("Open")) {
					Menu.select("Open");
					sleep(1000, 1500);
				}
			}
			
			while(Variables.TwentySeconds.isRunning()) {
				if(!Players.getLocal().isMoving()) {
					break;
				}
			}
		}
		
	}

}
