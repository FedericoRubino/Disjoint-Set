
/*
  Federico Rubino
  Assignment #6
  MazeGenerator
  DisjointSet.java
  works
*/

public class DisjointSet{

    //constructor
    public DisjointSet(int dimension){
	squared = dimension * dimension;
	sets = squared;
	parent = new int[squared];
	rank = new int[squared];
	for(int i = 0; i < squared; i++){
	    parent[i] = i;
	    rank[i] = 0;
	}
    }

    //finds the parent, if everything has the same parent,
    //then the set is completly joint
    public int find(int x){
	if(x != parent[x]){
	    parent[x] = find(parent[x]);
	}
	return parent[x];
    }

    //links the parents 
    public void union(int x, int y){ 
	if(x == y || find(x) == find(y)) return;
	if(find(x) != find(y)) {
	    sets--;
	    link(find(x), find(y));
	}
    }

    //connects the values in one tree
    private void link(int x, int y){
	if(rank[x] > rank[y]) {
	    parent[y] = x;
	} else {
	    parent[x] = y;
	    if(rank[x] == rank[y])
		rank[y]++;		
	}
    }

    //testing purposes
    public void print(){
	for(int i = 0;i < squared; i++){
	    System.out.printf("%d parent: %d rank: %d%n", i , parent[i],rank[i]);
	}
    }

    //checks if the entire tree is connected
    public boolean allConnected(){
	return (sets == 1);
    }

    //member variables
    private int sets;
    private int[] parent;
    private int[] rank;
    private int squared;
}
