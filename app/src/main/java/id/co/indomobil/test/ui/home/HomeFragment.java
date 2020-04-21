package id.co.indomobil.test.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import id.co.indomobil.test.R;
import id.co.indomobil.test.ui.gallery.GalleryFragment;
import id.co.indomobil.test.ui.home.HomeViewModel;
import id.co.indomobil.test.ui.sales.SalesInputItemFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        LinearLayout button = (LinearLayout) root.findViewById(R.id.action_sales);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
              SalesInputItemFragment salesInputItemFragment = new SalesInputItemFragment();
              FragmentManager manager = getFragmentManager();
              manager.beginTransaction()
                      .replace(R.id.nav_host_fragment, salesInputItemFragment, salesInputItemFragment.getTag())
                      .addToBackStack(null)
                      .commit();
            }
        });
        return root;
    }
}