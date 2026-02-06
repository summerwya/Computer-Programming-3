package Jan24;

import java.util.HashMap;
import java.util.Scanner;

public class MonthlyStoreSalesTracker {
    public static void main(String[] args) throws Exception {
        final Scanner in = new Scanner(System.in);
        final SalesData salesData = new SalesData();

        while (true) {
            clearConsole();
            System.out.println("\n\n=== Monthly Store Sales Tracker ===\n");
            System.out.println("a: Starts adding sales to a month");
            System.out.println("p: Print sales report");
            System.out.print("e: Exits the program\n\n> ");
            String option = in.nextLine().toLowerCase();

            // TODO - Call functionalize
            if (option.equalsIgnoreCase("a")) {
                System.out.print("\nEnter month (1-12): ");

                int month = in.nextInt(); // TODO: Add month checking
                int sales = 0;
                int[] salesArr = new int[4];

                in.nextLine();

                System.out.print("Overwrite/create sales for " + SalesData.MONTHS[month - 1] + "? (y / n)> ");
                if (in.nextLine().toLowerCase().startsWith("n")) {
                    System.out.println("Cancelled!");
                    in.nextLine();
                    continue;
                }

                clearConsole();
                System.out.println("\n\n=== Enter Weekly Sales ===\n");
                for (int i = 0; i < 4; i++) {
                    System.out.print("Enter sales on week " + (i + 1) + ": ");
                    salesArr[i] = in.nextInt();
                    sales += salesArr[i];
                }

                System.out.println("\n=== Summary ===\nTotal sales for this month: " + sales);
                for (int i = 0; i < 4; i++) {
                    System.out.println("Sales on week " + (i + 1) + ": " + salesArr[i]);
                }

                in.nextLine();
                System.out.print("\n\nSave these values? (y / n)> ");
                if (in.nextLine().toLowerCase().startsWith("n")) {
                    System.out.println("Cancelled!");
                    in.nextLine();
                    continue;
                }

                salesData.setSale(month, sales);
                System.out.println("Transaction finished.");
                in.nextLine();
            } else if (option.equalsIgnoreCase("p")) {
                System.out.println("\n\n=== Sales Report ===");
                salesData.printReport();
                in.nextLine();
            } else if (option.equalsIgnoreCase("e"))
                break;
        }

        in.close();
    }

    public static void clearConsole() {
        // TODO: Add compatibility
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static class SalesData {
        private HashMap<Integer, Integer> sales = new HashMap<>();
        public static final String[] MONTHS = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec" };

        public SalesData() {
        }

        public void setSale(int month, int amount) {
            sales.put(month, amount);
        }

        public void printReport() {
            int lowestSale = Integer.MAX_VALUE, highestSale = 0;
            int lowestSaleId = 0, highestSaleId = 0;
            int prevSales = Integer.MIN_VALUE;

            for (int d : sales.keySet()) {
                int sale = sales.get(d);

                if (prevSales != Integer.MIN_VALUE)
                    System.out.println("Sales on "
                    + d + ": " + sale
                    + " (" + Math.round((double) (sale - prevSales) / prevSales * 100)
                    + "% more than previous)");
                else
                    System.out.println("Sales on " + MONTHS[d-1] + ": " + sale);

                if (sale < lowestSale) {
                    lowestSale = sale;
                    lowestSaleId = d;
                }

                if (sale > highestSale) {
                    highestSale = sale;
                    highestSaleId = d;
                }
                prevSales = sale;
            }

            System.out.println("Highest sales: " + highestSale + " on " + MONTHS[highestSaleId - 1]);
            System.out.println("Lowest sales:  " + lowestSale + " on " + MONTHS[lowestSaleId - 1]);
        }
    }
}
