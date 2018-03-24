package hackathon.unscript.com.ams;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SPLoginActivity extends AppCompatActivity {
    static SharedPreferences sharedPreferences;
    static ProgressBar progressBar;
    EditText l_name, l_password;
    String username, password, userId;
    CheckBox showPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splogin);
        Bundle bundle;
        bundle = getIntent().getExtras();
        sharedPreferences = getApplicationContext().getSharedPreferences("userinfo", 0);

        if (BackgroundTask.backgroundToast != null)
            BackgroundTask.backgroundToast.cancel();
//        if (PostLoginActivity.networkToast != null)
//            PostLoginActivity.networkToast.cancel();
//        if (fetchData.predictionToast != null)
//            fetchData.predictionToast.cancel();
//        if (fetchData.oopsToast != null)
//            fetchData.oopsToast.cancel();
//        if (fetchData.alreadyToast != null)
//            fetchData.alreadyToast.cancel();

        l_name = findViewById(R.id.eusername);
        l_password = findViewById(R.id.epassword);
        showPassword = findViewById(R.id.showPassword);
        progressBar = findViewById(R.id.progressBar);
//        if (bundle != null) {
//            sharedPreferences.edit().putString("status", bundle.getString("status")).commit();
//            Log.d("MAINNN bundle not null ", "Status " + sharedPreferences.getString("status", null));
//        }
//        Log.d("SHARED PREF MAIN ", "status: " + sharedPreferences.getString("status", null));
//        if (sharedPreferences.getString("status", null) != null && !sharedPreferences.getString("status", null).equals("LOGOUT")) {
//            Log.d("MAINNN not logout", "Status " + sharedPreferences.getString("status", null) + " Username " + username + " UserId " + userId);
//            Intent intent = new Intent(this, PostLoginActivity.class);
//            intent.putExtra("status", sharedPreferences.getString("status", null));
//            intent.putExtra("username", sharedPreferences.getString("username", null));
//            intent.putExtra("userId", sharedPreferences.getString("userId", null));
//            startActivity(intent);
//            finish();
//        } else {
//            //sharedPreferences.edit().putString("status","Alive").commit();
//            Log.d("MAINNN else logout", "Status " + sharedPreferences.getString("status", null) + " Username " + username + " UserId " + userId);
//        }
    }

    public void onClickShowPassword(View view) {
        if (!showPassword.isChecked()) {
            l_password.setTransformationMethod(new PasswordTransformationMethod());
        } else {
            l_password.setTransformationMethod(null);
        }
    }

    public void loguser(View view) {
        userId = l_name.getText().toString();
        password = l_password.getText().toString();

        if (userId.isEmpty()) {
            l_name.setError("Username cannot be empty");
            return;
        }
        if (password.isEmpty()) {
            l_password.setError("Password cannot be empty");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(userId, password);
    }

}
