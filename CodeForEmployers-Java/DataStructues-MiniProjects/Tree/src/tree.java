
public class tree {
	static class Node{
		int value;
		Node leftChild, rightChild;
		
		public Node(int value) {
			this.value = value;
			leftChild = null;
			rightChild = null;
		}
		
	}
	Node root = new Node(0);
	boolean isFalse = true;
	
	void inOrder(Node x) {
		if (x != null) {
			inOrder(x.leftChild);
			System.out.print(x.value + ", ");
			inOrder(x.rightChild);
		}
	}
	
	void preOrder(Node x) {
		if (x != null) {
			System.out.print(x.value+ ", ");
			preOrder(x.leftChild);
			preOrder(x.rightChild);
		}
	}
	
	void postOrder(Node x) {
		if (x != null) {
			postOrder(x.rightChild);
			postOrder(x.leftChild);
			System.out.print(x.value + ", ");
		}
	}

	boolean isBST(Node root) {
		if(root != null) {
			isBST(root.leftChild);
			if(root.value < this.root.value) {
				if(root.leftChild != null && root.rightChild != null) {
					if(root.value < root.leftChild.value || root.value > root.rightChild.value) {
						isFalse = false;
						return false;
					}
					if(this.root.value < root.rightChild.value) {
						isFalse = false;
						return false;
					}
				
				}
				if(root.leftChild != null && root.rightChild == null) {
					if(root.value < root.leftChild.value) {
						isFalse = false;
						return false;
					}
				}
				if(root.leftChild == null && root.rightChild != null) {
					if(root.value > root.rightChild.value) {
						isFalse = false;
						return false;
					}
					if(this.root.value < root.rightChild.value) {
						isFalse = false;
						return false;
					}
				}
			}
			
			else if (root.value == this.root.value) {
				if(root.leftChild != null && root.rightChild != null) {
					if(root.value < root.leftChild.value || root.value > root.rightChild.value) {
						isFalse = false;
						return false;
					}
					
				}
				if(root.leftChild != null && root.rightChild == null) {
					if(root.value < root.leftChild.value) {
						isFalse = false;
						return false;
					}
				}
				if(root.leftChild == null && root.rightChild != null) {
					if(root.value > root.rightChild.value) {
						isFalse = false;
						return false;
				}
				}
			}
			
			else {
				if(root.leftChild != null && root.rightChild != null) {
					if(root.value < root.leftChild.value || root.value > root.rightChild.value) {
						isFalse = false;
						return false;
					}
					if(this.root.value > root.leftChild.value) {
						isFalse = false;
						return false;
					}
				}
				
				if(root.leftChild != null && root.rightChild == null) {
					if(root.value < root.leftChild.value) {
						isFalse = false;
						return false;
					}
					if(this.root.value > root.leftChild.value) {
						isFalse = false;
						return false;
					}
				}
				if(root.leftChild == null && root.rightChild != null) {
					if(root.value > root.rightChild.value) {
						isFalse = false;
						return false;
					}
				}
			}
			isBST(root.rightChild);
			}
			return isFalse;		
	}
	
	public static void main(String args[]) {
		boolean test1, test2;
		
		tree testTree = new tree();
		testTree.root.value = 12;
		testTree.root.leftChild = new Node(8); 
		testTree.root.rightChild = new Node(14);
		testTree.root.leftChild.leftChild = new Node(6);
		testTree.root.leftChild.rightChild = new Node(9);
		testTree.root.rightChild.leftChild = new Node(13);
		testTree.root.rightChild.rightChild = new Node(18);
		testTree.root.leftChild.leftChild.leftChild = new Node(3);
		testTree.root.leftChild.leftChild.rightChild = new Node(7);
		testTree.root.rightChild.rightChild.leftChild = new Node(15);
		testTree.root.rightChild.rightChild.rightChild = new Node(20);
		System.out.print("The tree printed inOrder is: ");
		testTree.inOrder(testTree.root);
		System.out.println("");
		System.out.print("The tree printed preOrder is: ");
		testTree.preOrder(testTree.root);
		System.out.println("");
		System.out.print("The tree printed postOrder is: ");
		testTree.postOrder(testTree.root);
		System.out.println("");
		test1 = testTree.isBST(testTree.root);
		System.out.println("Is tree 1 a BST: " + test1);
		System.out.println("");
		
		
		tree testTree2 = new tree();
		testTree2.root.value = 12;
		testTree2.root.leftChild = new Node(8); 
		testTree2.root.rightChild = new Node(14);
		testTree2.root.leftChild.leftChild = new Node(6);
		testTree2.root.leftChild.rightChild = new Node(13);
		testTree2.root.rightChild.leftChild = new Node(11);
		testTree2.root.rightChild.rightChild = new Node(18);
		testTree2.root.leftChild.leftChild.leftChild = new Node(3);
		testTree2.root.leftChild.leftChild.rightChild = new Node(7);
		testTree2.root.rightChild.rightChild.leftChild = new Node(15);
		testTree2.root.rightChild.rightChild.rightChild = new Node(20);
		System.out.print("The tree printed inOrder is: ");
		testTree2.inOrder(testTree2.root);
		System.out.println("");
		System.out.print("The tree printed preOrder is: ");
		testTree2.preOrder(testTree2.root);
		System.out.println("");
		System.out.print("The tree printed postOrder is: ");
		testTree2.postOrder(testTree2.root);
		System.out.println("");
		test2 = testTree2.isBST(testTree2.root);
		System.out.println("Is tree 2 a BST: " + test2);
		System.out.println("");
		
		
	}
}
