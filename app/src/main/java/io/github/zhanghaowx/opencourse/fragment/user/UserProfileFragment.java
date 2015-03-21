package io.github.zhanghaowx.opencourse.fragment.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.fragment.core.BaseFragment;
import io.github.zhanghaowx.opencourse.model.user.User;

public class UserProfileFragment extends BaseFragment {

    ImageView mUserProfileImageView;
    TextView mUserNameView;
    TextView mUserLevelView;

    Button mUserLogOutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_user_profile, container, false);

        mUserNameView = (TextView) rootView.findViewById(R.id.user_profile_user_name);
        mUserLevelView = (TextView) rootView.findViewById(R.id.user_profile_user_level);
        mUserProfileImageView = (ImageView) rootView.findViewById(R.id.user_profile_image);

        mUserLogOutButton = (Button) rootView.findViewById(R.id.user_profile_log_out);
        mUserLogOutButton.setCompoundDrawables(createIcon("fa-sign-out"), null, null, null);
        mUserLogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.logOut(getActivity());
                getActivity().finish();
            }
        });

        return rootView;
    }
}
