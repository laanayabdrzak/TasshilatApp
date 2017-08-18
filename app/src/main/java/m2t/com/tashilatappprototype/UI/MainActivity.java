package m2t.com.tashilatappprototype.UI;

import android.app.Fragment;
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

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.Common.utils.Utils;
import m2t.com.tashilatappprototype.UI.AccountsPayment.AccountPaymentFragment;
import m2t.com.tashilatappprototype.UI.Accueil.AccueilActivity;
import m2t.com.tashilatappprototype.UI.BillsPayment.BillsPaymentFragment;
import m2t.com.tashilatappprototype.UI.ChangePwd.ChangePwdFragment;
import m2t.com.tashilatappprototype.UI.ContactUs.ContactUsFragment;
import m2t.com.tashilatappprototype.UI.Depot.DepotFragment;
import m2t.com.tashilatappprototype.UI.FAQ.FAQFragment;
import m2t.com.tashilatappprototype.UI.Favoris.FavorisFragment;
import m2t.com.tashilatappprototype.UI.Home.HomeFragment;
import m2t.com.tashilatappprototype.UI.News.NewsFragment;
import m2t.com.tashilatappprototype.UI.Notifications.NotificationsFragment;
import m2t.com.tashilatappprototype.UI.Recharge.RechargeFragment;
import m2t.com.tashilatappprototype.UI.Settings.SettingsActivity;
import m2t.com.tashilatappprototype.UI.Solde.SoldeFragment;
import m2t.com.tashilatappprototype.UI.TicketsPayment.TicketsFragment;
import m2t.com.tashilatappprototype.UI.Transfert.TransfertFragment;

public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener, DrawerLocker {

	NavigationView navigationView;
	DrawerLayout drawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("Tasshilat");
		setSupportActionBar(toolbar);
		invalidateOptionsMenu();

		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
				R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		getFragmentManager().beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();

		navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem item = menu.findItem(R.id.action_log_out);
		if (getIntent().getStringExtra("FLAG").equals("client"))
			item.setVisible(true);
		else {
			item.setVisible(false);
			hideItemMenu();
		}
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
			startActivity(new Intent(MainActivity.this, AccueilActivity.class));
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();
		Fragment fragment = null;
		boolean replaceFragment = true;
		switch (id) {
		case R.id.nav_user_info:

			break;
		case R.id.nav_favoris:
			fragment = new FavorisFragment();
			break;
		case R.id.nav_activer_bloquer:

			break;
		case R.id.nav_change_pwd:
			fragment = new ChangePwdFragment();
			break;
		case R.id.nav_solde:
			fragment = new SoldeFragment();
			break;
		case R.id.nav_depot:
			fragment = new DepotFragment();
			break;
		case R.id.nav_transfert_payment_account:
			fragment = new TransfertFragment();
			break;
		case R.id.nav_manage_accounts:
			fragment = new AccountPaymentFragment();
			break;
		case R.id.nav_history_accounts:

			break;
		case R.id.nav_payment_bills:
			fragment = new BillsPaymentFragment();
			break;
		case R.id.nav_recharge:
			fragment = new RechargeFragment();
			break;
		case R.id.nav_transfert:
			fragment = new TransfertFragment();
			break;
		case R.id.nav_achat_biller:
			fragment = new TicketsFragment();
			break;
		case R.id.nav_commercant_payment:

			break;
		case R.id.nav_history_transaction:

			break;
		case R.id.nav_news:
			fragment = new NewsFragment();
			break;
		case R.id.nav_faq:
			fragment = new FAQFragment();
			break;
		case R.id.nav_contact_us:
			fragment = new ContactUsFragment();
			break;
		case R.id.nav_notifications:
			fragment = new NotificationsFragment();
			break;
		}
		Utils.replaceFragement(fragment, MainActivity.this);
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	private void hideItemMenu() {
		navigationView = (NavigationView) findViewById(R.id.nav_view);
		Menu nav_Menu = navigationView.getMenu();
		nav_Menu.findItem(R.id.nav_favoris).setVisible(false);
		nav_Menu.findItem(R.id.nav_user_info).setVisible(false);
		nav_Menu.findItem(R.id.nav_change_pwd).setVisible(false);
		nav_Menu.findItem(R.id.nav_activer_bloquer).setVisible(false);
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
	public void setDrawerLocked(boolean shouldLock);
}
