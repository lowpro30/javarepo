import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class KeychainReader {
    /*
     Reads a password from the MacOS Keychain using the 'security' command-line tool. 
     This is a simple example and may need to be adapted for your specific use case.
     */

     public static char[] readPassword(String service, String account) 
        throws IOException, InterruptedException {

            ProcessBuilder pb = new ProcessBuilder(
                "/usr/bin/security",
                "find-generic-password",
                "-s", service,
                "-a", account,
                "-w"
            );
            Process process = pb.start();
            return readPasswordFromProcess(process);
}
/**
 * Reads the password from the process output.
 */
private static char[] readPasswordFromProcess(Process process) 
throws IOException, InterruptedException {
    byte[] raw = null;
    byte[] errorBytes = null;
    CharBuffer decoded = null;

    try{
        try(InputStream stdout = process.getInputStream()){
          raw = stdout.readAllBytes();
        }
        try(InputStream stderr = process.getErrorStream()){
          errorBytes = stderr.readAllBytes();
        }
        int exitCode = process.waitFor();
        if(exitCode != 0){
            String errorMessage = (errorBytes == null || errorBytes.length ==0)
            ? "Unknown keychain error"
            : new String(errorBytes, StandardCharsets.UTF_8).trim();

            throw new IOException(
                "Security exited with exit code " 
                + exitCode 
                + ": " 
                + errorMessage);
        }
        int length = raw.length;
        //Remote trailing newline if present

        if(length > 0 && raw[length - 1] == '\n'){
            length--;
        }
        if(length > 0 && raw[length - 1] == '\r'){
            length--;
        }
    
    decoded = StandardCharsets.UTF_8
    .newDecoder()    
    .decode(ByteBuffer.wrap(raw, 0, length));

    char[] password = new char[decoded.length()];
    decoded.get(password);
    return password;
} finally {
    if(raw != null){
        Arrays.fill(raw, (byte) 0);
    }
    if(errorBytes != null){
        Arrays.fill(errorBytes, (byte) 0);
    }
    if (decoded != null && decoded.hasArray()) {
        Arrays.fill(decoded.array(), '\0');
    }
}
}

public static void main(String[] args) {
   String service = 
   args.length >0 
   ? args[0]
   : "com.example-service";

   String account = 
    args.length > 1
    ? args[1]
    : "example-account";

    char[] password = null;
    try {
        password = readPassword(service, account);
        System.out.println("Successfully retrieved secret. Length = "
        + password.length);
        
        // For testing purposes only
        // System.out.println("Password: " + new String(password));
    } catch (Exception e) {
        System.err.println("Failed to retrieve secret: " + e.getMessage());
        e.printStackTrace();
    } finally {
        if (password != null) {
            Arrays.fill(password, '\0');
        }   
    }
}
}
