/**
 * 
 * @author mikhail
 * Created 			07.10.2016
 * Last modified	10.10.2016
 *
 */

import edu.princeton.cs.algs4.*;

public class Board
{
	private final int[][] blocks;
	private final int n;
	
	/**
	 * construct a board from an n-by-n array of blocks
	 * (where blocks[i][j] = block in row i, column j)
	 * @param blocks
	 */
    public Board(int[][] blocks_)
    {
    	n = blocks_.length;
    	blocks = new int[n][n];
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < n; j++)
    			blocks[i][j] = blocks_[i][j];
    }
       
    /**
     * board dimension n
     * @return
     */
    public int dimension()
    {
    	return n;
    }
    
    /**
     * number of blocks out of place
     * @return
     */
    public int hamming()
    {
    	int h = 0;
    	int k = 1;
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < n; j++) {
    			if (blocks[i][j] != 0 && blocks[i][j] != k) h++;
    			k++;
    		}
    			
    	return h;
    }
    
    /**
     * sum of Manhattan distances between blocks and goal
     * @return
     */
    public int manhattan()
    {
    	int m = 0;
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < n; j++)
    			if (blocks[i][j] != 0) {
    				m += Math.abs((blocks[i][j]-1) / n - i);
    				m += Math.abs((blocks[i][j]-1) % n - j);
    			}
    	return m;
    }
    
    /**
     * is this board the goal board?
     * @return
     */
    public boolean isGoal()
    {
    	int k = 1;
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < n; j++)    			
    			if (blocks[i][j] != k++ % (n*n)) return false;
    	return true;
    }

    /**
     * a board that is obtained by exchanging any pair of blocks
     * @return
     */
    public Board twin()
    {
    	// Make copy of blocks
    	int[][] copy = new int[n][n];
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < n; j++)
    			copy[i][j] = blocks[i][j];
    	
    	// for exchange get first element
    	int k = 0;
    	// if zero get next
    	if (copy[k / n][k % n] == 0) k++;
    	// and next after k element
    	int l = k+1;
    	// if zero get next
    	if (copy[l / n][l % n] == 0) l++;
    	
    	// exchange them
    	int t = copy[k / n][k % n];
    	copy[k / n][k % n] = copy[l / n][l % n];
    	copy[l / n][l % n] = t;
    	
    	return new Board(copy);
    }
    
    /**
     * does this board equal y?
     */
    public boolean equals(Object y)
    {
    	if (y == null)
    		return false;
    	
    	return toString().equals(y.toString());
    }
    
    /**
     * all neighboring boards
     * @return
     */
    public Iterable<Board> neighbors()
    {
    	Stack<Board> stack = new Stack<Board>();
    	
    	// Find 0-th element
    	int zeroI = 0;
    	int zeroJ = 0;
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < n; j++)
    			if (blocks[i][j] == 0) {
    				zeroI = i; zeroJ = j;
    				break;
    			}
    	
    	for (int i = zeroI-1; i <= zeroI+1; i+=2)
   			if (i >= 0 && i < n) {   				
   				int t = blocks[zeroI][zeroJ];
   				blocks[zeroI][zeroJ] = blocks[i][zeroJ]; 
   				blocks[i][zeroJ] = t;
   				stack.push(new Board(blocks));
   				t = blocks[zeroI][zeroJ];
   				blocks[zeroI][zeroJ] = blocks[i][zeroJ]; 
   				blocks[i][zeroJ] = t;
   			}

   		for (int j = zeroJ-1; j <= zeroJ+1; j+=2)
   			if (j >= 0 && j < n) {
   				int t = blocks[zeroI][zeroJ];
   				blocks[zeroI][zeroJ] = blocks[zeroI][j]; 
   				blocks[zeroI][j] = t;
   				stack.push(new Board(blocks));
   				t = blocks[zeroI][zeroJ];
   				blocks[zeroI][zeroJ] = blocks[zeroI][j]; 
   				blocks[zeroI][j] = t;   				
   			}
   		
    	return stack;
    }
    
    /**
     * string representation of this board (in the output format specified below)
     */
    public String toString()
    {
    	String out = Integer.toString(n) + "\n";
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++)
    			out = out + " " + blocks[i][j];
    		out = out + "\n";
    	}
    	return out;
    }

    /**
     * unit tests (not graded)
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
        Board board = new Board(blocks);
        
        StdOut.print(board.manhattan());
    }
}