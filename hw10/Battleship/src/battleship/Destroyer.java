/*
 * Class Destroyer
 * 
 * @author Haojie Zheng & Lihong Zhao
 */
package battleship;

public class Destroyer extends Ship{
	/**
	 * Describes a ship of length 2
	 */
	private static final int DestroyerL = 2;
	private final String shipType = "destroyer";
	
	public Destroyer() {
		super(DestroyerL);
	}
	
	@Override
	public String getShipType()
	{
		return this.shipType;
	}
}
