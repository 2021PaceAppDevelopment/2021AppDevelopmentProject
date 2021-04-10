package com.example.nycftaetix;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class TicketPurchase extends AppCompatActivity {
    //EditText qrValue; might need later
    ImageView qrCode;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_purchase);
        qrCode = findViewById(R.id.QrCode);
        String data = "Activated";
        QRGEncoder qrgEncoder = new QRGEncoder(data,null, QRGContents.Type.TEXT,1000);
        try {
            // Getting QR-Code as Bitmap
            bitmap = qrgEncoder.getBitmap();
            // Setting Bitmap to ImageView
            qrCode.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.wtf("MAIN_ACTIVITY", e.toString());
        }
    }
}