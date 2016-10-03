package calvin.sjsu.cs175.memorygame;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RulesFragment extends Fragment {

    public RulesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_rules, container, false);


    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button exitBtn = (Button) getView().findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                exit();
            }
        });
    }



    public void exit() {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.remove(this);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
        View game = getActivity().findViewById(R.id.game);
        game.setVisibility(View.VISIBLE);
    }


}
