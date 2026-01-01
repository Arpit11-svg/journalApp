package net.edigest.journalApp.controller;

import net.edigest.journalApp.JournalApplication;
import net.edigest.journalApp.entity.JournalEntry;
import net.edigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

// controller ===> service ===> repository

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId myId,@RequestBody JournalEntry newEntry){
        JournalEntry old=journalEntryService.findById(myId).orElse(null); // took old entry of id
//        now see, which one to update title or content, if in new entry title or content is present only then update otherwise remains as previous
        if(old!=null){
            old.setTitle(newEntry.getTitle()!=null && newEntry.getTitle().isEmpty() ?newEntry.getTitle():old.getTitle());
            old.setContent(newEntry.getContent()!=null && newEntry.getContent().isEmpty() ?newEntry.getContent():old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;

    }
}

