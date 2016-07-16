package mx.unam.desarrolloappsavanzadas;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import mx.unam.desarrolloappsavanzadas.Menus.mAcercaDe;
import mx.unam.desarrolloappsavanzadas.Menus.mConfigurarCuenta;
import mx.unam.desarrolloappsavanzadas.Menus.mContacto;
import mx.unam.desarrolloappsavanzadas.adapters.PageAdapter;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final String KEY_EXTRA_NAME = "name";
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(toolbar);
        ((TextView) findViewById(R.id.tvTituloAppbar)).setText("Petagramy");

        tabLayout =     (TabLayout) findViewById(R.id.tabLayout);
        viewPager =     (ViewPager) findViewById(R.id.viewPager);

        Log.e("MainActivity", "onCreate");
        setUpViewPager();
    }


    // Métodos para los menus
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.mContacto:
                Intent intent = new Intent(this, mContacto.class);
                startActivity(intent);
                break;
            case R.id.mAcercaDe:
                Intent intent1 = new Intent(this, mAcercaDe.class);
                startActivity(intent1);
                break;
            case R.id.mConfigurarCuenta:
                Intent intent2 = new Intent(this,mConfigurarCuenta.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume(){
        super.onResume();

        Fragment fragment = ((PageAdapter) viewPager.getAdapter()).getFragment(1);
        if (fragment != null) {
            fragment.onResume();
        }
    }

    private void setUpViewPager(){

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerviewFragment());
        fragments.add(new PerfilFragment());

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), fragments));

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.dog_house_48);
        tabLayout.getTabAt(1).setIcon(R.drawable.year_of_dog_52);

    }

}
