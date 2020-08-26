package fr.arinonia.hexarion.launcher.utils;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.DigestInputStream;
import java.security.MessageDigest;

/**
 * Created by Arinonia on 17/08/2020 inside the package - fr.arinonia.hexarion.launcher.utils
 */
public class Util {

    public static int crossMult(int value, int maximum, int coefficient) {
        return (int)((double)value / (double)maximum * (double)coefficient);
    }

    public static String getUrlContent(String url) {
        StringBuilder content = new StringBuilder();

        try {
            URLConnection urlConnection = new URL(url).openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch(Exception ignored) {}
        return content.toString();
    }
    public static String getMD5(final File file) {
        DigestInputStream stream = null;
        try {
            stream = new DigestInputStream(new FileInputStream(file), MessageDigest.getInstance("MD5"));
            final byte[] buffer = new byte[65536];

            int read = stream.read(buffer);
            while(read >= 1)
                read = stream.read(buffer);
        }
        catch(final Exception ignored) {
            return null;
        }
        finally {
            closeSilently(stream);
        }

        return String.format("%1$032x", new Object[] { new BigInteger(1, stream.getMessageDigest().digest()) });
    }
    public static void closeSilently(final Closeable closeable) {
        if(closeable != null)
            try {
                closeable.close();
            }
            catch(final IOException ignored) {}
    }
}
