package info1.ships;

/**
 * Classe définissant une navire de catégorie "Porte-Avion" (taille 5)
 */

public class AircraftCarrier extends Ship {

    public AircraftCarrier(String name, String xyFront, String xyBack)
            throws BadCoordException, CoordsBadShipException {
        super(name, xyFront, xyBack);
        if(ShipCategory.AIRCRAFT_CARRIER.getSize() != super.getSize()) throw new CoordsBadShipException();
    }

    @Override
    public ShipCategory getCategory() {
        return ShipCategory.AIRCRAFT_CARRIER;
    }

}
