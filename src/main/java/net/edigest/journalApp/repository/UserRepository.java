package net.edigest.journalApp.repository;

import net.edigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


//  MongoRepository<User,ObjectId> ==> this User is list or POJO which is stored by db and ObjectId is dataType of Id
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
    void deleteByUserName(String userName);
}




