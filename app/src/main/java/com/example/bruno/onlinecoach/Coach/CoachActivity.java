package com.example.bruno.onlinecoach.Coach;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.bruno.onlinecoach.NewStudentFragment;
import com.example.bruno.onlinecoach.Objects.Exercise;
import com.example.bruno.onlinecoach.R;
import com.example.bruno.onlinecoach.dummy.StudentDummy;

public class CoachActivity extends AppCompatActivity implements
        StudentListFragment.OnListFragmentInteractionListener,
        StudentInfoFragment.OnTrainingListFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener,
        NewStudentFragment.OnFragmentInteractionListener{

    public static final String studentListFragmentTag = "student_list_fragment";
    public static final String studentInfoFragmentTag = "student_info_fragment";
    public static final String newStudentFragmentTag = "new_student_fragment";
    String coach_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_coach);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        */

        coach_id = getIntent().getExtras().getString("id");

        Bundle bundle = new Bundle();
        bundle.putString("id", coach_id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_coach);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_coach);
        navigationView.setNavigationItemSelectedListener(this);

        StudentListFragment studentListFragment = new StudentListFragment();
        studentListFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(studentListFragmentTag)
                .replace(R.id.frameCoach, studentListFragment, studentListFragmentTag)
                .commit();
    }

    @Override
    public void onStudentListFragmentInteraction(int position) {
        Toast.makeText(this, position + " pressed", Toast.LENGTH_SHORT).show();
        StudentInfoFragment studentInfoFragment = new StudentInfoFragment();
        getSupportFragmentManager().beginTransaction().addToBackStack(studentInfoFragmentTag).replace(R.id.frameCoach, studentInfoFragment, studentInfoFragmentTag).commit();
    }

    @Override
    public void OnTrainingListFragmentInteractionListener(Exercise exercise) {
        Toast.makeText(this, exercise.getDate_current() + " pressed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_new_student) {
            NewStudentFragment newStudentFragment = new NewStudentFragment();
            getSupportFragmentManager().beginTransaction().addToBackStack(newStudentFragmentTag).replace(R.id.frameCoach, newStudentFragment, newStudentFragmentTag).commit();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_coach);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coach, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_training) {
            return true;
        }else if(id == R.id.send_message){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNewStudentFragmentInteraction(Uri uri) {

    }
}
