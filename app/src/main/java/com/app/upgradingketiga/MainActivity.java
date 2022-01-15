package com.app.upgradingketiga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String BASE_URL = "https://www.thesportsdb.com/api/v1/json/2/search_all_teams.php?l=English%20Premier%20League";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ArrayList<TeamModel> teamList;
    private TeamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_team_main);
        progressBar = findViewById(R.id.progress_circular_main);

        AndroidNetworking.initialize(getApplicationContext());

        getData();
    }

    private void getData() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        teamList = new ArrayList();

        AndroidNetworking.get(BASE_URL).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("teams");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String image = jsonObject.getString("strTeamBadge");
                        String name = jsonObject.getString("strTeam");
                        String stadium = jsonObject.getString("strStadium");
                        String description = jsonObject.getString("strDescriptionEN");

                        teamList.add(new TeamModel(image, name, stadium, description));
                    }

                    setAdapter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(ANError anError) {
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setAdapter() {
        adapter = new TeamAdapter(teamList, getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new TeamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                TeamModel teamModel = teamList.get(position);
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("image", teamModel.getImage());
                intent.putExtra("team", teamModel.getTeamName());
                intent.putExtra("stadium", teamModel.getStadiumName());
                intent.putExtra("description", teamModel.getDescription());
                startActivity(intent);
            }
        });
    }
}