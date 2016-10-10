/**
 * 
 * @author mikhail
 * Created			10.10.2016
 * Last modified	10.10.2016
 */

import edu.princeton.cs.algs4.*;

public class Solver
{	
	private Node tree;
	private boolean solvable;
	
	private final class Node implements Comparable<Node> {
		private Node parent;
		private Board board;
		public Node(Node parent, Board board) {
			this.parent = parent;
			this.board = board;
		}
		private int getMoves() {
			int r = 0;
			Node node = parent;
			while (node != null) {
				r++;
				node = node.parent;
			}
			return r;
		}
		public int getPriority () 
		{
			return board.manhattan() + getMoves();
		}
		public int compareTo(Node that) {
			return this.getPriority() - that.getPriority();
		}
	}
	
	/**
	 * Find a solution to the initial board (using the A* algorithm)
	 * @param initial
	 */
    public Solver(Board initial)
    {
    	if (initial == null)
    		throw new java.lang.NullPointerException();
    	
    	MinPQ<Node> pq = new MinPQ<Node>();
    	pq.insert(new Node(null, initial));

    	MinPQ<Node> twinPq = new MinPQ<Node>();
    	twinPq.insert(new Node(null, initial.twin()));
    	
    	while (!pq.isEmpty() && !twinPq.isEmpty()) {    		
    		Node node = pq.delMin();
    		if (node.board.isGoal()) {
    			solvable = true;
    			tree = node;
    			return;
    		}
   			for (Board neighbor : node.board.neighbors())
   				if (node.parent == null || !neighbor.equals(node.parent.board))
   					pq.insert(new Node(node, neighbor));

   			node = twinPq.delMin();
   			if (node.board.isGoal()) {
   				solvable = false;
   				return;
   			}
   			for (Board neighbor : node.board.neighbors())
   				if (node.parent == null || !neighbor.equals(node.parent.board))
   					twinPq.insert(new Node(node, neighbor));
    	}
    }
    
    /**
     * Is the initial board solvable?
     * @return
     */
    public boolean isSolvable()
    {
    	return solvable;
    }
    
    /**
     * min number of moves to solve initial board; -1 if unsolvable 
     * @return
     */
    public int moves()
    {
    	if (solvable) 	return tree.getMoves();
    	else			return -1;
    }
    
    /**
     * Sequence of boards in a shortest solution; null if unsolvable
     * @return
     */
    public Iterable<Board> solution()
    {
    	if (solvable) {
    		Stack<Board> stack = new Stack<Board>();
        	Node node = tree;
        	while (node != null) {
        		stack.push(node.board);
        		node = node.parent;
    		}
    		return stack;
    	}
    	return null;
    }
    
    /**
     * Solve a slider puzzle (given below)
     * @param args
     */
    public static void main(String[] args)
    {
    	 // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
