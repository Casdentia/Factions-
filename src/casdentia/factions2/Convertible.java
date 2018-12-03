package casdentia.factions2;

import org.bukkit.configuration.ConfigurationSection;

public interface Convertible {

    void save(ConfigurationSection section);

    void load(ConfigurationSection section);
}
