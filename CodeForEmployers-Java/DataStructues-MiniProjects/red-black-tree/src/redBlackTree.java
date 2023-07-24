import java.io.*;



public class redBlackTree {
	
	static final int COUNT = 5;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\\u001B[30m";
	public static final String ANSI_RED = "\\u001B[31m";
	
	public static class Node{
		boolean isBlack;
		int key;
		Node left;
		Node right;
		Node parent;
		
		public Node(int key) {
			isBlack = true;
			this.key = key;
			right = null;
			left = null;
			parent = null;
		}
	}

	Node nil = new Node(-1);
	Node root = nil;
	
	public void insert(redBlackTree tree, Node insertNode) {
		Node y = tree.nil;
		Node nodeIter = tree.root;
		while(nodeIter != tree.nil) {
			y = nodeIter;
			if(insertNode.key < nodeIter.key) {
				nodeIter = nodeIter.left;
			}
			else {
				nodeIter = nodeIter.right;
			}
		}
		insertNode.parent = y;
		if(y == tree.nil) {
			tree.root = insertNode;
		}
		else if(insertNode.key < y.key) {
			y.left = insertNode;
		}
		else {
			y.right = insertNode;
		}
		insertNode.left = tree.nil;
		insertNode.right = tree.nil;
		insertNode.isBlack = false;
		insertFixup(tree, insertNode);
	}
	
	public void insertFixup(redBlackTree tree, Node insertNode) {
		while(insertNode.parent.isBlack == false) {
			if(insertNode.parent == insertNode.parent.parent.left) {
				Node temp = insertNode.parent.parent.right;
				if(temp.isBlack = false) {
					insertNode.parent.isBlack = true;
					temp.isBlack = true;
					insertNode.parent.parent.isBlack = false;
					insertNode = insertNode.parent.parent;
				}
				else if(insertNode == insertNode.parent.right) {
					insertNode = insertNode.parent;
					leftRotate(tree, insertNode);
				}
				insertNode.parent.isBlack = true;
				insertNode.parent.parent.isBlack = false;
				rightRotate(tree, insertNode.parent.parent);
			}
			else {
				Node temp2 = insertNode.parent.parent.left;
				if(temp2.isBlack = false) {
					insertNode.parent.isBlack = true;
					temp2.isBlack = true;
					insertNode.parent.parent.isBlack = false;
					insertNode = insertNode.parent.parent;
				}
				else if(insertNode == insertNode.parent.left) {
					insertNode = insertNode.parent;
					rightRotate(tree, insertNode);
				}
				insertNode.parent.isBlack = true;
				insertNode.parent.parent.isBlack = false;
				leftRotate(tree, insertNode.parent.parent);
				}
			}
		tree.root.isBlack = true;
		}
	
	public Node treeMinimum(redBlackTree T, Node root) {
		while(root.left != T.nil) {
			root = root.left;
		}
		return root;
	}
	
	public void leftRotate(redBlackTree T, Node x) {
		Node y;
		y = x.right;
		x.right = y.left;
		if(y.left != T.nil) {
			y.left.parent = x;
		}
		y.parent = x.parent;
		if(x.parent == T.nil) {
			T.root = y;
		}
		else if(x == x.parent.left) {
			x.parent.left = y;
		}
		else {
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}
	
	public void rightRotate(redBlackTree T, Node x) {
		Node y;
		y = x.left;
		x.left = y.right;
		if(y.right != T.nil) {
			y.right.parent = x;
		}
		y.parent = x.parent;
		if(x.parent == T.nil) {
			T.root = y;
		}
		else if(x == x.parent.right) {
			x.parent.right = y;
		}
		else {
			x.parent.left = y;
		}
		y.right = x;
		x.parent = y;
	}
	
	public void transplant(redBlackTree T, Node currentNode, Node newNode) {
		if(currentNode.parent == T.nil) {
			T.root = newNode;
		}
		else if(currentNode == currentNode.parent.left) {
			currentNode.parent.left = newNode;
		}
		else {
			currentNode.parent.right = newNode;
		}
		newNode.parent = currentNode.parent;
	}
	
	public void delete(redBlackTree T, Node deleted) {
		Node temp = deleted;
		Node tempColor = temp;
		tempColor.isBlack = temp.isBlack;
		Node temp2;
		temp.isBlack = true;
		if(deleted.left == T.nil) {
			temp2 = deleted.right;
			transplant(T, deleted, deleted.right);
		}
		else if(deleted.right == T.nil) {
			temp2 = deleted.left;
			transplant(T, deleted, deleted.left);
		}
		else {
			temp = treeMinimum(T, deleted.right);
			tempColor.isBlack = temp.isBlack;
			temp2 = temp.right;
			if(temp.parent == deleted) {
				temp2.parent = temp;
			}
			else {
				transplant(T, temp, temp.right);
				temp.right = deleted.right;
				temp.right.parent = temp;
			}
			transplant(T, deleted, temp);
			temp.left = deleted.left;
			temp.left.parent = temp;
			temp.isBlack = deleted.isBlack;
		}
		if(tempColor.isBlack) {
			deleteFixup(T, temp2);
		}
	}
	
	public void deleteFixup(redBlackTree T, Node fixNode) {
		Node temp;
		while(fixNode != T.root && fixNode.isBlack) {
			if(fixNode == fixNode.parent.left) {
				temp = fixNode.parent.right;
				if(temp.isBlack = false) {
					temp.isBlack = true;
					fixNode.parent.isBlack = false;
					leftRotate(T, fixNode.parent);
					temp = fixNode.parent.right;	
				}
				if(temp.left.isBlack && temp.right.isBlack) {
					temp.isBlack = false;
					fixNode = fixNode.parent;
				}
				else if(temp.right.isBlack) {
					temp.left.isBlack = true;
					temp.isBlack = false;
					rightRotate(T, temp);
					temp = fixNode.parent.right;
				}
				temp.isBlack = fixNode.parent.isBlack;
				fixNode.parent.isBlack = true;
				temp.right.isBlack = true;
				leftRotate(T, fixNode.parent);
				fixNode = T.root;
			}
			else {
				temp = fixNode.parent.left;
				if(temp.isBlack = false) {
					temp.isBlack = true;
					fixNode.parent.isBlack = false;
					rightRotate(T, fixNode.parent);
					temp = fixNode.parent.left;	
				}
				if(temp.right.isBlack && temp.left.isBlack) {
					temp.isBlack = false;
					fixNode = fixNode.parent;
				}
				else if(temp.left.isBlack) {
					temp.right.isBlack = true;
					temp.isBlack = false;
					leftRotate(T, temp);
					temp = fixNode.parent.left;
				}
				temp.isBlack = fixNode.parent.isBlack;
				fixNode.parent.isBlack = true;
				temp.left.isBlack = true;
				rightRotate(T, fixNode.parent);
				fixNode = T.root;
			}
		}
		fixNode.isBlack = true;	
	}
	/*
	 * Use of print2DUtil was inspired by author on StackOverflow, 
	 * i added Ansi color to get colors and used simular methodology
	 * I just wanted to make this aware to avoid plagiarism instances
	 */
	static void print2DUtil(Node root, int space)
    {
        if (root == null)
            return;
        space += COUNT;
        print2DUtil(root.right, space);
 
        if (root.isBlack) {
        	System.out.print("\n");
        	for (int i = COUNT; i < space; i++)
        		System.out.print(" ");
        	System.out.print( root.key + "\n");
        }
        else {
        	System.out.print("\n");
        	for (int i = COUNT; i < space; i++)
        		System.out.print(" ");
        	System.out.print("\u001b[1;31m" + root.key + "\u001b[0m" + "\n");
        }
        print2DUtil(root.left, space);
    }
    static void print2D(Node root)
    {
        print2DUtil(root, 0);
    }
 
    public static void main(String args[])
    {
    	redBlackTree tree = new redBlackTree();
        tree.root.key = 26;
        tree.root.parent = tree.nil;
        Node node2 = new Node(17);
        tree.insert(tree, node2);
        Node node3 = new Node(14);
        tree.insert(tree, node3);
        Node node4 = new Node(10);
        tree.insert(tree, node4);
        Node node5 = new Node(7);
        tree.insert(tree, node5);
        Node node6 = new Node(3);
        tree.insert(tree, node6);
        Node node7 = new Node(12);
        tree.insert(tree, node7);
        Node node8 = new Node(16);
        tree.insert(tree, node8);
        Node node9 = new Node(15);
        tree.insert(tree, node9);
        Node node10 = new Node(21);
        tree.insert(tree, node10);
        Node node11 = new Node(19);
        tree.insert(tree, node11);
        Node node12 = new Node(20);
        tree.insert(tree, node12);
        Node node13 = new Node(23);
        tree.insert(tree, node13);
        Node node14 = new Node(41);
        tree.insert(tree, node14);
        Node node15 = new Node(30);
        tree.insert(tree, node15);
        Node node16 = new Node(28);
        tree.insert(tree, node16);
        Node node17 = new Node(38);
        tree.insert(tree, node17);
        Node node18 = new Node(35);
        tree.insert(tree, node18);
        Node node19 = new Node(39);
        tree.insert(tree, node19);
        Node node20 = new Node(47);
        tree.insert(tree, node20);
        tree.nil.key = -1;
        tree.nil.isBlack = true;
        print2D(tree.root);
    }
}

