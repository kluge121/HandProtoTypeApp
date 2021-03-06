package com.globe.hand.common;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.globe.hand.Login.LoginActivity;
import com.globe.hand.Main.MainActivity;
import com.globe.hand.R;

/**
 * Created by ssangwoo on 2017-12-18.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;

    protected void setToolbar(int resId, boolean isSetIndicator) {
        toolbar = findViewById(resId);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        if(isSetIndicator) {
            setHomeAsUpIndicator();
        }
    }

    protected void setToolbarTitle(String toolbarTitle) {
        TextView textTitle = toolbar.findViewById(R.id.text_toolbar_title);
        textTitle.setText(toolbarTitle);
    }

    private void setHomeAsUpIndicator() {
        if(toolbar == null) return;
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
//        toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    protected void redirectLoginActivity(Context context, String errorContent) {
        redirectLoginActivity();
        new AlertDialog.Builder(context, R.style.dialog)
                .setMessage(errorContent)
                .setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();
    }

    protected void redirectMainActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
