package com.example.notificatoin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    NotificationCompat.Builder notBuilder;
    private static final int MY_NOTIFICATION_ID = 12345;
    private static final int MY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void notiButtonClicked(View v) {
        NotificationChannel notificationChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel("ID",
                    "NAME", NotificationManager.IMPORTANCE_DEFAULT);
            this.notBuilder = new
                    NotificationCompat.Builder(getApplicationContext(),
                    notificationChannel.getId());
        } else {
            notBuilder = new
                    NotificationCompat.Builder(getApplicationContext());
        }
        // this.notBuilder = new NotificationCompat.Builder(this);
        notBuilder.setAutoCancel(true);
        this.notBuilder.setSmallIcon(R.mipmap.ic_launcher);
        this.notBuilder.setTicker("This is a ticker");
        // Sét đặt thời điểm sự kiện xẩy ra.
        // Các thông báo trên Panel được sắp xếp bởi thời gian này.
        this.notBuilder.setWhen(System.currentTimeMillis() + 10 * 1000);
        this.notBuilder.setContentTitle("This is title");
        this.notBuilder.setContentText("This is content text ....");
        // Tạo một Intent
        Intent intent = new Intent(this, MainActivity.class);
        // PendingIntent.getActivity(..) sẽ start mới một Activity và trả về
        // đối tượng PendingIntent.
        // Nó cũng tương đương với gọi Context.startActivity(Intent).
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                MY_REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        this.notBuilder.setContentIntent(pendingIntent);
        // Lấy ra dịch vụ thông báo (Một dịch vụ có sẵn của hệ thống).
        NotificationManager notificationService =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationService.createNotificationChannel(notificationChannel);
        }
        // Xây dựng thông báo và gửi nó lên hệ thống.
        Notification notification = notBuilder.build();
        notificationService.notify(MY_NOTIFICATION_ID, notification);
    }
}
