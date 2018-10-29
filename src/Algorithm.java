import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

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

    static String RSAAlgorithm(String sourceText, BigInteger[] key, boolean isDecrypt) {
        StringBuilder output = new StringBuilder();
        if (!isDecrypt) {
            for (int i = 0; i < sourceText.length(); i++) {
                char item = sourceText.charAt(i);
                long code = item;
                String num = String.valueOf(code);
                BigInteger digit = new BigInteger(num);
                BigInteger res = digit.modPow(key[0], key[1]);
                long codeRes = res.longValue();
                ByteBuffer buffer = ByteBuffer.allocate(Long.SIZE / Byte.SIZE);
                buffer.putLong(0, codeRes);
                byte[] arr = buffer.array();
                String resStr = new String(arr);
                output.append(resStr);
            }
        } else {
            for (int i = 0; i < sourceText.length(); i += 8) {
                String resStr = sourceText.substring(i, i + 8);
                byte[] arr = resStr.getBytes(StandardCharsets.UTF_8);
                ByteBuffer buffer = ByteBuffer.allocate(12);
                buffer.put(arr, 0, arr.length);
                buffer.flip();
                long codeRes = buffer.getLong();
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
