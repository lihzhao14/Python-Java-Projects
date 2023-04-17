package battleship;

public class Battleship extends Ship {
	/**
	 * Describes a ship of length 4
	 */
    public Battleship() {
        super(4);
    }
    
    @Override
    public String getShipType() {
        return "Battleship";
    }
}
