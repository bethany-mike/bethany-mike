import java.io.IOException;
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
        int counter = 0;

        for(String contact : allContacts) {
            if (counter == 0 || counter == 1) {
                counter++;
            } else {
                String[] splitContacts = contact.split("\\|");
                Contact newContact = new Contact(splitContacts[0].trim(), splitContacts[1].trim());
                contactIterator.add(newContact);
            }
        }
        for(Contact contact : contactIterator){
            if(contact.getName().equals(userContact)){
                returnContact = contact;
            }
        }
            return returnContact;
    }

    public List<String> assassinateContact(List<String> allContacts, String contactToDelete) {
        String foundContact = "";
        for (String contact : allContacts) {
            if (contact.contains(contactToDelete)) {
                foundContact = contact;
            }
        }
        allContacts.remove(foundContact);
        return allContacts;
    }
}
