package android.example.com.psychic_app_hw_katsidzira_chelsea.frags;


import android.content.Context;
import android.example.com.psychic_app_hw_katsidzira_chelsea.ChoiceDatabaseHelper;
import android.example.com.psychic_app_hw_katsidzira_chelsea.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    private View rootView;
    private TextView mainTextView;
    private Spinner spinner;
    private Button startButton;
    private Context context;
    private List<String> categories;
    public static final String ARGS_KEY = "themes_list";
    private int choosenTheme;
    private ArrayList<Integer> nineties = new ArrayList<>(Arrays.asList(R.drawable.rugrats90s,
            R.drawable.drinks90s, R.drawable.justin90s, R.drawable.freshprince90s, R.drawable.clueless90s, R.drawable.models90s));
    private ArrayList<Integer> eighties = new ArrayList<>(Arrays.asList(R.drawable.fashion80s, R.drawable.hair80s,
            R.drawable.hair80s2, R.drawable.movies80s, R.drawable.music80s));
    private ArrayList<Integer> twenties = new ArrayList<>(Arrays.asList(R.drawable.comedy20s, R.drawable.gangster20s,
            R.drawable.music20s, R.drawable.fashion20s, R.drawable.prohibition20s));


    public MainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mainTextView = rootView.findViewById(R.id.pick_theme_textview);
        spinner = rootView.findViewById(R.id.themes_spinner);
        startButton = rootView.findViewById(R.id.start_button);

        categories = new ArrayList<>();
        categories.add("Choose from");
        categories.add("90's Throwback");
        categories.add("80's Jam");
        categories.add("20's Jazz");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                rootView.getContext(),
                android.R.layout.simple_spinner_item,
                categories);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        choosenTheme = 1;
                        break;
                    case 2:
                        choosenTheme = 2;
                        break;
                    case 3:
                        choosenTheme = 3;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(rootView.getContext(), "Please select a theme", Toast.LENGTH_SHORT).show();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(nineties);
                Collections.shuffle(eighties);
                Collections.shuffle(twenties);
                switch (choosenTheme) {
                    case 0:
                        break;
                    case 1:
                        startGame(nineties);
                        break;
                    case 2:
                        startGame(eighties);
                        break;
                    case 3:
                        startGame(twenties);
                        break;
                    default:
                        break;
                }
            }
        });
        return rootView;


    }

    public void startGame(ArrayList themeList) {
        SecondFragment secondFragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putIntegerArrayList(ARGS_KEY, themeList);
        secondFragment.setArguments(args);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, secondFragment)
                .addToBackStack("second")
                .commit();
    }

}
