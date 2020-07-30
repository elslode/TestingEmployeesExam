package slode.elsloude.testingemployeesexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import slode.elsloude.testingemployeesexam.adapters.EmployeeAdapter;
import slode.elsloude.testingemployeesexam.api.ApiFactory;
import slode.elsloude.testingemployeesexam.api.ApiService;
import slode.elsloude.testingemployeesexam.pojo.Employee;
import slode.elsloude.testingemployeesexam.pojo.EmployeeResponse;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewItem;
    private EmployeeAdapter employeeAdapter;
    private Disposable disposable;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewItem = findViewById(R.id.recyclerView_item);
        employeeAdapter = new EmployeeAdapter();
        employeeAdapter.setEmployees(new ArrayList<Employee>());
        recyclerViewItem.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewItem.setAdapter(employeeAdapter);
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.apiService();
        compositeDisposable = new CompositeDisposable();
        disposable = apiService.getEmloyees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmployeeResponse>() {
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                        employeeAdapter.setEmployees(employeeResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        if(compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        super.onDestroy();
    }
}