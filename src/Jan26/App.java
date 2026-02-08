import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static ArrayList<Person> people = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        final Concert concert = new Concert(); // Could've just provided the ticket price and free ticket pool directly but rules said to use access modifiers
        final Scanner in = new Scanner(System.in);

        // Set the free ticket pool and ticket price
        concert.setFreeTicketPool(1000000);
        concert.setTicketPrice(50000);

        boolean stop = false;

        while (!stop) {
            System.out.println("\n=== Concert Tickets buy & sell ===");
            System.out.println("> Free Ticket Pool: " + concert.getFreeTicketPool());
            System.out.println("\nb: Buy Ticket");
            // System.out.println("\n"); TODO - build buy
            System.out.println("c: Check amount in free ticket pool");
            System.out.println("s: Check amount of sold tickets");
            System.out.println("e: Print summary and exit");
            System.out.print("\n> ");

            String _cmd = in.nextLine().toLowerCase();
            char cmd = _cmd.length() > 0 ? _cmd.charAt(0) : 'k';

            switch (cmd) {
                case 'b':
                    System.out.println("\t=== Enter this Person's info ===\n");
                    System.out.print("\tName: ");
                    Person person = findOrCreatePerson(in.nextLine());
                    concert.buyTicket(person);
                    break;
                case 'c':
                    concert.printFreeTicketPool();
                    break;
                case 's':
                    concert.printSoldTickets();
                    break;
                case 'e': {
                    System.out.println("=== Summary ===");
                    System.out.println("Total Tickets sold:\t" + concert.getSoldTickets());
                    System.out.println("Paid Tickets sold:\t" + concert.getSoldPaidTickets());
                    System.out.println("Free Tickets sold:\t" + concert.getSoldFreeTickets());
                    System.out.println("Total unique people:\t" + people.size());
                    System.out.println("Money left in free ticket pool:\t" + concert.getFreeTicketPool());
                    System.out.println("\n\n== People who Bought Tickets ==");
                    for (Person p : people) {
                        System.out.println(p.name + " bought " + p.ticketsBought + " tickets!");
                    }
                    System.out.println(("\nPress e to exit or type nothing to go back> "));
                    if (in.nextLine().equalsIgnoreCase("e"))
                        stop = true;
                }
                    break;
                default:
                    System.out.println("You've typed an invalid option!");
            }
        }

        in.close();
    }

    private static Person findOrCreatePerson(String name) {
        for (Person p : people) {
            if (p.name.equalsIgnoreCase(name))
                return p;
        }
        Person p = new Person(name);
        people.add(p);
        return p;
    }

    private static class Person {
        private String name;
        public int ticketsBought = 0;

        public Person(String name) {
            this.name = name;
        }
    }

    private static class Concert {
        private int freeTicketPool = 0;
        private int ticketPrice = 0;
        private ArrayList<App.Person> soldFreeTickets = new ArrayList<Person>();
        private ArrayList<App.Person> soldPaidTickets = new ArrayList<Person>();

        public Concert() {
        }

        public void buyTicket(Person forWho) {
            if (freeTicketPool >= ticketPrice) {
                System.out.println("\t> " + forWho.name + " bought a ticket using the money from the free ticket pool");

                this.freeTicketPool -= this.ticketPrice;
                this.soldFreeTickets.add(forWho);
                System.out.println("\tThe free ticket pool is now at " + this.freeTicketPool);
            } else {
                System.out.println("\t> " + forWho.name + " bought a ticket using their own money");

                this.soldPaidTickets.add(forWho);
            }
            forWho.ticketsBought++;
            System.out.println("They now have " + forWho.ticketsBought + " tickets");
        }

        public void printFreeTicketPool() {
            System.out.println("> Free Ticket Pool: " + this.freeTicketPool);
        }

        public void printSoldTickets() {
            System.out.println("> Sold tickets: " + this.soldFreeTickets.size() + this.soldPaidTickets.size());
        }

        // SECTION - Modifiers
        // Getters
        public int getSoldTickets() {
            return this.soldFreeTickets.size() + this.soldPaidTickets.size();
        }

        public int getSoldFreeTickets() {
            return this.soldFreeTickets.size();
        }

        public int getSoldPaidTickets() {
            return this.soldPaidTickets.size();
        }

        public int getFreeTicketPool() {
            return this.freeTicketPool;
        }

        // Setters
        public void setFreeTicketPool(int freeTicketPool) {
            this.freeTicketPool = freeTicketPool;
        }

        public void setTicketPrice(int ticketPrice) {
            this.ticketPrice = ticketPrice;
        }
        // !SECTION
    }
}

/*
 * Encapsulation is when we put variables {} which are inaccessible from outside
 * of that.
 * 
 * Example:
 * 
 * int x = 5
 * {
 * int x = 0
 * sout(x) // outputs 0
 * }
 * sout(x) // outputs 5
 * 
 * That happens because those variables are in different scopes
 */