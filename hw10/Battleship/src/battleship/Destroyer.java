package battleship;

public class Destroyer extends Ship {
	/**
	 * Describes a ship of length 2
	 */
    public Destroyer() {
        super(2);
    }
    
    @Override
    public String getShipType() {
        return "Destroyer";
    }
}
