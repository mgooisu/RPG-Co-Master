package Creature.Helpers.Types.SpeciesInfo;

import Helpers.FileReadWrite;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Serializable ArrayList of species types that includes the standard types for 5e, but can be expanded to include others
 */
public class SpeciesList implements Serializable {
    private ArrayList<Species> speciesList;

    private void editListFile() throws IOException, ClassNotFoundException {
        FileReadWrite.writeObject("src/Creature/Helpers/Types/SpeciesInfo/SpeciesListFile", this);
    }

    public static SpeciesList getListFile() throws IOException, ClassNotFoundException {
        return (SpeciesList) FileReadWrite.readObject("src/Creature/Helpers/Types/SpeciesInfo/SpeciesListFile");
    }

    public SpeciesList() throws IOException, ClassNotFoundException {
    }

    //We don't want these to be enums. Users should be able to add more types if they require
    String[] baseHumanoidSpecies = {"Dragonborn", "Dwarf", "Elf", "Gnome", "Half-Elf", "Halfling", "Half-Orc", "Human", "Tiefling"};
    String[] baseHumanoidDescriptions = {
            "Dragonborn look very much like dragons standing erect in humanoid form, though they lack wings or a tail.",
            "Bold and hardy, dwarves are known as skilled warriors, miners, and workers of stone and metal.",
            "Elves are a magical people of otherworldly grace, living in the world but not entirely part of it.",
            "A gnome’s energy and enthusiasm for living shines through every inch of his or her tiny body.",
            "Half-elves combine what some say are the best qualities of their elf and human parents.",
            "The diminutive halflings survive in a world full of larger creatures by avoiding notice or, barring that, avoiding offense.",
            "Half-orcs’ grayish pigmentation, sloping foreheads, jutting jaws, prominent teeth, and towering builds make their orcish heritage plain for all to see.",
            "Humans are the most adaptable and ambitious people among the common races. Whatever drives them, humans are the innovators, the achievers, and the pioneers of the worlds.",
            "To be greeted with stares and whispers, to suffer violence and insult on the street, to see mistrust and fear in every eye: this is the lot of the tiefling."
    };
    String[] baseMonsterSpecies = {"Abberation", "Beast", "Celestial", "Construct", "Dragon", "Elemental", "Fey", "Fiend", "Giant",
            "Humanoid", "Ooze", "Plant", "Undead"};
    String[] baseMonsterDescriptions = {
            "Aberrations are alien entities, often with powers drawn from their minds. Examples include aboleths, beholders, mind flayers, and slaadi.",
            "Beasts are nonhumanoid creatures that are part of the natural world. Some beasts have magical powers, but are generally low in intelligence. Ordinary animals, dinosaurs, and giant animals are included in this type. Other examples include quippers and stirges.",
            "Celestials are creatures native to the Upper Planes, and good by nature. Examples include angels, couatls, and pegasi.",
            "Constructs are creatures that are created artificially. Examples include golems, although modrons are also constructs.",
            "Dragons are large, powerful reptilian creatures. This includes both true dragons such as the chromatic and metallic dragons, but also related creatures such as wyverns.",
            "Elementals are creatures from the Elemental Planes. Examples include the common elementals as well as azer, genies, and invisible stalkers.",
            "Fey are creatures of magic with a connection to nature. Often they are connected to the Feywild, or certain Outer Planes such as Arborea or the Beastlands. Examples include dryads and satyrs.",
            "Fiends are creatures native to the Lower Planes, and evil by nature. Examples include demons, devils, and rakshasa.",
            "Giants are human-like but larger than humans. Examples include the true giants as well as ogres and trolls.",
            "Humanoids are the main people of most worlds, bipeds with culture but few natural magical abilities. Examples include humans, dwarves, elves, and the goblinoid races.",
            "Monstrosities are unnatural creatures from a variety of origins, including curses and magical experimentation. Examples include minotaurs and owlbears. The monstrosity type is also a 'catch-all' category for monsters that don't fit into other types.",
            "Oozes are gelatinous creatures with no fixed form. Examples include black puddings and gelatinous cubes.",
            "Plants include both vegetable and fungal creatures. Examples include shambling mounds and myconids.",
            "Undead creatures were once alive, but reanimated by unnatural forces. Examples include ghosts, specters, vampires, and zombies.",
    };
}