package com.codurance;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class WorldShould {

  private static final Set<Cell> NO_ALIVE_CELLS = new HashSet<>();
  private static final World WORLD_WITHOUT_ALIVE_CELLS = new World(NO_ALIVE_CELLS);
  private static final Position POSITION = new Position(0, 0);

  @Mock Cell cellAliveInNextGeneration;
  @Mock Cell cellDeadInNextGeneration;

  @Test public void return_a_world_without_alive_cells_if_no_alive_cells_in_seed() {
    World world = new World(NO_ALIVE_CELLS);

    World nextGenerationWorld = world.nextGeneration();

    assertThat(nextGenerationWorld, is(WORLD_WITHOUT_ALIVE_CELLS));
  }

  @Test public void return_a_new_world_containing_only_cells_that_are_alive_in_the_next_generation() {
    Set<Cell> seed = new HashSet<>();
    seed.add(cellAliveInNextGeneration);
    seed.add(cellDeadInNextGeneration);

    World world = new World(seed);

    Neighbourhood cellDeadInNextGenerationNeighbourhood = new Neighbourhood(world, cellDeadInNextGeneration);
    Neighbourhood cellAliveInNextGenerationNeighbourhood = new Neighbourhood(world, cellAliveInNextGeneration);

    when(cellDeadInNextGeneration.isAliveInNextGeneration(cellDeadInNextGenerationNeighbourhood)).thenReturn(false);
    when(cellAliveInNextGeneration.isAliveInNextGeneration(cellAliveInNextGenerationNeighbourhood)).thenReturn(true);
    when(cellDeadInNextGeneration.position()).thenReturn(POSITION);
    when(cellAliveInNextGeneration.position()).thenReturn(POSITION);

    World nextGeneration = world.nextGeneration();

    Set<Cell> nextGenerationSeed = new HashSet<>();
    nextGenerationSeed.add(cellAliveInNextGeneration);

    assertThat(nextGeneration, is(new World(nextGenerationSeed)));
  }

  //@Test public void return_a_new_world_containing_reborn_neighbours_of_current_alive_cells() {
  //  Set<Cell> seed = new HashSet<>();
  //  seed.add(cellAliveInNextGeneration);
  //  seed.add(cellDeadInNextGeneration);
  //
  //  World world = new World(seed);
  //
  //  Neighbourhood cellDeadInNextGenerationNeighbourhood = new Neighbourhood(world, cellDeadInNextGeneration);
  //  Neighbourhood cellAliveInNextGenerationNeighbourhood = new Neighbourhood(world, cellAliveInNextGeneration);
  //
  //  when(cellDeadInNextGeneration.isAliveInNextGeneration(cellDeadInNextGenerationNeighbourhood)).thenReturn(false);
  //  when(cellAliveInNextGeneration.isAliveInNextGeneration(cellAliveInNextGenerationNeighbourhood)).thenReturn(true);
  //
  //  World nextGeneration = world.nextGeneration();
  //
  //  Set<Cell> nextGenerationSeed = new HashSet<>();
  //  nextGenerationSeed.add(cellAliveInNextGeneration);
  //
  //  assertThat(nextGeneration, is(new World(nextGenerationSeed)));
  //
  //
  //}
}