package Creature.Helpers.Enums;

/**
 * 5e Skills are a measure of specific regions of ability like arcana or animal handling. A creature's ability with
 * these skills usually stems from a natural taklent stemming from Abilities or proficiency in that specific skill.
 * This Enum takes on the value of all of the skills in 5e.
 */
public enum Skills {
    ATHLETICS(
            "Strength",
            "covers difficult situations you encounter while climbing, jumping, or swimming."
    ),
    ACROBATICS("Dexterity","covers your attempt to stay on your feet in a tricky situation, " +
            "such as when you’re trying to run across a sheet of ice, balance on a tightrope, or stay upright on a " +
            "rocking ship’s deck. The GM might also call for a Dexterity (Acrobatics) check to see if you can perform " +
            "acrobatic stunts, including dives, rolls, somersaults, and flips."),
    SLEIGHT_OF_HAND("Dexterity","Whenever you attempt an act of legerdemain or manual trickery, " +
            "such as planting something on someone else or concealing an object on your person"),
    STEALTH("Dexterity","when you attempt to conceal yourself from enemies, slink past guards," +
            " slip away without being noticed, or sneak up on someone without being seen or heard."),
    ARCANA("Intelligence"," measures your ability to recall lore about spells, magic items," +
            " eldritch symbols, magical traditions, the planes of existence, and the inhabitants of those planes"),
    HISTORY("Intelligence","measures your ability to recall lore about historical events," +
            " legendary people, ancient kingdoms, past disputes, recent wars, and lost civilizations."),
    INVESTIGATION("Intelligence","When you look around for clues and make deductions based on those clues, " +
            "you make an Intelligence (Investigation) check.  You might deduce the location of a hidden object, discern " +
            "from the appearance of a wound what kind of weapon dealt it, or determine the weakest point in a tunnel that " +
            "could cause it to collapse. Poring through ancient scrolls in search of a hidden fragment of knowledge " +
            "might also call for an Intelligence (Investigation) check."),
    NATURE("Intelligence","measures your ability to recall lore" +
            " about terrain, plants and animals, the weather, and natural cycles."),
    RELIGION("Intelligence","measures your ability to recall lore about deities," +
            " rites and prayers, religious hierarchies, holy symbols, and the practices of secret cults."),
    ANIMAL_HANDLING("Wisdom","When there is any question whether you can calm down a domesticated " +
            "animal, keep a mount from getting spooked, or intuit an animal’s intentions. You also make a Wisdom" +
            " (Animal Handling) check to control your mount when you attempt a risky maneuver."),
    INSIGHT("Wisdom","decides whether you can determine the true intentions of a creature, " +
            "such as when searching out a lie or predicting someone’s next move." +
            " Doing so involves gleaning clues from body language, speech habits, and changes in mannerisms."),
    MEDICINE("Wisdom","lets you try to stabilize a dying companion or diagnose an illness."),
    PERCEPTION("Wisdom"," lets you spot, hear, or otherwise detect the presence of something. " +
            "It measures your general awareness of your surroundings and the keenness of your senses. For example, " +
            "you might try to hear a conversation through a closed door, eavesdrop under an open window, or hear" +
            " monsters moving stealthily in the forest. Or you might try to spot things that are obscured or easy to " +
            "miss, whether they are orcs lying in ambush on a road, thugs hiding in the shadows of an alley, or " +
            "candlelight under a closed secret door."),
    SURVIVAL("Wisdom","check to follow tracks, hunt wild game, " +
            "guide your group through frozen wastelands, identify signs that owlbears live nearby, " +
            "predict the weather, or avoid quicksand and other natural hazards."),
    DECEPTION("Charisma","whether you can convincingly hide the truth, either verbally or through " +
            "your actions. This deception can encompass everything from misleading others through ambiguity " +
            "to telling outright lies. Typical situations include trying to fast talk a guard, con a merchant," +
            " earn money through gambling, pass yourself off in a disguise, dull someone’s suspicions with false " +
            "assurances, or maintain a straight face while telling a blatant lie."),
    INTIMIDATION("Charisma","When you attempt to influence someone through overt threats, hostile actions, and physical violence,"),
    PERFORMANCE("Charisma","determines how well you can delight an audience with music," +
            " dance, acting, storytelling, or some other form of entertainment."),
    PERSUASION("Charisma"," When you attempt to influence someone or a group of people with" +
            " tact, social graces, or good nature");

    private String parentAbility;
    private String description;
    Skills(String parentAbility,String description ){
        this.description = description;
        this.parentAbility = parentAbility;
    }
    public String getDescription(){

        return description;
    }
    public String getParentAbility(){
        return parentAbility;
    }
}
