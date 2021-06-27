package Creature.Helpers.Types.SpeciesInfo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class SpeciesListTesting {
    private SpeciesList speciesList;
    @BeforeEach
    void setup() throws IOException, ClassNotFoundException {
        speciesList = new SpeciesList();
    }
}
