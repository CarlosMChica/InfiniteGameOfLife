package com.codurance;

import java.util.HashSet;
import java.util.Set;

public class World {

  private final Set<Cell> aliveCells;

  public World(Set<Cell> seed) {
    this.aliveCells = seed;
  }

  public World nextGeneration() {
    Set<Cell> nextGeneration = new HashSet<>();
    aliveCells.forEach(cell -> {
      if (cell.isAliveInNextGeneration(neighbourhood(cell))) {
        nextGeneration.add(cell);
      }
      neighbourhood(cell).deadNeighbours().forEach(neighbour -> {
        if (neighbour.isAliveInNextGeneration(neighbourhood(neighbour))) {
          nextGeneration.add(neighbour);
        }
      });
    });
    return new World(nextGeneration);
  }

  public Cell cellAt(Position position) {
    return aliveCells.stream()
        .filter(cell -> cell.position().equals(position))
        .findFirst()
        .orElse(new DeadCell(position));
  }

  private Neighbourhood neighbourhood(Cell cell) {
    return new Neighbourhood(this, cell);
  }

  @Override public String toString() {
    return "World{" + "aliveCells=" + aliveCells + '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    World world = (World) o;

    return aliveCells != null ? aliveCells.equals(world.aliveCells) : world.aliveCells == null;
  }

  @Override public int hashCode() {
    return aliveCells != null ? aliveCells.hashCode() : 0;
  }
}
