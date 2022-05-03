package jhb.dvt.co.za.stackapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import jhb.dvt.co.za.stackapp.R;
import jhb.dvt.co.za.stackapp.adapters.QuestionsAdapter;
import jhb.dvt.co.za.stackapp.asynctasks.QuestionsAsyncTask;
import jhb.dvt.co.za.stackapp.asynctasks.QuestionsAsyncTask.QuestionsResultListener;
import jhb.dvt.co.za.stackapp.utils.HttpUtils;

public class MainActivity extends Activity implements QuestionsResultListener {

    private RecyclerView recyclerView;
    private ImageView noInternetImage;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.stackRecyclerView);

        noInternetImage = findViewById(R.id.noInternetPlaceImage);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkInternetConnection();
            }
        });

        checkInternetConnection();
    }

    private void checkInternetConnection() {
        if (HttpUtils.isHTTPConnectionPossible(this)) {
            getDataAndPopulateRecycerView();
            if (noInternetImage.getVisibility() == View.VISIBLE) {
                noInternetImage.setVisibility(View.GONE);
            }
        }
        else {
            recyclerView.setVisibility(View.GONE);
            noInternetImage.setVisibility(View.VISIBLE);
            if (swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    private void getDataAndPopulateRecycerView() {
        String url = "https://api.stackexchange.com/2.2/questions?pagesize=50&todate=" +
                (System.currentTimeMillis() / 1000L) +
                "&order=desc&sort=creation&tagged=android&site=stackoverflow";

        (new QuestionsAsyncTask(this, url, this)).execute(this);
    }

    @Override
    public void setQuestionsResult(String result) {

        QuestionsAdapter adapter = new QuestionsAdapter(this, result);

        recyclerView.setAdapter(adapter);

        if (recyclerView.getVisibility() == View.GONE) {
            recyclerView.setVisibility(View.VISIBLE);
        }

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}