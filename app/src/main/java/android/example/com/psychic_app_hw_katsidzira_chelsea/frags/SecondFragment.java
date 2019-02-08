package android.example.com.psychic_app_hw_katsidzira_chelsea.frags;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.example.com.psychic_app_hw_katsidzira_chelsea.ChoiceDatabaseHelper;
import android.example.com.psychic_app_hw_katsidzira_chelsea.R;
import android.example.com.psychic_app_hw_katsidzira_chelsea.frags.ThirdFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

import static android.example.com.psychic_app_hw_katsidzira_chelsea.frags.MainFragment.ARGS_KEY;

public class SecondFragment extends Fragment {
    private static final String CORRECT_CHOICES = "correct_choices";
    private View rootView;
    private ArrayList<Integer> themesList;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private Random rand;
    private String userPick;
    int correctChoices;
    int wrongChoices;
    int totalChoices;
    Context context;


    public SecondFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_second, container, false);
        image1 = rootView.findViewById(R.id.image1);
        image2 = rootView.findViewById(R.id.image2);
        image3 = rootView.findViewById(R.id.image3);
        image4 = rootView.findViewById(R.id.image4);
        final ImageView[] images = new ImageView[] {image1, image2, image3, image4};
        rand = new Random();
        final int androidPick = rand.nextInt(images.length);
        context = getContext();
        Log.d("SecondFrag", "androidPick " + androidPick);

        Bundle args = getArguments();
        themesList = args.getIntegerArrayList(ARGS_KEY);

        for (int i = 0; i < images.length; i++) {
            images[i].setImageResource(themesList.get(i));

            images[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ThirdFragment thirdFragment = new ThirdFragment();
                    Bundle args = new Bundle();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.main_container, thirdFragment)
                            .addToBackStack("third")
                            .commit();
                    userPick = String.valueOf(v.getTag());
                    Log.d("SecondFrag", "userPick " + userPick);
                    Bundle total = new Bundle();
                    Intent intent = new Intent(v.getContext(), ThirdFragment.class);
                    if (userPick.equals("image" + androidPick)) {
                        Log.d("SecondFrag", "userPick and androidPick match");
                        correctChoices++;
                        totalChoices++;
                    } else {
                        totalChoices++;
                    }
                    Log.i("second frag", correctChoices + " " + wrongChoices + " " + totalChoices);
//                    ChoiceDatabaseHelper databaseHelper = ChoiceDatabaseHelper.getInstance(context);
//                    SQLiteDatabase database = databaseHelper.getWritableDatabase();
////                    databaseHelper.insertData(0);
//                    databaseHelper.updateChoices(1);
//                    databaseHelper.queryChoices();
                }
            });
        }



        return rootView;
    }
//
//    public void chosenImage() {
//        if () {
//
//        }
//    }

}
