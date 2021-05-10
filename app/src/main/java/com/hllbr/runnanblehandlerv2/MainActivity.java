package com.hllbr.runnanblehandlerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Basit Kronometre

    int number ;

    TextView textView ;

    Runnable runnable ;

    Handler handler ;

    //visible- Invisible işlemleri için butonumu tanımlamam gerekiyor.
    Button buton ;
    Button fastButon ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        buton = findViewById(R.id.startButon);

        number = 0 ;

        fastButon = findViewById(R.id.fastButon);


    }
    public void start(View view){

        handler = new Handler(Looper.getMainLooper());

        runnable = new Runnable() {
            @Override
            public void run() {
                textView.setText("Time "+number);
                 number++ ;
                 textView.setText("Time "+number);

                 handler.postDelayed(runnable,1000);

            }
        };
            handler.post(runnable);
            Toast.makeText(MainActivity.this,"app is started",Toast.LENGTH_SHORT).show();

        //Yapının güvenli bir şekilde standrtlara uygun çalışması için gerekli işlemleri yapıyorum

        //Butona bir kez basıldıktan sonra ikinci kez basılamasın diye butonu tıklanmaz hale getirerek runnable metodunun birden fazla kez çağrılmasını önleyebilrim

        //hesap makinesinde edit text için string ifadelerin girilmesini engellemek için klavyeyi sadece rakamlardan oluşan bir ifade edile kilitlediğim gibi

        //bu işlemi gerçekeleştirmek için butonu tanımlamam gerekiyor .Handler başlatıldıktan hemen sonra tuşu görünmez hale getirirsem sorunum ortadan kalkmış olacaktır.

        buton.setEnabled(false);//butona tıklandıktan sonra gösterme.bunu tekrar stop tuşuna basıldığında aktif hale getiririm.
        fastButon.setEnabled(false);

    }

    public void stop(View view){

        buton.setEnabled(true);
        fastButon.setEnabled(true);

        //stop tuşuna basıldığında handler kapatılması gerekiyor Arkaplanda çalışan runnable kapatmama lazım

        handler.removeCallbacks(runnable);//removeCallbacks neyi ele aldıklarından neyi kapatayım diyor bizde runnable kapat diyoruz .Bu şekilde ifade edebiliriz.

        //number sıfırlanabilir.Number ifademi sıfırlamadan duraklatacak bir başka metod ve buton daha tasarlayabilirim.
        number = 0 ;
        textView.setText("Time is reset.Time "+ number);
        Toast.makeText(getApplicationContext(),"app stoped",Toast.LENGTH_SHORT).show();
    }
    public void justStop(View view){

        buton.setEnabled(true);
        fastButon.setEnabled(true);
        handler.removeCallbacks(runnable);
        textView.setText("Time is Stopped.Time "+number);

        Toast.makeText(getApplicationContext(),"app is just stopped",Toast.LENGTH_LONG).show();
    }
    public void fast(View view){
        handler = new Handler(Looper.getMainLooper());

        runnable = new Runnable() {
            @Override
            public void run() {
                textView.setText("Time "+number);
                number++;
                textView.setText("Time "+number);
                handler.postDelayed(runnable,500);

            }
        };
        handler.post(runnable);

    }
}