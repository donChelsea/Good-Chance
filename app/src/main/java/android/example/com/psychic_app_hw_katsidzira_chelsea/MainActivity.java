package android.example.com.psychic_app_hw_katsidzira_chelsea;

import android.example.com.psychic_app_hw_katsidzira_chelsea.frags.MainFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_activity_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment mainFragment = new MainFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, mainFragment).addToBackStack("first");
                fragmentTransaction.commit();
            }
        });


    }

}
