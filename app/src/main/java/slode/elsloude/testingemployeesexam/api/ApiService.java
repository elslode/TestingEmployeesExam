package slode.elsloude.testingemployeesexam.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import slode.elsloude.testingemployeesexam.pojo.EmployeeResponse;

public interface ApiService {
    @GET("testTask.json")
    Observable <EmployeeResponse> getEmloyees();
}
