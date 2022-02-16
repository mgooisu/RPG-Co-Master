package Creature;

import java.io.Serializable;

/**
 * Simple Feature datatype for combat creatures - explains general abilities the creature has that aren't actions
 */
public class Features implements Serializable {
    private String name, description;

    /**
     *
     * @param name Name of the Feature e.g."Keen Sight"
     * @param description Description of the Feature e.g" advantage on wisdom(Perception) checks that rely on sight"
     */
    public Features(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
