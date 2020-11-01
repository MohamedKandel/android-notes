package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import static android.app.DownloadManager.*;

public class MainActivity<REQUEST_CODE> extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    EditText main_txt;
    Spinner font_size;
    CheckBox cbx_bold;
    CheckBox cbx_Italic;
    CheckBox cbx_underline;
    Spinner font_color;
    Spinner font_family;
    RadioButton rad_left;
    RadioButton rad_center;
    RadioButton rad_right;
    EditText txt_name;
    Button btn_new;
    Button btn_save;
    Button btn_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }

        main_txt = findViewById(R.id.txt_main);
        font_size = findViewById(R.id.spn_size);
        font_color = findViewById(R.id.spn_color);
        font_family = findViewById(R.id.spn_font);
        cbx_bold = findViewById(R.id.cbx_bold);
        cbx_Italic = findViewById(R.id.cbx_italic);
        cbx_underline = findViewById(R.id.cbx_line);
        rad_left = findViewById(R.id.rad_left);
        rad_center = findViewById(R.id.rad_center);
        rad_right = findViewById(R.id.rad_right);
        txt_name = findViewById(R.id.txt_name);
        btn_new = findViewById(R.id.btn_1);
        btn_save = findViewById(R.id.btn_2);
        btn_load = findViewById(R.id.btn_3);

        font_size.setSelection(3);

        font_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                change_size();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cbx_bold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Bold_Changes();
            }
        });

        cbx_Italic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                italic();
            }
        });

        cbx_underline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Under_Changes();
            }
        });

        font_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                change_color();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        font_family.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                change_font();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rad_left.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                alignment();
            }
        });

        rad_center.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                alignment();
            }
        });

        rad_right.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                alignment();
            }
        });

        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newfile();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();


            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load();
            }
        });
    }

    Toast ts;

    protected void change_size() {
        int size = Integer.parseInt(font_size.getSelectedItem().toString());
        main_txt.setTextSize(size);
    }

    protected void Bold_Changes() {

        change_font();
    }

    protected void italic() {

        change_font();
    }

    protected void Under_Changes() {
        if (cbx_underline.isChecked()) {
            main_txt.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        } else {
            main_txt.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
        }
    }

    protected void change_color() {
        //String color = font_color.getSelectedItem().toString();
        int index = font_color.getSelectedItemPosition();
        switch (index) {
            case 0:
                main_txt.setTextColor(getResources().getColor(R.color.black));
                break;
            case 1:
                main_txt.setTextColor(getResources().getColor(R.color.red));
                break;
            case 2:
                main_txt.setTextColor(getResources().getColor(R.color.green));
                break;
            case 3:
                main_txt.setTextColor(getResources().getColor(R.color.blue));
                break;
            case 4:
                main_txt.setTextColor(getResources().getColor(R.color.yellow));
                break;
            case 5:
                main_txt.setTextColor(getResources().getColor(R.color.purple));
                break;
            case 6:
                main_txt.setTextColor(getResources().getColor(R.color.pink));
                break;
            case 7:
                main_txt.setTextColor(getResources().getColor(R.color.gray));
                break;
            default:
                break;
        }
    }

    protected void change_font() {
        String font = font_family.getSelectedItem().toString();

        switch (font) {
            case "Arial":
                font = "Arial.ttf";
                break;
            case "Aldhabi":
                font = "Aldhabi.ttf";
                break;
            case "Andalus":
                font = "Andlso.ttf";
                break;
            case "Agency FB":
                font = "AGENCYB.TTF";
                break;
            case "Bauhaus 93":
                font = "BAUHS93.TTF";
                break;
            case "Bernard MT":
                font = "BERNHC.TTF";
                break;
            case "Brush Script Std":
                font = "BrushScriptStd.otf";
                break;
            case "Forte":
                font = "FORTE.TTF";
                break;
            case "Urdu Typesetting":
                font = "UrdType.ttf";
                break;
            case "Tahoma":
                font = "tahoma.ttf";
                break;
            case "MonoType Corsiva":
                font = "MTCORSVA.TTF";
                break;
            default:
                break;
        }
        Typeface tf = Typeface.createFromAsset(getAssets(), font);
        if (cbx_bold.isChecked()) {
            if (cbx_Italic.isChecked()) {
                main_txt.setTypeface(tf, Typeface.BOLD_ITALIC);
            } else {
                main_txt.setTypeface(tf, Typeface.BOLD);
            }
        } else {
            if (cbx_Italic.isChecked()) {
                main_txt.setTypeface(tf, Typeface.ITALIC);
            } else {
                main_txt.setTypeface(tf, Typeface.NORMAL);
            }
        }

    }

    protected void alignment() {
        if (rad_left.isChecked()) {
            main_txt.setGravity(Gravity.LEFT);
        } else if (rad_center.isChecked()) {
            main_txt.setGravity(Gravity.CENTER);
        } else {
            main_txt.setGravity(Gravity.RIGHT);
        }
    }

    protected void newfile() {
        main_txt.setText("");
        font_size.setSelection(3);
        main_txt.setTextSize(11);
        cbx_bold.setChecked(false);
        cbx_Italic.setChecked(false);
        cbx_underline.setChecked(false);
        font_color.setSelection(0);
        main_txt.setTextColor(Color.BLACK);
        font_family.setSelection(0);
        Typeface tf = Typeface.createFromAsset(getAssets(), "Arial.ttf");
        main_txt.setTypeface(tf);
        rad_left.setChecked(false);
        rad_right.setChecked(false);
        rad_center.setChecked(false);
        main_txt.setGravity(Gravity.NO_GRAVITY);
        txt_name.setText("");
        txt_name.setHint("File Name Here");
        main_txt.setHint("Write your text here");
        main_txt.requestFocus();
    }

    protected void save() {
            //========================================================================================
            if ("".equals(txt_name.getText().toString().trim())) {
                ts = Toast.makeText(this, "Can't save file with empty name", Toast.LENGTH_LONG);
                ts.show();
                txt_name.requestFocus();
            } else {
                try {
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Word";
                    File f = new File(path);
                    f.mkdir();
                    PrintWriter pw = new PrintWriter(path + "/" + txt_name.getText() + ".txt");
                    pw.write(main_txt.getText().toString());
                    pw.close();

                    PrintWriter pwset = new PrintWriter(path + "/" + txt_name.getText() + "Set.txt");
                    String setting = font_size.getSelectedItem() + "\n" +
                            cbx_bold.isChecked() + "\n" +
                            cbx_Italic.isChecked() + "\n" +
                            cbx_underline.isChecked() + "\n" +
                            font_color.getSelectedItem() + "\n" +
                            font_family.getSelectedItem() + "\n" +
                            rad_left.isChecked() + "\n" +
                            rad_center.isChecked() + "\n" +
                            rad_right.isChecked() + "\n";
                    pwset.write(setting);
                    pwset.close();
                    ts = Toast.makeText(this, "File is saved", Toast.LENGTH_SHORT);
                    ts.show();
                } catch (Exception ex) {
                    ts = Toast.makeText(this, "Can't Save this file with this name", Toast.LENGTH_LONG);
                    ts.show();
                }
            }
    }

    protected void load() {
        try {
            //======================================================================================
            if ("".equals(txt_name.getText().toString().trim())) {
                ts = Toast.makeText(this, "No Such file with empty name in directory", Toast.LENGTH_LONG);
                ts.show();
                txt_name.requestFocus();
            } else {
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Word";
                FileReader fr = new FileReader(path + "/" + txt_name.getText() + ".txt");
                BufferedReader br = new BufferedReader(fr);
                String content = "";
                String line = "";
                while ((line = br.readLine()) != null) {
                    content += line + "\n";
                }
                fr = new FileReader(path + "/" + txt_name.getText() + "Set.txt");
                br = new BufferedReader(fr);
                String[] setting = new String[9];
                int i = 0;
                while ((line = br.readLine()) != null) {
                    setting[i] = line;
                    i++;
                }
                fr.close();
                br.close();
                font_size.setSelection(((ArrayAdapter<String>) font_size.getAdapter()).getPosition(setting[0]));
                main_txt.setTextSize(Float.parseFloat(setting[0]));
                cbx_bold.setChecked(Boolean.parseBoolean(setting[1]));
                cbx_Italic.setChecked(Boolean.parseBoolean(setting[2]));
                cbx_underline.setChecked(Boolean.parseBoolean(setting[3]));
                font_color.setSelection(((ArrayAdapter<String>) font_color.getAdapter()).getPosition(setting[4]));
                font_family.setSelection(((ArrayAdapter<String>) font_family.getAdapter()).getPosition(setting[5]));
                if ("true".equals(setting[6])) {
                    rad_left.setChecked(true);
                } else {
                    if ("true".equals(setting[7])) {
                        rad_center.setChecked(true);
                    } else {
                        if ("true".equals(setting[8])) {
                            rad_right.setChecked(true);
                        }
                    }
                }

                    main_txt.setText(content);
            }
            } catch (Exception ex) {
            ts = Toast.makeText(this, "Can't load this File with this name", Toast.LENGTH_LONG);
            ts.show();
        }

    }
}