package com.app.upgradingketiga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private TextView tv_team, tv_stadium, tv_description;
    private ImageView img_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_team = findViewById(R.id.tv_team_detail);
        tv_stadium = findViewById(R.id.tv_stadium_detail);
        tv_description = findViewById(R.id.tv_description_detail);
        img_logo = findViewById(R.id.img_logo_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tv_team.setText(bundle.getString("team"));
            tv_stadium.setText(bundle.getString("stadium"));
            tv_description.setText(bundle.getString("description"));
            Picasso.get().load(bundle.getString("image")).into(img_logo);
        }
    }
}