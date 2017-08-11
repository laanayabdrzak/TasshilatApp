package m2t.com.tashilatappprototype.UI.Accueil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import m2t.com.tashilatappprototype.R;
import m2t.com.tashilatappprototype.UI.MainActivity;
import m2t.com.tashilatappprototype.UI.SignIn.SignInActivity;

public class AccueilActivity extends AppCompatActivity {

    private TextView txtOne, txtVTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        txtVTwo = (TextView) findViewById(R.id.txt_two);
        txtOne = (TextView) findViewById(R.id.txt_one);


        txtOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccueilActivity.this, MainActivity.class);
                i.putExtra("FLAG", "notClient");
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        txtVTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccueilActivity.this, SignInActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

    }
}
