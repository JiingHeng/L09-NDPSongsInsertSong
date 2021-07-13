package sg.edu.rp.c346.id20013327.l09_ndpsongsinsertsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    Button btnShow;
    ArrayList<Song> songl;
    ArrayAdapter<Song> songaa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle(getTitle().toString() + " ~ " + "Show Song");

        lv = findViewById(R.id.lv);
        btnShow = findViewById(R.id.btnShow5Star);

        DBHelper dbh = new DBHelper(this);
        songl = dbh.getAllSongs();
        dbh.close();

        songaa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songl);
        lv.setAdapter(songaa);

        lv.setOnItemClickListener(((parent, view, position, id) -> {
            Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
            i.putExtra("song", songl.get(position));
            startActivity(i);
        }));

        btnShow.setOnClickListener((v -> {
//            DBHelper dbh = new DBHelper(SecondActivity.this);
            songl.clear();
            songl.addAll(dbh.getAllSongs());
            songaa.notifyDataSetChanged();
        }));
    }
}