package com.skullybunny.medinoteservices.medinote.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.skullybunny.medinoteservices.medinote.fragments.BaseFragment;
import com.skullybunny.medinoteservices.medinote.fragments.CreateMedicalNoteFragment;
import com.skullybunny.medinoteservices.medinote.authenticator.CurrentUser;
import com.skullybunny.medinoteservices.medinote.fragments.DoctorRegistrationFragment;
import com.skullybunny.medinoteservices.medinote.R;

import com.skullybunny.medinoteservices.medinote.fragments.ContactUsFragment;
import com.skullybunny.medinoteservices.medinote.fragments.HelpFragment;
import com.skullybunny.medinoteservices.medinote.fragments.StudentRegistrationFragment;
import com.skullybunny.medinoteservices.medinote.fragments.CheckMedicalNoteByMENFragment;

public class Navigation extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        removeNavigationMenuItemsByRights(navigationView);
        Fragment homeFragment = getHomeFragment();
        changeFragment(homeFragment, false);

        //Must be commented out or removed for production!!!
        //Toast.makeText(this, CurrentUser.getUser().getAuthorization(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, CurrentUser.getUser().getAccountType(), Toast.LENGTH_SHORT).show();
    }

    private void removeNavigationMenuItemsByRights(NavigationView navigationView)
    {
        String accountType = CurrentUser.getUser().getAccountType();
        Menu navigationMenu = navigationView.getMenu();

        if (accountType.equals("Doctor"))
        {
            navigationMenu.findItem(R.id.nav_register_doctor).setVisible(false);
        }
        else if (accountType.equals("Student"))
        {
            navigationMenu.findItem(R.id.nav_create_medicalnote).setVisible(false);
            navigationMenu.findItem(R.id.nav_register_doctor).setVisible(false);
            navigationMenu.findItem(R.id.nav_register_student).setVisible(false);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Fragment homeFragment = getHomeFragment();
            changeFragment(homeFragment, true);
        } else if (id == R.id.nav_register_student) {
            Fragment registerStudentFragment = new StudentRegistrationFragment();
            changeFragment(registerStudentFragment, true);
        } else if(id == R.id.nav_register_doctor) {
            Fragment registerDoctorFragment = new DoctorRegistrationFragment();
            changeFragment(registerDoctorFragment, true);
        } else if(id == R.id.nav_check_medicalnote_by_men) {
            Fragment checkMediNoteByMENFragment = new CheckMedicalNoteByMENFragment();
            changeFragment(checkMediNoteByMENFragment, true);
        } else if(id == R.id.nav_create_medicalnote) {
            Fragment createMedicalNoteFragment = new CreateMedicalNoteFragment();
            changeFragment(createMedicalNoteFragment, true);
        } else if (id == R.id.nav_inf) {
            Fragment helpFragment;
            helpFragment= new HelpFragment();
            changeFragment(helpFragment, true);
        } else if (id == R.id.nav_contact_us) {
            Fragment contactFragment;
            contactFragment= new ContactUsFragment();
            changeFragment(contactFragment, true);
        } else if (id == R.id.nav_logout) {
            logOutUser();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Fragment getHomeFragment()
    {
        String accountType = CurrentUser.getUser().getAccountType();
        Fragment homeFragment;
        if (accountType.equals("Admin"))
        {
            homeFragment = new DoctorRegistrationFragment();
        }
        else if (accountType.equals("Doctor"))
        {
            homeFragment = new CreateMedicalNoteFragment();
        }
        else
        {
            homeFragment = new CheckMedicalNoteByMENFragment();
        }
        return homeFragment;
    }

    public void logOutUser()
    {
        CurrentUser.logOut(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
