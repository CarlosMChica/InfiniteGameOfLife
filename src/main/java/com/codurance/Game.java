package com.codurance;

public class Game {

  private final World seed;
  private final WorldPrinter worldPrinter;

  public Game(World seed,
              WorldPrinter worldPrinter) {
    this.seed = seed;
    this.worldPrinter = worldPrinter;
  }

  public void run(int generations) {
    print(seed);
    printWorlds(generations);
  }

  private void printWorlds(int generations) {
    World world = seed;
    for (int i = 0; i < generations; i++) {
      world = world.nextGeneration();
      print(world);
    }
  }

  private void print(World world) {
    worldPrinter.print(world);
  }
}
