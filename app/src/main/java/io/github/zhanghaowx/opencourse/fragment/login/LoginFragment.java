package io.github.zhanghaowx.opencourse.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.github.zhanghaowx.opencourse.R;
import io.github.zhanghaowx.opencourse.activity.login.LoginEmailActivity;
import io.github.zhanghaowx.opencourse.fragment.core.BaseFragment;

/**
 * Fragment for login activity
 */
public class LoginFragment extends BaseFragment {
    private static final String TAG = LoginFragment.class.getSimpleName();

    private Button mEmailSignIn;
    private Button mFacebookSignIn;
    private Button mGoogleSignIn;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        mEmailSignIn = (Button) rootView.findViewById(R.id.email_sign_in_button);
        mEmailSignIn.setCompoundDrawables(createIcon("fa-envelope"), null, null, null);
        mEmailSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginEmailActivity.class);
                startActivity(intent);
            }
        });

        mFacebookSignIn = (Button) rootView.findViewById(R.id.facebook_sign_in_button);
        mFacebookSignIn.setCompoundDrawables(createIcon("fa-facebook"), null, null, null);

        mGoogleSignIn = (Button) rootView.findViewById(R.id.google_sign_in_button);
        mGoogleSignIn.setCompoundDrawables(createIcon("fa-google-plus"), null, null, null);

        return rootView;
    }
}
