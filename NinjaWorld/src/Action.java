import java.io.Serializable;

public class Action  implements Serializable{

	private int numActions;
	private boolean usedOffensive;
	private boolean usedItem;
	private boolean usedMove;
	private boolean usedEnvi;

	private boolean usedDefensive;
	//2 actions per round, then schedule abilities
	//for each ability, target enemies can choose to activate a defense.  If so, on the next round, -1 action.

	public Action() {
		this.refresh();
	}
	
	
	public void refresh() {
		usedOffensive = false;
		usedDefensive = false;
		usedItem = false;
		usedMove = false;
		usedEnvi = false;
	}

	
	
	public boolean isUsedOffensive() {
		return usedOffensive;
	}

	public void setUsedOffensive(boolean usedOffensive) {
		this.usedOffensive = usedOffensive;
	}

	public boolean isUsedDefensive() {
		return usedDefensive;
	}

	public void setUsedDefensive(boolean usedDefensive) {
		this.usedDefensive = usedDefensive;
	}

	public boolean isUsedItem() {
		return usedItem;
	}

	public void setUsedItem(boolean usedItem) {
		this.usedItem = usedItem;
	}

	public boolean isUsedMove() {
		return usedMove;
	}

	public void setUsedMove(boolean usedMove) {
		this.usedMove = usedMove;
	}

	public boolean isUsedEnvi() {
		return usedEnvi;
	}

	public void setUsedEnvi(boolean usedEnvi) {
		this.usedEnvi = usedEnvi;
	}

	public int getNumActions() {
		return numActions;
	}

	public void setNumActions(int numActions) {
		this.numActions = numActions;
	}

	public void decrementNumActions() {
		this.numActions = numActions - 1;
	}
	
}//action
