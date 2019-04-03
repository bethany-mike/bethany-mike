import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ContactBook {
    private List<Contact> contacts;

    public ContactBook() {
        contacts = new ArrayList<>();
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<String> returnAllContacts (Path pathName) {
        List<String> returnList = new ArrayList<>();
        try {
            returnList = Files.readAllLines(pathName);
        } catch (IOException littleGuy) {
            System.out.println(littleGuy);
        }
        return returnList;
    }


    public Contact returnContactObject(List<String> allContacts, String userContact){
        List<Contact> contactIterator = new ArrayList<>();
        Contact returnContact = new Contact();

        for(String contact : allContacts) {
            String[] splitContacts = contact.split("\\|");
            Contact newContact = new Contact(splitContacts[0],splitContacts[1]);
            contactIterator.add(newContact);
        }


        for(Contact contact: contactIterator){
            if(contact.getName().equals(userContact)){
                returnContact = contact;
            }
        }

            return returnContact;
    }
}
