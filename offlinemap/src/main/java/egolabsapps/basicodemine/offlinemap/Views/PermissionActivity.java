package egolabsapps.basicodemine.offlinemap.Views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import egolabsapps.basicodemine.offlinemap.Interfaces.PermissionResultListener;
import egolabsapps.basicodemine.offlinemap.R;
import egolabsapps.basicodemine.offlinemap.Utils.MapUtils;
import egolabsapps.basicodemine.offlinemap.Utils.PermissionUtils;

import static egolabsapps.basicodemine.offlinemap.Utils.PermissionUtils.PERMISSION_RESULT;

public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        try {
            if (getSupportActionBar() != null)
                getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (PermissionUtils.askForPermissions(this, PERMISSION_RESULT)) {
            PermissionResultListener.getInstance().notifyUserAccept();
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_RESULT) {
            PermissionResultListener.getInstance().notifyUserAccept();
            finish();
        } else {
            PermissionResultListener.getInstance().notifyUserReject();
            finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == PERMISSION_RESULT) {
            PermissionResultListener.getInstance().notifyUserAccept();
            finish();
        } else {
            PermissionResultListener.getInstance().notifyUserReject();
            finish();
        }
    }
}
