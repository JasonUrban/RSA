import java.math.BigInteger;
import java.util.Random;

class Algorithm {
    private static BigInteger gcd(BigInteger a, BigInteger b) {
        BigInteger t;
        while (!b.equals(BigInteger.ZERO)) {
            t = a;
            a = b;
            b = t.remainder(b);
        }
        return a;
    }

    static boolean isPrime(BigInteger n) {
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }
        for (BigInteger i = new BigInteger("3"); i.multiply(i).compareTo(n) <= 0; i = i.add(BigInteger.TWO)) {
            if (n.mod(i).equals(BigInteger.ZERO))
                return false;
        }
        return true;
    }

    static boolean relativelyPrime(BigInteger a, BigInteger b) {
        return gcd(a, b).equals(BigInteger.ONE);
    }

    static BigInteger[] xgcd(BigInteger p, BigInteger q) {
        if (q.equals(BigInteger.ZERO)) {
            return new BigInteger[]{new BigInteger(String.valueOf(1)), new BigInteger(String.valueOf(0))};
        }
        BigInteger[] vals = xgcd(q, p.mod(q));
        BigInteger a = vals[1];
        BigInteger b = vals[0].subtract((p.divide(q)).multiply(vals[1]));
        return new BigInteger[]{a, b};
    }

    static String RSAAlgorithm(String sourceText, BigInteger[] key) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < sourceText.length(); i++) {
            output.append((char) new BigInteger(String.valueOf((long) sourceText.charAt(i))).modPow(key[0], key[1]).longValue());
        }
        return output.toString();
    }
}
