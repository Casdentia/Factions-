package casdentia.factions2;

public enum FactionRank {

    NONE("None"), DEFAULT("Default"),LEADER("Leader");

    private String name;

    FactionRank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
