package com.example.xgramajo.tabbedproject;

import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.SlidingDrawer;

import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity implements CartaFragment.SendProducts{

    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    /**Drawer */
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        /**ESTO DICE EN QUE TAB ARRANCA LA ACTIVITY*/
        mViewPager.setCurrentItem(1);

        /*Drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        /*SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());*/
        mSectionsPageAdapter.addFragment(new PromoFragment(), "Promo");
        mSectionsPageAdapter.addFragment(new CartaFragment(), "Carta");
        mSectionsPageAdapter.addFragment(new PedidosFragment(), "Pedidos");
        viewPager.setAdapter(mSectionsPageAdapter);
    }

    /**ACA MANDA LA CARTA LA LISTA SELECCIONADA (selectedList)*/
    @Override
    public void setSelectedList(ArrayList<ProductClass> selectedList){

        if (mSectionsPageAdapter.getCount() == 0) {

        }

        PedidosFragment pedidosFragment = /*new PedidosFragment();*/(PedidosFragment) mSectionsPageAdapter.getItem(2);

        pedidosFragment.setProducts(selectedList);
        /*
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("products",selectedList);
        pedidosFragment.setArguments(bundle);
        */
        FragmentTransaction fragmentTransaction =   getSupportFragmentManager().beginTransaction().replace(R.id.container, pedidosFragment,null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}