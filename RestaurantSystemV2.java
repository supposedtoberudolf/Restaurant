import java.util.Scanner;
import java.util.Random;

public class RestaurantSystemV2 {

    static Scanner scanner = new Scanner(System.in);

    Random random = new Random();

    int randomNum = random.nextInt(1, 50) + 1;

    static Menu[] menu = {
        new Menu("TAPSILOG",0, 99),
        new Menu("HOTSILOG",0, 79),
        new Menu("CHICKSILOG",0, 109),
        new Menu("TOSILOG",0, 89),
        new Menu("LONGSILOG",0, 89),
        new Menu("BAGNET SILOG",0, 119),
        new Menu("BACON SILOG",0, 109),
    }; 
    
    public static void main(String[] args) {
        
    double total = 0;

    int index = 0;

    WelcomePage();

    while (true) {

    int menuChoice = MainMenu();

        if (menuChoice == 1) {
            
            do {

                int OrderChoice = getUserOrder();
            
                if (OrderChoice <= 7 && OrderChoice >= 1) {
                    index = OrderChoice - 1;
                    total += ProcessOrder(index);
                }
                else {
                    break;
                }

            } while (true);
        }

        else if (menuChoice == 2) {

            if (total <= 0) System.out.println("\nHINDI KAPA UMUUORDER TANGA\n");
            else {
                PaymentProcess(total);
            }

        }
        else {
            System.out.println("\nESMERINGHOY");
            break;
        }
   
    }

    }

    static void loading(String message, int milliseconds) {

        System.out.print(message);

        for (int i = 0; i < 4; i++) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

            System.out.print(" .");
        }
        System.out.println("\n");
    }

    static void WelcomePage() {

        System.out.printf("===============================================\n");
        System.out.printf("        --- WELCOME TO BISAYA EATERY ---       \n");
        System.out.printf("      FAMOUS SILOG MEALS | DELECIOUS SILOG     \n");
        System.out.printf("===============================================\n");
        System.out.printf("           PRESS [ENTER] TO CONTINUE           \n");
        System.out.printf("===============================================\n");
        scanner.nextLine();
    }

    static int MainMenu() {
        
        int choice = 0;

    
        do {
            
            System.out.printf("===============================================\n");
            System.out.printf("        --- WELCOME TO BISAYA EATERY ---       \n");
            System.out.printf("      FAMOUS SILOG MEALS | DELECIOUS SILOG     \n");
            System.out.printf("===============================================\n\n");
            System.out.println("[1] ORDER");
            System.out.println("[2] PAY ORDER");
            System.out.println("[3] EXIT");
            System.out.printf("\n===============================================\n\n");

            System.out.printf("CHOOSE: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) System.out.println("\nINVALID INPUT\nINPUT MUST NOT BE EMPTY\n");
            else if (!(input.matches("[1-3]"))) System.out.println("\nINVALID CHOICE\nPLEASE TRY AGAIN\n");
            else {
                choice = Integer.parseInt(input);
                break;    
            }

        } while (true);

        return choice;
    }
    
    static int getUserOrder() {

        int choice = 0;
    
        while (true) {
            System.out.printf("===============================================\n");
            System.out.printf("        --- WELCOME TO BISAYA EATERY ---       \n");
            System.out.printf("      FAMOUS SILOG MEALS | DELECIOUS SILOG     \n");
            System.out.printf("===============================================\n\n");
            for (int i = 0; i < menu.length; i++) {
                System.out.printf("[%d] ", i + 1);
                menu[i].displayMenu();
            }
            System.out.print("[8] EXIT");
            System.out.println("\n\n===============================================");
            System.out.print("\nCHOOSE YOUR MEAL: ");
            String input = scanner.nextLine().trim();
        
                if (input.isEmpty()) System.out.println("\nINVALID INPUT\nINPUT MUST NOT BE EMPTY\n");
                else if (!(input.matches("[1-8]"))) System.out.println("\nINVALID CHOICE\nPLEASE TRY AGAIN\n");
                else {
                    choice = Integer.parseInt(input);
                    break;    
                }
        }        
        
        return choice;
    }

    static double ProcessOrder(int index) {

        double total = 0;

        int quantity = 0;

        while (true) {
            System.out.print("\nENTER AMOUNT OF ORDER(S): ");
            String input = scanner.nextLine().trim();

                if (input.isEmpty()) System.out.println("\nPLEASE ENTER AN AMOUNT");
                else if (!(input.matches("[0-9]"))) System.out.println("\nINVALID INPUT\nPLEASE TRY AGAIN!\n");   
                else {
                    quantity = Integer.parseInt(input);
                    break;
                }
        }

        loading("\nPROCESSING ORDER", 500);

        total += menu[index].price * quantity;
        menu[index].FinalQuantity += quantity;

        return total;
    }
    
    static void PaymentProcess(double total) {

        double payment = 0;
        int isValid = 0;

        System.out.printf("===============================================\n");
        System.out.printf("             --- ORDER OVERVIEW ---            \n");
        System.out.printf("===============================================\n\n");

        for (int i = 0; i < menu.length; i++) {
                if (menu[i].FinalQuantity > 0) System.out.printf("%-24s : PHP %-6.2f - %dx\n", menu[i].name, menu[i].price, menu[i].FinalQuantity);
        }
        System.out.printf("\n\nTOTAL\t\t\t : PHP %.2f", total);
        System.out.printf("\n===============================================\n");
        System.out.print("\nPRESS ENTER TO CONTINUE");
        scanner.nextLine();

        System.out.printf("===============================================\n");
        System.out.printf("                 --- PAYMENT ---               \n");
        System.out.printf("===============================================\n\n");
    
        do {
            System.out.printf("\nYOUR TOTAL IS PHP %.2f\n", total);
            System.out.printf("\nENTER YOUR PAYMENT(ENTER C TO CANCEL): ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) System.out.println("YOU DIDN'T ENTER AN AMOUNT\nPLEASE TRY AGAIN");
            else if (input.equalsIgnoreCase("C")) {

                while (true) {
                    System.out.println("\nDO YOU REALLY WISH TO CANCEL ORDER?\n");
                    System.out.print("\nPRESS [Y] TO CONTINUE, [N] TO CANCEL: ");
                    String confirm = scanner.nextLine().trim(); 
                
                    if (!(confirm.equalsIgnoreCase("Y") || confirm.equalsIgnoreCase("N"))) System.out.println("\nINVALID INPUT\nPLEASE TRY AGAIN!\n");
                    else if (confirm.equalsIgnoreCase("N")) break;
                    else {
                        isValid = 1;
                        break;
                    }  

                }
                
            }
            else if (!(input.matches("^[0-9]+(\\\\.[0-9]+)?$"))) System.out.println("\nINVALID INPUT\nPLEASE TRY AGAIN!\n");
            else {
                payment = Double.parseDouble(input);

                if (payment < total) {
                    System.out.println("\nINSUFFICIENT PAYMENT! AMOUNT IS LESS THAN TOTAL.\nPLEASE TRY AGAIN!\n");
                    continue;
                }
                break;
            }
        } while (isValid == 0);
            
        if (isValid == 1) System.out.println("\nORDER SUCCESSFULLY CANCELLED.\n\n");
        else if (isValid == 0) {
            System.out.printf("\nPAYMENT SUCCESSFULL! YOUR CHANGE IS PHP %.2f\n", (payment - total));   
        }

}

}