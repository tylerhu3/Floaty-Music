package com.tyler.miniaudio;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class BubbleHead {

    ImageView prev, h1, h2, h3, h4, h5, h6, h7, h8, h9, h10;
    SavedPreferences savedPreferences;

    BubbleHead(View V){
        savedPreferences = SavedPreferences.getInstance();
        h1 = V.findViewById(R.id.head1);
        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (prev!= null){
                    prev.setBackgroundColor(Color.WHITE);
                }
                prev = h1;
                savedPreferences.put(SavedPreferences.ICON_NUMBER, 1);
                h1.setBackgroundColor(Color.GRAY);
                if(FloatingViewService.serviceAlive){
                    FloatingViewService.getInstance().changeBubbleHead();

                }

            }
        });

        h2 = V.findViewById(R.id.head2);
        h2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prev!= null){
                    prev.setBackgroundColor(Color.WHITE);
                }
                prev = h2;
                savedPreferences.put(SavedPreferences.ICON_NUMBER,2);
                h2.setBackgroundColor(Color.GRAY);
                if(FloatingViewService.serviceAlive){
                    FloatingViewService.getInstance().changeBubbleHead();
                }
            }
        });

        h3 = V.findViewById(R.id.head3);
        h3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prev!= null){
                    prev.setBackgroundColor(Color.WHITE);
                }
                prev = h3;
                savedPreferences.put(SavedPreferences.ICON_NUMBER, 3);
                h3.setBackgroundColor(Color.GRAY);
                if(FloatingViewService.serviceAlive){
                    FloatingViewService.getInstance().changeBubbleHead();
                }
            }
        });

        h4 = V.findViewById(R.id.head4);
        h4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prev!= null){
                    prev.setBackgroundColor(Color.WHITE);
                }
                prev = h4;
                savedPreferences.put(SavedPreferences.ICON_NUMBER, 4);
                h4.setBackgroundColor(Color.GRAY);
                if(FloatingViewService.serviceAlive){
                    FloatingViewService.getInstance().changeBubbleHead();
                }
            }
        });

        h5 = V.findViewById(R.id.head5);
        h5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prev!= null){
                    prev.setBackgroundColor(Color.WHITE);
                }
                prev = h5;
                savedPreferences.put(SavedPreferences.ICON_NUMBER,5);
                h5.setBackgroundColor(Color.GRAY);
                if(FloatingViewService.serviceAlive){
                    FloatingViewService.getInstance().changeBubbleHead();
                }
            }
        });

        h6 = V.findViewById(R.id.head6);
        h6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prev!= null){
                    prev.setBackgroundColor(Color.WHITE);
                }
                prev = h6;
                savedPreferences.put(SavedPreferences.ICON_NUMBER,6);
                h6.setBackgroundColor(Color.GRAY);
                if(FloatingViewService.serviceAlive){
                    FloatingViewService.getInstance().changeBubbleHead();
                }
            }
        });

        h7 = V.findViewById(R.id.head7);
        h7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prev!= null){
                    prev.setBackgroundColor(Color.WHITE);
                }
                prev = h7;
                savedPreferences.put(SavedPreferences.ICON_NUMBER, 7);
                h7.setBackgroundColor(Color.GRAY);
                if(FloatingViewService.serviceAlive){
                    FloatingViewService.getInstance().changeBubbleHead();
                }
            }
        });

        h8 = V.findViewById(R.id.head8);
        h8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prev!= null){
                    prev.setBackgroundColor(Color.WHITE);
                }
                prev = h8;
                savedPreferences.put(SavedPreferences.ICON_NUMBER, 8);
                h8.setBackgroundColor(Color.GRAY);
                if(FloatingViewService.serviceAlive){
                    FloatingViewService.getInstance().changeBubbleHead();
                }
            }
        });

        h9 = V.findViewById(R.id.head9);
        h9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prev!= null){
                    prev.setBackgroundColor(Color.WHITE);
                }
                prev = h9;
                savedPreferences.put(SavedPreferences.ICON_NUMBER, 9);
                h9.setBackgroundColor(Color.GRAY);
                if(FloatingViewService.serviceAlive){
                    FloatingViewService.getInstance().changeBubbleHead();
                }
            }
        });

        h10 = V.findViewById(R.id.head10);
        h10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prev!= null){
                    prev.setBackgroundColor(Color.WHITE);
                }
                prev = h10;
                savedPreferences.put(SavedPreferences.ICON_NUMBER, 10);
                h10.setBackgroundColor(Color.GRAY);
                if(FloatingViewService.serviceAlive){
                    FloatingViewService.getInstance().changeBubbleHead();
                }
            }
        });
    }
}
