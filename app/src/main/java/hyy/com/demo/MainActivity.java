package hyy.com.demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import butterknife.BindView;
import butterknife.ButterKnife;
import hyy.com.demo.constants.Constants;
import hyy.com.dropdownlistviewdemo.R;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_drop_down_list)
    Button btnDropDownList;
    @BindView(R.id.btn_auto_extends_viewgroup)
    Button btnAutoExtViewgroup;
    @BindView(R.id.btn_hyy_date_picker)
    Button btnHyyDatePicker;
    @BindView(R.id.btn_ad_view)
    Button btnAdView;
    @BindView(R.id.btn_regular)
    Button btnRegular;
    @BindView(R.id.btn_spinner)
    Button btnSpinner;
    @BindView(R.id.btn_camera)
    Button btnCamera;
    @BindView(R.id.btn_tiles)
    Button btnTiles;

    @BindView(R.id.btn_captcha)
    Button btnCaptcha;

    @BindView(R.id.btn_rotate_captcha)
    Button btnRotateCaptcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        init();
        initListener();
    }

    private void initListener() {
        btnDropDownList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContainerActivity.class);

                intent.putExtra("type", Constants.TYPE_DROP_DOWN_LIST);
                startActivity(intent);
            }
        });

        btnAutoExtViewgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContainerActivity.class);

                intent.putExtra("type", Constants.TYPE_AUTO_EXT_VIEW_GROUP);
                startActivity(intent);
            }
        });

        btnHyyDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContainerActivity.class);

                intent.putExtra("type", Constants.TYPE_HYY_DATE_PICKER);
                startActivity(intent);
            }
        });

        btnAdView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContainerActivity.class);

                intent.putExtra("type", Constants.TYPE_AD_VIEW);
                startActivity(intent);
            }
        });

        btnRegular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContainerActivity.class);

                intent.putExtra("type", Constants.TYPE_REGULAR);
                startActivity(intent);
            }
        });

        btnSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContainerActivity.class);

                intent.putExtra("type", Constants.TYPE_SPINNER);
                startActivity(intent);
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContainerActivity.class);

                intent.putExtra("type", Constants.TYPE_CAMERA);
                startActivity(intent);
            }
        });

        btnTiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContainerActivity.class);

                intent.putExtra("type", Constants.TYPE_TILES);
                startActivity(intent);
            }
        });

        btnCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContainerActivity.class);

                intent.putExtra("type", Constants.TYPE_CAPTCHA);
                startActivity(intent);
            }
        });

        btnRotateCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContainerActivity.class);

                intent.putExtra("type", Constants.TYPE_ROTATE_CAPTCHA);
                startActivity(intent);
            }
        });
    }

    private void init() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
