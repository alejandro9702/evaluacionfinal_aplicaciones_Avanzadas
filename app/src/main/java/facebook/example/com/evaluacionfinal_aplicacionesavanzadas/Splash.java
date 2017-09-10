package facebook.example.com.evaluacionfinal_aplicacionesavanzadas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imagen=(ImageView)findViewById(R.id.mobile);
        Animation animacion = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
        imagen.startAnimation(animacion);

        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(8000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
