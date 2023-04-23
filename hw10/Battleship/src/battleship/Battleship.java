/**
 * Class Battleship
 * 
 * @author Haojie Zheng & Lihong Zhao
 */
package battleship;

public class Battleship extends Ship{
	/**
	 * Describes a ship of length 4
	 */
	private static final int BattleshipL = 4;
	private final String shipType = "battleship";
	
	public Battleship() {
		super(BattleshipL);
	}
	
	@Override
	public String getShipType()
	{
		return this.shipType;
	}

}
