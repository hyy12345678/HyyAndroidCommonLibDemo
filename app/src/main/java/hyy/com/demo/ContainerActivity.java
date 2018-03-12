package hyy.com.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import hyy.com.demo.constants.Constants;
import hyy.com.demo.fragments.AdViewFragment;
import hyy.com.demo.fragments.AutoExtVGFragment;
import hyy.com.demo.fragments.DropDownListFragment;
import hyy.com.demo.fragments.HyyDatePickerFragment;
import hyy.com.demo.fragments.RegularFragment;
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

            default:
                break;
        }

        if(null != fragment){
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
}
