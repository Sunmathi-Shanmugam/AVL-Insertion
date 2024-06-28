class Node {
    int data;
    Node left;
    Node right;
    int height;

    Node(int d) {
        data = d;
        left = null;
        right = null;
        height = 1;
    }
}

class BST {
    // Method to insert a new node in the BST
    Node insert(int data, Node root) {
        if (root == null) {
            Node newNode = new Node(data);
            return newNode;
        }
        if (data < root.data) {
            root.left = insert(data, root.left);
        } else if (data > root.data) {
            root.right = insert(data, root.right);
        } else {
            return root; // Duplicate data is not allowed
        }

        root.height = 1 + max(heig(root.left), heig(root.right));

        int bf = hbf(root);

        if (bf > 1 && data < root.left.data) // left-left case
            return rightrotate(root);

        if (bf > 1 && data > root.left.data) { // left-right case
            root.left = leftrotate(root.left);
            return rightrotate(root);
        }

        if (bf < -1 && data > root.right.data) // right-right case
            return leftrotate(root);

        if (bf < -1 && data < root.right.data) { // right-left case
            root.right = rightrotate(root.right);
            return leftrotate(root);
        }

        return root;
    }

    Node rightrotate(Node root) {
        Node x = root.left;
        Node y = x.right;

        x.right = root;
        root.left = y;

        root.height = 1 + max(heig(root.left), heig(root.right));
        x.height = 1 + max(heig(x.left), heig(x.right));

        return x;
    }

    Node leftrotate(Node root) {
        Node x = root.right;
        Node y = x.left;

        x.left = root;
        root.right = y;

        root.height = 1 + max(heig(root.left), heig(root.right));
        x.height = 1 + max(heig(x.left), heig(x.right));

        return x;
    }

    int hbf(Node root) {
        if (root == null)
            return 0;
        return heig(root.left) - heig(root.right);
    }

    int heig(Node root) {
        if (root == null)
            return 0;
        else
            return root.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node createmirror(Node root) {
        if (root == null)
            return null;

        Node temp = root.left;
        root.left = createmirror(root.right);
        root.right = createmirror(temp);
        return root;
    }

    boolean sumpath(int sum, Node root) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null && sum - root.data == 0)
            return true;

        return sumpath(sum - root.data, root.left) || sumpath(sum - root.data, root.right);
    }

    boolean isbst(Node root, int min, int max) {
        if (root == null)
            return true;
        if (root.data > max || root.data < min)
            return false;

        return isbst(root.left, min, root.data - 1) && isbst(root.right, root.data + 1, max);
    }

    int height(Node root) {
        if (root == null)
            return -1;

        int lh = height(root.left);
        int rh = height(root.right);

        if (lh > rh)
            return lh + 1;
        else
            return rh + 1;
    }

    Node delete(int d, Node root) {
        if (root == null)
            return null;
        else if (d < root.data)
            root.left = delete(d, root.left);
        else if (d > root.data)
            root.right = delete(d, root.right);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else {
                Node temp = minValueNode(root.right);
                root.data = temp.data;
                root.right = delete(temp.data, root.right);
            }
        }

        if (root == null)
            return root;

        root.height = 1 + max(heig(root.left), heig(root.right));

        int bf = hbf(root);

        if (bf > 1 && hbf(root.left) >= 0)
            return rightrotate(root);

        if (bf > 1 && hbf(root.left) < 0) {
            root.left = leftrotate(root.left);
            return rightrotate(root);
        }

        if (bf < -1 && hbf(root.right) <= 0)
            return leftrotate(root);

        if (bf < -1 && hbf(root.right) > 0) {
            root.right = rightrotate(root.right);
            return leftrotate(root);
        }

        return root;
    }

    Node minValueNode(Node root) {
        Node current = root;
        while (current.left != null)
            current = current.left;
        return current;
    }

    int countleaf(Node root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;

        return countleaf(root.left) + countleaf(root.right);
    }

    boolean search(int k, Node root) {
        if (root == null) {
            return false;
        }
        if (k == root.data) {
            return true;
        }
        if (k < root.data) {
            return search(k, root.left);
        } else {
            return search(k, root.right);
        }
    }

    void printlevel(int level, Node root) {
        if (root == null)
            return;
        else if (level == 0)
            System.out.print(root.data + " ");
        else {
            printlevel(level - 1, root.left);
            printlevel(level - 1, root.right);
        }

        return;
    }

    void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }
}

public class Avlisertion {
    public static void main(String args[]) {
        BST b1 = new BST();
        Node root = null;
        root = b1.insert(56, root);
        root = b1.insert(90, root);
        root = b1.insert(99, root);
        root = b1.insert(89, root);
        root = b1.insert(40, root);
        
        
        

        b1.preorder(root);
    }
}
