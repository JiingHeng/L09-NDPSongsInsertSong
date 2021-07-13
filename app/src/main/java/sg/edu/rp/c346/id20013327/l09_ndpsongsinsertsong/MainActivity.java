package sg.edu.rp.c346.id20013327.l09_ndpsongsinsertsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RadioButton oneStar, twoStar, threeStar, fourStar, fiveStar;
    TextView tvSongTitle, tvSingers, tvYear, tvStars;
    EditText etSongTitle, etSingers, etYear;
    Button btnInsert, btnShowList;
    ListView lv;
    RadioGroup rgStars;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oneStar = findViewById(R.id.rb1);
        twoStar = findViewById(R.id.rb2);
        threeStar = findViewById(R.id.rb3);
        fourStar = findViewById(R.id.rb4);
        fiveStar = findViewById(R.id.rb5);
        tvSongTitle = findViewById(R.id.tvSongTitle);
        tvSingers = findViewById(R.id.tvSingers);
        tvYear = findViewById(R.id.tvYear);
        tvStars = findViewById(R.id.tvStars);
        etSongTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        lv = findViewById(R.id.lv);
        rgStars = findViewById(R.id.rg);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String singer = etSingers.getText().toString().trim();
                String title = etSongTitle.getText().toString().trim();
                if(title.length() == 0 || singer.length() == 0) {
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }
                String yearStr = etYear.getText().toString().trim();
                int year = 0;
                try {
                    year = Integer.valueOf(yearStr);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                int selectedID = rgStars.getCheckedRadioButtonId();
//                int stars = getStars();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(title, singer, year, selectedID);

                if(inserted_id != -1) {
                    al.clear();
                    al.addAll(dbh.getAllSongs());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert song successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

//        private int getStars() {
//            int stars = 1;
//            switch (rgStars.getCheckedRadioButtonId()) {
//                case R.id.rb1:
//                    stars = 1;
//                    break;
//                case R.id.rb2:
//                    stars = 2;
//                    break;
//                case R.id.rb3:
//                    stars = 3;
//                    break;
//                case R.id.rb4:
//                    stars = 4;
//                    break;
//                case R.id.rb5:
//                    stars = 5;
//                    break;
//            }
//        }
    }

}