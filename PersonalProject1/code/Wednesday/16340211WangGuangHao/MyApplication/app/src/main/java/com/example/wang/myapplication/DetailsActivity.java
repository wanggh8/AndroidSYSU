package com.example.wang.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {
    private boolean tag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        Collection c = (Collection) getIntent().getSerializableExtra("Collection");
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.Top);
        relativeLayout.setBackgroundColor(Color.parseColor(c.getBackground()));

        /* 商品参数获取 */
        TextView name = (TextView) findViewById(R.id.Name);
        TextView Category = (TextView) findViewById(R.id.Category);
        TextView contain = (TextView) findViewById(R.id.contain);
        TextView Nutrition = (TextView) findViewById(R.id.Nutrition);
        name.setText(c.getName());
        Category.setText(c.getCategory());
        contain.setText("富含"); 
        Nutrition.setText(c.getNutrition());
        /* ListView其他选项 */
        String[] operations1 = new String[] { "更多资料" };
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<>(this, R.layout.underlist, operations1);
        ListView listView1 = (ListView) findViewById(R.id.moreinfor);
        listView1.setAdapter(arrayAdapter1);
        String[] options = new String[] { "分享信息", "不感兴趣", "查看更多信息", "出错反馈" };
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.underlist, options);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);

        /* 返回按钮侦听 */
        Button back = (Button) findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /* 星星切换 */
        final Button star = (Button) findViewById(R.id.Star);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tag) {
                    star.setBackground(getResources().getDrawable(R.mipmap.full_star));
                    tag = true;
                } else {
                    star.setBackground(getResources().getDrawable(R.mipmap.empty_star));
                    tag = false;
                }
            }
        });

        /* 收藏按钮侦听 */
        Button collect = (Button) findViewById(R.id.Collect);
        ;
        final TextView Name = findViewById(R.id.Name);
        final TextView Catogary = findViewById(R.id.Category);
        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> temp = new LinkedHashMap<>();
                temp.put("cycle", Catogary.getText().toString().substring(0, 1));
                temp.put("name", Name.getText().toString());
                Toast.makeText(DetailsActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("favoratethings", (Serializable) temp);
                intent.putExtras(bundle);
                setResult(1, intent);
            }
        });

    }
}