package android.example.com.psychic_app_hw_katsidzira_chelsea;

import android.example.com.psychic_app_hw_katsidzira_chelsea.frags.MainFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    public static ChoiceDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Good Chance!");

        databaseHelper = new ChoiceDatabaseHelper(this);

        findViewById(R.id.main_activity_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment mainFragment = MainFragment.newInstance();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, mainFragment).addToBackStack("first");
                fragmentTransaction.commit();
            }
        });
    }
}
