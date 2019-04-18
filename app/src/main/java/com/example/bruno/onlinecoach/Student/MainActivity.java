package com.example.bruno.onlinecoach.Student;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bruno.onlinecoach.Coach.StudentListFragment;
import com.example.bruno.onlinecoach.CoachStudentInfoFragment;
import com.example.bruno.onlinecoach.Objects.Coach;
import com.example.bruno.onlinecoach.Objects.Exercise;
import com.example.bruno.onlinecoach.Objects.Measure;
import com.example.bruno.onlinecoach.Objects.Student;
import com.example.bruno.onlinecoach.Objects.User;
import com.example.bruno.onlinecoach.R;
import com.example.bruno.onlinecoach.dummy.StudentDummy;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        MainMenuFragment.OnFragmentInteractionListener,
        WeightListFragment.OnWeightListFragmentInteractionListener,
        ExerciseListFragment.OnExerciseListFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        StudentListFragment.OnListFragmentInteractionListener,
        CoachStudentInfoFragment.OnFragmentInteractionListener{

    FrameLayout main_frame;
    private static final String TAG = "MainActivityQuery";
    public static final String menuFragmentTag = "main_menu_fragment";
    public static final String studentListFragmentTag = "student_list_fragment";
    public static final String weightListFragmentTag = "weight_list_fragment";
    public static final String exerciseListFragmentTag = "exercise_list_fragment";
    public static final String coachStudentInfoFragmentTag = "coach_student_info";
    public static final String profileFragmentTag = "profile_fragment";
    String student_id, user_email;
    Student student = new Student();
    Coach coach = new Coach();
    Exercise exercise = new Exercise();
    private DatabaseReference mDatabase;
    TextView student_name, coach_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        student_name = navigationView.getHeaderView(0).findViewById(R.id.tv_student_name);
        coach_name = navigationView.getHeaderView(0).findViewById(R.id.tv_coach_name);

        main_frame = findViewById(R.id.frame);

        student_id = getIntent().getExtras().getString("id");
        user_email = getIntent().getExtras().getString("email");

        int userType = getIntent().getExtras().getInt("type");

        if(userType == 0){
            getStudentInfo(user_email);
        }else if(userType == 1){
            getCoachInfo(user_email);
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //int count = getFragmentManager().getBackStackEntryCount();

              //getFragmentManager().popBackStack();
            super.onBackPressed();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_weight) {
            // Handle the camera action
            onMenuFragmentInteraction(1);
        } else if (id == R.id.nav_training) {
            onMenuFragmentInteraction(2);
        } else if (id == R.id.nav_coach) {
            addCoach();
        } else if (id == R.id.nav_message) {
            onMenuFragmentInteraction(4);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMenuFragmentInteraction(int i) {
        switch(i){
            case 1:
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("measures", student.getMeasures_history());
                Toast.makeText(this, "Option " + i + " pressed", Toast.LENGTH_SHORT).show();
                WeightListFragment weightListFragment = new WeightListFragment();
                weightListFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().addToBackStack(weightListFragmentTag).replace(R.id.frame, weightListFragment, weightListFragmentTag).commit();
                break;
            case 2:
                Bundle bundle2 = new Bundle();
                bundle2.putStringArrayList("exercises", student.getExercises());
                Toast.makeText(this, "Option " + i + " pressed", Toast.LENGTH_SHORT).show();
                ExerciseListFragment exerciseListFragment = new ExerciseListFragment();
                exerciseListFragment.setArguments(bundle2);
                getSupportFragmentManager().beginTransaction().addToBackStack(exerciseListFragmentTag).replace(R.id.frame, exerciseListFragment, exerciseListFragmentTag).commit();
                break;
            case 3:
                Toast.makeText(this, "Option " + i + " pressed", Toast.LENGTH_SHORT).show();
                Bundle bundle3 = new Bundle();
                bundle3.putDouble("weight", student.getLastWeight());
                bundle3.putDouble("weight_goal", student.getWeight_goal());
                bundle3.putString("name", student.getName());
                bundle3.putString("coach_name", student.getCoach_name());
                bundle3.putInt("age", student.getAge());
                ProfileFragment profileFragment = new ProfileFragment();
                profileFragment.setArguments(bundle3);
                getSupportFragmentManager().beginTransaction().addToBackStack(profileFragmentTag).replace(R.id.frame, profileFragment, profileFragmentTag).commit();
                break;
            case 4:
                Toast.makeText(this, "Option " + i + " pressed", Toast.LENGTH_SHORT).show();
                String number = student.getCoach_phone();  // The number on which you want to send SMS
                if(number == null || number.length() == 0){
                    Toast.makeText(this, "Coach does not have a phone number", Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
                }
            default:
                Toast.makeText(this, "Choose an option", Toast.LENGTH_SHORT).show();
        }

    }




    private void getStudentInfo(String email){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query myTopPostsQuery = mDatabase.child("users").orderByChild("email").equalTo(email);

        // [START basic_query_value_listener]
        // My top posts by number of stars
        myTopPostsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.w(TAG, "loadPost:onDataChange");
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    //System.out.println(dataSnapshot.getValue());
                    //User u = postSnapshot.getValue(User.class);
                    student = postSnapshot.child("measures").getValue(Student.class);
                    student.setId(postSnapshot.getKey());
                    student.setCoach(postSnapshot.child("coach").getValue().toString());
                    student.setName(postSnapshot.child("name").getValue().toString());
                    student.setAge(Integer.valueOf(postSnapshot.child("age").getValue().toString()));
                    student.setWeight_goal(Integer.valueOf(postSnapshot.child("weight_goal").getValue().toString()));
                    student.setPhone(postSnapshot.child("phone").getValue().toString());

                    for (DataSnapshot exercise: postSnapshot.child("exercises").child("current").getChildren()) {
                        student.getExercises().add(exercise.getValue().toString());
                        //System.out.println("Ex " + exercise.child("name").getValue());
                    }

                    for (DataSnapshot measure: postSnapshot.child("measures").child("history").getChildren()) {
                        student.getMeasures_history().add(new Measure(
                                measure.child("date").getValue().toString(),
                                Double.parseDouble(measure.child("weight").getValue().toString()),
                                Double.parseDouble(measure.child("fat").getValue().toString()),
                                Double.parseDouble(measure.child("muscle").getValue().toString())
                        ));
                        //System.out.println("Ex " + exercise.child("name").getValue());
                    }


                    //System.out.println(student.getWeight());
                    //loadFragment(student);
                    loadCoachInfo(student.getCoach());
                    mDatabase.removeEventListener(this);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });

    }

    private void getCoachInfo(String email){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query myTopPostsQuery = mDatabase.child("users").orderByChild("email").equalTo(email);

        myTopPostsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.w(TAG, "loadCoach:onDataChange");
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                    //System.out.println(dataSnapshot.getValue());
                    //User u = postSnapshot.getValue(User.class);
                    coach = postSnapshot.getValue(Coach.class);
                    coach.setId(postSnapshot.getKey());

                }


                loadStudentsInfo(coach);
                mDatabase.removeEventListener(this);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });

    }

    private void loadFragment(Student student){

        Bundle bundle = new Bundle();
        bundle.putDouble("weight", student.getLastWeight());

        MainMenuFragment mainMenuFragment = new MainMenuFragment();
        mainMenuFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(menuFragmentTag)
                .replace(R.id.frame, mainMenuFragment, menuFragmentTag)
                .commit();
    }

    private void loadCoachFragment(Coach coach){

        Bundle bundle = new Bundle();
        final ArrayList<String> students_name = new ArrayList<>();
        for(Student s : coach.getStudents()){
            students_name.add(s.getName());
        }

        bundle.putStringArrayList("students", students_name);

        StudentListFragment studentListFragment = new StudentListFragment();
        studentListFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(studentListFragmentTag)
                .replace(R.id.frame, studentListFragment, studentListFragmentTag)
                .commit();
    }

    private void loadStudentsInfo(final Coach coach){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query myTopPostsQuery = mDatabase.child("users").orderByChild("coach").equalTo(coach.getId());
        final ArrayList<Student> student_list = coach.getStudents();
        myTopPostsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.w(TAG, "loadCoach:onDataChange");

                coach.setStudents(new ArrayList<Student>());
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Student s = postSnapshot.child("measures").getValue(Student.class);
                    s.setId(postSnapshot.getKey());
                    s.setName(postSnapshot.child("name").getValue().toString());
                    s.setPhone(postSnapshot.child("phone").getValue().toString());

                    for (DataSnapshot exercise: postSnapshot.child("exercises").child("current").getChildren()) {
                        Object o = exercise.getValue();
                        s.getExercises().add(o.toString());
                        //System.out.println("Ex " + exercise.child("name").getValue());
                    }

                    for (DataSnapshot measure: postSnapshot.child("measures").child("history").getChildren()) {
                        s.getMeasures_history().add(new Measure(
                                measure.child("date").getValue().toString(),
                                Double.parseDouble(measure.child("weight").getValue().toString()),
                                Double.parseDouble(measure.child("fat").getValue().toString()),
                                Double.parseDouble(measure.child("muscle").getValue().toString())
                        ));
                        //System.out.println("Ex " + exercise.child("name").getValue());
                    }

                    coach.getStudents().add(s);


                }

                System.out.println(coach.getStudents().size());
                loadCoachFragment(coach);
                //mDatabase.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    private void loadCoachInfo(String id){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query myTopPostsQuery = mDatabase.child("users").orderByKey().equalTo(id);
        //final ArrayList<Student> student_list = coach.getStudents();
        myTopPostsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.w(TAG, "loadCoach:onDataChange");

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    //Student s = postSnapshot.child("measures").getValue(Student.class);
                    //student.setCoach_name(postSnapshot.getKey());
                    student.setCoach_name(postSnapshot.child("name").getValue().toString());
                    student.setCoach_phone(postSnapshot.child("phone").getValue().toString());

                }

                System.out.println(student.getCoach_name() + " | " + student.getCoach_phone());
                if (student.getCoach_name() == null)
                    coach_name.setText("Coach Not Assigned");
                else
                    coach_name.setText("Coach: " + student.getCoach_name());
                //coach_name.setText("Coach: " + student.getCoach_name());
                student_name.setText(student.getName());
                loadFragment(student);
                mDatabase.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    private void addCoach(){
        final AlertDialog.Builder inputAlert = new AlertDialog.Builder(this);
        inputAlert.setTitle("Linking the Student and Coach");
        inputAlert.setMessage("Insert you coach email");
        final EditText userInput = new EditText(getBaseContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(10,5,10,5);
        userInput.setLayoutParams(lp);
        inputAlert.setView(userInput);
        inputAlert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInputValue = userInput.getText().toString();
                Toast.makeText(getBaseContext(), userInputValue + " updated", Toast.LENGTH_SHORT).show();
                updateCoach(userInputValue);
            }
        });
        inputAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = inputAlert.create();
        alertDialog.show();
    }

    private void updateCoach(String email){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query myTopPostsQuery = mDatabase.child("users").orderByChild("email").equalTo(email);

        // [START basic_query_value_listener]
        // My top posts by number of stars
        myTopPostsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.w(TAG, "loadPost:onDataChange");
                String coach_id = "";
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    coach_id = postSnapshot.getKey();
                }

                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("users/"+student.getId()).child("coach").setValue(coach_id);
                mDatabase.removeEventListener(this);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    private void updateNode(String id, int fat, int weight, int muscle){
        student.getMeasures_history().add(new Measure("2019-04-14", weight, fat, muscle));

        mDatabase = FirebaseDatabase.getInstance().getReference();


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                //Post post = dataSnapshot.getValue(Post.class);
                // ...
                //Snackbar.make(R.id., "Measures Added", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                //Toast.makeText(getBaseContext(), "New measure added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addListenerForSingleValueEvent(postListener);
        mDatabase.child("users").child(id).child("measures").child("history").setValue(student.getMeasures_history());

    }

    @Override
    public void onStudentListFragmentInteraction(int position) {
        Toast.makeText(getBaseContext(), position + " clicked", Toast.LENGTH_SHORT).show();

        Bundle bundle = new Bundle();
        Student s = coach.getStudents().get(position);
        ArrayList<String> student_exercise = s.getExercises();

        bundle.putStringArrayList("student_exercise", student_exercise);
        bundle.putDouble("weight", s.getMeasures_history().get(0).getWeight());
        bundle.putDouble("fat", s.getMeasures_history().get(0).getFat());
        bundle.putDouble("muscle", s.getMeasures_history().get(0).getMuscle());
        bundle.putString("name", s.getName());
        bundle.putString("phone", s.getPhone());
        bundle.putString("id", s.getId());

        CoachStudentInfoFragment coachStudentInfoFragment = new CoachStudentInfoFragment();
        coachStudentInfoFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(coachStudentInfoFragmentTag)
                .replace(R.id.frame, coachStudentInfoFragment, coachStudentInfoFragmentTag)
                .commit();
    }

    @Override
    public void onFragmentInteraction(String ex) {
        Toast.makeText(getBaseContext(), ex + " clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentInteractionButton(final String id, final String exe) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Query myTopPostsQuery = mDatabase.child("users").orderByKey().equalTo(id);

        myTopPostsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              Log.w(TAG, "loadExer:onDataChange");
              //Map<String, String> ex = new HashMap<>();
              List<String> ex = new ArrayList<>();
              for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                  for (DataSnapshot exercise : postSnapshot.child("exercises").child("current").getChildren()) {
                      ex.add(exercise.getValue().toString());
                      //System.out.println("Ex " + exercise.child("name").getValue());
                  }

              }

              ex.add(exe);

              mDatabase = FirebaseDatabase.getInstance().getReference();
              mDatabase.child("users/"+id).child("exercises").child("current").setValue(ex);
              Toast.makeText(getBaseContext(), exe + " added", Toast.LENGTH_SHORT).show();
              mDatabase.removeEventListener(this);

          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });


    }

    @Override
    public void onExerciseListFragmentInteraction(String item) {

        final AlertDialog.Builder inputAlert = new AlertDialog.Builder(this);
        inputAlert.setTitle(item);
        inputAlert.setMessage("Today's input");
        final EditText userInput = new EditText(getBaseContext());
        inputAlert.setView(userInput);
        inputAlert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInputValue = userInput.getText().toString();
                Toast.makeText(getBaseContext(), userInputValue + " updated", Toast.LENGTH_SHORT).show();
            }
        });
        inputAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = inputAlert.create();
        alertDialog.show();
    }

    @Override
    public void onWeightListFragmentInteraction(Context ctx) {

        final AlertDialog.Builder inputAlert = new AlertDialog.Builder(this);
        inputAlert.setTitle("New Measure");
        inputAlert.setMessage("Enter your measures");

        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.alert_dialog_weight, null);
        inputAlert.setView(v);
        final EditText userInputW = v.findViewById(R.id.et_weight_input);
        final EditText userInputF = v.findViewById(R.id.et_fat_input);
        final EditText userInputM = v.findViewById(R.id.et_muscle_input);

        inputAlert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int userInputValueW = Integer.valueOf(userInputW.getText().toString());
                int userInputValueF = Integer.valueOf(userInputF.getText().toString());
                int userInputValueM = Integer.valueOf(userInputM.getText().toString());
                //Toast.makeText(getBaseContext(), userInputValueW + " " + userInputValueF + " " + userInputValueM + " updated", Toast.LENGTH_SHORT).show();
                updateNode(student.getId(), userInputValueF, userInputValueW, userInputValueM);
            }
        });
        inputAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = inputAlert.create();
        alertDialog.show();
    }

    @Override
    public void onProfileFragmentInteraction(int age, double weight, double weight_goal) {

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users/"+student.getId()).child("age").setValue(age);
        mDatabase.child("users/"+student.getId()).child("weight").setValue(weight);
        mDatabase.child("users/"+student.getId()).child("weight_goal").setValue(weight_goal);

    }
}
