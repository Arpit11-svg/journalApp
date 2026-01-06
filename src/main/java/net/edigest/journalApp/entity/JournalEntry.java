package net.edigest.journalApp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "journal_entries")  //tells to ORM that this class is like Document/row in mongoDB

@Getter
@Setter
@NoArgsConstructor
public class JournalEntry {

    @Id  // this id is treated as a primary key, if this not given then it auto assign by mongoDB OR this ID is mapped with id of MongoDB
    private ObjectId id;

    @NonNull
    private String title;

    private  String content;

    private LocalDateTime date;

}
