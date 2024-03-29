package com.example.cyclingapp;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.cyclingapp.data.model.AppDatabase;
import com.example.cyclingapp.data.model.ClubProfile;
import com.example.cyclingapp.data.model.Event;
import com.example.cyclingapp.data.model.EventDao;
import com.example.cyclingapp.data.model.Role;
import com.example.cyclingapp.ui.event.EventAdapter;
import com.example.cyclingapp.ui.event.EventCreate;
import com.example.cyclingapp.ui.event.EventList;

import java.util.ArrayList;
import java.util.List;

public class ClubProfileEventViewModel extends AndroidViewModel{

    private final EventDao eventDao;
    /**
     * Inserts an event profile into the database.
     * The insertion is performed asynchronously.
     *
     * @param application The club profile's event to insert into the database.
     */
    public ClubProfileEventViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
        eventDao = db.eventDao();
    }

    public void insertEvent(Event event) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            eventDao.insertEvent(event);
        });
    }

    public List<Event> getEventsByClubName(String clubName) {
        List<Event> events = new ArrayList<>();
        AppDatabase.databaseWriteExecutor.execute(() -> {
            events.addAll(eventDao.getEventsByClubName(clubName));
        });
        return events;
    }

}
