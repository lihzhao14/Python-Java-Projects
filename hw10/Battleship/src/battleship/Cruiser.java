/**
 * Class Cruiser
 * 
 * @author Haojie Zheng & Lihong Zhao
 */

package battleship;

public class Cruiser extends Ship {
	/**
	 * Describes a ship of length 3
	 */
	private static final int CruiserL = 3;
	private final String shipType = "cruiser";
	
	public Cruiser() {
		super(CruiserL);
	}
	
	@Override
	public String getShipType()
	{
		return this.shipType;
	}
}
