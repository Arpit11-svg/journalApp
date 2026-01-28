package net.edigest.journalApp.service;

import net.edigest.journalApp.entity.JournalEntry;
import net.edigest.journalApp.entity.User;
import net.edigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public  class JournalEntryService  {

    @Autowired
    private JournalEntryRepository journalEntryRepository;  // auto object creation by Spring IOC

    @Autowired
    private UserService userService;

    @Transactional
    public void saveNewUser(JournalEntry journalEntry, String userName){
        try{
            User user=userService.findByUserName(userName);
            JournalEntry saved = journalEntryRepository.save(journalEntry);// this .save method is of MongoRepository which we have extended in repo class
            user.getJournalEntries().add(saved); //add into a list
            userService.saveUser(user); // now "user" will save in DB
        }
        catch(Exception e){
//            if any error occurred then, throw exception so that @TRANSACTIONAL has some knowledge that there is an exception occurs OR something has gone wrong
            System.out.println(e.getMessage());
            throw new RuntimeException("An error occurred, while saving the entry.");
        }

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

    @Transactional
    public boolean deleteById(ObjectId id,String userName){
        boolean removed=false;
        try{
            User user=userService.findByUserName(userName);
            removed=user.getJournalEntries().removeIf(x->x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting an entry",e);
        }
        return removed;
    }

}
