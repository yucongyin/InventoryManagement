import java.util.Date;

// Product class to store product information
class Product {
    // Product serial number
    String serialNumber;
    // Product name
    String name;
    // Product inventory level
    int inventory;
    // Product expiration date
    Date expiration;

    // Constructor to create a new product
    public Product(String serialNumber, String name, int inventory, Date expiration) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.inventory = inventory;
        this.expiration = expiration;
    }

    public void updateInventory(int quantity) {
        this.inventory += quantity;
    }
}

// Binary search tree to store product information
class ProductBinarySearchTree {
    // Node class to store a product and links to the left and right subtrees
    class Node {
        Product product;
        // Left subtree
        Node left;
        // Right subtree
        Node right;

        public Node(Product product) {
            this.product = product;
            this.left = null;
            this.right = null;
        }
    }

    Node root;

    public void addProduct(Product product) {
        // If the tree is empty, set the new product as the root
        if (this.root == null) {
            this.root = new Node(product);
        }
        // Otherwise, find the correct position for the new product in the tree
        else {
            // Start at the root of the tree
            Node current = this.root;
            // Loop until we find the correct position for the new product
            while (true) {
                // If the serial number of the new product is less than the current node's
                // serial number, go left
                if (product.serialNumber.compareTo(current.product.serialNumber) < 0) {
                    // If there is no left child, insert the new product here
                    if (current.left == null) {
                        current.left = new Node(product);
                        break;
                    }
                    // Otherwise, move to the left child
                    else {
                        current = current.left;
                    }
                }
                // If the serial number of the new product is greater than or equal to the
                // current node's serial number, go right
                else {
                    // If there is no right child, insert the new product here
                    if (current.right == null) {
                        current.right = new Node(product);
                        break;
                    }
                    // Otherwise, move to the right child
                    else {
                        current = current.right;
                    }
                }
            }
        }
    }

}
