package com.codurance;

public abstract class Cell {

  private final Position position;

  public Cell(Position position) {
    this.position = position;
  }

  public Position position() {
    return position;
  }

  @Override public String toString() {
    return "Cell{" + "position=" + position + '}';
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Cell cell = (Cell) o;

    return position != null ? position.equals(cell.position) : cell.position == null;
  }

  @Override public int hashCode() {
    return position != null ? position.hashCode() : 0;
  }

  public abstract boolean isAliveInNextGeneration(Neighbourhood neighbourhood);

  public abstract boolean isAlive();
}
