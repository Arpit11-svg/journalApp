package net.edigest.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data // this will include all required annotations from project lombok
public class User {
    @Id  // this Id is mapped with id of mongoDB
    private ObjectId id;

    @Indexed(unique = true)  //this userName will be unique, and this indexing will be helpful for searching userName; for this we have to add some code in app.context file also
    @NonNull
    private String userName;

    private String email;
    private boolean sentimentAnalysis;
    @NonNull
    private String password;

    @DBRef  // this annotation will refer/map this user collection to journalEntry (work like a Foreign key in SQL)
//    this user will only store Id of JournalEntry
    private List<JournalEntry> journalEntries=new ArrayList<>();
    private List<String> roles;



}
