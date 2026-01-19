package net.edigest.journalApp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "config_journal_app")  //tells to ORM that this class is like Collection in mongoDB

@Getter
@Setter
@NoArgsConstructor
public class ConfigJournalAppEntity {

    private String key;

    private  String value;
}
