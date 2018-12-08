package casdentia.factions2;

import org.bukkit.configuration.ConfigurationSection;

public class Faction implements Convertible {

    private String name;
    private int balance;

    public Faction() {

    }

    public Faction(String name, FactionsPlayer factionsPlayer) {
        this.name = name;
        factionsPlayer.setFaction(this);
        factionsPlayer.setRank(FactionRank.LEADER);
    }

    public String getName() {
        return name;
    }

    @Override
    public void save(ConfigurationSection section) {
        section.createSection(name);
        section.set(name+ ".balance", balance);
    }

    @Override
    public void load(ConfigurationSection section) {
        name = section.getName();
        balance = section.getInt(name + ".balance");
    }
}
