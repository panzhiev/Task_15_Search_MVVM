package com.tim.task_15_search_mvvm.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tim.task_15_search_mvvm.R;
import com.tim.task_15_search_mvvm.databases.AdapterSharedPreferences;
import com.tim.task_15_search_mvvm.databinding.DetailsPersonFragmentBinding;
import com.tim.task_15_search_mvvm.model.Person;

/**
 * Created by Tim on 12.03.2017.
 */

public class DetailsPerson extends Fragment {

    public static final String TAG = "DetailsPerson";

    DetailsPersonFragmentBinding detailsPersonFragmentBinding;
    AdapterSharedPreferences adapterSharedPreferences;
    Person mPerson;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.detailsPersonFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.details_person_fragment, container, false);

//        int idPerson = getArguments().getInt("IdPersonToDetailActivity");
//        adapterSharedPreferences = new AdapterSharedPreferences();
//        Person person = adapterSharedPreferences.getPersonById(getContext(), String.valueOf(idPerson));
        detailsPersonFragmentBinding.setPerson(mPerson);

        View view = detailsPersonFragmentBinding.getRoot();
        return view;
    }

    public void setPerson(Person person)
    {
        this.mPerson = person;
    }

    public static DetailsPerson newInstance() {

        DetailsPerson detailsPerson = new DetailsPerson();
        return detailsPerson;
    }
}
