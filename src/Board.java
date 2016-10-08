/**
 * 
 * @author mikhail
 *
 */

import java.util.Iterator;
import edu.princeton.cs.algs4.*;


public final class Board
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
    		for (int j = 0; j < n; j++)
    			if (blocks[i][j] != 0 && blocks[i][j] != k++) h++;
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
    		{
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
    			if (blocks[i][j] != (k++ % n*n)) return false;
    	return true;
    }
    
    /**
     * a board that is obtained by exchanging any pair of blocks
     * @return
     */
    public Board twin()
    {
    	int[][] blocksCopy = new int[n][n];
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < n; j++)
    			blocksCopy[i][j] = blocks[i][j];
    	
    	int k = 1;
    	if (blocksCopy[(k-1) / n][(k-1) % n] == 0) k++;
    	int l = k+1;
    	if (blocksCopy[(l-1) / n][(l-1) % n] == 0) l++;
    	
    	int t = blocksCopy[(k-1) / n][(k-1) % n];
    	blocksCopy[(k-1) / n][(k-1) % n] = blocksCopy[(l-1) / n][(l-1) % n];
    	blocksCopy[(l-1) / n][(l-1) % n] = t;
    	
    	return new Board(blocksCopy);
    }
    
    /**
     * does this board equal y?
     */
    public boolean equals(Object y)
    {
    	return toString().equals(y.toString());
    }
    
    /**
     * all neighboring boards
     * @return
     */
    public Iterable<Board> neighbors()
    {
    	Stack<Board> stack = new Stack<Board>();
    	
    	int zeroI = 0;
    	int zeroJ = 0;
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < n; j++)
    			if (blocks[i][j] == 0) {
    				zeroI = i;
    				zeroJ = j;
    			}
    	
    	for (int i = zeroI-1; i <= zeroI+1; i+=2)
    		for (int j = zeroJ-1; j <= zeroJ+1; j+=2) {
    			if (i >= 0 && i < n && j >= 0 && j < n) {
    				
    				
    			}
    			
    		}
    			
    	
    	return stack;
    }
    
    /**
     * string representation of this board (in the output format specified below)
     */
    public String toString()
    {
    	return null;
    }

    /**
     * unit tests (not graded)
     * @param args
     */
    public static void main(String[] args)
    {    	
    }
}