class Main {
	public static void main (String[] args) {

		BinarySearchTree bst = new BinarySearchTree();
		bst.root = bst.insert(bst.root, 10);
		bst.root = bst.insert(bst.root, 12);
		bst.root = bst.insert(bst.root, 5);
		bst.root = bst.insert(bst.root, 4);
		bst.root = bst.insert(bst.root , 6);
		bst.root = bst.insert(bst.root, 9);
		bst.root = bst.insert(bst.root, 8);
        bst.root = bst.insert(bst.root , 11);
        bst.root = bst.insert(bst.root , 13);
        bst.root = bst.insert(bst.root , 7);
        
        bst.display(bst.root);

	}
}
class BinarySearchTree {
	Node root;
	BinarySearchTree() {
		root = null;
	}
	Node insert(Node root, int data) {
		if(root == null) {
			return new Node(data);
		}
		if(data < root.data) {
			root.llink = insert(root.llink, data);
		} else {
			root.rlink = insert(root.rlink, data);
		}
		return root;
	}
	void display(Node root){
	    if(root != null){
	        display(root.llink);
	        System.out.println(root.data);
	        display(root.rlink);
	    }
	}
	
}
class Node {
	int data;
	Node llink;
	Node rlink;
	Node(int data) {
		this.data = data;
		llink = null;
		rlink = null;
	}
}