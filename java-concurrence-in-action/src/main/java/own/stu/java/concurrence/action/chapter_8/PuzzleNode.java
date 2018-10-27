package own.stu.java.concurrence.action.chapter_8;

import java.util.LinkedList;
import java.util.List;

public class PuzzleNode<P, M> {
  final P position;
  final M move;
  final PuzzleNode<P, M> prev;

  public PuzzleNode(P position, M move, PuzzleNode<P, M> prev) {
    this.position = position;
    this.move = move;
    this.prev = prev;
  }

  List<M> asMoveList(){
    List<M> solution = new LinkedList<M>();
    for(PuzzleNode<P, M> n = this; n != null; n = n.prev)
      solution.add(0, n.move);

    return solution;
  }
}
