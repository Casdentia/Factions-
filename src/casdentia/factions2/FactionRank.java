package casdentia.factions2;

public enum FactionRank {

    NONE("None", -1),
    DEFAULT("Default", 0),
    MODERATOR("Moderator", 1),
    LEADER("Leader", 2);

    private String name;
    private int value;

    FactionRank(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
