import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        } while (retString.isEmpty());

        return retString;
    }

    public static int getInt(Scanner pipe, String prompt) {
        int retInt = 0;
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retInt = pipe.nextInt();
                done = true;
            } else {
                String trash = pipe.nextLine();
            }
        } while (!done);

        return retInt;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        double retDouble = 0;
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retDouble = pipe.nextDouble();
                done = true;
            } else {
                pipe.nextLine();
            }
        } while (!done);

        return retDouble;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retInt = 0;
        boolean done = false;
        do {
            System.out.printf("\n%1$s [%2$d-%3$d]: ", prompt, low, high);
            if (pipe.hasNextInt()) {
                retInt = pipe.nextInt();
                if (retInt >= low && retInt <= high) {
                    done = true;
                } else {
                    done = false;
                }
            } else {
                String trash = pipe.nextLine();
            }
        } while (!done);

        return retInt;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retDouble = 0;
        boolean done = false;
        do {
            System.out.printf("\n%1 [%2$,.2f-%3$,.2f]: ", prompt, low, high);
            if (pipe.hasNextDouble()) {
                retDouble = pipe.nextDouble();
                if (retDouble >= low && retDouble <= high) {
                    done = true;
                } else {
                    done = false;
                }
            } else {
                String trash = pipe.nextLine();
            }
        } while (!done);

        return retDouble;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean retBool = false;
        String yOrN = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextLine()) {
                yOrN = pipe.nextLine();
                if (yOrN.equals("Y") || yOrN.equals("y")) {
                    retBool = true;
                    done = true;
                } else if (yOrN.equals("N") || yOrN.equals("n")) {
                    retBool = false;
                    done = true;
                }
            }
        } while (!done);

        return retBool;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String retString = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(retString);
            if (matcher.find()) {
                done = true;
            }
        } while (!done);
        return retString;
    }

    public static void prettyHeader(String msg) {
        for (int i = 0; i < 60; i++) {
            System.out.print("*");
        }
        System.out.print("\n***");
        int spacesLeft = (54 - msg.length()) / 2;
        int spacesRight = (54 - spacesLeft) - msg.length();
        for (int i = 0; i < spacesLeft; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < spacesRight; i++) {
            System.out.print(" ");
        }
        System.out.print("***\n");
        for (int i = 0; i < 60; i++) {
            System.out.print("*");
        }
    }
}
