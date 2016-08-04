package com.codurance;

public class DeadCell extends Cell {
  public DeadCell(Position position) {
    super(position);
  }

  @Override public boolean isAliveInNextGeneration(Neighbourhood neighbourhood) {
    return neighbourhood.aliveNeighbours().size() == 3;
  }

  @Override public boolean isAlive() {
    return false;
  }
}
