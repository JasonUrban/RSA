import java.math.BigInteger;

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

    static boolean isNotPrime(BigInteger n) {
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return true;
        }
        for (BigInteger i = new BigInteger("3"); i.multiply(i).compareTo(n) <= 0; i = i.add(BigInteger.TWO)) {
            if (n.mod(i).equals(BigInteger.ZERO))
                return true;
        }
        return false;
    }

    static boolean notRelativelyPrime(BigInteger a, BigInteger b) {
        return !gcd(a, b).equals(BigInteger.ONE);
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

    static String RSAAlgorithm(String sourceText, BigInteger[] key, boolean isDecrypt) {
        StringBuilder output = new StringBuilder();
        if (!isDecrypt) {
            for (int i = 0; i < sourceText.length(); i++) {
                char item = sourceText.charAt(i);
                String num = String.valueOf((long) item);
                BigInteger digit = new BigInteger(num);
                BigInteger res = digit.modPow(key[0], key[1]);
                long codeRes = res.longValue();
                String hex = Long.toHexString(codeRes).toUpperCase();
                StringBuilder hexRes = new StringBuilder();
                for (int j = 0; j < 16 - hex.length(); j++) {
                    hexRes.append("0");
                }
                hexRes.append(hex);
                hex = hexRes.toString();
                StringBuilder resStr = new StringBuilder();
                for (int j = 0; j < hex.length(); j += 4) {
                    String subStr = hex.substring(j, j + 4);
                    resStr.append((char) Integer.parseInt(subStr, 16));
                }
                output.append(resStr);
            }
        } else {
            for (int i = 0; i < sourceText.length(); i += 4) {
                String resStr = sourceText.substring(i, i + 4);
                long codeRes = 0x0, multiplier = 0x1000000000000L;
                for (int j = 0; j < resStr.length(); j++) {
                    char item = resStr.charAt(j);
                    codeRes += (int) item * multiplier;
                    multiplier /= 0x10000;
                }
                String num = String.valueOf(codeRes);
                BigInteger digit = new BigInteger(num);
                BigInteger res = digit.modPow(key[0], key[1]);
                long code = res.longValue();
                char item = (char) code;
                output.append(item);
            }
        }
        return output.toString();
    }
}
