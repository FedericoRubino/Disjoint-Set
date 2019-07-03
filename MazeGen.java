/*
  Federico Rubino
  frubino
  MazeGen.java
  Maze Generation with Disjoint Sets
  Assignment #6
  tested/ working
*/


import java.util.Random;
import java.lang.Math;

public class MazeGen {

    //initialize maze, with an array n squared 
    //fill first quadrant with B to have one opening on the left
    //fill last with E to have an opening on the right
    //the rest of the maze will be closed boxes(F) //15
    public MazeGen(int n){
	rows = n;
	size = n * n;
	set = new DisjointSet(n);
	maze = new int[size]; // n x n sized maze
	maze[0] = 11; //B open on left
	maze[size - 1] = 14; //E open on right
	for(int i = 1; i < size - 1; i++){//fill with E's
	    maze[i] = 15;//F closed
	}
    }

    //this will randomly connect all rooms 
    public void generateMaze(){
	Random rand = new Random();
	int room2 = -1;
	boolean connected = false;
	while (!connected){
	    //	    int mazeInd = 0;
	    int mazeInd = rand.nextInt(size);
	    //while loop bc i want to check every time
	    //	    while(!connected){
		room2 = -1;
		int value = rand.nextInt(4); //random value 0-3
		switch(value) {
		case 0: //left -1
		    if(mazeInd % rows != 0) { //if its not on left
			room2 = mazeInd - 1;
		    }
		    break;
		case 1: //right +1
		    if((mazeInd + 1) % rows != 0){ //not on right egde
			room2 = mazeInd + 1;
		    }
		    break;
		case 2: // bottom - rows
		    if(mazeInd >= rows){ 
			room2 = mazeInd - rows;
		    }		    
		    break;
		default: // top + rows
		    if(mazeInd < (size - rows)){ 
			room2 = mazeInd + rows;
		    }
		    break;
		}//switch
		if(room2 >= 0 && mazeInd < size ){ 
		    connect(mazeInd, room2);
		}
		//		mazeInd++;
		connected = allConnected();
		//	    }//while 1
	}//while
    }

    //this will print the maze in the apropriate format
    public void printMaze(){
	int num = 0;
	for(int i = 0; i < rows; i++){
	    for(int j = 0; j < rows; j++){
		if(maze[num] > 9){
		    System.out.printf("%c", (maze[num] + 87));
		}else{
		    System.out.printf("%d", maze[num]);
		}
		num++;
	    }
	    System.out.println();
	}
    }
    
    //connect(union) the rooms
    private void connect(int room1, int room2){
	if(set.find(room1) == set.find(room2) || room1 < 0 || room2 < 0){
	    return;
	}
	set.union(room1, room2);//connects in DisjointSet
	//this ensures that room2 index is the larger index than room1
	if(room2 < room1){
	    int temp = room1;
	    room1 = room2;
	    room2 = temp;
	}
	int diff = room2 - room1;
	if(diff == rows){
	    //top-bottom case room2 will always be the one on bottom
	    maze[room1] = maze[room1] & 0x0D; //remove bottom
	    maze[room2] = maze[room2] & 0x07; // remove top
	}
	if(diff == 1){
	    //left-right case room2 will always be on the right
	    maze[room1] = maze[room1] & 0x0E; //remove right
	    maze[room2] = maze[room2] & 0x0B; //remove left
	}
    }

    //checks if all sets are connected
    public boolean allConnected(){
	return set.allConnected();
    }

    //member variables

    private int maze[];
    private int size;
    private int rows;
    private DisjointSet set;
}
