package id.co.indomobil.test.ui.tools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import id.co.indomobil.test.R;

import static id.co.indomobil.test.DBHelper.DatabaseHelper.getDBName;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    Button btnBackupDB;
    private SQLiteDatabase sqLiteDatabase;
    private Context mContext = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        btnBackupDB = (Button) root.findViewById(R.id.btnBackup);
        mContext = getActivity();

        btnBackupDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String outFileName = Environment.getExternalStorageDirectory() + "/" + getDBName().replace(".db", "") + ".db";
                BackupDB(outFileName);
                return;
            }
        });
        return root;
    }



    public void BackupDB(String outFileName) {
        final String inFileName = mContext.getDatabasePath(getDBName()).getPath();
        //final String inFileName = "/data/data/<your.app.package>/databases/foo.db";
        File dbFile = new File(inFileName);
        FileInputStream fis = null;
        OutputStream output = null;

        try {
            fis = new FileInputStream(dbFile);
            // Open the empty db as the output stream
            output = new FileOutputStream(outFileName);
            // Transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            try {
                while ((length = fis.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }

                // Close the streams
                output.flush();
                output.close();
                fis.close();
                Toast.makeText(mContext, "Backup database success", Toast.LENGTH_LONG).show();
                ShowMessage("DB has backed up successfully at",outFileName);
            } catch (IOException e) {
                //e.printStackTrace();
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
                //getFragmentManager().popBackStackImmediate(null, 0);
                return;
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
            //getFragmentManager().popBackStackImmediate(null, 0);
            return;
        }
    }

    public void ShowMessage(String Title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.setCancelable(false);
        //builder.setNegativeButton(android.R.string.no, null);
        builder.setIcon(android.R.drawable.ic_dialog_info);

        builder.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.show();
    }

}