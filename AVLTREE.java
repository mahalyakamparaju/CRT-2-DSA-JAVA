class AVLTREE {
    public static void main(String[] args) {
        AVL tree = new AVL();

        int[] values = {10, 20, 30, 40, 50, 25};
        for (int v : values) {
            tree.root = tree.insert(tree.root, v);
        }

        System.out.print("Inorder   : ");
        tree.inorder(tree.root);

        System.out.print("\nPreorder  : ");
        tree.preorder(tree.root);

        System.out.print("\nPostorder : ");
        tree.postorder(tree.root);
    }
}

class AVL {
    Node root;

    int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    int balanceFactor(Node node) {
        return (node == null) ? 0 : height(node.llink) - height(node.rlink);
    }

    Node minValue(Node node) {
        Node current = node;
        while (current.llink != null)
            current = current.llink;
        return current;
    }

    // Right Rotation (LL Case)
    Node rightRotate(Node y) {
        Node x = y.llink;
        Node t = x.rlink;

        x.rlink = y;
        y.llink = t;

        y.height = Math.max(height(y.llink), height(y.rlink)) + 1;
        x.height = Math.max(height(x.llink), height(x.rlink)) + 1;

        return x;
    }

    // Left Rotation (RR Case)
    Node leftRotate(Node x) {
        Node y = x.rlink;
        Node t = y.llink;

        y.llink = x;
        x.rlink = t;

        x.height = Math.max(height(x.llink), height(x.rlink)) + 1;
        y.height = Math.max(height(y.llink), height(y.rlink)) + 1;

        return y;
    }

    Node insert(Node node, int data) {
        if (node == null)
            return new Node(data);

        if (data < node.data)
            node.llink = insert(node.llink, data);
        else if (data > node.data)
            node.rlink = insert(node.rlink, data);
        else
            return node;

        node.height = Math.max(height(node.llink), height(node.rlink)) + 1;
        int balance = balanceFactor(node);

        // LL
        if (balance > 1 && data < node.llink.data)
            return rightRotate(node);

        // RR
        if (balance < -1 && data > node.rlink.data)
            return leftRotate(node);

        // LR
        if (balance > 1 && data > node.llink.data) {
            node.llink = leftRotate(node.llink);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && data < node.rlink.data) {
            node.rlink = rightRotate(node.rlink);
            return leftRotate(node);
        }

        return node;
    }

    Node delete(Node node, int data) {
        if (node == null)
            return node;

        if (data < node.data)
            node.llink = delete(node.llink, data);
        else if (data > node.data)
            node.rlink = delete(node.rlink, data);
        else {
            if (node.llink == null || node.rlink == null) {
                Node temp = (node.llink != null) ? node.llink : node.rlink;
                if (temp == null)
                    node = null;
                else
                    node = temp;
            } else {
                Node temp = minValue(node.rlink);
                node.data = temp.data;
                node.rlink = delete(node.rlink, temp.data);
            }
        }

        if (node == null)
            return node;

        node.height = Math.max(height(node.llink), height(node.rlink)) + 1;
        int balance = balanceFactor(node);

        // LL
        if (balance > 1 && balanceFactor(node.llink) >= 0)
            return rightRotate(node);

        // LR
        if (balance > 1 && balanceFactor(node.llink) < 0) {
            node.llink = leftRotate(node.llink);
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && balanceFactor(node.rlink) <= 0)
            return leftRotate(node);

        // RL
        if (balance < -1 && balanceFactor(node.rlink) > 0) {
            node.rlink = rightRotate(node.rlink);
            return leftRotate(node);
        }

        return node;
    }

    // Traversals
    void inorder(Node node) {
        if (node != null) {
            inorder(node.llink);
            System.out.print(node.data + " ");
            inorder(node.rlink);
        }
    }

    void preorder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorder(node.llink);
            preorder(node.rlink);
        }
    }

    void postorder(Node node) {
        if (node != null) {
            postorder(node.llink);
            postorder(node.rlink);
            System.out.print(node.data + " ");
        }
    }
}

class Node {
    int data;
    int height;
    Node llink, rlink;

    Node(int data) {
        this.data = data;
        this.height = 1;
        llink = rlink = null;
    }
}
