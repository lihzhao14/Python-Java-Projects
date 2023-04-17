package battleship;

public class Submarine extends Ship {
	/**
	 * Describes a ship of length 1
	 */
    public Submarine() {
        super(1);
    }
    
    @Override
    public String getShipType() {
        return "Submarine";
    }

}
