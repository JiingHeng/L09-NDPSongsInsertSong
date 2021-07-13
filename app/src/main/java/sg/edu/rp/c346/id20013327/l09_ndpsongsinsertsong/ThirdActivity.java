package sg.edu.rp.c346.id20013327.l09_ndpsongsinsertsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etTitle, etSingers, etYear;
    Button btnCancel, btnUpdate, btnDelete;
    TextView tvID, tvTitle, tvSingers, tvYear;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etID = findViewById(R.id.etIDInput);
        etTitle = findViewById(R.id.etSongTitle2);
        etSingers = findViewById(R.id.etSingers2);
        etYear = findViewById(R.id.etYear2);
        rb1 = findViewById(R.id.rb1star);
        rb2 = findViewById(R.id.rb2star);
        rb3 = findViewById(R.id.rb3star);
        rb4 = findViewById(R.id.rb4star);
        rb5 = findViewById(R.id.rb5star);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        final Song currentSong = (Song) i.getSerializableExtra("song");

        etID.setText(currentSong.getId()+"");
        etTitle.setText(currentSong.getTitle()+"");
        etSingers.setText(currentSong.getTitle()+"");
        etYear.setText(currentSong.getYear()+"");
        switch (currentSong.getStars()) {
            case 5: rb5.setChecked(true);
                    break;
            case 4: rb4.setChecked(true);
                    break;
            case 3: rb3.setChecked(true);
                    break;
            case 2: rb2.setChecked(true);
                    break;
            case 1: rb1.setChecked(true);
        }

        btnUpdate.setOnClickListener(v -> {
            DBHelper dbh = new DBHelper(ThirdActivity.this);
            currentSong.setTitle(etTitle.getText().toString().trim());
            currentSong.setSingers(etSingers.getText().toString().trim());
            int year = 0;
            try {
                year = Integer.valueOf(etYear.getText().toString().trim());
            } catch (Exception e) {
                Toast.makeText(ThirdActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                return;
            }
            currentSong.setYear(year);

            int selectedRB = rg.getCheckedRadioButtonId();
            RadioButton rb = (RadioButton) findViewById(selectedRB);
            currentSong.setStars(Integer.parseInt(rb.getText().toString()));
            int result = dbh.updateSong(currentSong);
            if (result > 0) {
                Toast.makeText(ThirdActivity.this, "Song updated", Toast.LENGTH_SHORT).show();
                Intent a = new Intent();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(v -> {
            DBHelper dbh = new DBHelper(ThirdActivity.this);
            int result = dbh.deleteSong(currentSong.getId());
            if (result > 0) {
                Toast.makeText(ThirdActivity.this, "Song deleted", Toast.LENGTH_SHORT).show();
                Intent b = new Intent();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> {
            finish();
        });
    }
}