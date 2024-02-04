package com.bootcamp.backendSgc.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.ContactModel;
import com.bootcamp.backendSgc.models.SupplierModel;
import com.bootcamp.backendSgc.repositories.ContactRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ContactService {
	
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	SupplierService supplierService;
	
	public List<ContactModel> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<ContactModel> getContactById(Integer id) {
        return contactRepository.findById(id);
    }
    
    public Optional<List<ContactModel>> getContactBySupplier(Integer idSupplier) {
    	SupplierModel supplier = supplierService.getSupplierById(idSupplier).orElseThrow(() ->
        new IllegalArgumentException("id_supplier: " + idSupplier + " not found"));
        return Optional.ofNullable(contactRepository.findBySupplier(supplier));
    }


    public ContactModel createContact(ContactModel contact) {
    	
        if(validateContactInput(contact)) {
            if (contact.getCreatedAt() == null) {
                contact.setCreatedAt(LocalDateTime.now());
            }

            SupplierModel supplier = supplierService.getSupplierById(contact.getSupplier().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

            contact.setSupplier(supplier);

            return contactRepository.save(contact);
        }

        return null;
    }

    public ContactModel updateContact(Integer id, ContactModel contact) {
        Optional<ContactModel> optionalContact = contactRepository.findById(id);

        if (optionalContact.isPresent() && validateContactInput(contact)) {
            ContactModel modContact = optionalContact.get();
            
            SupplierModel supplier = supplierService.getSupplierById(contact.getSupplier().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

            modContact.setName(contact.getName());
            modContact.setLastName(contact.getLastName());
            modContact.setEmail(contact.getEmail());
            modContact.setPhoneNumber(contact.getPhoneNumber());
            modContact.setRole(contact.getRole());
            modContact.setSupplier(supplier);

            updateTimestamp(modContact);

            return contactRepository.save(modContact);
        }

        return null;
    }

    public void deleteContactById(Integer id) {
        contactRepository.deleteById(id);
    }

    private boolean validateContactInput(ContactModel contact) {
        String regex1 = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
        String regex2 = "^[0-9 A-Za-z]{3,50}$";
        String regex3 = "^\\+\\d{1,4}-\\d{1,6}-\\d{4,20}$";

        if (!contact.getEmail().matches(regex1)) {
            return false;
        }
        if (!contact.getName().matches(regex2)) {
            return false;
        }
        if (!contact.getLastName().matches(regex2)) {
            return false;
        }
        if (!contact.getRole().matches(regex2)) {
            return false;
        }
        if (!contact.getPhoneNumber().matches(regex3)) {
            return false;
        }

        return true;
    }

    private void updateTimestamp(ContactModel contact) {
        contact.setUpdatedAt(LocalDateTime.now());
    }
}
