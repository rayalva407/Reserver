package android.reserver.com;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.jetbrains.annotations.NonNls;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FloorPlanActivity extends AppCompatActivity {
    androidx.gridlayout.widget.GridLayout gridLayout;
    ImageView backButton;
    int number;
    String[] state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_floor_plan);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.floor_plan_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gridLayout = findViewById(R.id.light_grid);
        backButton = findViewById(R.id.imageView25);

        if (savedInstanceState != null) {
            number = savedInstanceState.getInt("num_attendees");
        }
        else {
            Intent intent = getIntent();
            number = intent.getIntExtra("num_attendees", 0);
        }

         for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            if (number >= 5 && imageView.getContentDescription().equals("table")) {
                imageView.setImageResource(R.drawable.round_faded);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("num_attendees", number);
    }

}