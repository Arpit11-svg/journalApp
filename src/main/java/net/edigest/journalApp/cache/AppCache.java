package net.edigest.journalApp.cache;

import jakarta.annotation.PostConstruct;
import net.edigest.journalApp.entity.ConfigJournalAppEntity;
import net.edigest.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public Map<String,String> appCache;

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    //As bean is created of AppCache, this method will be called immediately (due to @PostConstruct)
    @PostConstruct
    public void init(){
        appCache=new HashMap<>(); //each time we are refreshing this Map
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();

        for(ConfigJournalAppEntity configJournalAppEntity:all){
            appCache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }
        //Only one time, this takes value from DB and stores in Map and gives value as needed, means it works as InMemoryCache
    }
}
