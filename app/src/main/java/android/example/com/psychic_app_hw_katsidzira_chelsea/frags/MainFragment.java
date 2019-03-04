package android.example.com.psychic_app_hw_katsidzira_chelsea.frags;

import android.example.com.psychic_app_hw_katsidzira_chelsea.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    private TextView mainTextView;
    private Spinner spinner;
    private Button startButton;
    private List<String> categories;
    public static final String THEMES_LIST = "themes_list";
    private int choosenTheme;
    private ArrayList<Integer> nineties = new ArrayList<>(Arrays.asList(R.drawable.rugrats90s,
            R.drawable.drinks90s, R.drawable.justin90s, R.drawable.freshprince90s, R.drawable.clueless90s, R.drawable.models90s));
    private ArrayList<Integer> eighties = new ArrayList<>(Arrays.asList(R.drawable.fashion80s, R.drawable.hair80s,
            R.drawable.hair80s2, R.drawable.movies80s, R.drawable.music80s));
    private ArrayList<Integer> twenties = new ArrayList<>(Arrays.asList(R.drawable.comedy20s, R.drawable.gangster20s,
            R.drawable.music20s, R.drawable.fashion20s, R.drawable.prohibition20s));


    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainTextView = view.findViewById(R.id.pick_theme_textview);
        spinner = view.findViewById(R.id.themes_spinner);
        startButton = view.findViewById(R.id.start_button);

        categories = new ArrayList<>();
        categories.add("Choose from");
        categories.add("90's Throwback");
        categories.add("80's Jam");
        categories.add("20's Jazz");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                view.getContext(),
                android.R.layout.simple_spinner_item,
                categories);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
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
                Toast.makeText(getContext(), "Please select a theme", Toast.LENGTH_SHORT).show();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (choosenTheme) {
                    case 1:
                        Collections.shuffle(nineties);
                        startGame(nineties);
                        break;
                    case 2:
                        Collections.shuffle(eighties);
                        startGame(eighties);
                        break;
                    case 3:
                        Collections.shuffle(twenties);
                        startGame(twenties);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void startGame(ArrayList themeList) {
        SecondFragment secondFragment = SecondFragment.newInstance();
        Bundle args = new Bundle();
        args.putIntegerArrayList(THEMES_LIST, themeList);
        secondFragment.setArguments(args);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, secondFragment)
                .addToBackStack("second")
                .commit();
    }

}
