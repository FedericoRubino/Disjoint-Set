/*
  Federico Rubino
  frubino
  Assignment #6
  Maze Generation with Disjoint Sets
  p6.java
  works
*/

//I/O
public class p6 {

    public static void main(String args[]){
	int num = 0;
	try{
	    num = Integer.parseInt(args[0]);
	    if(num >= 3){
		MazeGen maze = new MazeGen(num);
		maze.generateMaze();
		maze.printMaze();
	    } else {
		System.out.println("maze size must at least be 3");
	    }
	} catch(NumberFormatException e) {
	    System.err.println( args[0] + " is not a valid integer");
	}
    }
}
