package io.github.jhcpokemon.democontainer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.jhcpokemon.democontainer.R;
import io.github.jhcpokemon.democontainer.fragment.MyFragment;

/**
 * Created by jhcpokemon on 09/03/15.
 */
public class Activity2 extends Activity implements MyFragment.OnButtonClicked {
    @Bind(R.id.check_box)
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_2);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onClick() {
        return checkBox.isChecked();
    }
}
