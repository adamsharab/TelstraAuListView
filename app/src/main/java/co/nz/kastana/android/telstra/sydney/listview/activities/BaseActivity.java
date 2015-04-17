package co.nz.kastana.android.telstra.sydney.listview.activities;

import com.google.inject.Inject;

import co.nz.kastana.android.telstra.sydney.listview.roboguice.EventBus;
import roboguice.activity.RoboActionBarActivity;

public class BaseActivity extends RoboActionBarActivity {
    @Inject
    protected EventBus eventBus;
}
