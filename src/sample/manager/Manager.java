package sample.manager;

import sample.contact.Contact;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class Manager
{
    private final List<Contact> contacts = new LinkedList<>();

    private static Long getID() {
        return Math.round(Math.random() * 1000 + System.currentTimeMillis());
    }

    // добавление контакта и возвращение его уникального ID
    public Long addContact(String firstName, String secondName,
                           String phoneNumber, String email) {
        Contact newContact =
                new Contact(firstName, secondName, phoneNumber, email);

        if (contacts.contains(newContact)) {
            String message = "This contact in list";
            System.out.println(message);
        }

        newContact.setId(getID());
        contacts.add(newContact);

        return newContact.getId();
    }

    // удаление котакта
    public void deleteContact(Long contactId) {
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            Contact nextContact = iterator.next();
            if (nextContact.getId().equals(contactId)) {
                iterator.remove();
            }
        }
    }

    // редактирование котакта
    public void updateContact(Contact contact) {
        Contact oldContact = getContact(contact.getId());
        if (oldContact == null) {
            String message = "Contacts is not exist";
            System.out.println(message);
        }
        oldContact.setFirstName(contact.getFirstName());
        oldContact.setSecondName(contact.getSecondName());
        oldContact.setPhoneNumber(contact.getPhoneNumber());
        oldContact.setEmail(contact.getEmail());
    }

    // получение контакта по ID
    public Contact getContact(Long contactId) {
        Iterator<Contact> contactIterator = contacts.iterator();
        while (contactIterator.hasNext()) {
            Contact nextContact = contactIterator.next();
            if (nextContact.getId().equals(contactId)) {
                return nextContact;
            }
        }
        return null;
    }

    // дать полный список контактов
    public List<Contact> showContacts() {
        return contacts;
    }

}
