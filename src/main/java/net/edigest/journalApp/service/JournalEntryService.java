package net.edigest.journalApp.service;

import net.edigest.journalApp.entity.JournalEntry;
import net.edigest.journalApp.entity.User;
import net.edigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public  class JournalEntryService  {

    @Autowired
    private JournalEntryRepository journalEntryRepository;  // auto object creation by Spring IOC

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName){
        User user=userService.findByUserName(userName);
        JournalEntry saved = journalEntryRepository.save(journalEntry);// this .save method is of MongoRepository which we have extended in repo class
        user.getJournalEntries().add(saved); //add into a list
        userService.saveEntry(user); // now "user" will save in DB
    }

    public void saveEntry(JournalEntry journalEntry){
         journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id,String userName){
        User user=userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }
}
