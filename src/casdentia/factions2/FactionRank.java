package casdentia.factions2;

public enum FactionRank {

    DEFAULT("default"), LEADER("leader");

    private String name;

    FactionRank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
