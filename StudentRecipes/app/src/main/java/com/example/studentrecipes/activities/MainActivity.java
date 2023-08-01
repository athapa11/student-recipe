package com.example.studentrecipes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentrecipes.R;
import com.example.studentrecipes.ReminderBroadcast;
import com.example.studentrecipes.data.SQLiteDatabaseHelper;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    public static final String MAIN_CHANNEL = "Main Channel";

    private NotificationManagerCompat notificationManager;

    private SQLiteDatabaseHelper db;
    private TextView register;
    private EditText editTextUsername, editTextPassword;
    private Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Add Test user "eee"
        db = new SQLiteDatabaseHelper(this);
        Boolean checkUsername = db.checkUsername("eee");
        if(checkUsername == true){
            db.addUser("eee", "eee");
        }

        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);

        register = (TextView) findViewById(R.id.go_to_register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        logIn = (Button) findViewById(R.id.login);
        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                Boolean authentication = db.authenticate(username, password);
                if(authentication == true){ // Go to Home screen
                    //Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();

        // Notification onDestroy (Closing app)
        notificationManager = NotificationManagerCompat.from(MainActivity.this);
        Notification notification = new NotificationCompat.Builder(MainActivity.this, MAIN_CHANNEL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Student Recipes")
                .setContentText("Thank you for using the app!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();
        notificationManager.notify(1, notification);

        Log.v(TAG,"onBackPressed");
    }

    @Override
    protected void onResume() {
        super.onResume();

        createNotificationChannel();

        // Scheduled notification
        Calendar calendar = Calendar.getInstance();

        Intent intent = new Intent(MainActivity.this, ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent
                .getBroadcast(MainActivity.this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmManager.INTERVAL_DAY, pendingIntent);
        // Test to show notification works
        alarmManager.set(AlarmManager.RTC_WAKEUP, 1, pendingIntent);

        Log.v(TAG,"onResume");
    }

    private void createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel mainChannel = new NotificationChannel
                    (MAIN_CHANNEL, "Main Channel", NotificationManager.IMPORTANCE_DEFAULT);
            mainChannel.setDescription("This is the main channel");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(mainChannel);
        }
    }
}