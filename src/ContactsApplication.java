import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactsApplication {
    public static void main(String[] args) {

        String directory = "data";
        String filename = "contacts.txt";


        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);

        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }

            if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }

        } catch (IOException ioe) {
            System.out.println("It's broked");
        }

        ContactBook contactBook = new ContactBook();
        Scanner scanner = new Scanner(System.in);
        String userContinue;

        do {
            System.out.println("1. View contacts.\n2. Add a new contact.\n3. Search a contact by name.\n4. Delete an existing contact.\n" +
                    "5. Exit.\nEnter an option (1, 2, 3, 4 or 5):");
            int userInput = scanner.nextInt();
            scanner.nextLine();
            switch (userInput) {
                case 1:
                    List<String> allContacts = contactBook.returnAllContacts(dataFile);
                    for (String contact : allContacts) {
                        System.out.println(contact);
                    }
                    break;
                case 2:
                    System.out.println("Enter contact name.");
                    String contactName = scanner.nextLine();
                    System.out.println("Enter contact phone number.");
                    String contactNumber = scanner.nextLine();
                    try {
                        Files.write(
                                Paths.get(directory, filename),
                                Arrays.asList(contactName + " | " + contactNumber),
                                StandardOpenOption.APPEND
                        );
                    } catch (IOException thisGuy) {
                        System.out.println(thisGuy);
                    }
                    break;
                case 3:
                    System.out.println("Enter name to search for.");
                    String userContact = scanner.nextLine();
                    allContacts = contactBook.returnAllContacts(dataFile);
                    Contact anotherContact = contactBook.returnContactObject(allContacts, userContact);
                    if (anotherContact.getName() == null) {
                        System.out.println("No contact found.");
                    } else {
                        System.out.println(anotherContact.getName());
                        System.out.println(anotherContact.getPhoneNumber());
                    }
                    break;
                case 4:
                    System.out.println("Provide a contact name to delete.");
                    String nameToDelete = scanner.nextLine();
                    allContacts = contactBook.returnAllContacts(dataFile);
                    List<String> newContactList = contactBook.assassinateContact(allContacts, nameToDelete);
                    try {
                        Files.write(dataFile, newContactList);
                    } catch (IOException thisGuy) {
                        System.out.println(thisGuy);
                    }
                    break;
                case 5:
                    System.exit(0);
            }
            System.out.println("Would you like to do something else? (y/n)");
            userContinue = scanner.nextLine();
        } while (userContinue.equals("y"));
    }
}
