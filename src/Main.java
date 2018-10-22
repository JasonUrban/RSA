import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static BigInteger gcd(BigInteger a, BigInteger b) {
        BigInteger t;
        while (!b.equals(BigInteger.ZERO)) {
            t = a;
            a = b;
            b = t.remainder(b);
        }
        return a;
    }

    private static boolean isPrime(BigInteger n) {
        //check if n is a multiple of 2
        if (n.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
            return false;
        }
        //if not, then just check the odds
        for (BigInteger i = new BigInteger("3"); i.multiply(i).compareTo(n) <= 0; i = i.add(new BigInteger("2"))) {
            if (n.mod(i).equals(BigInteger.ZERO))
                return false;
        }
        return true;
    }

    private static boolean relativelyPrime(BigInteger a, BigInteger b) {
        return gcd(a, b).equals(BigInteger.ONE);
    }

    private static BigInteger[] xgcd(BigInteger p, BigInteger q) {
        if (q.equals(BigInteger.ZERO)) {
            return new BigInteger[]{new BigInteger(String.valueOf(1)), new BigInteger(String.valueOf(0))};
        }
        BigInteger[] vals = xgcd(q, p.mod(q));
        BigInteger a = vals[1];
        BigInteger b = vals[0].subtract((p.divide(q)).multiply(vals[1]));
        return new BigInteger[]{a, b};
    }

    private static BigInteger inputNum(String str) {
        Scanner scanner = new Scanner(System.in);
        BigInteger num;
        boolean isCorrect, isPrime;
        do {
            isCorrect = true;
            isPrime = true;
            System.out.print("Input prime number " + str + ": ");
            num = BigInteger.ZERO;
            try {
                num = scanner.nextBigInteger();
                if (!isPrime(num)) {
                    throw new Exception("Incorrect input! Digit must be prime!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input!");
                isCorrect = false;
                scanner = new Scanner(System.in);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                isPrime = false;
            }
        } while (!isPrime || !isCorrect);
        return num;
    }

    public static void main(String[] args) {
        BigInteger p = inputNum("p"), q = inputNum("q");
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e;
        Random rand = new Random();
        BigInteger d;
        do {
            e = new BigInteger(phi.bitLength(), rand);
            d = xgcd(phi, e)[1];
        } while (e.compareTo(phi) >= 0 || !relativelyPrime(phi, e) || d.compareTo(BigInteger.ZERO) < 0);
        System.out.println("e = " + e);
        System.out.println("d = " + d);
        System.out.print("Input some text here: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        long[] out = new long[input.length()], in = new long[input.length()];
        for (int i = 0; i < input.length(); i++) {
            out[i] = new BigInteger(String.valueOf((long) input.charAt(i))).modPow(e, n).longValue();
            System.out.print((char) out[i]);
        }
        System.out.println();
        for (int i = 0; i < out.length; i++) {
            in[i] = new BigInteger(String.valueOf(out[i])).modPow(d, n).longValue();
            System.out.print((char) in[i]);
        }
        System.out.println();
    }
}
