package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private ArrayList<MyModel> list = new ArrayList<>();
    private ArrayList<MyModel> selectionList = new ArrayList<>();
    private int counter = 0;
    int back = 0;
    private Toolbar toolbar;
    private TextView txtToolbar;
    private ImageButton btnBack;
    private boolean isActionMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtToolbar = findViewById(R.id.text_toolbar);
        txtToolbar.setVisibility(View.GONE);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setVisibility(View.GONE);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list.add(new MyModel(R.drawable.ag1, "Ahfcnno nzeijceb hjbciuieb buac......."));
        list.add(new MyModel(R.drawable.ag1, "Ahfcnno nzeijceb hjbciuieb buac......."));
        list.add(new MyModel(R.drawable.og, "Ahfcnno nzeijceb hjbciuieb buac......."));
        list.add(new MyModel(R.drawable.ag1, "Ahfcnno nzeijceb hjbciuieb buac......."));
        list.add(new MyModel(R.drawable.ag1, "Ahfcnno nzeijceb hjbciuieb buac......."));
        // Ajoutez d'autres éléments à votre liste si nécessaire

        adapter = new MyAdapter(MainActivity.this, list, MainActivity.this);
        recyclerView.setAdapter(adapter);
    }

    public void startSelection(int index) {
        if (!isActionMode) {
            isActionMode = true;
            txtToolbar.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.VISIBLE);
            toolbar.inflateMenu(R.menu.menu_action);
        }

        if (selectionList.contains(list.get(index))) {
            selectionList.remove(list.get(index));
            counter--;
        } else {
            selectionList.add(list.get(index));
            counter++;
        }

        updateToolbarText(counter);
        adapter.setActionMode(isActionMode, selectionList);
    }

    private void updateToolbarText(int counter) {
        if (counter == 0) {
            txtToolbar.setText("0 élément");
        } else if (counter == 1) {
            txtToolbar.setText("1 élément");
        } else {
            txtToolbar.setText(counter + " éléments");
        }
    }

    static class Anim extends Animation {

        private int width, startWidth;
        private View view;

        public Anim(int width, View view) {

            this.width = width;
            this.view = view;
            this.startWidth = view.getWidth();
        }


        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {

            int newWidth = startWidth + (int) ((width - startWidth) * interpolatedTime);
            view.getLayoutParams().width = newWidth;
            view.requestLayout();

            super.applyTransformation(interpolatedTime, t);
        }


        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        back++;
        if (back == 2) {
            super.onBackPressed();
        }
    }

}
