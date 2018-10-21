package audioapk.com.example.android.farmertofarmer.LogIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import audioapk.com.example.android.farmertofarmer.R;

public class LogIn extends AppCompatActivity implements View.OnFocusChangeListener {

    private LogInDatabase logInDatabase;

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
        logInDatabase = new LogInDatabase(this);

        farmerIdEditText.setOnFocusChangeListener(this);
        farmerPassword.setOnFocusChangeListener(this);


    }


    public void signUpClicked(View view) {

        String userName = farmerIdEditText.getText().toString().trim();
        String password = farmerPassword.getText().toString();

        if (userName.equals("")){
            farmerIdEditText.setError("Id could't be empty");
            return;
        }
        if (logInDatabase.isLogInExist(userName)){
            farmerIdEditText.setError("Id already exists");
            Toast.makeText(this,"Id already exists",Toast.LENGTH_LONG).show();
            return;
        }
        if (password.trim().equals("")){
            farmerPassword.setError("Password could't be empty");
            return;
        }

        logInDatabase.insert(userName,password);
        Toast.makeText(this,"SignUp successful",Toast.LENGTH_LONG).show();
        //TODO(a-1) database conditions should be addressed here to check login and password is correct

        Intent intent = new Intent(this,Profile.class);
        intent.putExtra("farmerId",userName);
        finish();
        startActivity(intent);


    }

    public void logInClicked(View view) {

        String userName = farmerIdEditText.getText().toString().trim();
        String password = farmerPassword.getText().toString();

        if (userName.equals("")){
            farmerIdEditText.setError("Id could't be empty");
            return;
        }
        if (password.trim().equals("")){
            farmerPassword.setError("Password could't be empty");
            return;
        }
        if (!logInDatabase.isLogInExist(userName)){
            farmerIdEditText.setError("Id does not exists");
            Toast.makeText(this,"Id does not exists",Toast.LENGTH_LONG).show();
            return;
        }
        if (!logInDatabase.checkPassword(userName,password)){

            farmerPassword.setError("Password is incorrect");
            Toast.makeText(this,"Please check your password",Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this,"SignUp successful",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,Profile.class);
        intent.putExtra("farmerId",userName);
        finish();
        startActivity(intent);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus){
            if (((EditText)v).getText().toString().trim().equals("")){
                ((EditText)v).setError("Could't be empty");
            }
        }
    }
}
