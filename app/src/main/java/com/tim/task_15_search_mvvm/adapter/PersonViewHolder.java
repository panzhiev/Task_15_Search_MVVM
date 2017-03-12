package com.tim.task_15_search_mvvm.adapter;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.tim.task_15_search_mvvm.databinding.ItemPersonBinding;
import com.tim.task_15_search_mvvm.model.Person;

/**
 * Created by Tim on 06.03.2017.
 */

public class PersonViewHolder extends SortedListAdapter.ViewHolder<Person> {

    private final ItemPersonBinding mBinding;

    public PersonViewHolder(ItemPersonBinding binding, MyAdapter.Listener listener) {
        super(binding.getRoot());

        binding.setListener(listener);
        mBinding = binding;
    }

    @Override
    protected void performBind(Person item) {
        mBinding.setPerson(item);
    }
}
