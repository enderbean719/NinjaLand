
public class Action {
	private boolean usedOffensive;
	private boolean usedDefensive;
	private boolean usedItem;
	private boolean usedMove;
	private boolean usedEnvi;
	
	
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
	
	
	
	
}//action
