package com.codurance;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) public class NeighbourhoodShould {

  private static final Position POSITION = new Position(0, 0);
  private static final Cell CELL = new AliveCell(POSITION);
  private static final Cell ALIVE_NEIGHBOUR = new AliveCell(POSITION);
  private static final Cell DEAD_NEIGHBOUR_1 = new DeadCell(CELL.position().left());
  private static final Cell DEAD_NEIGHBOUR_2 = new DeadCell(CELL.position().topLeft());

  @Mock World world;

  @Test public void return_dead_neighbours_of_given_cell() {
    when(world.cellAt(CELL.position().left())).thenReturn(DEAD_NEIGHBOUR_1);
    when(world.cellAt(CELL.position().topLeft())).thenReturn(DEAD_NEIGHBOUR_2);
    when(world.cellAt(CELL.position().top())).thenReturn(ALIVE_NEIGHBOUR);
    when(world.cellAt(CELL.position().topRight())).thenReturn(ALIVE_NEIGHBOUR);
    when(world.cellAt(CELL.position().right())).thenReturn(ALIVE_NEIGHBOUR);
    when(world.cellAt(CELL.position().bottomRight())).thenReturn(ALIVE_NEIGHBOUR);
    when(world.cellAt(CELL.position().bottom())).thenReturn(ALIVE_NEIGHBOUR);
    when(world.cellAt(CELL.position().bottomLeft())).thenReturn(ALIVE_NEIGHBOUR);

    Neighbourhood neighbourhood = new Neighbourhood(world, CELL);

    Set<Cell> deadNeighbours = neighbourhood.deadNeighbours();

    Set<Cell> expectedDeadNeighbours = new HashSet<>();
    expectedDeadNeighbours.add(DEAD_NEIGHBOUR_1);
    expectedDeadNeighbours.add(DEAD_NEIGHBOUR_2);

    assertThat(deadNeighbours, is(expectedDeadNeighbours));
  }
}