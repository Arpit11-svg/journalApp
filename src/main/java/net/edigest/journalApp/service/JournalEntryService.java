package net.edigest.journalApp.service;

import net.edigest.journalApp.entity.JournalEntry;
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

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry); // this .save method is of MongoRepository which we have extended in repo class
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }
}
