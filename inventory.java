import java.util.Date;

class Product {

    String serialNumber;
    String name;
    int inventory;
    Date expiration;

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

// Hash table to store product information
class ProductHashTable {

    private Product[] table;
    private int size;

    public ProductHashTable(int size) {
        this.table = new Product[size];
        this.size = size;
    }

    // Hash function to map a serial number to an index in the table
    public int hash(String serialNumber) {
        int number = Integer.parseInt(serialNumber);
        return number % this.size;
    }

    public void addProduct(Product product) {
        // Use the hash function to get the index for the product
        int index = hash(product.serialNumber);
        // Add the product to the table at the specified index
        this.table[index] = product;
    }

    public Product getProduct(String serialNumber) {
        // Use the hash function to get the index for the product
        int index = hash(serialNumber);
        return this.table[index];
    }
}

// Linked list to store products sorted by inventory level
class ProductInventoryList {
    // Node class to store a product and a link to the next node
    class Node {

        Product product;
        Node next;

        public Node(Product product) {
            this.product = product;
            this.next = null;
        }
    }

    Node head;

    public void addProduct(Product product) {

        Node node = new Node(product);
        // If the list is empty, set the new node as the head
        if (this.head == null) {
            this.head = node;
        }
        // Otherwise, find the correct position for the new node in the list
        else {
            // Start at the head of the list
            Node current = this.head;
            // Keep track of the previous node
            Node previous = null;
            // Loop through the list until we find the correct position for the new node
            while (current != null && product.inventory > current.product.inventory) {
                // Save the current node as the previous node
                previous = current;
                // Move to the next node in the list
                current = current.next;
            }

            if (previous == null) {
                this.head = node;
            }
        }

    }

    public Product getProduct(String serialNumber) {

        Node current = this.head;

        while (current != null && !current.product.serialNumber.equals(serialNumber)) {

            current = current.next;
        }

        return current != null ? current.product : null;
    }

    public void updateInventory(String serialNumber, int quantity) {

        Node current = this.head;

        while (current != null && !current.product.serialNumber.equals(serialNumber)) {

            current = current.next;
        }

        if (current != null) {
            current.product.updateInventory(quantity);
        }
    }

    public void removeProduct(String serialNumber) {

        Node current = this.head;
        Node previous = null;

        while (current != null && !current.product.serialNumber.equals(serialNumber)) {

            previous = current;
            current = current.next;
        }

        if (current != null) {
            if (previous == null) {
                this.head = current.next;
            }

            else {
                previous.next = current.next;
            }
        }
    }

}