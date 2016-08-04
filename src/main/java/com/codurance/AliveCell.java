package com.codurance;

public class AliveCell extends Cell {

  public AliveCell(Position position) {
    super(position);
  }

  @Override public boolean isAliveInNextGeneration(Neighbourhood neighbourhood) {
    int aliveNeighbours = neighbourhood.aliveNeighbours().size();
    return aliveNeighbours == 2 || aliveNeighbours == 3;
  }

  @Override public boolean isAlive() {
    return true;
  }
}
