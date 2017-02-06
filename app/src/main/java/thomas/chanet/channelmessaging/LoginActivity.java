package thomas.chanet.channelmessaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OnDownloadCompleteListener {

    private Button btnValider;
    private TextView txtViewId;
    private TextView txtViewPwd;
    private EditText EditTextId;
    private EditText EditTextPwd;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnValider = (Button) findViewById(R.id.button);
        txtViewId= (TextView) findViewById(R.id.textView);
        txtViewPwd= (TextView) findViewById(R.id.textView2);
        EditTextId =(EditText) findViewById(R.id.editText);
        EditTextPwd =(EditText) findViewById(R.id.editText2);
        btnValider.setOnClickListener(this) ;

    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.button)
        {
            HashMap<String, String> loginParams = new HashMap<>();
            loginParams.put("username", EditTextId.getText().toString());
            loginParams.put("password", EditTextPwd.getText().toString());
            LoginAsync login = new LoginAsync(getApplicationContext(),loginParams);

            login.setOnDownloadCompleteListener(this);
            login.execute();

        }

    }

    @Override
    public void onDownloadComplete(String result) {
        Gson gson = new Gson();
        JsonAccess token = gson.fromJson(result, JsonAccess.class);
        if (token.getAccesstoken()!=null)
        {

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("AccessToken", token.getAccesstoken());
            editor.commit();
            Intent newActivity = new Intent(getApplicationContext(),ChannelListActivity.class);
            startActivity(newActivity);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Mauvais identifiant",Toast.LENGTH_SHORT).show();

        }

    }
}







