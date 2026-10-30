public class Menu {

    String name;
    int FinalQuantity;
    double price;
    
    Menu(String name, int quantity, double price) {
        this.name = name;
        this.FinalQuantity = quantity;
        this.price = price; 
    }

    void displayMenu() {

        System.out.printf("%-14s - PHP %.2f\n", this.name, this.price);
    }

}
