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

@RunWith(MockitoJUnitRunner.class) public class AliveCellShould {

  private static final AliveCell NEIGHBOUR_1 = new AliveCell(new Position(0, 1));
  private static final AliveCell NEIGHBOUR_2 = new AliveCell(new Position(0, 0));
  private static final AliveCell NEIGHBOUR_3 = new AliveCell(new Position(0, 2));

  private static final Set<Cell> TWO_NEIGHBOURS = new HashSet<Cell>() {
    {
      add(NEIGHBOUR_1);
      add(NEIGHBOUR_2);
    }
  };

  private static final Set<Cell> THREE_NEIGHBOURS = new HashSet<Cell>() {{
    add(NEIGHBOUR_1);
    add(NEIGHBOUR_2);
    add(NEIGHBOUR_3);
  }};

  @Mock Neighbourhood neighbourhood;

  @Test public void be_alive_in_next_generation_if_has_two_alive_neighbours() {
    givenNeighborhoodWithAliveNeighbours(THREE_NEIGHBOURS);

    AliveCell aliveCell = new AliveCell(null);

    assertThat(aliveCell.isAliveInNextGeneration(neighbourhood), is(true));
  }

  @Test public void be_alive_in_next_generation_if_has_three_alive_neighbours() {
    givenNeighborhoodWithAliveNeighbours(TWO_NEIGHBOURS);

    AliveCell aliveCell = new AliveCell(null);

    assertThat(aliveCell.isAliveInNextGeneration(neighbourhood), is(true));
  }


  private void givenNeighborhoodWithAliveNeighbours(Set<Cell> neighbours) {
    when(neighbourhood.aliveNeighbours()).thenReturn(neighbours);
  }
}