package com.wu1015.coursessystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.wu1015.coursessystem.fragement.ItemClassCenterFragment;
import com.wu1015.coursessystem.fragement.ItemClassCenterFragment2;
import com.wu1015.coursessystem.fragement.ItemClassTableFragment;
import com.wu1015.coursessystem.fragement.ItemClassTableFragment2;
import com.wu1015.coursessystem.fragement.ItemClassTableFragment3;
import com.wu1015.coursessystem.fragement.ItemMeFragment;
import com.wu1015.coursessystem.model.User;
import com.wu1015.coursessystem.utils.DBOpenHelper;

import java.util.Arrays;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private ItemClassCenterFragment2 itemClassCenterFragment;
    private ItemClassTableFragment2 itemClassTableFragment;
    private ItemClassTableFragment3 itemClassTableFragment2;
    private ItemMeFragment itemMeFragment;
    private Intent intent;
    private User user;

    List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        todo 管理员的页面
        toolbar = findViewById(R.id.toolbar);
        viewPager2 = findViewById(R.id.viewPage2);
        bottomNavigationView = findViewById(R.id.bottomNavView);

        intent = getIntent();

        initData();
    }

    public void initData() {
        sqLiteOpenHelper = new DBOpenHelper(this, "Login.db", null, 1);
        userSet();
        fragmentList = newFragments();
        MainActivity2.ViewPageAdapter viewPageAdapter = new MainActivity2.ViewPageAdapter(this, fragmentList);
        viewPager2.setUserInputEnabled(false);
        viewPager2.setAdapter(viewPageAdapter);


//        ViewGroup.LayoutParams layoutParams=viewPager2.getLayoutParams();
//        layoutParams.height=;
//        viewPager2.setLayoutParams(layoutParams);
//        todo viewpage动态计算高度

/*
        tabLayout=view.findViewById(R.id.tabLayout);
        viewPager2=view.findViewById(R.id.viewPage2);

        List<String> titleList= Arrays.asList("Touhou_News","Reimu","tab3");
        List<Fragment> fragmentList =newFragments();

        ViewPageAdapter viewPageAdapter=new ViewPageAdapter(getActivity(),fragmentList);
        viewPager2.setAdapter(viewPageAdapter);
//        viewPager2.setSaveEnabled(false);
//        关联TabLayout和ViewPage2
        new TabLayoutMediator(tabLayout, viewPager2, true,
                (tab, position) -> tab.setText(titleList.get(position)))
                .attach();
*/

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.itemClassCenter) {
                    fragmentList = newFragments();
                    MainActivity2.ViewPageAdapter viewPageAdapter = new MainActivity2.ViewPageAdapter(MainActivity2.this, fragmentList);
                    viewPager2.setAdapter(viewPageAdapter);
                    viewPager2.setCurrentItem(0);
                    return true;
                } else if (id == R.id.itemMe) {
                    viewPager2.setCurrentItem(1);
                    return true;
                } else if (id == R.id.itemClassTable) {
                    viewPager2.setCurrentItem(2);
//                    fragmentList = newFragments();
//                    MainActivity2.ViewPageAdapter viewPageAdapter = new MainActivity2.ViewPageAdapter(MainActivity2.this, fragmentList);
//                    viewPager2.setAdapter(viewPageAdapter);
                    return true;
                } else if (id == R.id.itemClassTable2) {
                    viewPager2.setCurrentItem(3);
                    return true;
                }

                return false;
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.itemLogout) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                sleep(1000);
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    };
                    thread.start();
                } else if (id == R.id.itemEditMsg) {

                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                sleep(100);
                                Intent intent = new Intent(getApplicationContext(), MeEditActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("U_Id", user.getU_Id());
                                bundle.putString("U_No", user.getU_No());
                                bundle.putString("U_Name", user.getU_Name());
                                bundle.putString("U_Sex", user.getU_Sex());
                                bundle.putString("U_Mail", user.getU_Mail());
                                bundle.putString("U_Grade", user.getU_Grade());
                                bundle.putString("U_Major", user.getU_Major());
                                bundle.putByteArray("U_Img", user.getU_Img());
                                bundle.putString("U_Flag", user.getU_Flag());
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    };
                    thread.start();
                } else if (id == R.id.itemAbout) {
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            try {
                                sleep(100);
                                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                                startActivity(intent);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    };
                    thread.start();
                } else {
                    return false;
                }
                return true;
            }
        });
    }

    public void userSet() {
        Bundle bundle = intent.getExtras();
        user = new User();
        user.setU_Id(bundle.getString("U_Id", null));
        user.setU_No(bundle.getString("U_No", null));
        user.setU_Name(bundle.getString("U_Name", null));
        user.setU_Sex(bundle.getString("U_Sex", null));
        user.setU_Mail(bundle.getString("U_Mail", null));
        user.setU_Grade(bundle.getString("U_Grade", null));
        user.setU_Major(bundle.getString("U_Major", null));
        user.setU_Img(bundle.getByteArray("U_Img"));
        user.setU_Flag(bundle.getString("U_Flag"));
    }

    private List<Fragment> newFragments() {

        itemMeFragment = new ItemMeFragment(user);
        itemClassCenterFragment = new ItemClassCenterFragment2(user);
        itemClassTableFragment = new ItemClassTableFragment2(user);
        itemClassTableFragment2 = new ItemClassTableFragment3(user);
        return Arrays.asList(itemClassCenterFragment, itemMeFragment, itemClassTableFragment, itemClassTableFragment2);
    }

    class ViewPageAdapter extends FragmentStateAdapter {
        private List<Fragment> fragmentList;

        public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragmentList) {
            super(fragmentActivity);
            this.fragmentList = fragmentList;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }
}