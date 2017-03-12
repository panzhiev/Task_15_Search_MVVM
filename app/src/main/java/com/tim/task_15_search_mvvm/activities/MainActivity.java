package com.tim.task_15_search_mvvm.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tim.task_15_search_mvvm.R;
import com.tim.task_15_search_mvvm.adapter.MyAdapter;
import com.tim.task_15_search_mvvm.databases.AdapterSharedPreferences;
import com.tim.task_15_search_mvvm.databinding.ActivityMainBinding;
import com.tim.task_15_search_mvvm.databinding.ItemPersonBinding;
import com.tim.task_15_search_mvvm.fragments.DetailsPerson;
import com.tim.task_15_search_mvvm.model.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    public static final String[] NAME =  new String[] {"Denys", "Stanislav", "Vadim", "Eugeniy", "Alexander", "Vladislav", "Sergey", "Aleksey"};
    private static final String[] SURNAME = new String[] {"Kalashnyk", "Puzin", "Hotiun", "Kovalov", "Merezhko", "Pomoynyts'kyy", "Rudenko", "Greenyuk"};
    private static final String[] PHONE_NUMBER = new String [] {"0993414821", "0667077979", "0974995005", "0933988237", "0932007592", "0631365815", "0938375507", "0679999977"};
    private static final String[] MAIL = new String [] {"kalashnyk.denys@gmail.com", "stanislavshido@gmail.com", "v.a.d.i.k@mail.ru", "eugene.kovalev@me.com", "merezhkosasha@gmail.com", "ekar89@mail.ru", "thesergeyrudenko@gmail.com", "alexey.grinyuk@gmail.com"};
    private static final String[] SKYPE = new String [] {"denis_ka27", "shido_s", "hotun.vadim", "kovalev_eugene", "sasha_merezhko", "vladislavpom", "sergey_rudenko_84", "greenya1"};

    private static final Comparator<Person> ALPHABETICAL_COMPARATOR = new Comparator<Person>() {
        @Override
        public int compare(Person a, Person b) {
            return a.getName().compareTo(b.getName());
        }
    };

    private MyAdapter mAdapter;
    AdapterSharedPreferences adapterSharedPreferences = new AdapterSharedPreferences();
    private ActivityMainBinding mBinding;
    private ItemPersonBinding itemPersonBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mAdapter = new MyAdapter(this, ALPHABETICAL_COMPARATOR, new MyAdapter.Listener() {
            @Override
            public void onPersonModelClicked(Person person) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("IdPersonToDetailActivity", (int) person.getId());
//                fragment.setArguments(bundle);

                DetailsPerson fragment = DetailsPerson.newInstance();
                fragment.setPerson(person);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null);
                fragmentTransaction.commit();
                mBinding.recyclerView.setVisibility(View.GONE);

//                itemPersonBinding.setPerson(person);
//                final String name = getString(R.string.model_clicked_pattern, person.getName());
//                Snackbar.make(mBinding.getRoot(), name, Snackbar.LENGTH_SHORT).show();
            }
        });

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(mAdapter);

//        mModels = new ArrayList<>();
        for (int i = 0, count = NAME.length; i < count; i++) {
            adapterSharedPreferences.addPerson(this, new Person(i, NAME[i], SURNAME[i], PHONE_NUMBER[i], MAIL[i], SKYPE[i]));
        }
        mAdapter.edit()
                .replaceAll(adapterSharedPreferences.getPersons(this))
                .commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<Person> filteredModelList = filter(adapterSharedPreferences.getPersons(this), query);
        mAdapter.edit()
                .replaceAll(filteredModelList)
                .commit();

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private static List<Person> filter(List<Person> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<Person> filteredModelList = new ArrayList<>();
        for (Person model : models) {
            final String name = model.getName().toLowerCase();
            if (name.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public void onBackPressed() {
        mBinding.recyclerView.setVisibility(View.VISIBLE);
        super.onBackPressed();
    }
}
