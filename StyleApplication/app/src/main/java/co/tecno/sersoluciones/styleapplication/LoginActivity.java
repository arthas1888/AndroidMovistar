package co.tecno.sersoluciones.styleapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import co.tecno.sersoluciones.styleapplication.utilities.MyPreferences;

public class LoginActivity extends AppCompatActivity {

    EditText editText;
    private MyPreferences myPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText = (EditText) findViewById(R.id.editTextUsername);
        myPreferences = new MyPreferences(this);
        if (myPreferences.isLogin()) startMainActivity();
    }

    public void login(View view) {
        String textUsername = editText.getText().toString();
        if (textUsername.isEmpty()){
            editText.setError("Campo Username obligatorio");
            return;
        }
        myPreferences.setUsername(textUsername);
        myPreferences.setStateLogin(true);
        startMainActivity();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
