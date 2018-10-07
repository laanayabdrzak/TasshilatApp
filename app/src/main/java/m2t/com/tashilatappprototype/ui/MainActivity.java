package m2t.com.tashilatappprototype.ui;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

import java.util.HashMap;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.common.pojo.LogOutResponse;
import m2t.com.tashilatappprototype.common.utils.SessionManager;
import m2t.com.tashilatappprototype.common.utils.Utility;
import m2t.com.tashilatappprototype.data.local.DatabaseHandler;
import m2t.com.tashilatappprototype.data.remote.ApiClient;
import m2t.com.tashilatappprototype.data.remote.ApiInterface;
import m2t.com.tashilatappprototype.ui.comptes.ComptesFragment;
import m2t.com.tashilatappprototype.ui.dashboard.DashboardFragment;
import m2t.com.tashilatappprototype.ui.help.HelpFragment;
import m2t.com.tashilatappprototype.ui.profil.ProfilFragment;
import m2t.com.tashilatappprototype.ui.services.ServiceFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DrawerLocker{


	public ActionBarDrawerToggle toggle;
	public Toolbar toolbar;
	NavigationView navigationView;
	DrawerLayout drawer;
	SessionManager sessionManager;
	DatabaseHandler db;
	TextView toolbarTitle;
    private boolean mToolBarNavigationListenerIsRegistered = false;
	private BottomNavigationView bottomNavigationView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("");
        toolbar.setSubtitle("");
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);

		setSupportActionBar(toolbar);
		invalidateOptionsMenu();
		drawer = findViewById(R.id.drawer_layout);
		toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
				R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		this.setDrawerLocked(true);
		// Calling sync state is necessary to show your hamburger icon...
		// or so I hear. Doesn't hurt including it even if you find it works
		// without it on your test device(s)
		//toggle.syncState();

		//getFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();
        //Utility.replaceFragement(new HomeFragment(), MainActivity.this);
        Utility.replaceFragement(new DashboardFragment(), MainActivity.this);

		bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

		initiateBottomMenu();
		controlOnUser();
	}


	@Override
	public void onBackPressed() {
		int count = getFragmentManager().getBackStackEntryCount();
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			if (count == 0) {
				super.onBackPressed();
				//additional code
			} else {
				getFragmentManager().popBackStack();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem item = menu.findItem(R.id.action_log_out);
		MenuItem item2 = menu.findItem(R.id.action_add);
		MenuItem item3 = menu.findItem(R.id.action_favoris);
		if (getIntent().getStringExtra("FLAG").equals("client"))
			item.setVisible(true);
		else {
			item.setVisible(false);
		}
		item2.setVisible(false);
		item3.setVisible(false);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		switch (id) {
		/*case R.id.action_settings:
			startActivity(new Intent(MainActivity.this, SettingsActivity.class));
			break;*/
		case R.id.action_log_out:
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

			alertDialogBuilder.setTitle("DECONNEXION");
			alertDialogBuilder
					.setMessage("êtes vous sûr de bien vouloir vous déconnecter ?")
					.setCancelable(false)
					.setPositiveButton("Déconnecter", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							logOutUser();
							finish();
						}
					})
					.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.dismiss();
						}
					});

			// create alert dialog and show it
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();

			break;
		}

		return super.onOptionsItemSelected(item);
	}


    /**
     * To be semantically or contextually correct, maybe change the name
     * and signature of this function to something like:
     *
     * private void showBackButton(boolean show)
     * Just a suggestion.
     */
    public void enableViews(boolean enable) {

        // To keep states of ActionBar and ActionBarDrawerToggle synchronized,
        // when you enable on one, you disable on the other.
        // And as you may notice, the order for this operation is disable first, then enable - VERY VERY IMPORTANT.
		if(enable) {
			// Remove hamburger
			toggle.setDrawerIndicatorEnabled(false);
			// Show back button
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			//this.setDrawerLocked(true);
			// when DrawerToggle is disabled i.e. setDrawerIndicatorEnabled(false), navigation icon
			// clicks are disabled i.e. the UP button will not work.
			// We need to add a listener, as in below, so DrawerToggle will forward
			// click events to this listener.
			if(!mToolBarNavigationListenerIsRegistered) {
				toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (getFragmentManager().getBackStackEntryCount() == 0) {
							onBackPressed();
						} else {
							getFragmentManager().popBackStack();
						}
					}
				});

				mToolBarNavigationListenerIsRegistered = true;
			}

		} else {
			// Remove back button
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			// Remove hamburger
			toggle.setDrawerIndicatorEnabled(false);
			// Remove the/any drawer toggle listener
			toggle.setToolbarNavigationClickListener(null);
			mToolBarNavigationListenerIsRegistered = false;
		}

        // So, one may think "Hmm why not simplify to:
        // .....
        // getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
        // mDrawer.setDrawerIndicatorEnabled(!enable);
        // ......
        // To re-iterate, the order in which you enable and disable views IS important #dontSimplify.
    }
    public void initiateBottomMenu() {
		int[] image = {
				R.drawable.ic_dashboard_black_24dp,
				R.drawable.ic_assignment_black_24dp,
				R.drawable.ic_branding_watermark_black_24dp,
				R.drawable.ic_perso_info_black_24dp,
				R.drawable.ic_help_outline_black_24dp
		};
		int[] color = {
				ContextCompat.getColor(this, R.color.firstColor),
				ContextCompat.getColor(this, R.color.secondColor),
				ContextCompat.getColor(this, R.color.thirdColor),
				ContextCompat.getColor(this, R.color.fourthColor),
				ContextCompat.getColor(this, R.color.fifthColor)
		};

		if (bottomNavigationView != null) {
			bottomNavigationView.isWithText(false);
			// bottomNavigationView.activateTabletMode();
			bottomNavigationView.isColoredBackground(true);
			bottomNavigationView.setTextActiveSize(getResources().getDimension(R.dimen.text_active));
			bottomNavigationView.setTextInactiveSize(getResources().getDimension(R.dimen.text_inactive));
			bottomNavigationView.setItemActiveColorWithoutColoredBackground(ContextCompat.getColor(this, R.color.primary_darker));
			//bottomNavigationView.setFont(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Noh_normal.ttf"));
		}

		BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
				("Accueil", color[0], image[0]);
		BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
				("Services", color[1], image[1]);
		BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
				("Comptes", color[2], image[2]);
		BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
				("Profil", color[3], image[3]);
		BottomNavigationItem bottomNavigationItem4 = new BottomNavigationItem
				("Help", color[4], image[4]);


		bottomNavigationView.addTab(bottomNavigationItem);
		bottomNavigationView.addTab(bottomNavigationItem1);
		bottomNavigationView.addTab(bottomNavigationItem2);
		bottomNavigationView.addTab(bottomNavigationItem3);
		bottomNavigationView.addTab(bottomNavigationItem4);

		bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
			@Override
			public void onNavigationItemClick(int index) {
				switch (index) {
					case 0:
						Utility.replaceFragement(new DashboardFragment(), MainActivity.this);
						break;
					case 1:
						Utility.replaceFragement(new ServiceFragment(), MainActivity.this);
						break;
					case 2:
						Utility.replaceFragement(new ComptesFragment(), MainActivity.this);
						break;
					case 3:
						Utility.replaceFragement(new ProfilFragment(), MainActivity.this);
						break;
					case 4:
						Utility.replaceFragement(new HelpFragment(), MainActivity.this);
						break;
				}
			}
		});
	}
    public void setActionBarTitle(int id, int colorID) {
        getSupportActionBar().setTitle(id);
        getSupportActionBar().setBackgroundDrawable(
        		new ColorDrawable(getResources().getColor(colorID)));
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_add_account_black_24dp);
            //actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);

            toolbarTitle.setText(getResources().getString(id));

        }
    }

	private void controlOnUser() {
		db = new DatabaseHandler(getApplicationContext());
		sessionManager = new SessionManager(getApplicationContext());
	}

	private void logOutUser() {
		ApiInterface apiService =
				ApiClient.getClient().create(ApiInterface.class);
		// get user data from session
		HashMap<String, String> user = sessionManager.getUserDetails();
		Log.d("Shared Pref", user.get(SessionManager.KEY_PHPSESSID) + ";"
				+ user.get(SessionManager.KEY_COOKIE));
		Call<LogOutResponse> call = apiService.setLogOut(user.get(SessionManager.KEY_PHPSESSID) + ";"
				+ user.get(SessionManager.KEY_COOKIE));
		call.enqueue(new Callback<LogOutResponse>() {
			@Override
			public void onResponse(Call<LogOutResponse> call, Response<LogOutResponse> response) {
				//startActivity(new Intent(MainActivity.this, AccueilActivity.class));
				sessionManager.logoutUser();
				db.deleteAllOperators();
				db.deleteAllOperatorsFAV();
			}

			@Override
			public void onFailure(Call<LogOutResponse> call, Throwable t) {
				Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public void setDrawerLocked(boolean shouldLock) {
		if (shouldLock) {
			drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		} else {
			drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

		}
	}
}
interface DrawerLocker {
	void setDrawerLocked(boolean shouldLock);
}
