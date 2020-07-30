package slode.elsloude.testingemployeesexam.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Placeholder;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import javax.xml.namespace.QName;

import slode.elsloude.testingemployeesexam.R;
import slode.elsloude.testingemployeesexam.pojo.Employee;
import slode.elsloude.testingemployeesexam.pojo.Specialty;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employees, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.textViewName.setText(employee.getName());
        holder.textViewLastName.setText(employee.getLName());
        holder.textViewBirthday.setText(employee.getBirthday());
        try {
            if (employee.getAvatrUrl().isEmpty()) {
//                holder.imageViewAvatar.setImageResource(R.drawable.placeholder);
                Picasso
                        .get()
                        .load(R.drawable.placeholder)
                        .noFade()
                        .into(holder.imageViewAvatar);
            } else {
                Picasso.get().load(employee.getAvatrUrl()).into(holder.imageViewAvatar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.textViewSpecialty.setText(employee.getSpecialty().get(0).getName());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewLastName;
        private ImageView imageViewAvatar;
        private TextView textViewBirthday;
        private TextView textViewSpecialty;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLastName = itemView.findViewById(R.id.textViewLastName);
            textViewBirthday = itemView.findViewById(R.id.textViewBirthday);
            imageViewAvatar = itemView.findViewById(R.id.imageViewAvatars);
            textViewSpecialty = itemView.findViewById(R.id.textViewSpecialty);
        }
    }
}
