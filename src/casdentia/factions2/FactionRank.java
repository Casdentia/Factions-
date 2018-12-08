package casdentia.factions2;

public enum FactionRank {

    DEFAULT("default"), LEADER("leader"), NONE("none");

    private String name;

    FactionRank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
