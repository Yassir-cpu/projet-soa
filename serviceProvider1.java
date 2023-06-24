import java.io.*;
import java.net.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.*;

class MD5
{
    public String getMd5(String input)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32)
            {
                hashtext = "0" + hashtext;
            }
            return hashtext.toUpperCase();
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
}

class SHA1
{
    public String getSha1(String input)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32)
                hashtext = "0" + hashtext;
            return hashtext.toUpperCase();
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
}

class SHA256
{
    public String getSha256(String input)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hexString = new StringBuilder(no.toString(16));
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }
}

class serviceProvider1
{
    public static void main(String args[]) throws Exception
    {
		ServerSocket ss = new ServerSocket(4001);
		System.out.println("Service Provider 1 Started at port number : 4001");
		while(true)
			{
				try
				{
					Socket s = ss.accept();
					System.out.println("Connection established with Service Requester");
					DataOutputStream dos = new DataOutputStream(s.getOutputStream());
					BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
					BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
					String text = br.readLine();
					String method = br.readLine();
					if (method.equals("MD5"))
					{
						MD5 md5 = new MD5();
						String hash = md5.getMd5(text);
						dos.writeBytes("Hash Value generated by MD5 : " + hash + "\n");
					}
					else if (method.equals("SHA1"))
					{
						SHA1 sha1 = new SHA1();
						String hash = sha1.getSha1(text);
						dos.writeBytes("Hash Value generated by SHA1 : " + hash + "\n");
					}
					else if (method.equals("SHA256"))
					{
						SHA256 sha256 = new SHA256();
						String hash = sha256.getSha256(text);
						hash=hash.toUpperCase();
						dos.writeBytes("Hash Value generated by SHA256 : " + hash + "\n");
					}
					else
					{
						dos.writeBytes("Please Enter either MD5 or SHA1 or SHA256\n");
					}
					dos.close();
					br.close();
					kb.close();
					s.close();
				}
				catch (Exception e)
				{
					System.out.println("\nSocket connection was interrupted with the Requester\n");
				}
			}
	}
}
