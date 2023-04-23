/**
 * Class Submarine
 * 
 * @author Haojie Zheng & Lihong Zhao
 */
package battleship;

public class Submarine extends Ship {
	/**
	 * Describes a ship of length 1
	 */
	private static final int SubmarineL = 1;
	private final String shipType = "submarine";
	
	public Submarine() {
		super(SubmarineL);
	}
	
	@Override
	public String getShipType()
	{
		return this.shipType;
	}
}
