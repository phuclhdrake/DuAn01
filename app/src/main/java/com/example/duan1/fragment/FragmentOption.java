package com.example.duan1.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.biometric.BiometricPrompt;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.Dao.StaffDAO;
import com.example.duan1.R;
import com.example.duan1.activity.DoanhThuActivity;
import com.example.duan1.activity.HoadonActivity;
import com.example.duan1.activity.StaffActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.Executor;

public class FragmentOption extends Fragment {

    private CardView cardViewDoanhThu, cardViewBill, cvStaff;

    public FragmentOption() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardViewDoanhThu = view.findViewById(R.id.cardViewDoanhThu);
        cardViewBill= view.findViewById(R.id.cardViewBill);
        cvStaff= view.findViewById(R.id.cardViewNhanVien);

        cardViewDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DoanhThuActivity.class);
                intent.setAction("AddStaff");
                startActivity(intent);
            }
        });

        cardViewBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HoadonActivity.class);
                startActivity(intent);
            }
        });

        cvStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater layoutInflater = getLayoutInflater();
                View view1 = layoutInflater.inflate(R.layout.dialog_enter_password, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();

                //anh xa
                TextInputEditText edtPassword = view1.findViewById(R.id.edtEnterPassword);
                ImageView imgFingerPrint = view1.findViewById(R.id.imgFingerprint);
                TextView tvLogin = view1.findViewById(R.id.tvOkPassword);
                TextView tvChangePassword = view1.findViewById(R.id.tvChangePassword);

                //event
                tvLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String password = edtPassword.getText().toString();
                        StaffDAO staffDAO = new StaffDAO(getContext());
                        boolean check = staffDAO.checkPassword("Qu???n l??", password);

                        if (check){
                            Intent intent = new Intent(getActivity(), StaffActivity.class);
                            startActivity(intent);
                        }else {
                            edtPassword.setError("Sai m???t kh???u");
                        }
                    }
                });

                imgFingerPrint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // H??m x??c th???c v??n tay
                        fingerPrint(dialog);
                    }
                });

                tvChangePassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogChangePassword();
                    }
                });
            }
        });
    }

    private void dialogChangePassword() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_change_password);

        EditText edtOldPass = dialog.findViewById(R.id.edtOldPassword);
        EditText edtNewPass = dialog.findViewById(R.id.edtNewPassword);
        EditText edtAgainPass = dialog.findViewById(R.id.edtAgainPassword);
        Button btnCancel = dialog.findViewById(R.id.btnCancelChangePassword);
        Button btnChangePass = dialog.findViewById(R.id.btnChangePassword);

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaffDAO staffDAO = new StaffDAO(getContext());
                String oldPass = edtOldPass.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String againPass = edtAgainPass.getText().toString();

                if (oldPass.isEmpty() || newPass.isEmpty() || againPass.isEmpty()){
                    edtOldPass.setError("Kh??ng ????? tr???ng ?? n??o");
                    return;
                }

                boolean check = staffDAO.checkPassword("Qu???n l??", oldPass);
                if (!check){
                    edtOldPass.setError("M???t kh???u kh??ng ????ng");
                    return;
                }

                if (!(newPass.equals(againPass))){
                   edtAgainPass.setError("M???t kh???u kh??ng gi???ng nhau");
                   return;
                }

                staffDAO.changePassword("Qu???n l??", newPass);
                Toast.makeText(getContext(), "?????i m???t kh???u th??nh c??ng", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void checkError() {

    }

    // X??c th???c v??n tay
    private void fingerPrint(Dialog dialog) {
        Executor executor = ContextCompat.getMainExecutor(getContext());
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor,
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                    }

                    @Override
                    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        // Intent ?????n StaffActivity n???u ????ng v??n tay
                        dialog.dismiss();
                        Intent intent = new Intent(getActivity(), StaffActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                    }
                });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("X??c th???c ng?????i d??ng")
                .setDescription("Qu??t v??n tay ????? x??c th???c danh t??nh c???a b???n")
                .setNegativeButtonText("Tho??t") .build();
        biometricPrompt.authenticate(promptInfo);

    }

    public static FragmentOption newInstance(String param1, String param2) {
        FragmentOption fragment = new FragmentOption();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option, container, false);
    }
}