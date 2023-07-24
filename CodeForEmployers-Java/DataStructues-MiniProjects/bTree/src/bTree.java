import java.util.*;


//All comments are to demonstrate understanding  
//and for personal learning
public class bTree {
	// make variable t for the trees minimum order
	int t;
	
	public class Node{
		int n;
		int key[] = new int[2*t-1];
		Node children[] = new Node[2*t];
		boolean isLeaf = true;
		
		public String printKeys() {
			String result = "";
			for(int i=0; i < key.length; i++) {
				result += key[i];
			}
			return result;
		}
	}
	
	Node root;
	
	//B-tREE-CREATE 
	public bTree(int t) {
		//setting the n for t given t
		this.t = t;
		//allocating the node and setting it to the root
		root = new Node();
		root.n = 0;
		root.isLeaf = true;
	}
	
	//B-tREE-SPLt-CHILD (FIX MY VARIABLES)
	public void splitChild(Node x, int i) {
		//create new nodes for placeholders
		Node z = new Node();
		//set y to the child in question
		Node y = x.children[i];
		//if y is a leaf, so is z
		z.isLeaf = y.isLeaf;
		//z's order is t-1
		z.n = t-1;
		//for each value of t-1
		for(int j=0; j < t-1; j++) {
			//set z's key of that value to 
			z.key[j] = y.key[j+t];
		}
		if(y.isLeaf == false) {
			for(int j=0; j<t; j++) {
				z.children[j] = y.children[j+t];
			}
		}
		y.n = t-1;
		for(int j=x.n; j>i+1; j--){
			x.children[j+1] = x.children[j];
		}
		x.children[i+1] = z;
		for(int j=x.n-1; j>i; j--) {
			x.key[j+1] = x.key[j];
		}
		x.key[i] = y.key[t];
		x.n = x.n + 1;
	}
	
	//B-tREE-INSERt (FIX MY VAIABLES
	public void insert(bTree t, int k) {
		Node r = t.root;
		if(r.n == (2*this.t-1)) {
			Node s = new Node();
			t.root = s;
			s.isLeaf = false;
			s.n = 0;
			s.children[0] = r;
			splitChild(s, 0);
			insertNonfull(s, k);
		}
		else {
			insertNonfull(r, k);
		}
	}
	
	// B-tREE-INSERt-NONFULL
	public void insertNonfull(Node x, int k) {
		int i = x.n-1;
		if(x.isLeaf) {
			while(i >= 0 && k < x.key[i]) {
				x.key[i+1] = x.key[i];
				i = i-1;
			}
			x.key[i+1] = k;
			x.n = x.n + 1;
		}
		else {
			while(i >= 0 && k < x.key[i]) {
				i = i-1;
			}
			i = i+1;
			if(x.children[i].n == 2*t-1) {
				splitChild(x, i);
				if(k > x.key[i]) {
					i = i+1;
				}
			}
			insertNonfull(x.children[i], k);
		}
	}
	
	//This was our attempt at a print method that we could not get to work 
	public void printFunction(bTree T) {
		ArrayList<Node> AL = new ArrayList<Node>();
		AL.add(T.root);
		printArrayList(AL);
		
		while(true) {
			ArrayList<Node> Temp = AL;
			AL = new ArrayList<Node>();
			for(int i=0; i<Temp.size(); i++) {
				if(!Temp.get(i).isLeaf) {
					for(int j=0; j<Temp.get(i).children.length; j++) {
						AL.add(Temp.get(i).children[j]);
					}
				}
			}
			if(AL.size() == 0) {
				break;
			}
			printArrayList(AL);
		}
	}
	
	public void printArrayList(ArrayList<Node> x) {
		String r = "";
		for(int i=0; i<x.size(); i++) {
			r += "   " + x.get(i).printKeys() + "   ";
		}
		System.out.println(r);
	}
	

	

	public void functionalityPrint(Node x, int p, String name) {
		if (x!=null) {
		System.out.print("Keys in " + name + " of tree " + p + ": ");
		for(int j=0; j<x.n; j++) {
			System.out.print((char)x.key[j] + " ");
		}
		System.out.println("");
		}
	}
	
	public static void main(String args[]) {
		bTree tree1 = new bTree(2);
		bTree tree2 = new bTree(3);
		
		//97-122 is the alphabet in ascii numbers
		for(int i=97; i < 123; i++) {
			tree1.insert(tree1, i);
			tree2.insert(tree2, i);
			
			tree1.functionalityPrint(tree1.root, 1, "root");
			for(int r=0; r<4; r++) {
				tree1.functionalityPrint(tree1.root.children[r], 1, "child" + r);
			}
			System.out.println("");
			tree2.functionalityPrint(tree2.root, 2, "root");
			for(int r=0; r<6; r++) {
				tree2.functionalityPrint(tree2.root.children[r], 2, "child" + r);
			}
			System.out.println("");
			
			
		}
		
	}
}
