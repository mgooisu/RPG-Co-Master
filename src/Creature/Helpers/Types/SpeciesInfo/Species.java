package Creature.Helpers.Types.SpeciesInfo;


import java.io.Serializable;

/**
 * Contains information on the species of a creature. E.g human, fae, demon, ect.
 */
//Todo Challenge rating and experience for murdering (:C
public class Species implements Serializable {
    private String name,description;
    private Role role;
    public enum Role{MONSTER,HUMANOID}


    public Species(String name, String description, Role role) {
        this.name = name;
        this.description = description;
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    /**
     * The role enumerator makes it easier to distinguish between categories of species
     * @return role - MONSTER, HUMANOID - potentially add more to act as filters
     */
    public Role getRole(){
        return role;
    }
}
