package hyy.com.demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import hyy.com.demo.constants.Constants;
import hyy.com.demo.fragments.AdViewFragment;
import hyy.com.demo.fragments.AutoExtVGFragment;
import hyy.com.demo.fragments.CameraFragment;
import hyy.com.demo.fragments.CaptchaFragment;
import hyy.com.demo.fragments.DropDownListFragment;
import hyy.com.demo.fragments.HyyDatePickerFragment;
import hyy.com.demo.fragments.RegularFragment;
import hyy.com.demo.fragments.RotateCaptchaFragment;
import hyy.com.demo.fragments.SpinnerFragment;
import hyy.com.demo.fragments.SwipeCaptchaFragment;
import hyy.com.demo.fragments.TilesFragment;
import hyy.com.dropdownlistviewdemo.R;

public class ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        int type = getIntent().getIntExtra("type", -1);

        initFragment(type);
    }

    private void initFragment(int type) {
        Fragment fragment = null;

        switch (type) {
            case Constants.TYPE_DROP_DOWN_LIST:

                fragment = DropDownListFragment.getInstance();

                break;
            case Constants.TYPE_AUTO_EXT_VIEW_GROUP:
                fragment = AutoExtVGFragment.newInstance();

                break;
            case Constants.TYPE_HYY_DATE_PICKER:
                fragment = HyyDatePickerFragment.getInstance();

                break;

            case Constants.TYPE_AD_VIEW:
                fragment = AdViewFragment.newInstance();

                break;

            case Constants.TYPE_REGULAR:
                fragment = RegularFragment.getInstance();

                break;

            case Constants.TYPE_SPINNER:
                fragment = SpinnerFragment.newInstance();

                break;

            case Constants.TYPE_CAMERA:
                fragment = CameraFragment.newInstance();

                break;

            case Constants.TYPE_TILES:
                fragment = TilesFragment.newInstance();

                break;

            case Constants.TYPE_CAPTCHA:
                fragment = CaptchaFragment.newInstance();
                break;

            case Constants.TYPE_ROTATE_CAPTCHA:
                fragment = RotateCaptchaFragment.newInstance();
                break;

            case Constants.TYPE_SWIPE_CAPTCHA:
                fragment = SwipeCaptchaFragment.newInstance();
                break;

            default:
                break;
        }

        if (null != fragment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_container, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment mFragment : fragments) {
            mFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
