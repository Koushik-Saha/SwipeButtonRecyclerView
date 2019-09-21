package koushikdesign.example.androidswipetoshowbutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import koushikdesign.example.androidswipetoshowbutton.Adapter.MyAdapter;
import koushikdesign.example.androidswipetoshowbutton.Helper.MyButtonClickListener;
import koushikdesign.example.androidswipetoshowbutton.Helper.MySwipeHelper;
import koushikdesign.example.androidswipetoshowbutton.Model.Item;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Init
        recyclerView = (RecyclerView) findViewById(R.id.recycler_test);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        MySwipeHelper swipeHelper = new MySwipeHelper(this, recyclerView,200) {
            @Override
            public void instantiateMyButton(RecyclerView.ViewHolder viewHolder, List<MySwipeHelper.MyButton> buffer) {
                buffer.add(new MyButton(MainActivity.this,
                        "Delete",
                        30,
                        0,
                        Color.parseColor("#FF3C30"),
                        new MyButtonClickListener(){
                            @Override
                            public void onClick(int pos) {
                                Toast.makeText(MainActivity.this, "Delete click", Toast.LENGTH_SHORT).show();
                            }
                        }));

                buffer.add(new MyButton(MainActivity.this,
                        "Update",
                        30,
                        R.drawable.ic_edit_white_24dp,
                        Color.parseColor("#FF9502"),
                        new MyButtonClickListener(){
                            @Override
                            public void onClick(int pos) {
                                Toast.makeText(MainActivity.this, "Update click", Toast.LENGTH_SHORT).show();
                            }
                        }));
            }
        };

        generateItem();
    }

    private void generateItem()
    {
        List<Item> itemList = new ArrayList<>();
        for (int i=0;i<50;i++)
        {
            itemList.add(new Item("Pie 0"+(++i),
                    "100,000$",
                    "https://i.pinimg.com/736x/57/d4/5c/57d45c7b694df21dd43568a70a4308dc.jpg"));
        }

        adapter = new MyAdapter(this,itemList);
        recyclerView.setAdapter(adapter);
    }
}
