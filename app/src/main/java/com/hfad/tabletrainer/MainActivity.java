package com.hfad.tabletrainer;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
  //  Dialog dialog;
    //The navigationview
    private NavigationView navigationView;
    //our layout for the navigationdrawer
    private DrawerLayout drawerLayout;
    private Timer timer;
    TextView textViewTime;
    static TextView textViewPoint;
    private int min, sec;

    static Table table = new Table(1);
    static Time  time  =  new Time(10);
    static int   lastTabSel = 1, lastTimSel = 2;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;


        //make a new timer
        timer = new Timer();
        timer.schedule(new TimerTask() {
            //   @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 1000);
        // Initializing Toolbar and setting it as the actionbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewPoint = findViewById(R.id.Point);
        table.setPoint(0);
        textViewPoint.setText("Point: "+table.getPoint());

        //Initializing NavigationView
        navigationView = findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener()
                {

                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                        //Checking if the item is in checked state or not, if not make it in checked state
                        if (menuItem.isChecked())
                            menuItem.setChecked(false);
                        else
                            menuItem.setChecked(true);

                        //Closing drawer on item click
                        drawerLayout.closeDrawers();

                        //Check to see which item was being clicked and perform appropriate action
                        Fragment fragment = null;
                        String title= "";
                        switch (menuItem.getItemId()) {

                            //Replacing the main content with ContentFragment
                            case R.id.main:
                                fragment = new HomeFragment();
                                title = getResources().getString(R.string.hjem);
                                break;
                            case R.id.tabel_1:
                                fragment = new Table1Fragment();
                                title = getResources().getString(R.string.tabel_1);
                                MainActivity.table.setTable(1);
                                MainActivity.table.createAnswers();
                                lastTabSel = 0;
                                break;
                            case R.id.tabel_2:
                                fragment = new Table2Fragment();
                                title = getResources().getString(R.string.tabel_2);
                                MainActivity.table.setTable(2);
                                MainActivity.table.createAnswers();
                                lastTabSel = 1;
                                break;
                            case R.id.tabel_3:
                                fragment = new Table3Fragment();
                                title = getResources().getString(R.string.tabel_3);
                                MainActivity.table.setTable(3);
                                MainActivity.table.createAnswers();
                                lastTabSel = 2;
                                break;
                            case R.id.tabel_4:
                                fragment = new Table4Fragment();
                                title = getResources().getString(R.string.tabel_4);
                                MainActivity.table.setTable(4);
                                MainActivity.table.createAnswers();
                                lastTabSel = 3;
                                break;
                            case R.id.tabel_5:
                                fragment = new Table5Fragment();
                                title = getResources().getString(R.string.tabel_5);
                                MainActivity.table.setTable(5);
                                MainActivity.table.createAnswers();
                                lastTabSel = 4;
                                break;
                            case R.id.tabel_6:
                                fragment = new Table6Fragment();
                                title = getResources().getString(R.string.tabel_6);
                                MainActivity.table.setTable(6);
                                MainActivity.table.createAnswers();
                                lastTabSel = 5;
                                break;
                            case R.id.tabel_7:
                                fragment = new Table7Fragment();
                                title = getResources().getString(R.string.tabel_7);
                                MainActivity.table.setTable(7);
                                MainActivity.table.createAnswers();
                                lastTabSel = 6;
                                break;
                            case R.id.tabel_8:
                                fragment = new Table8Fragment();
                                title = getResources().getString(R.string.tabel_8);
                                MainActivity.table.setTable(8);
                                MainActivity.table.createAnswers();
                                lastTabSel = 7;
                                break;
                            case R.id.tabel_9:
                                fragment = new Table9Fragment();
                                title = getResources().getString(R.string.tabel_9);
                                MainActivity.table.setTable(9);
                                MainActivity.table.createAnswers();
                                lastTabSel = 8;
                                break;
                            case R.id.tabel_10:
                                fragment = new Table10Fragment();
                                title = getResources().getString(R.string.tabel_10);
                                MainActivity.table.setTable(10);
                                MainActivity.table.createAnswers();
                                lastTabSel = 9;
                                break;
                            case R.id.trainer:
                                fragment = new TrainerHomeFragment();
                                title = getResources().getString(R.string.tr_n);
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();

                                return true;

                        } //after switch
                        if (fragment != null) {
                            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame, fragment);
                            fragmentTransaction.commit();
                            getSupportActionBar().setTitle(title); //set the title of the action bar
                        }
                        return true;
                    }
                });  //end of nagivation drawer code

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = findViewById(R.id.drawer);

        //Showing how to override onDrawerClosed and onDrawerOpened
        //although in this app we actually dont do anything in there
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // doing the fragment transaction here - replacing frame with HomeFragment -
        //which is the startup fragment in the app.
        fragmentTransaction.replace(R.id.frame, new HomeFragment());
        fragmentTransaction.commit(); //set starting fragment

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            //no settings activity - code does not do anything
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        this.runOnUiThread(Timer_Tick);

    }
    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            String minStr, secStr;
            textViewTime = findViewById(R.id.timerView);

            //This method runs in the same thread as the UI.
            // so we can draw
            if (time.isRun())
            {
                min = time.getMinutes();
                sec = time.getSeconds();
                if(min==0&&sec==0)
                {
                    time.setRun(false);
                    Dialog dialog;
                    dialog = new Dialog(context);
                    dialog.setTitle(R.string.Done); //hardcoded - should be in strings.xml!
                    dialog.setContentView(R.layout.time_done);
                    dialog.show();
                }
                else
                {
                    if(sec>0)
                    {
                        sec--;
                        time.setSeconds(sec);
                    }
                    else
                    {
                        time.setSeconds(60);
                        time.setMinutes(time.getMinutes()-1);
                    }
                }

                //update the counter - notice this is NOT seconds in this example
                //you need TWO counters - one for the time and one for the
                if(time.getMinutes()>=10)
                {
                    minStr = "" + time.getMinutes();
                }
                else
                {
                    minStr = "0" + time.getMinutes();
                }
                if(time.getSeconds()>=10)
                {
                    secStr = "" + time.getSeconds();
                }
                else
                {
                    secStr = "0" + time.getSeconds();
                }
                textViewTime.setText(minStr+":"+secStr);

            }

        }
    };
}