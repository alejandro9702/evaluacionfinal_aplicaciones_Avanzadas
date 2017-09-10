package facebook.example.com.evaluacionfinal_aplicacionesavanzadas;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.ActionBarActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends ActionBarActivity {
 private CallbackManager cM;
    private LoginButton lb;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FacebookSdk.sdkInitialize(getApplicationContext());
        cM=CallbackManager.Factory.create();

        getFbkeyHash("NMZTJstufOOCPo8NatvuytWa44I=");
        setContentView(R.layout.activity_main);

        //banner
        adView = (AdView) findViewById(R.id.ad_view);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);

        lb=(LoginButton)findViewById(R.id.login_facebook);
        lb.registerCallback(cM, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Toast.makeText(MainActivity.this,"¡inicio de sesion Exitoso!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this,"¡Cancelado!",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this,"¡inicio de sesion NO Exitoso!",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getFbkeyHash(String packageName) {
        try {
            PackageInfo info= getPackageManager().getPackageInfo(
                    packageName,PackageManager.GET_SIGNATURES);
            for (Signature signature:info.signatures){
                MessageDigest md=MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("keyHash :", Base64.encodeToString(md.digest(),Base64.DEFAULT));
                System.out.println("KeyHash: "+ Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }

        }catch (PackageManager.NameNotFoundException e){
            System.out.println("error");
        }catch (NoSuchAlgorithmException e){
            System.out.println("error");        }
    }

protected  void onActivityResult(int reqCode,int resCode,Intent i){
    cM.onActivityResult(reqCode,resCode,i);
}


//banner

    @Override
    protected void onPause() {
        if(adView != null){
            adView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if(adView != null){
            adView.resume();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if(adView != null){
            adView.destroy();
        }
        super.onDestroy();
    }
}
