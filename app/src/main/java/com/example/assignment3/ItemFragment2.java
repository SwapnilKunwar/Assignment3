package com.example.assignment3;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.assignment3.placeholder.PlaceholderContent;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment2 extends ListFragment {

    public static item itm;
    public  static String[] arrstr;
    public ItemFragment2(item itm2){
        itm=itm2;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        arrstr=new String[5];
        arrstr[0]=itm.pof;
        arrstr[1]=itm.def;
        arrstr[2]=itm.example;
        arrstr[3]=itm.syn;
        arrstr[4]= itm.ant;
        View view = inflater.inflate(R.layout.fragment_item_list2, container, false);

        // Set the adapter for the list view
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrstr);
        setListAdapter(adapter);

        return view;
    }


}