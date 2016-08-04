package com.codurance;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class Neighbourhood {

  private final World world;
  private final Cell cell;

  public Neighbourhood(World world,
                       Cell cell) {
    this.world = world;
    this.cell = cell;
  }

  public Set<Cell> aliveNeighbours() {
    return filterNeighbours(Cell::isAlive);
  }

  public Set<Cell> deadNeighbours() {
    return filterNeighbours(cell -> !cell.isAlive());
  }

  private Set<Cell> filterNeighbours(Predicate<Cell> predicate) {
    return neighbours().
        filter(predicate).
        collect(toSet());
  }

  private Stream<Cell> neighbours() {
    Set<Cell> neighbours = new HashSet<>();
    neighbours.add(world.cellAt(cell.position().left()));
    neighbours.add(world.cellAt(cell.position().topLeft()));
    neighbours.add(world.cellAt(cell.position().top()));
    neighbours.add(world.cellAt(cell.position().topRight()));
    neighbours.add(world.cellAt(cell.position().right()));
    neighbours.add(world.cellAt(cell.position().bottomRight()));
    neighbours.add(world.cellAt(cell.position().bottom()));
    neighbours.add(world.cellAt(cell.position().bottomLeft()));
    return neighbours.stream();
  }

  @Override public String toString() {
    return "Neighbourhood{" + "world=" + world + ", cell=" + cell + '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Neighbourhood that = (Neighbourhood) o;

    if (world != null ? !world.equals(that.world) : that.world != null) {
      return false;
    }
    return cell != null ? cell.equals(that.cell) : that.cell == null;
  }

  @Override public int hashCode() {
    int result = world != null ? world.hashCode() : 0;
    result = 31 * result + (cell != null ? cell.hashCode() : 0);
    return result;
  }
}
