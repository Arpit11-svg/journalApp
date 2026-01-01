package net.edigest.journalApp.repository;

import net.edigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


//  MongoRepository<JournalEntry,ObjectId> ==> this JournalEntry is list or POJO which is stored by db and ObjectI is dataType of Id
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}
