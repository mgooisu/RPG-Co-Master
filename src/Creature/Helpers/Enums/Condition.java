package Creature.Helpers.Enums;

public enum Condition {
    BLINDED( new String[]{
            "A blinded creature can't see and automatically fails any ability check that requires sight.",
            "Attack rolls against the creature have advantage, and the creature's attack rolls have disadvantage."
    }),
    CHARMED(new String[]{
            "A charmed creature can't attack the charmer or target the charmer with harmful abilities or magical effects.",
            "The charmer has advantage on any ability check to interact socially with the creature."
    }),
    DEAFENED(new String[]{
            "A deafened creature can't hear and automatically fails any ability check that requires hearing."
    }),
    FRIGHTENED(new String[]{
            "A frightened creature has disadvantage on ability checks and attack rolls while the source of its fear " +
                    "within line of sight.",
            "The creature can't willingly move closer to the source of its fear."
    }),
    GRAPPLED(new String[]{
            "A grappled creature's speed becomes 0, and it can't benefit from any bonus to its speed.",
            "The condition ends if the grappler is incapacitated (see the condition).",
            "The condition also ends if an effect removes the grappled creature from the reach of the grappler" +
                    "or grappling effect, such as when the creature is hurled away by the thunderwave spell."
    }),
    INCAPACITATED(new String[]{
            "An incapacitated creature can't take actions or reactions."
    }),
    INVISIBLE(new String[]{
            "An invisible creature is impossible to see without the aid of magic or a special sense." +
                    "For the purpose of hiding, the creature is heavily obscured. The creature's location" +
                    "can be detected by any noise it makes or any tracks it leaves.",
            "Attack rolls against the creature have disadvantage, and the creature's attack rolls have advantage."
    }),
    PARALYZED(new String[]{
            "A paralyzed creature is incapacitated (see the condition) and can't move or speak.",
            "The creature automatically fails Strength and Dexterity saving throws.",
            "Attack rolls against teh creature have advantage.",
            "Any attack that hits the creature is a critical hit if the attacker is within 5 feet of the creature."
    }),
    PETRIFIED(new String[]{
            "A petrified creature is transformed, along with any nonmagical object it is wearing or carrying, into a " +
                    "solid inanimate substance (usually stone). Its weight increases by a factor of 10, and it " +
                    "ceases aging.",
            "The creature is incapacitated(See the condition), can't move or speak , and it unaware of its surroundings.",
            "Attack rolls against the creature have advantage.",
            "The creature has resistance to all damage.",
            "The creature is immune to potion and disease, although a poison or disease already in its system is" +
                    "suspended, not neutralized."
    }),
    POISONED(new String[]{
            "A poisoned creature has disadvantage on attack rolls and ability checks."
    }),
    PRONE(new String[]{
            "A prone creature's only movement option is to crawl, unless it stands up and thereby ends the condition",
            "The creature has disadvantage on attack rolls.",
            "An attack roll against the creature has disadvantage attack rolls.",
            "An attack roll against the creature has advantage if the attacker is within 5 feet of the creature." +
                    "Otherwise, the attack roll has disadvantage"
    }),
    RESTRAINED(new String[]{
            "A restrained creature's speed becomes 0, and it can't benefit from any bonus to speed.",
            "Attack rolls against the creature have advantage, and the creature's attack roll have disadvantage.",
            "The creature has disadvantage on Dexterity saving throws."

    }),
    STUNNED(new String[]{
            "A stunned creature is incapacitated (see the condition), can't move, and can speak only falteringly.",
            "The creature automatically fails Strength and Dexterity saving throws.",
            "Attack rolls against the creature have advantage."
    }),
    UNCONSCIOUS(new String[]{
       "A stunned creature is incapacitated (see the condition), can't move or speak, and is unaware of its surroundings.",
            "The creature drops whatever it's holding and falls prone.",
            "The creature automatically fails Strength and Dexterity saving throws.",
            "Attack rolls against this creature have advantage.",
            "Any attack that hits the creature is a critical hit if the attacker is within 5 feet of the creature."
    });
    private String[] effects;
    Condition(String[] effects){
        this.effects = effects;
    }
    public String[] getEffects(){
        return effects;
    }

}
