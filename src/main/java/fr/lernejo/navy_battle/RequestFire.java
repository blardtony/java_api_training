package fr.lernejo.navy_battle;

public class RequestFire {
    private final EnumConsequence consequence;
    private final boolean shipLeft;

    public RequestFire(EnumConsequence consequence, boolean shipLeft) {
        this.consequence = consequence;
        this.shipLeft = shipLeft;
    }

    public RequestFire() {
        this.consequence = EnumConsequence.miss;
        this.shipLeft = false;
    }
    public EnumConsequence getConsequence() {
        return consequence;
    }

    public boolean isShipLeft() {
        return shipLeft;
    }
}
