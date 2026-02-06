package Feb5;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("=== Demonstration of Math class ===");

        MathProgram prog = new MathProgram(new Scanner(System.in));
        prog.start();

        System.out.println("Finished!");
    }

    private static class MathProgram {
        private Scanner in;
        private double ans = 0;

        public MathProgram(Scanner in) {
            this.in = in;
        }

        public void start() {
            while(true) {
                System.out.println("\n\n== Select an option ==");
                System.out.println("Previous Answer: " + ans);
                System.out.println("\n1 - Absolute");
                System.out.println("2 - Square Root");
                System.out.println("3 - Max");
                System.out.println("4 - Min");
                System.out.println("5 - Random");
                System.out.print("\n> ");

                boolean fail = false;

                switch (in.nextInt()) {
                    case 1: abs(); break;
                    case 2: sqrt(); break;
                    case 3: max(); break;
                    case 4: min(); break;
                    case 5: rand(); break;
                
                    default:
                        fail = true;
                        System.out.println("Invalid option!");
                        break;
                }

                if (!fail) System.out.println("> The answer is " + ans);
            }
        }

        private void abs() {
            System.out.println("= Math Abs =");
            System.out.print("Enter a number: ");
            double num = in.nextDouble();

            ans = Math.abs(num);
        }

        private void sqrt() {
            System.out.println("= Math Sqrt =");
            System.out.print("Enter a number: ");
            double num = in.nextDouble();

            ans = Math.sqrt(num);
        }

        private void rand() {
            System.out.println("= Math Random =");

            ans = Math.random();
        }

        private void max() {
            System.out.println("= Math Max =");

            double[] nums = getTwoNumbers();
            ans = Math.max(nums[0], nums[1]);
        }
        
        private void min() {
            System.out.println("= Math Min =");

            double[] nums = getTwoNumbers();
            ans = Math.max(nums[0], nums[1]);
        }

        private double[] getTwoNumbers() {
            double[] ret = new double[2];

            System.out.print("Enter first number: ");
            ret[0] = in.nextDouble();
            
            System.out.print("Enter second number: ");
            ret[1] = in.nextDouble();

            return ret;
        }
    }
}

/**
 * - I only used nextDouble() and nextInt() for the user input.
 * - The instantiated variables are from the classes double and MathProgram.
 * - The math class methods i've used are abs, sqrt, min, max, and random
 * - There is a helper function to get two numbers from the user, this applies DRY
 * - The object made would be the MathProgram class
 * - The constructor of the MathProgram class accepts a Scanner in which it gets user input from (if the Scanner is scanning from System.in)
 */