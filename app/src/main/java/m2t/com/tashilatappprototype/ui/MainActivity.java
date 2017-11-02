package m2t.com.tashilatappprototype.ui;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.common.pojo.LogOutResponse;
import m2t.com.tashilatappprototype.common.utils.SessionManager;
import m2t.com.tashilatappprototype.common.utils.Utils;
import m2t.com.tashilatappprototype.data.local.DatabaseHandler;
import m2t.com.tashilatappprototype.data.remote.ApiClient;
import m2t.com.tashilatappprototype.data.remote.ApiInterface;
import m2t.com.tashilatappprototype.ui.accountsPayment.AccountPaymentFragment;
import m2t.com.tashilatappprototype.ui.billsPayment.BillsPaymentFragment;
import m2t.com.tashilatappprototype.ui.changePwd.ChangePwdFragment;
import m2t.com.tashilatappprototype.ui.contactUs.ContactUsFragment;
import m2t.com.tashilatappprototype.ui.faq.FAQFragment;
import m2t.com.tashilatappprototype.ui.history.HistoryFragment;
import m2t.com.tashilatappprototype.ui.home.HomeFragment;
import m2t.com.tashilatappprototype.ui.news.NewsFragment;
import m2t.com.tashilatappprototype.ui.notifications.NotificationsFragment;
import m2t.com.tashilatappprototype.ui.recharge.RechargeFragment;
import m2t.com.tashilatappprototype.ui.settings.SettingsActivity;
import m2t.com.tashilatappprototype.ui.solde.SoldeFragment;
import m2t.com.tashilatappprototype.ui.ticketsPayment.TicketsFragment;
import m2t.com.tashilatappprototype.ui.transfert.TransfertFragment;
import m2t.com.tashilatappprototype.ui.userInfo.UserInfoFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener, DrawerLocker {

    ActionBarDrawerToggle toggle;
	NavigationView navigationView;
	DrawerLayout drawer;
	SessionManager sessionManager;
	DatabaseHandler db;
	TextView toolbarTitle;
    private boolean mToolBarNavigationListenerIsRegistered = false;
    //Setting default colors for menu item Text and Icon
    int navDefaultTextColor = Color.parseColor("#EF6C00");


    //Defining ColorStateList for menu item Text
    ColorStateList navMenuTextList = new ColorStateList(
            new int[][]{
                    new int[]{android.R.attr.state_checked},
                    new int[]{android.R.attr.state_enabled},
                    new int[]{android.R.attr.state_pressed},
                    new int[]{android.R.attr.state_focused},
                    new int[]{android.R.attr.state_pressed}
            },
            new int[] {
                    R.color.primary_darker,
                    navDefaultTextColor,
                    navDefaultTextColor,
                    navDefaultTextColor,
                    navDefaultTextColor
            }
    );

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("");
        toolbar.setSubtitle("");
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);


		setSupportActionBar(toolbar);
		invalidateOptionsMenu();

		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
				R.string.navigation_drawer_close);
        // Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(toggle);
        // Calling sync state is necessary to show your hamburger icon...
        // or so I hear. Doesn't hurt including it even if you find it works
        // without it on your test device(s)
        toggle.syncState();

		//getFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();
        Utils.replaceFragement(new HomeFragment(), MainActivity.this);

		navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		controlOnUser();
	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
            Utils.replaceFragement(new HomeFragment(), MainActivity.this);
			//super.onBackPressed();
		}

        /*if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem item = menu.findItem(R.id.action_log_out);
		MenuItem item2 = menu.findItem(R.id.action_add);
		if (getIntent().getStringExtra("FLAG").equals("client"))
			item.setVisible(true);
		else {
			item.setVisible(false);
			hideItemMenu();
		}
		item2.setVisible(false);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		switch (id) {
		case R.id.action_settings:
			startActivity(new Intent(MainActivity.this, SettingsActivity.class));
			break;
		case R.id.action_log_out:
            logOututUser();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		int id = item.getItemId();
		Fragment fragment = null;
        Bundle bundle = new Bundle();
		switch (id) {
		case R.id.nav_user_info:
			fragment = new UserInfoFragment();
			break;
		/*case R.id.nav_favoris:
			fragment = new FavorisFragment();
            bundle.putString("nav", "");
			break;*/
		/*case R.id.nav_activer_bloquer:

			break;*/
		case R.id.nav_change_pwd:
			fragment = new ChangePwdFragment();
            bundle.putString("nav", "");
			break;
		case R.id.nav_solde:
			fragment = new SoldeFragment();
            bundle.putString("nav", "");
			break;
		/*case R.id.nav_depot:
			fragment = new DepotFragment();
            bundle.putString("nav", "");
			break;*/
		case R.id.nav_transfert_payment_account:
			fragment = new TransfertFragment();
            bundle.putString("nav", "");
			break;
		case R.id.nav_manage_accounts:
			fragment = new AccountPaymentFragment();
            bundle.putString("nav", "");
			break;
		case R.id.nav_history_accounts:
			fragment = new HistoryFragment();
            bundle.putString("nav", "history_accounts");
			break;
		case R.id.nav_payment_bills:
			fragment = new BillsPaymentFragment();
            bundle.putString("nav", "");
			break;
		case R.id.nav_recharge:
			fragment = new RechargeFragment();
            bundle.putString("nav", "");
			break;
		case R.id.nav_transfert:
			fragment = new TransfertFragment();
            bundle.putString("nav", "");
			break;
		case R.id.nav_achat_biller:
			fragment = new TicketsFragment();
            bundle.putString("nav", "");
			break;
		case R.id.nav_commercant_payment:

			break;
		case R.id.nav_history_transaction:
			fragment = new HistoryFragment();
            bundle.putString("nav", "history_trans");
			break;
		case R.id.nav_news:
			fragment = new NewsFragment();
            bundle.putString("nav", "");
			break;
		case R.id.nav_faq:
			fragment = new FAQFragment();
            bundle.putString("nav", "");
			break;
		case R.id.nav_contact_us:
			fragment = new ContactUsFragment();
            bundle.putString("nav", "");
			break;
		case R.id.nav_notifications:
			fragment = new NotificationsFragment();
            bundle.putString("nav", "");
			break;
        default:
            fragment = new HomeFragment();
            break;
		}
		Utils.replaceFragement(fragment, MainActivity.this);
        fragment.setArguments(bundle);
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	private void hideItemMenu() {
		navigationView = (NavigationView) findViewById(R.id.nav_view);
		Menu nav_Menu = navigationView.getMenu();
		//nav_Menu.findItem(R.id.nav_favoris).setVisible(false);
		nav_Menu.findItem(R.id.nav_user_info).setVisible(false);
		nav_Menu.findItem(R.id.nav_change_pwd).setVisible(false);
		//nav_Menu.findItem(R.id.nav_activer_bloquer).setVisible(false);
	}

	@Override
	public void setDrawerLocked(boolean shouldLock) {
		if (shouldLock) {
			drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		} else {
			drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
		}
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
            this.setDrawerLocked(true);
            // when DrawerToggle is disabled i.e. setDrawerIndicatorEnabled(false), navigation icon
            // clicks are disabled i.e. the UP button will not work.
            // We need to add a listener, as in below, so DrawerToggle will forward
            // click events to this listener.
            if(!mToolBarNavigationListenerIsRegistered) {
                toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getFragmentManager().getBackStackEntryCount() == 0) {
                            moveTaskToBack(false);
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
            // Show hamburger
            toggle.setDrawerIndicatorEnabled(true);
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

    public void setActionBarTitle(int id) {
        getSupportActionBar().setTitle(id);
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

	private void logOututUser() {
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
			}

			@Override
			public void onFailure(Call<LogOutResponse> call, Throwable t) {
				Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
			}
		});
	}
}

interface DrawerLocker {
    void setDrawerLocked(boolean shouldLock);
}
