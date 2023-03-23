package com.example.assignment3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

public class ItemFragments extends ListFragment{

    // Define an array of items to display in the list
    private String[] items;
    private ArrayList<item> arr1;
    public ItemFragments(String[] arr ,ArrayList<item> itemss){
        items = arr;
        arr1 = itemss;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter for the list view
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Handle item click event

        ItemFragment2 myListFragment2 = new ItemFragment2(arr1.get(position));
        Log.d("ar1def" , arr1.get(position).def);
        // Add the ListFragment to the container view using the FragmentManager
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, myListFragment2)
                .commit();

        Toast.makeText(getActivity(), "Item clicked: " + items[position], Toast.LENGTH_SHORT).show();
    }
}

