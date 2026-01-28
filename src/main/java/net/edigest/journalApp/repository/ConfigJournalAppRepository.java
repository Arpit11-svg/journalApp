package net.edigest.journalApp.repository;

import net.edigest.journalApp.entity.ConfigJournalAppEntity;
import net.edigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


//  MongoRepository<ConfigJournalAppEntity,ObjectId> ==> this ConfigJournalAppEntity is POJO which is stored by db and ObjectId is dataType of Id
public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, String> {

}




