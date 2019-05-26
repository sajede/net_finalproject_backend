package ir.asta.training.contacts.Util;

import javax.inject.Named;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Named("sessionIdGenerator")
public class IdGenerator {

        public IdGenerator()
        {
            // Start with the current system time as a seed
            long seed = System.currentTimeMillis();

            // Also throw in the system identifier for 'this' from toString
            char[] entropy = toString().toCharArray();
            for (int i = 0; i < entropy.length; i++)
            {
                //noinspection IntegerMultiplicationImplicitCastToLong
                long update = ((byte) entropy[i]) << ((i % 8) * 8);
                seed ^= update;
            }

            random.setSeed(seed);
        }

        public synchronized String generateId(int length)
        {
            byte[] buffer = new byte[length];

            // Render the result as a String of hexadecimal digits
            StringBuffer reply = new StringBuffer();

            int resultLenBytes = 0;
            while (resultLenBytes < length)
            {
                random.nextBytes(buffer);
                buffer = getDigest().digest(buffer);

                for (int j = 0; j < buffer.length && resultLenBytes < length; j++)
                {
                    byte b1 = (byte) ((buffer[j] & 0xf0) >> 4);
                    if (b1 < 10)
                    {
                        reply.append((char) ('0' + b1));
                    }
                    else
                    {
                        reply.append((char) ('A' + (b1 - 10)));
                    }

                    byte b2 = (byte) (buffer[j] & 0x0f);
                    if (b2 < 10)
                    {
                        reply.append((char) ('0' + b2));
                    }
                    else
                    {
                        reply.append((char) ('A' + (b2 - 10)));
                    }

                    resultLenBytes++;
                }
            }

            return reply.toString();
        }


        public synchronized String getAlgorithm()
        {
            return algorithm;
        }


        public synchronized void setAlgorithm(String algorithm)
        {
            this.algorithm = algorithm;
            digest = null;
        }


        private MessageDigest getDigest()
        {
            if (digest == null)
            {
                try
                {
                    digest = MessageDigest.getInstance(algorithm);
                }
                catch (NoSuchAlgorithmException ex)
                {
                    try
                    {
                        digest = MessageDigest.getInstance(DEFAULT_ALGORITHM);
                    }
                    catch (NoSuchAlgorithmException ex2)
                    {
                        digest = null;
                        throw new IllegalStateException("No algorithms for IdGenerator");
                    }
                }

                System.out.println("Using MessageDigest: " + digest.getAlgorithm());
            }

            return digest;
        }


        @SuppressWarnings({"EmptyMethod"})
        @Override
        public final String toString()
        {
            // This is to make the point that we need toString to return something
            // that includes some sort of system identifier as does the default.
            // Don't change this unless you really know what you are doing.
            return super.toString();
        }


        protected static final String DEFAULT_ALGORITHM = "MD5";


        private String algorithm = DEFAULT_ALGORITHM;


        private Random random = new SecureRandom();


        private MessageDigest digest = null;

    }
