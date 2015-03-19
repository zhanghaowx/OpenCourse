package io.github.zhanghaowx.opencourse.fragment.core;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.adapter.core.DrawerMenuAdapter;
import io.github.zhanghaowx.opencourse.model.core.DrawerMenu;
import io.github.zhanghaowx.opencourse.utils.SharedPreferenceNames;

/**
 * Fragment used for interaction management and presentation to the drawer menu
 */
public class NavigationDrawerFragment extends BaseFragment {

    private static final String TAG = NavigationDrawerFragment.class.getSimpleName();
    /**
     * Remember the position of the selected item
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * View Components
     */
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View mDrawerView;
    private ListView mDrawerListView;

    /**
     * Saved States
     */
    private View mFragmentContainerView;
    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState = false;

    /**
     * Shared preferences
     */
    private boolean mUserLearnedDrawer;
    private String mUserLoginSessionId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Read in the flag indicating whether or not the user has
        // demonstrated awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(SharedPreferenceNames.PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDrawerView = inflater.inflate(R.layout.fragment_drawer_menu, container, false);
        mDrawerListView = (ListView) mDrawerView.findViewById(R.id.fragment_drawerMenu_listView);
        DrawerMenuAdapter drawerMenuAdapter = new DrawerMenuAdapter(mContext, getDrawerMenuItems());
        mDrawerListView.setAdapter(drawerMenuAdapter);
        mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        return mDrawerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration to drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout != null) {
                if (isDrawerOpen()) {
                    mDrawerLayout.closeDrawer(mFragmentContainerView);
                } else {
                    mDrawerLayout.openDrawer(mFragmentContainerView);
                }
            } else {
                getActivity().finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Return whether or not the drawer is open
     *
     * @return
     */
    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        // set up the drawer's list view with items and click listener

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),              /* host Activity */
                mDrawerLayout,              /* DrawerLayout object */
                R.string.app_name,             /* "open drawer" description for accessibility */
                R.string.app_name              /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    // The user manually opened the drawer; store this flag to prevent auto-showing
                    // the navigation drawer automatically in the future.
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(SharedPreferenceNames.PREF_USER_LEARNED_DRAWER, true).apply();
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
        // per the navigation drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    /**
     * Request update draw menu's content after user logged in
     */
    public void requestUpdateMenu() {
        DrawerMenuAdapter adapter = (DrawerMenuAdapter) mDrawerListView.getAdapter();
        adapter.getListItemsInDrawerMenu().clear();
        adapter.getListItemsInDrawerMenu().addAll(getDrawerMenuItems());
        adapter.notifyDataSetChanged();
    }


    /**
     * Read drawer menu titles from resources file and create a list to hold them.
     *
     * @return
     */
    private ArrayList<DrawerMenu> getDrawerMenuItems() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLoginSessionId = sp.getString(SharedPreferenceNames.PREF_USER_SESSION_ID, null);
        ArrayList<DrawerMenu> menuDrawerListItems = new ArrayList<DrawerMenu>();
        try {
            Resources res = getActivity().getResources();
            if (mUserLoginSessionId != null) {
                menuDrawerListItems.add(new DrawerMenu(res.getString(R.string.fragment_drawerMenu_item_myAccount), R.drawable.ic_account_circle_white_24dp));
                menuDrawerListItems.add(new DrawerMenu(res.getString(R.string.fragment_drawerMenu_item_notification), R.drawable.ic_notifications_white_24dp));
                menuDrawerListItems.add(new DrawerMenu(res.getString(R.string.fragment_drawerMenu_item_settings), R.drawable.ic_settings_white_24dp));
            } else {
                menuDrawerListItems.add(new DrawerMenu(res.getString(R.string.fragment_drawerMenu_item_login), R.drawable.ic_account_circle_white_24dp));
            }
        } catch (Resources.NotFoundException notFoundException) {
            Log.e(TAG, "Error Getting Drawer Menu Title Array", notFoundException);
        }


        return menuDrawerListItems;
    }

    /**
     * Select an item in the drawer menu
     *
     * @param position
     */
    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
        if (mDrawerListView != null) {
            mDrawerListView.setItemChecked(position, true);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }

    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(int position);
    }
}
