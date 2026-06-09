import java.util.Scanner;

// ================= PRODUCT CLASS =================
class Product {
    String productName;
    int quantity;
    int threshold;

    public Product(String productName, int quantity, int threshold) {
        this.productName = productName;
        this.quantity = quantity;
        this.threshold = threshold;
    }

    public String getStatus() {
        if (quantity == 0) return "OUT OF STOCK";
        else if (quantity < threshold) return "LOW STOCK";
        else return "IN STOCK";
    }
}

// ================= INVENTORY MANAGER =================
class InventoryManager {
    Product[] products;
    int count;

    public InventoryManager(int size) {
        products = new Product[size];
        count = 0;
    }

    // ADD PRODUCT
    public void addProduct(Product p) {
        if (count < products.length) {
            products[count++] = p;
            System.out.println("✅ Product added successfully!");
        } else {
            System.out.println("❌ Inventory Full!");
        }
    }

    // REMOVE PRODUCT
    public void removeProduct(String name) {
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (products[i].productName.equalsIgnoreCase(name)) {

                // Shift elements left
                for (int j = i; j < count - 1; j++) {
                    products[j] = products[j + 1];
                }

                products[count - 1] = null;
                count--;

                System.out.println("🗑️ Product removed successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("❌ Product not found!");
        }
    }

    // SEARCH PRODUCT
    public void searchProduct(String name) {
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (products[i].productName.equalsIgnoreCase(name)) {

                System.out.println("\n🔍 PRODUCT FOUND:");
                System.out.println("Name     : " + products[i].productName);
                System.out.println("Quantity : " + products[i].quantity);
                System.out.println("Threshold : " + products[i].threshold);
                System.out.println("Status   : " + products[i].getStatus());

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("❌ Product not found!");
        }
    }

    // DISPLAY INVENTORY
    public void displayInventory() {

        System.out.println("\n=================================================");
        System.out.println("           📦 INVENTORY DASHBOARD");
        System.out.println("=================================================");

        if (count == 0) {
            System.out.println("No products available.");
            return;
        }

        System.out.printf("%-15s %-10s %-10s %-15s\n",
                "NAME", "QTY", "THRESH", "STATUS");

        System.out.println("-------------------------------------------------");

        for (int i = 0; i < count; i++) {
            System.out.printf("%-15s %-10d %-10d %-15s\n",
                    products[i].productName,
                    products[i].quantity,
                    products[i].threshold,
                    products[i].getStatus());
        }

        System.out.println("=================================================\n");
    }

    // LOW STOCK ALERT
    public void checkLowStock() {
        System.out.println("\n⚠️ LOW STOCK ALERTS:");

        boolean alert = false;

        for (int i = 0; i < count; i++) {
            if (products[i].quantity < products[i].threshold) {
                System.out.println("⚠️ " + products[i].productName + " is LOW on stock!");
                alert = true;
            }
        }

        if (!alert) {
            System.out.println("All products are sufficiently stocked.");
        }
    }
}

// ================= MAIN CLASS =================
public class project {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InventoryManager manager = new InventoryManager(100);

        int choice;

        do {
            System.out.println("\n================ MENU ================");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Search Product");
            System.out.println("4. Display Inventory");
            System.out.println("5. Check Low Stock");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    System.out.print("Enter Threshold: ");
                    int th = sc.nextInt();
                    sc.nextLine();

                    manager.addProduct(new Product(name, qty, th));
                    break;

                case 2:
                    System.out.print("Enter Product Name to Remove: ");
                    manager.removeProduct(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Enter Product Name to Search: ");
                    manager.searchProduct(sc.nextLine());
                    break;

                case 4:
                    manager.displayInventory();
                    break;

                case 5:
                    manager.checkLowStock();
                    break;

                case 6:
                    System.out.println("Exiting System... 👋");
                    break;

                default:
                    System.out.println("❌ Invalid Choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}