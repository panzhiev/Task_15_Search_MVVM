package com.tim.task_15_search_mvvm.databases;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tim.task_15_search_mvvm.model.Person;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tim on 18.01.2017.
 */

public class AdapterSharedPreferences {

    public static final String PREFS_NAME = "CRUD_APP";
    public static final String PERSON_CONSTANT = "Person Constant";

    public AdapterSharedPreferences() {
        super();
    }

    public void savePerson(Context context, List<Person> Persons) {
        SharedPreferences preferences;
        SharedPreferences.Editor editor;

        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();

        Gson gson = new Gson();
        String jsonPerson = gson.toJson(Persons);

        editor.putString(PERSON_CONSTANT, jsonPerson);

        editor.apply();
    }

    public void addPerson(Context context, Person Person) {
        List<Person> personList = getPersons(context);
        if (personList == null) {
            personList = new ArrayList<Person>();
        }
        personList.add(Person);
        savePerson(context, personList);
    }

    public ArrayList<Person> getPersons(Context context) {
        SharedPreferences preferences;
        List<Person> personList;

        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        if (preferences.contains(PERSON_CONSTANT)) {
            String jsonPerson = preferences.getString(PERSON_CONSTANT, null);
            Gson gson = new Gson();
            Person[] PersonsItems = gson.fromJson(jsonPerson, Person[].class);

            personList = Arrays.asList(PersonsItems);
            personList = new ArrayList<Person>(personList);
        } else {
            return null;
        }
        return (ArrayList<Person>) personList;
    }

    public void removePerson(Context context, Person person) {
        ArrayList<Person> personList = getPersons(context);
        if (personList != null) {
            personList.remove(person);
            savePerson(context, personList);
        }
    }

    public void updatePerson(Context context, Person newPerson) {
        ArrayList<Person> personList = getPersons(context);
        if (personList != null) {
            for (Person person : personList) {
                if (person.equals(newPerson)) {
                    personList.set(personList.indexOf(person), newPerson);
                }
            }
        }
        savePerson(context, personList);
    }

    public  Person getPersonById(Context mContext, long id ) {

        AbstractList<Person> persons = getPersons(mContext);

        for (Person person : persons) {

            if (person.getId() == id) return person;
        }
        return null;
    }

//    public void updatePerson(Context context, Person newPerson){
//        ArrayList<Person> personList = getPersons(context);
//        if (personList != null) {
//            for (Person person: personList) {
//                if(person.equals(newPerson)) person = newPerson;
//            }
//            savePerson(context, personList);
//        }
//    }
}
