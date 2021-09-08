package com.example.sorting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class ListMaking extends AppCompatActivity {
    private DBHelper mDBHelper = new DBHelper(this);

    public ListMaking() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog.Builder list = new AlertDialog.Builder(this);



        list.setIcon(R.drawable.list);
        list.setTitle("목록 생성하기");

        list.setNegativeButton("기존 목록에 추가", new DialogInterface.OnClickListener() { // 기존 목록에 추가 버튼을 누를 시 Reading 클래스로 이동
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ListMaking.this, Reading.class);
                startActivity(intent);
            }
        }).setPositiveButton("새 목록 만들기", new DialogInterface.OnClickListener() { // 새 목록 만들기 버튼을 누를 시 DB를 초기화하고 Reading 클래스로 이동
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.setMessage("새 목록이 생성되면 기존 목록이 삭제됩니다.\n계속하시겠습니까?");

                    builder.setNegativeButton("네", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface di, int which) {
                            mDBHelper.dbInitialize(); // DB 초기화
                            Intent intent = new Intent(ListMaking.this,Reading.class);
                            startActivity(intent);
                        }
                    }).setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface di, int which) {
                            finish();
                        }
                    });

                    AlertDialog di = builder.create();
                    di.show();
                }


        });
        AlertDialog dialog = list.create();
        dialog.show();
    }
}