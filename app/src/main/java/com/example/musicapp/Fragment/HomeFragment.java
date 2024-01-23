package com.example.musicapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.musicapp.Adapters.SliderAdapter;
import com.example.musicapp.Adapters.SliderMoveAdapter;
import com.example.musicapp.Models.SliderModel;
import com.example.musicapp.Models.SliderMoveModel;
import com.example.musicapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class HomeFragment extends Fragment {



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Avatar
        ImageButton imgBtnAvatar = view.findViewById(R.id.imgBtn_avatar);

        imgBtnAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
            }
        });

        // For SliderAdapter
        ViewPager2 slider1 = view.findViewById(R.id.slider1);
        TabLayout sliderIndicator = view.findViewById(R.id.slider_indicator); //SliderIndicator

        ArrayList<SliderModel> sliderModelList = new ArrayList<>();
        sliderModelList.add(new SliderModel(R.drawable.poster1, "Playlist #1"));
        sliderModelList.add(new SliderModel(R.drawable.poster2, "Playlist #2"));
        sliderModelList.add(new SliderModel(R.drawable.poster3, "Playlist #3"));
        sliderModelList.add(new SliderModel(R.drawable.poster4, "Playlist #4"));
        sliderModelList.add(new SliderModel(R.drawable.poster5, "Playlist #5"));

        SliderAdapter sliderAdapter = new SliderAdapter(getContext(), sliderModelList);
        slider1.setAdapter(sliderAdapter);
        //SliderIndicator
        new TabLayoutMediator(sliderIndicator, slider1, (tab, position) -> {
            //
        }).attach();
        //SliderMoveAnimation
        slider1.setPageTransformer((page, position) -> {
            float scale = Math.max(0.95f, 1 - Math.abs(position)); //max(small picture {0f -> 1f}, 1 - Math.abs(position) #= {1})
            page.setScaleY(scale);
        });

        // For SliderMoveAdapter
        ViewPager2 slider2 = view.findViewById(R.id.slider2);

        ArrayList<SliderMoveModel> sliderMoveModelList = new ArrayList<>();
        sliderMoveModelList.add(new SliderMoveModel(R.drawable.slider1, "Slide 1"));
        sliderMoveModelList.add(new SliderMoveModel(R.drawable.slider2, "Slide 2"));
        sliderMoveModelList.add(new SliderMoveModel(R.drawable.slider3, "Slide 3"));
        sliderMoveModelList.add(new SliderMoveModel(R.drawable.slider4, "Slide 4"));


        // Create a copy of the list for the infinite scrolling effect
        ArrayList<SliderMoveModel> infiniteList = new ArrayList<>(sliderMoveModelList);
        infiniteList.addAll(0, sliderMoveModelList.subList(sliderMoveModelList.size() - 2, sliderMoveModelList.size()));
        infiniteList.addAll(sliderMoveModelList.subList(0, 2));

        SliderMoveAdapter sliderMoveAdapter = new SliderMoveAdapter(getActivity(), infiniteList);
        slider2.setAdapter(sliderMoveAdapter);

        // Auto scroll
        int delayInMilliseconds = 7000; // 7 seconds
        final Handler handler = new Handler();
        final Runnable runnableCode = new Runnable() {
            @Override
            public void run() {
                // Get the current item
                int currentItem = slider2.getCurrentItem();
                // Check if the last item is reached
                if (currentItem >= infiniteList.size() - 2) {
                    currentItem = 2; // start over
                } else {
                    currentItem++;
                }
                // Set the next item
                slider2.setCurrentItem(currentItem, false);
                // Reset the runnable after a delay
                handler.postDelayed(this, delayInMilliseconds);
            }
        };
        // Execute the Runnable every X milliseconds
        handler.postDelayed(runnableCode, delayInMilliseconds);


        return view;
    }


}
