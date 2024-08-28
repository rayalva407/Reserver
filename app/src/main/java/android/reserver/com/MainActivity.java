package android.reserver.com;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button submitButton;
    EditText numberEditText;
    int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        submitButton = findViewById(R.id.submitButton);
        numberEditText = findViewById(R.id.num_attend_edit_text);

        numberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    number = Integer.parseInt(charSequence.toString());
                }
                catch (NumberFormatException e) {
                    number = 0;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        submitButton.setOnClickListener( (v) -> {
            try {
                if (number < 1) {
                    Toast.makeText(MainActivity.this, "Please enter a valid number between 1 and 6.", Toast.LENGTH_SHORT).show();
                }
                else if (number > 6) {
                    Toast.makeText(MainActivity.this, "Please call us to make a reservartion larger than 6 people", Toast.LENGTH_SHORT).show();
                }
                else {
                    onSubmitButtonClick(v);
                }
            }
            catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Please enter a valid number between 1 and 6.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onSubmitButtonClick(View view) {
        Intent intent = new Intent(this, FloorPlanActivity.class);
        intent.putExtra("num_attendees", number);
        startActivity(intent);
    }
}