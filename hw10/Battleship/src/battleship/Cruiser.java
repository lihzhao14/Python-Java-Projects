package battleship;

public class Cruiser extends Ship {
	/**
	 * Describes a ship of length 3
	 */
    public Cruiser() {
        super(3);
    }
    
    @Override
    public String getShipType() {
        return "Cruiser";
    }
}
