package com.meuf.sosyalkulup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.meuf.sosyalkulup.Fragments.BottomBarFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomBarActivity extends AppCompatActivity {

    private static final String TAG_FRAGMENT_HOME = "tag_frag_home";
    private static final String TAG_FRAGMENT_DASHBOARD = "tag_frag_dashboard";
    private static final String TAG_FRAGMENT_MESSAGE = "tag_frag_message";

    public BottomNavigationView bottomNavigationView;

    private List<BottomBarFragment> fragments = new ArrayList<>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bottom_bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        switchFragment(0, TAG_FRAGMENT_HOME);
                        return true;
                    case R.id.navigation_dashboard:
                        switchFragment(1, TAG_FRAGMENT_DASHBOARD);
                        return true;
                    case R.id.navigation_chat:
                        switchFragment(2, TAG_FRAGMENT_MESSAGE);
                        return true;
                }
                return false;
            }
        });

        buildFragmentsList();

        // Set the 0th Fragment to be displayed by default.
        switchFragment(0, TAG_FRAGMENT_HOME);

    }

    private void switchFragment(int pos, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_fragmentholder, fragments.get(pos), tag)
                .commit();
    }


    private void buildFragmentsList() {
        BottomBarFragment homeFragment = buildFragment("@string/title_home");
        BottomBarFragment dashboardFragment = buildFragment("Dashboard");
        BottomBarFragment messageFragment = buildFragment("Chat");

        fragments.add(homeFragment);
        fragments.add(dashboardFragment);
        fragments.add(messageFragment);
    }

    /**
     * Creates a {@link BottomBarFragment} with corresponding Item title.
     *
     * @param title
     * @return
     */
    private BottomBarFragment buildFragment(String title) {
        BottomBarFragment fragment = new BottomBarFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BottomBarFragment.ARG_TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

}
