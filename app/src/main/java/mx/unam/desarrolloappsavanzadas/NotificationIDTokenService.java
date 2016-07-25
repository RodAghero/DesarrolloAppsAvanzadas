package mx.unam.desarrolloappsavanzadas;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Roy on 23/07/2016.
 */
public class NotificationIDTokenService extends FirebaseInstanceIdService {

    private static final String TAG = "FIREBASE_TOKEN";

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();

        Log.d(TAG, "Solicitando Token");
        String token = FirebaseInstanceId.getInstance().getToken();

        enviarTokenRegistro(token);

    }

    private void enviarTokenRegistro(String token) {
        Log.d(TAG, token);
    }

}
