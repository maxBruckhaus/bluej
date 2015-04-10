/*  Maxwell Bruckhaus
 *  Period 3
 *  April 5th, 2015
 *  Time taken:
 *
*/


import java.util.Scanner;

public class EncryptXORMBruckhausPeriod3 {



    private String inputFile = "";
    private String outputFile = "";
    private String encryptionKey = "";


    public static void main(String[] args){
        EncryptXORMBruckhausPeriod3 crypter = new EncryptXORMBruckhausPeriod3();
        System.out.println("Welcome to the XOR Text File Encryptor!");
        Scanner scanner = new Scanner(System.in);
        crypter.inputFile = scanner.next("Enter a file to encrypt: ");
        crypter.outputFile = scanner.next("Enter a file to output: ");
        crypter.encryptionKey = scanner.next("Enter a private key: ");


    }

//    Prompt the user for the following information
//
//    An input file to encrypt
//    An output file to store the result
//    A private key (String)
//    Encrypt the input file and write the result to an output file
//
//    As you read the input file, XOR each character with the next available character in the key and append the result to the encrypted message String. Cycle through the key characters as needed.
//    Write the encrypted message to the output file.
//    You may use Java's built-in ^ operator for this lab.
//
//
//
//    Welcome to the XOR Text File Encryptor!
//
//    Enter a file to encrypt> message.txt
//    Enter a file to output> scrambled.txt
//    Enter a private key> kjfd09
//
//    Success!  File scrambled.txt created!
//
//    Press any key to continue..x


//    while there are bytes to be read, repeat this process:
// 1.  Create a new array to store the characters in
//     (the size of this array is your choice.  It's a
//      buffer.  You choose the size.  Common sizes are
//      powers of two:  64, 128, 256, 1024, 2048, etc.)



}


/*

        methods: Look in FileReader's grandparent (Reader) for these methods.

        boolean ready()
        tells whether the FileReader has information to read
        void close()
        closes the reader when you're done with it
        int read(char[] cbuf)
        reads characters into an array and returns the number of
        characters read.

        pseudocode:

// while there are bytes to be read, repeat this process:
// 1.  Create a new array to store the characters in
//     (the size of this array is your choice.  It's a
//      buffer.  You choose the size.  Common sizes are
//      powers of two:  64, 128, 256, 1024, 2048, etc.)
//
// 2.  Read characters into the buffer using the
//     read(char[] cbuf) method and save the return value.
//     The return value tells you how many characters were read,
//     which may be less than the array/buffer size.
//
// 3.  Loop through each character in the buffer and either
//     XOR it now or add it to a String and do the XORing later
//     on the complete String (which will be the entire file).
//     Note:  Do not loop through the buffer length.  Use the
//     return value you got from read(char[] cbuf) to know how
//     many characters you should be looping through.

// close the FileReader when you're done with it

*/
