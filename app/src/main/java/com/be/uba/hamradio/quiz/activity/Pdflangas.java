package com.be.uba.hamradio.quiz.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import roboguice.activity.RoboActivity;
import com.be.uba.hamradio.quiz.R;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Pdflangas extends RoboActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdflangas);
        
	     CopyAssets();
	     
    }
    
    
    private void CopyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("Files");
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        for(String filename : files) {
            System.out.println("File name => "+filename);
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open("Files/"+filename);
                out = new FileOutputStream(Environment.getExternalStorageDirectory().toString() +"/" + filename);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;
            } catch(Exception e) {
                Log.e("tag", e.getMessage());
            }
        }
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

    //pirmas mygtukas
    public void buttonOnClick1 (View v) {

        Button button = (Button) v;

        openPdf();

    }

    void openPdf() {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        File file = new File(path, "18-Eth-NL-SITE-ON-UBA-jun2010-ed3.pdf");

        intent.setDataAndType(Uri.fromFile(file), "application/pdf");

        startActivity(intent);
    }
    
    //antras mygtukas
    public void buttonOnClick2 (View v2) {

        Button button = (Button) v2;

        openPdf2();

    }

    void openPdf2() {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        File file = new File(path, "santrupos.pdf");

        intent.setDataAndType(Uri.fromFile(file), "application/pdf");

        startActivity(intent);
    }
    
    //trecias mygtukas
    public void buttonOnClick3 (View v3) {

        Button button = (Button) v3;

        openPdf3();

    }

    void openPdf3() {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        File file = new File(path, "atstojamosios.pdf");

        intent.setDataAndType(Uri.fromFile(file), "application/pdf");

        startActivity(intent);
    }
    
    
    

}
