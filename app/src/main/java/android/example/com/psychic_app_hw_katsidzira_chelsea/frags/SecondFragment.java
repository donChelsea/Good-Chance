package android.example.com.psychic_app_hw_katsidzira_chelsea.frags;


import android.support.v4.app.Fragment;
import android.example.com.psychic_app_hw_katsidzira_chelsea.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

import static android.example.com.psychic_app_hw_katsidzira_chelsea.MainActivity.databaseHelper;
import static android.example.com.psychic_app_hw_katsidzira_chelsea.frags.MainFragment.THEMES_LIST;

public class SecondFragment extends Fragment {
    private static final int CORRECT = 1;
    private static final int INCORRECT = 0;
    public static final String USER_PICK = "user image";
    public static final String TAG = "SecondFrag";
    public static final String USER_IMAGE = "user pick image";
    public static final String ANDROID_IMAGE = "android pick image";
    public static final String RESULT_STRING = "result string";
    public static final String PERCENTAGE = "percentage";
    ArrayList<Integer> themesList;
    private ImageView image0;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private Random rand;
    private String userPickNumber;
    private int userPickImage;
    private int androidPick;
    public int androidPickImage;
    public String resultString;
    public float percentage;


    public SecondFragment() {}

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image0 = view.findViewById(R.id.image1);
        image1 = view.findViewById(R.id.image2);
        image2 = view.findViewById(R.id.image3);
        image3 = view.findViewById(R.id.image4);
        ImageView[] images = new ImageView[] {image0, image1, image2, image3};
        rand = new Random();

        Bundle args = getArguments();
        assert args != null;
        themesList = args.getIntegerArrayList(THEMES_LIST);

        for (int i = 0; i < images.length; i++) {
            ImageView userSelected = images[i];
            userSelected.setImageResource(themesList.get(i));
            userSelected.setOnClickListener(new onImageClickedListener());
        }

        androidPick = rand.nextInt(images.length);
        Log.d(TAG, "androidPick " + androidPick);
    }

    class onImageClickedListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            userPickNumber = (String) v.getTag();
            int num = Integer.parseInt(userPickNumber.substring(userPickNumber.length() - 1));
            userPickImage = themesList.get(num);
            androidPickImage = themesList.get(androidPick);
            float allRight = databaseHelper.getCorrectAnswers();
            float allAnswers = databaseHelper.getAllAnswers();
            if (userPickNumber.equals("image" + androidPick)) {
                databaseHelper.addScore(CORRECT);
                resultString = "Perfect Match!";
            } else {
                resultString = "No match!";
                databaseHelper.addScore(INCORRECT);
            }
            percentage = (allRight / allAnswers) * 100;
            moveToNextFragment();
        }
    }

    public void moveToNextFragment() {
        ThirdFragment thirdFragment = ThirdFragment.newInstance();
        Bundle args = new Bundle();
        args.putInt(USER_IMAGE, userPickImage);
        args.putInt(ANDROID_IMAGE, androidPickImage);
        args.putString(RESULT_STRING, resultString);
        args.putFloat(PERCENTAGE, percentage);
        thirdFragment.setArguments(args);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, thirdFragment)
                .addToBackStack("third")
                .commit();
    }
}
