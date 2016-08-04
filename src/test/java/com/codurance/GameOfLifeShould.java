package com.codurance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) public class GameOfLifeShould {

  private static final int NUMBER_OF_GENERATIONS = 2;
  private static final int NO_GENERATIONS = 0;

  @Mock WorldPrinter worldPrinter;

  @Mock World seed;
  @Mock World world;
  @Mock World world2;

  private Game game;

  @Before public void setUp() {
    game = new Game(seed, worldPrinter);
  }

  @Test public void always_print_seed_world() {
    game.run(NO_GENERATIONS);

    verify(worldPrinter).print(seed);
  }

  @Test public void print_world_for_every_generation() {
    givenWorldsForAllGenerations();

    game.run(NUMBER_OF_GENERATIONS);

    verify(worldPrinter).print(world);
    verify(worldPrinter).print(world2);
  }

  private void givenWorldsForAllGenerations() {
    givenFirstGenerationWorld();
    givenSecondGenerationWorld();
  }

  private void givenFirstGenerationWorld() {
    when(seed.nextGeneration()).thenReturn(world);
  }

  private void givenSecondGenerationWorld() {
    when(world.nextGeneration()).thenReturn(world2);
  }
}
