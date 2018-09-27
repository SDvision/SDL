package audioapk.com.example.android.farmertofarmer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class LogIn extends AppCompatActivity {

    public static final String LOGIN = "login",
                                STATE = "state",
                                DISTRICT = "district",
            SHARED_FILE = "com.example.android.farmertofarmer.loginId";


    private EditText farmerIdEditText,farmerPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        farmerIdEditText = findViewById(R.id.login_farmerID);
        farmerPassword = findViewById(R.id.login_password);


    }


    public void loginClicked(View view) {

        if (farmerIdEditText.getText().toString().trim().equals("")){
            farmerIdEditText.setError("Id could't be empty");
            return;
        }
        if (farmerPassword.getText().toString().equals("")){
            farmerPassword.setError("Password could't be empty");
            return;
        }
        //TODO(a-1) database conditions should be addressed here to check login and password is correct

        Intent intent = new Intent(this,Profile.class);
        intent.putExtra("farmerId",farmerIdEditText.getText().toString().trim());
        finish();
        startActivity(intent);


    }
}
