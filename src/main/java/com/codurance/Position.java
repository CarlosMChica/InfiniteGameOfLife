package com.codurance;

public class Position {
  private final int x;
  private final int y;

  public Position(int x,
                  int y) {

    this.x = x;
    this.y = y;
  }

  @Override public String toString() {
    return "Position{" + "x=" + x + ", y=" + y + '}';
  }

  @Override public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Position position = (Position) o;

    if (x != position.x) {
      return false;
    }
    if (y != position.y) {
      return false;
    }

    return true;
  }

  @Override public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }

  public Position left() {
    return new Position(x - 1, y);
  }

  public Position topLeft() {
    return new Position(x - 1, y - 1);
  }

  public Position top() {
    return new Position(x, y - 1);
  }

  public Position topRight() {
    return new Position(x + 1, y - 1);
  }

  public Position right() {
    return new Position(x + 1, y);
  }

  public Position bottomRight() {
    return new Position(x + 1, y + 1);
  }

  public Position bottom() {
    return new Position(x, y - 1);
  }

  public Position bottomLeft() {
    return new Position(x - 1, y + 1);
  }
}
